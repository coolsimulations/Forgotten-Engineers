package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;

import java.util.*;

public class CompressorItem extends FilterDeviceItem {

    public static Map<ItemStack, ItemStack> COMPRESSOR_RECIPES = new LinkedHashMap<>();

    public CompressorItem(Properties properties) {
        super(properties);
    }

    public static void loadRecipes(RecipeManager recipeManager) {
        COMPRESSOR_RECIPES.clear();
        for (RecipeHolder<?> entry : recipeManager.getRecipes()) {
            if (!(entry.value() instanceof CraftingRecipe recipe)) continue;
            ItemStack result;
            List<Optional<Ingredient>> ingredients;

            if (recipe instanceof ShapelessRecipe shapeless) {
                ingredients = shapeless.ingredients.stream().map(Optional::of).toList();
                result = shapeless.result.create();
            } else if (recipe instanceof ShapedRecipe shaped) {
                ingredients = shaped.getIngredients();
                result = shaped.result.create();
            } else continue;

            List<Ingredient> nonEmptyIngredients = ingredients.stream().filter(Optional::isPresent).map(Optional::get).toList();

            if (nonEmptyIngredients.size() < 4) continue;

            for (Item item : BuiltInRegistries.ITEM.stream().toList()) {
                if (nonEmptyIngredients.stream().allMatch(ingredient -> ingredient.test(new ItemStack(item)))) {
                    if (result.is(FETags.COMPRESSOR_IGNORE_ITEMS) || Block.byItem(result.getItem()).defaultBlockState().is(FETags.COMPRESSOR_IGNORE_BLOCKS)) continue;

                    ItemStack input = new ItemStack(item);
                    int count = (int) nonEmptyIngredients.stream().filter(ingredient -> ingredient.test(input)).count();
                    input.setCount(count);
                    if (!BuiltInRegistries.ITEM.getKey(item).getNamespace().equals("minecraft") && CompressorItem.hasDuplicateKey(input) /*TODO: Check duplicate not valid */)
                        ForgottenEngineersCommon.LOG.warn("Duplicate Compressor entry for input: {} which makes {} {}. If not intentional, add it to the #forgottenengineers:compressor_ignore_items or #forgottenengineers:compressor_ignore_blocks tag", input.getDisplayName().getString(), count, result.getDisplayName().getString());

                    SelectableRecipe.SingleInputSet<StonecutterRecipe> stonecutter = recipeManager.stonecutterRecipes().selectByInput(input);
                    boolean addedStonecutterVariant = false;
                    if (!stonecutter.isEmpty()) {
                        for (SelectableRecipe.SingleInputEntry<StonecutterRecipe> stonecutterEntry : stonecutter.entries()) {
                            if(stonecutterEntry.recipe().recipe().isPresent()) {
                                ItemStack stonecutterResult = stonecutterEntry.recipe().recipe().get().value().result().create();
                                if (stonecutterResult.is(result.getItem())) {
                                    input.setCount(1);
                                    CompressorItem.COMPRESSOR_RECIPES.put(input, stonecutterResult);
                                    addedStonecutterVariant = true;
                                }
                            }
                        }
                    }

                    if(!addedStonecutterVariant)
                        CompressorItem.COMPRESSOR_RECIPES.put(input, result);
                }
            }
        }
    }

    public static Optional<CompressorRecipe> getRecipeFromInput(BundleContents.Mutable contents, ItemStack input) {
        List<Map.Entry<ItemStack, ItemStack>> recipes = COMPRESSOR_RECIPES.entrySet().stream().filter(entry -> entry.getKey().is(input.getItem())).toList();
        if (recipes.isEmpty()) return Optional.empty();

        if (recipes.size() == 1) {
            Map.Entry<ItemStack, ItemStack> recipe = recipes.getFirst();
            return Optional.of(new CompressorRecipe(recipe.getKey(), recipe.getValue()));
        }

        for (Map.Entry<ItemStack, ItemStack> recipe : recipes) {
            ItemStack result = recipe.getValue();
            boolean resultInFilter = contents.items.stream().map(ItemStack::getItem).anyMatch(item -> item == result.getItem());

            if (resultInFilter)
                return Optional.of(new CompressorRecipe(recipe.getKey(), recipe.getValue()));
        }

        Map.Entry<ItemStack, ItemStack> firstRecipe = recipes.getFirst();

        return Optional.of(new CompressorRecipe(firstRecipe.getKey(), firstRecipe.getValue()));
    }

    public static Optional<ForgottenEngineersCommon.DeviceResult> tryCompress(CompressorRecipe recipe, ItemStack stack) {
        if (recipe.input().is(stack.getItem())) {
            int resultAmount = stack.getCount() / recipe.input().getCount();
            int leftOverAmount = stack.getCount() % recipe.input().getCount();
            if (resultAmount > 0) {
                ItemStack result = recipe.output();
                result.setCount(result.getCount() * resultAmount);
                stack.setCount(leftOverAmount);
                return Optional.of(new ForgottenEngineersCommon.DeviceResult(result, stack));
            }
        }

        return Optional.empty();
    }

    public static ForgottenEngineersCommon.RecipeResult addItemToCompressor(Player player, CompressorRecipe recipe, ItemStack compressor, ItemStack stack) {
        ItemStack stackCopy = stack.copy();
        Item filter = stackCopy.getItem();
        int filterCount = stackCopy.getCount();
        Optional<ForgottenEngineersCommon.RecipeResult> result = processStack(recipe, compressor, stack);
        if (result.isPresent()) {
            compressor = result.get().device();
            int amountLeft = filterCount % recipe.input().getCount();
            if (amountLeft > 0)
                player.awardStat(Stats.ITEM_PICKED_UP.get(filter), amountLeft);

            BundleContents contents = compressor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

            Optional<ItemStack> compressorFilter = mutable.items.stream().filter(item -> item.is(filter)).findFirst();
            if (compressorFilter.isPresent()) {
                ItemStack filterStack = compressorFilter.get().copy();
                compressorFilter.get().setCount(1);
                filterStack.shrink(1);
                compressor.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());

                Optional<ForgottenEngineersCommon.RecipeResult> filterResult = processStack(recipe, compressor, filterStack);

                if (filterResult.isPresent()) {
                    if (!result.get().result().isEmpty()) {
                        ItemStack mergedResult = ForgottenEngineersCommon.mergeItemStacks(filterResult.get().result(), result.get().result());
                        ItemStack mergedLeftover = ForgottenEngineersCommon.mergeItemStacks(filterResult.get().leftover(), result.get().leftover());

                        return new ForgottenEngineersCommon.RecipeResult(filterResult.get().device(), mergedResult, mergedLeftover);
                    }

                    return filterResult.get();
                }
            }
        }

        return new ForgottenEngineersCommon.RecipeResult(compressor, ItemStack.EMPTY, stack);
    }

    private static Optional<ForgottenEngineersCommon.RecipeResult> processStack(CompressorRecipe recipe, ItemStack compressor, ItemStack stack) {
        BundleContents contents = compressor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

        Optional<ForgottenEngineersCommon.DeviceResult> result = tryCompress(recipe, stack);

        if (result.isPresent()) {
            ItemStack leftover = result.get().leftover();
            int leftOverAmount = 0;
            if (!leftover.isEmpty())
                leftOverAmount = mutable.tryInsert(leftover);
            leftover.shrink(leftOverAmount);
            compressor.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());

            return Optional.of(new ForgottenEngineersCommon.RecipeResult(compressor, result.get().result(), leftover));
        } else {
            int leftOverAmount = 0;
            if (!stack.isEmpty())
                leftOverAmount = mutable.tryInsert(stack);
            stack.shrink(leftOverAmount);
            compressor.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
            return Optional.of(new ForgottenEngineersCommon.RecipeResult(compressor, ItemStack.EMPTY, stack));
        }
    }

    public static boolean isValidInput(ItemStack stack) {
        return hasDuplicateKey(stack) || COMPRESSOR_RECIPES.keySet().stream().anyMatch(input -> input.getItem() == stack.getItem());
    }

    public static boolean hasDuplicateKey(ItemStack stack) {
        List<ItemStack> duplicates = COMPRESSOR_RECIPES.entrySet().stream().filter(entry -> entry.getValue().is(stack.getItem())).map(Map.Entry::getKey).toList();
        return !duplicates.isEmpty() && COMPRESSOR_RECIPES.keySet().stream().filter(input -> input.is(duplicates.getFirst().getItem())).toList().size() > 1;
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;

        return !stack.is(FETags.STRIPPER_IGNORE_ITEMS) && isValidInput(stack);
    }

    public record CompressorRecipe(ItemStack input, ItemStack output, boolean duplicate) {
        public CompressorRecipe {
            Objects.requireNonNull(input, "Input cannot be null");
            Objects.requireNonNull(output, "Output cannot be null");

            input = input.copy();
            output = output.copy();
        }

        public CompressorRecipe(ItemStack input, ItemStack output) {
            this(input, output, false);
        }

        @Override
        public ItemStack input() {
            return input.copy();
        }

        @Override
        public ItemStack output() {
            return output.copy();
        }
    }
}
