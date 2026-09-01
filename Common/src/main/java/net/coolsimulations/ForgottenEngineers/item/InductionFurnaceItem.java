package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

import java.util.*;

public class InductionFurnaceItem extends FilterDeviceItem {

    public static Map<Item, InductionRecipe> INDUCTION_RECIPES = new LinkedHashMap<>();

    public InductionFurnaceItem(Properties properties) {
        super(properties);
    }

    public static void loadRecipes(RecipeManager recipeManager) {
        INDUCTION_RECIPES.clear();
        for (RecipeHolder<?> entry : recipeManager.getRecipes()) {
            if (!(entry.value() instanceof AbstractCookingRecipe recipe)) continue;
            ItemStack result = recipe.result().create();
            Ingredient ingredient = recipe.input();

            for (Item item : BuiltInRegistries.ITEM) {
                ItemStack input = new ItemStack(item);

                if (ingredient.test(input)) {

                    InductionRecipe newRecipe = new InductionRecipe(result.copy(), recipe.cookingTime(), recipe.experience());
                    InductionRecipe existingRecipe = INDUCTION_RECIPES.get(item);
                    if (existingRecipe == null || newRecipe.cookingTime() < existingRecipe.cookingTime() || (newRecipe.cookingTime() == existingRecipe.cookingTime() && newRecipe.experience() > existingRecipe.experience()))
                        INDUCTION_RECIPES.put(item, newRecipe);
                }
            }
        }
    }

    public record SmeltResult(ItemStack result, ItemStack leftover, Item fuel, int fuelUsed) {}

    public static Optional<SmeltResult> trySmelt(Player player, InductionRecipe recipe, ItemStack stack, Level level, Map<Integer, ItemStack> fuelCarriers) {
        List<FuelCarrierItem.FuelOption> options = FuelCarrierItem.getAllFuels(level, fuelCarriers);
        if (!options.isEmpty() && !stack.isEmpty()) {
            FuelCarrierItem.FuelOption option = options.getFirst();
            BundleContents contents = fuelCarriers.get(option.fuelCarrierIndex()).getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            Item lowestFuel = contents.items().get(option.fuelIndex()).item().value();
            List<FuelCarrierItem.FuelOption> lowestOptions = FuelCarrierItem.getOptionsForType(lowestFuel, level, fuelCarriers);
            int fuelCount = FuelCarrierItem.getCountAcrossOptions(lowestOptions, fuelCarriers);
            int amountCanSmelt = FuelCarrierItem.getFuelDuration(lowestFuel, level, fuelCount) / recipe.cookingTime();

            int resultAmount = Math.min(stack.getCount() * recipe.output().getCount(), amountCanSmelt);
            int leftOverAmount = stack.getCount() - resultAmount / recipe.output().getCount();
            float fuelUsed = (float) (stack.getCount() * recipe.cookingTime()) / FuelCarrierItem.getFuelDuration(lowestFuel, level, 1);
            if (resultAmount > 0 && fuelUsed % 1 == 0) {
                ItemStack result = recipe.output();
                result.setCount(resultAmount);
                if (level instanceof ServerLevel serverLevel)
                    AbstractFurnaceBlockEntity.createExperience(serverLevel, player.position(), resultAmount, recipe.experience());
                stack.setCount(leftOverAmount);
                return Optional.of(new SmeltResult(result, stack, lowestFuel, (int) fuelUsed));
            }
        }

        return Optional.empty();
    }

    public static ForgottenEngineersCommon.RecipeResult addItemToInductionFurnace(Player player, Level level, Map<Integer, ItemStack> fuelCarriers, InductionRecipe recipe, ItemStack inductionFurnace, ItemStack stack) {
        ItemStack stackCopy = stack.copy();
        Item filter = stackCopy.getItem();
        Optional<ForgottenEngineersCommon.RecipeResult> result = processStack(player, recipe, inductionFurnace, stack, level, fuelCarriers);
        if (result.isPresent()) {
            inductionFurnace = result.get().device();

            BundleContents contents = inductionFurnace.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

            Optional<ItemStack> inductionFurnaceFilter = mutable.items.stream().filter(item -> item.is(filter)).findFirst();
            if (inductionFurnaceFilter.isPresent()) {
                ItemStack filterStack = inductionFurnaceFilter.get().copy();
                inductionFurnaceFilter.get().setCount(1);
                filterStack.shrink(1);
                inductionFurnace.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
                int remaining = filterStack.getCount();
                Optional<ForgottenEngineersCommon.RecipeResult> iterativeResult = Optional.empty();

                while (remaining > 1 && iterativeResult.isEmpty()) {
                    remaining--;
                    ItemStack currentFilter = filterStack.copy();
                    currentFilter.setCount(remaining);
                    ItemStack simulatedFurnace = inductionFurnace.copy();
                    Optional<ForgottenEngineersCommon.RecipeResult> filterResult = processStack(player, recipe, simulatedFurnace, currentFilter, level, fuelCarriers);
                    if (filterResult.isPresent()) {
                        if (!filterResult.get().result().isEmpty()) {
                            filterStack.shrink(filterResult.get().result().getCount());
                            iterativeResult = Optional.of(new ForgottenEngineersCommon.RecipeResult(filterResult.get().device(), filterResult.get().result(), filterStack));
                        }
                    }
                }

                Optional<ForgottenEngineersCommon.RecipeResult> filterResult;
                if (iterativeResult.isPresent())
                    filterResult = iterativeResult;
                else
                    filterResult = processStack(player, recipe, inductionFurnace, filterStack, level, fuelCarriers);

                if (filterResult.isPresent()) {
                    if (!result.get().result().isEmpty()) {
                        ItemStack mergedResult = ForgottenEngineersCommon.mergeItemStacks(filterResult.get().result(), result.get().result());
                        ItemStack mergedLeftover = ForgottenEngineersCommon.mergeItemStacks(filterResult.get().leftover(), result.get().leftover());

                        return new ForgottenEngineersCommon.RecipeResult(filterResult.get().device(), mergedResult, mergedLeftover);
                    }
                    if (!filterResult.get().result().isEmpty())
                        return filterResult.get();
                }
            }
        }

        return new ForgottenEngineersCommon.RecipeResult(inductionFurnace, ItemStack.EMPTY, stack);
    }

    private static Optional<ForgottenEngineersCommon.RecipeResult> processStack(Player player, InductionRecipe recipe, ItemStack inductionFurnace, ItemStack stack, Level level, Map<Integer, ItemStack> fuelCarriers) {
        BundleContents contents = inductionFurnace.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);
        Optional<SmeltResult> result = trySmelt(player, recipe, stack, level, fuelCarriers);

        if (result.isPresent()) {
            FuelCarrierItem.consumeFuel(result.get().fuel(), level, result.get().fuelUsed(), fuelCarriers);
            ItemStack leftover = result.get().leftover();
            processLeftOverStack(inductionFurnace, mutable, leftover);

            return Optional.of(new ForgottenEngineersCommon.RecipeResult(inductionFurnace, result.get().result(), leftover));
        } else {
            processLeftOverStack(inductionFurnace, mutable, stack);
            return Optional.of(new ForgottenEngineersCommon.RecipeResult(inductionFurnace, ItemStack.EMPTY, stack));
        }
    }

    private static void processLeftOverStack(ItemStack inductionFurnace, BundleContents.Mutable mutable, ItemStack stack) {
        int leftOverAmount = 0;
        if (!stack.isEmpty())
            leftOverAmount = mutable.tryInsert(stack);
        stack.shrink(leftOverAmount);
        inductionFurnace.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;

        return !stack.is(FETags.INDUCTION_FURNACE_IGNORE_ITEMS) && INDUCTION_RECIPES.containsKey(stack.getItem());
    }

    public record InductionRecipe(ItemStack output, int cookingTime, float experience) {
        public InductionRecipe {
            Objects.requireNonNull(output, "Output cannot be null");

            output = output.copy();
        }

        @Override
        public ItemStack output() {
            return output.copy();
        }
    }
}
