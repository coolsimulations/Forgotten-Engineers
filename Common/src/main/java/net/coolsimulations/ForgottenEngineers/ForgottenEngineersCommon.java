package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.event.FEEntityEvents;
import net.coolsimulations.ForgottenEngineers.event.FERegistryEvents;
import net.coolsimulations.ForgottenEngineers.item.*;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPayload;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPayload;
import net.coolsimulations.ForgottenEngineers.sounds.ForgottenEngineersSounds;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;

public class ForgottenEngineersCommon {

    public static final String MOD_ID = "forgottenengineers";
    public static final String MOD_NAME = "Forgotten Engineers";
    public static final String MOD_VERSION = "1.2.0";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    private static boolean isRegistered = false;

    public void init() {
        ForgottenEngineersItems.init();
        ForgottenEngineersSounds.init();

        if (!isRegistered) {
            FEEntityEvents.PLAYER_ITEM_ENTITY_PICKUP.register(ForgottenEngineersCommon::onPlayerItemEntityPickup);
            FERegistryEvents.POST_RECIPE_LOAD.register(ForgottenEngineersCommon::onPostRecipeLoad);
            isRegistered = true;
        }
    }

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    public static void onPostRecipeLoad(RecipeManager manager) {
        CompressorItem.loadRecipes(manager);
        InductionFurnaceItem.loadRecipes(manager);
    }

    public static List<InductionRecipeSyncPayload.RecipeData> createInductionRecipeSyncData() {
        return InductionFurnaceItem.INDUCTION_RECIPES.entrySet().stream().map(entry -> {

            Item input = entry.getKey();
            InductionFurnaceItem.InductionRecipe recipe = entry.getValue();

            return new InductionRecipeSyncPayload.RecipeData(
                    BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(input)).get().value(),
                    BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(recipe.output().getItem())).get().value(),
                    recipe.cookingTime(),
                    recipe.experience()
            );
        }).toList();
    }

    public static List<CompressorRecipeSyncPayload.RecipeData> createCompressorRecipeSyncData() {
        return CompressorItem.COMPRESSOR_RECIPES.entrySet().stream().map(entry -> new CompressorRecipeSyncPayload.RecipeData(
                        entry.getKey().copy(),
                        entry.getValue().copy()
                )).toList();
    }

    private static boolean onPlayerItemEntityPickup(Player player, ItemEntity item) {
        if (item.level().isClientSide() || item.hasPickUpDelay() || (item.getOwner() != null && !item.getOwner().getUUID().equals(player.getUUID())))
            return true;

        Map<Integer, ItemStack> inventoryEnderRouters = getInventoryItems(player, stack -> stack.is(FEItems.ENDER_ROUTER));
        Map<Integer, ItemStack> inventoryRouters = getInventoryItems(player, stack -> stack.is(FETags.ROUTERS));
        Map<Integer, ItemStack> inventoryStrippers = getInventoryItems(player, stack -> stack.is(FEItems.STRIPPER));
        Map<Integer, ItemStack> inventoryCompressors = getInventoryItems(player, stack -> stack.is(FEItems.COMPRESSOR));
        Map<Integer, ItemStack> inventoryFuelCarriers = getInventoryItems(player, stack -> stack.is(FEItems.FUEL_CARRIER));
        Map<Integer, ItemStack> inventoryInductionFurnaces = getInventoryItems(player, stack -> stack.is(FEItems.INDUCTION_FURNACE));

        if(!inventoryStrippers.isEmpty())
            if (handleStripperPickup(player, new EnderStack(false, item, ItemStack.EMPTY), inventoryStrippers, inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters).getResult())
                return false;

        if(!inventoryInductionFurnaces.isEmpty())
            if (handleInductionFurnacePickup(player, new EnderStack(false, item, ItemStack.EMPTY), inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, inventoryEnderRouters).getResult())
                return false;

        if(!inventoryCompressors.isEmpty())
            if (handleCompressorPickup(player, new EnderStack(false, item, ItemStack.EMPTY), inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters).getResult())
                return false;

        if(!inventoryEnderRouters.isEmpty())
            if (handleEnderRouterPickup(player, item, inventoryEnderRouters))
                return false;

        if(!inventoryRouters.isEmpty())
            if (handleRouterPickup(player, new EnderStack(false, item, ItemStack.EMPTY), inventoryRouters).getResult())
                return false;

        return true;
    }

    public record DeviceResult(ItemStack result, ItemStack leftover) {}

    public record RecipeResult(ItemStack device, ItemStack result, ItemStack leftover) {}

    public static ItemStack mergeItemStacks(ItemStack from, ItemStack to) {
        ItemStack merged;
        if (!from.isEmpty())
            merged = from.transmuteCopy(to.getItem());
        else
            merged = new ItemStack(to.getItem());
        merged.setCount(from.getCount() + to.getCount());
        return merged;
    }

    public static Map<Integer, ItemStack> getInventoryItems(Player player, Predicate<ItemStack> condition) {
        Map<Integer, ItemStack> matchingItems = new LinkedHashMap<>();
        NonNullList<ItemStack> inventoryItems = player.getInventory().getNonEquipmentItems();

        for (int i = 0; i < inventoryItems.size(); i++) {
            ItemStack stack = inventoryItems.get(i);
            if (condition.test(stack))
                matchingItems.put(i, stack);
        }

        return matchingItems;
    }

    public static boolean matchesDeviceFilter(ItemStack device, ItemStack stack, boolean router) {
        BundleContents.Mutable contents = new BundleContents.Mutable(device.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));

        for (ItemInstance filter : router ? RouterItem.getFilterItems(contents.items) : contents.items)
            if (ItemStack.isSameItem((ItemStack)filter, stack)) return true;

        return false;
    }

    public static boolean hasFilterItemAlready(ItemLike itemToAdd, List<ItemStack> items) {
        if (items.isEmpty()) return false;
        return items.stream().anyMatch(item -> item != null && item.is(itemToAdd.asItem()));
    }

    private static boolean canDeviceAccept(ItemStack compressor, ItemStack stack, int amount) {
        BundleContents contents = compressor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

        int existingCount = mutable.items.stream().filter(existing -> ItemStack.isSameItemSameComponents(existing, stack)).mapToInt(ItemInstance::count).sum();

        if (existingCount + stack.getCount() >= amount) return true;

        ItemStack test = stack.copy();
        test.setCount(1);

        return mutable.tryInsert(test) > 0;
    }

    private static void processRemaining(Player player, ItemStack originalStack, ItemStack remaining, EnderStack enderStack) {
        if (!remaining.isEmpty()) {
            if (enderStack.isEnder()) {
                int addedToEnder = originalStack.getCount() - remaining.getCount();
                if (addedToEnder > 0)
                    player.awardStat(Stats.ITEM_PICKED_UP.get(remaining.getItem()), addedToEnder);
                remaining = player.getEnderChestInventory().addItem(remaining);
                if (!remaining.isEmpty()) {
                    player.awardStat(Stats.ITEM_PICKED_UP.get(remaining.getItem()), remaining.getCount() - addedToEnder);
                    player.getInventory().placeItemBackInInventory(remaining);
                }
            } else {
                player.awardStat(Stats.ITEM_PICKED_UP.get(remaining.getItem()), remaining.getCount());
                player.getInventory().placeItemBackInInventory(remaining);
            }
        }
    }

    public static EnderStack handleDeviceOutput(Player player, EnderStack output, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters) {
        EnderStack remaining = new EnderStack(output.isEnder(), null, output.stack().copy());

        if (!inventoryEnderRouters.isEmpty())
            for (Map.Entry<Integer, ItemStack> entry : inventoryEnderRouters.entrySet()) {
                if (remaining.stack().isEmpty()) break;

                ItemStack enderRouter = entry.getValue();

                if (enderRouter.isEmpty() || remaining.stack().is(FETags.ROUTER_IGNORE_ITEMS)) continue;
                if (!matchesDeviceFilter(enderRouter, remaining.stack(), true)) continue;

                RouterItem.Result result = EnderRouterItem.addItemToEnderChest(player, enderRouter, remaining.stack());
                remaining = new EnderStack(remaining.isEnder(), null, result.stack());
                player.awardStat(Stats.ITEM_PICKED_UP.get(output.stack().getItem()), output.stack().getCount() - remaining.stack().getCount());
                if (remaining.isEnder())
                    player.getEnderChestInventory().getItems().set(entry.getKey(), result.device());
                else
                    player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());
            }

        if (!inventoryRouters.isEmpty())
            for (Map.Entry<Integer, ItemStack> entry : inventoryRouters.entrySet()) {
                if (remaining.stack().isEmpty()) break;

                ItemStack router = entry.getValue();

                if (router.isEmpty() || remaining.stack().is(FETags.ROUTER_IGNORE_ITEMS)) continue;
                if (!matchesDeviceFilter(router, remaining.stack(), true)) continue;

                RouterItem.Result result = RouterItem.addItemToShulker(router, remaining.stack());
                remaining = new EnderStack(remaining.isEnder(), null, result.stack());
                player.awardStat(Stats.ITEM_PICKED_UP.get(output.stack().getItem()), output.stack().getCount() - remaining.stack().getCount());
                if (output.isEnder())
                    player.getEnderChestInventory().getItems().set(entry.getKey(), result.device());
                else
                    player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());
            }

        return remaining;
    }

    public static EnderResult handleStripperPickup(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryStrippers, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters) {
        if (enderStack.stack().is(FETags.STRIPPER_IGNORE_ITEMS)) return new EnderResult(enderStack.stack(), false);
        if (!(enderStack.stack().getItem() instanceof BlockItem)) return new EnderResult(enderStack.stack(), false);
        ItemStack originalStack = enderStack.stack().copy();

        for (Map.Entry<Integer, ItemStack> entry : inventoryStrippers.entrySet()) {
            ItemStack stripper = entry.getValue();

            if (stripper.isEmpty()) continue;
            if (!matchesDeviceFilter(stripper, enderStack.stack(), false)) continue;

            ItemStack before = originalStack.copy();
            ItemStack remaining = handleStripperOutput(player, enderStack, inventoryStrippers, inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters).stack();

            if (ItemStack.matches(before, remaining)) continue;

            if (!remaining.isEmpty()) {
                processRemaining(player, before, remaining, enderStack);
            }

            if (!enderStack.isEnder()) {
                player.take(enderStack.entity(), originalStack.getCount());
                player.onItemPickup(enderStack.entity());
                enderStack.entity().discard();
            }

            return new EnderResult(ItemStack.EMPTY, true);
        }

        return new EnderResult(enderStack.stack(), false);
    }

    private static EnderStack handleStripperOutput(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryStrippers, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters) {
        ItemStack workingStack = enderStack.stack();

        while (!workingStack.isEmpty()) {
            boolean processed = false;

            for (Map.Entry<Integer, ItemStack> entry : inventoryStrippers.entrySet()) {
                ItemStack stripper = entry.getValue();

                if (stripper.isEmpty()) continue;
                if (!matchesDeviceFilter(stripper, workingStack, false)) continue;
                if (!(workingStack.getItem() instanceof BlockItem blockItem)) continue;

                Optional<FERegistration.IFERegistry.AxeResult> axeResult = FEServices.REGISTRY.getAxeBlockState(player, blockItem.getBlock().defaultBlockState());

                if (axeResult.isEmpty()) continue;

                ItemStack before = workingStack.copy();
                ItemStack result = workingStack.transmuteCopy(axeResult.get().resultState().getBlock());

                if (result.is(workingStack.getItem())) continue;

                workingStack = result;
                processed = true;

                if (ItemStack.matches(before, workingStack)) continue;

                break;
            }

            if (!processed)
                break;
        }

        enderStack = new EnderStack(enderStack.isEnder(), null, workingStack);

        if (!enderStack.stack().isEmpty())
            enderStack = handleCompressorOutput(player, enderStack, inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters);

        return enderStack;
    }

    public static EnderResult handleCompressorPickup(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters) {
        if (enderStack.stack().is(FETags.COMPRESSOR_IGNORE_ITEMS)) return new EnderResult(enderStack.stack(), false);
        if (Block.byItem(enderStack.stack().getItem()).defaultBlockState().is(FETags.COMPRESSOR_IGNORE_BLOCKS)) return new EnderResult(enderStack.stack(), false);
        ItemStack originalStack = enderStack.stack().copy();

        for (Map.Entry<Integer, ItemStack> entry : inventoryCompressors.entrySet()) {
            ItemStack compressor = entry.getValue();

            if (compressor.isEmpty()) continue;
            if (!matchesDeviceFilter(compressor, enderStack.stack(), false)) continue;

            BundleContents.Mutable contents = new BundleContents.Mutable(compressor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));
            Optional<CompressorItem.CompressorRecipe> recipe = CompressorItem.getRecipeFromInput(contents, enderStack.stack());

            if (recipe.isEmpty()) continue;
            if (!canDeviceAccept(compressor, enderStack.stack(), recipe.get().input().getCount())) continue;

            RecipeResult result = CompressorItem.addItemToCompressor(player, recipe.get(), compressor, enderStack.stack());

            if (enderStack.isEnder())
                player.getEnderChestInventory().getItems().set(entry.getKey(), result.device());
            else
                player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());

            if (!result.result().isEmpty()) {
                ItemStack originalResult = result.result().copy();
                player.awardStat(Stats.ITEM_CRAFTED.get(originalResult.getItem()), originalResult.getCount());
                ItemStack remaining = handleCompressorOutput(player, new EnderStack(enderStack.isEnder(), null, result.result()), inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters).stack();
                processRemaining(player, originalStack, remaining, enderStack);
            }

            if (!result.leftover().isEmpty()) {
                ItemStack originalLeftover = result.leftover().copy();
                ItemStack remaining = handleCompressorOutput(player, new EnderStack(enderStack.isEnder(), null, result.leftover()), inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters).stack();
                processRemaining(player, originalLeftover, remaining, enderStack);
            }

            if (!enderStack.isEnder()) {
                player.take(enderStack.entity(), originalStack.getCount());
                player.onItemPickup(enderStack.entity());
                enderStack.entity().discard();
            }

            return new EnderResult(enderStack.stack(), true);
        }

        return new EnderResult(enderStack.stack(), false);
    }

    private static EnderStack handleCompressorOutput(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters) {
        return handleCompressorOutput(player, enderStack, inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters, false);
    }

    private static EnderStack handleCompressorOutput(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters, boolean fromInduction) {
        ItemStack workingStack = enderStack.stack();
        while (!workingStack.isEmpty()) {
            boolean processed = false;

            for (Map.Entry<Integer, ItemStack> entry : inventoryCompressors.entrySet()) {
                ItemStack compressor = entry.getValue();

                if (compressor.isEmpty()) continue;
                if (!matchesDeviceFilter(compressor, workingStack, false)) continue;

                BundleContents.Mutable contents = new BundleContents.Mutable(compressor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));
                Optional<CompressorItem.CompressorRecipe> recipe = CompressorItem.getRecipeFromInput(contents, workingStack);

                if (recipe.isEmpty()) continue;
                if (!canDeviceAccept(compressor, workingStack, recipe.get().input().getCount())) continue;

                ItemStack before = workingStack.copy();
                RecipeResult result = CompressorItem.addItemToCompressor(player, recipe.get(), compressor, workingStack);
                if (enderStack.isEnder())
                    player.getEnderChestInventory().getItems().set(entry.getKey(), result.device());
                else
                    player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());

                if (!result.result().isEmpty()) {
                    ItemStack originalResult = result.result().copy();
                    player.awardStat(Stats.ITEM_CRAFTED.get(originalResult.getItem()), originalResult.getCount());
                    ItemStack output = handleCompressorOutput(player, new EnderStack(enderStack.isEnder(), null, result.result()), inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters).stack();
                    if (!output.isEmpty()) {
                        output = handleInductionFurnaceOutput(player, new EnderStack(enderStack.isEnder(), null, output), inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, inventoryEnderRouters, true).stack();
                        processRemaining(player, before, output, enderStack);
                    }
                }

                workingStack = result.leftover();

                if (ItemStack.matches(before, workingStack)) continue;

                processed = true;
                break;
            }

            if (!processed)
                break;
        }

        enderStack = new EnderStack(enderStack.isEnder(), null, workingStack);
        if (!enderStack.stack().isEmpty() && !fromInduction)
            enderStack = handleInductionFurnaceOutput(player, enderStack, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, inventoryEnderRouters, true);
        else if (!enderStack.stack().isEmpty())
            enderStack = handleDeviceOutput(player, enderStack, inventoryRouters, inventoryEnderRouters);
        return enderStack;
    }

    public static EnderResult handleInductionFurnacePickup(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters) {
        if (enderStack.stack().is(FETags.INDUCTION_FURNACE_IGNORE_ITEMS)) return new EnderResult(enderStack.stack(), false);
        ItemStack originalStack = enderStack.stack().copy();

        for (Map.Entry<Integer, ItemStack> entry : inventoryInductionFurnaces.entrySet()) {
            ItemStack inductionFurnace = entry.getValue();

            if (inductionFurnace.isEmpty()) continue;
            if (!matchesDeviceFilter(inductionFurnace, enderStack.stack(), false)) continue;

            BundleContents.Mutable contents = new BundleContents.Mutable(inductionFurnace.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));
            InductionFurnaceItem.InductionRecipe recipe = InductionFurnaceItem.INDUCTION_RECIPES.get(enderStack.stack().getItem());

            if (!canDeviceAccept(inductionFurnace, enderStack.stack(), 1)) continue;

            RecipeResult result = InductionFurnaceItem.addItemToInductionFurnace(player, player.level(), inventoryFuelCarriers, recipe, inductionFurnace, enderStack.stack());

            if (enderStack.isEnder())
                player.getEnderChestInventory().getItems().set(entry.getKey(), result.device());
            else
                player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());

            if (!result.result().isEmpty()) {
                ItemStack originalResult = result.result().copy();
                player.awardStat(Stats.ITEM_CRAFTED.get(originalResult.getItem()), originalResult.getCount());
                ItemStack remaining = handleInductionFurnaceOutput(player, new EnderStack(enderStack.isEnder(), null, result.result()), inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, inventoryEnderRouters).stack();
                processRemaining(player, originalStack, remaining, enderStack);
            }

            if (!result.leftover().isEmpty()) {
                ItemStack originalLeftover = result.leftover().copy();
                ItemStack remaining = handleInductionFurnaceOutput(player, new EnderStack(enderStack.isEnder(), null, result.leftover()), inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, inventoryEnderRouters).stack();
                processRemaining(player, originalLeftover, remaining, enderStack);
            }

            if (!enderStack.isEnder()) {
                player.take(enderStack.entity(), originalStack.getCount());
                player.onItemPickup(enderStack.entity());
                enderStack.entity().discard();
            }

            return new EnderResult(enderStack.stack(), true);
        }

        return new EnderResult(enderStack.stack(), false);
    }

    public static EnderStack handleInductionFurnaceOutput(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters) {
        return handleInductionFurnaceOutput(player, enderStack, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, inventoryEnderRouters, false);
    }

    public static EnderStack handleInductionFurnaceOutput(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryInductionFurnaces, Map<Integer, ItemStack> inventoryFuelCarriers, Map<Integer, ItemStack> inventoryCompressors, Map<Integer, ItemStack> inventoryRouters, Map<Integer, ItemStack> inventoryEnderRouters, boolean fromCompressor) {
        ItemStack workingStack = enderStack.stack();
        while (!workingStack.isEmpty()) {
            boolean processed = false;

            for (Map.Entry<Integer, ItemStack> entry : inventoryInductionFurnaces.entrySet()) {
                ItemStack inductionFurnace = entry.getValue();

                if (inductionFurnace.isEmpty()) continue;
                if (!matchesDeviceFilter(inductionFurnace, workingStack, false)) continue;

                BundleContents.Mutable contents = new BundleContents.Mutable(inductionFurnace.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));
                InductionFurnaceItem.InductionRecipe recipe = InductionFurnaceItem.INDUCTION_RECIPES.get(workingStack.getItem());

                if (!canDeviceAccept(inductionFurnace, workingStack, 1)) continue;

                ItemStack before = workingStack.copy();
                RecipeResult result = InductionFurnaceItem.addItemToInductionFurnace(player, player.level(), inventoryFuelCarriers, recipe, inductionFurnace, workingStack);

                if (enderStack.isEnder())
                    player.getEnderChestInventory().getItems().set(entry.getKey(), result.device());
                else
                    player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());

                if (!result.result().isEmpty()) {
                    ItemStack originalResult = result.result().copy();
                    player.awardStat(Stats.ITEM_CRAFTED.get(originalResult.getItem()), originalResult.getCount());
                    ItemStack output = handleInductionFurnaceOutput(player, new EnderStack(enderStack.isEnder(), null, result.result()), inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, inventoryEnderRouters).stack();

                    if (!output.isEmpty()) {
                        output = handleCompressorOutput(player, new EnderStack(enderStack.isEnder(), null, output), inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters, true).stack();
                        processRemaining(player, before, output, enderStack);
                    }
                }

                workingStack = result.leftover();

                if (ItemStack.matches(before, workingStack)) continue;

                processed = true;
                break;
            }

            if (!processed)
                break;
        }

        enderStack = new EnderStack(enderStack.isEnder(), null, workingStack);

        if (!enderStack.stack().isEmpty() && !fromCompressor)
            enderStack = handleCompressorOutput(player, enderStack, inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, inventoryEnderRouters, true);
        else if (!enderStack.stack().isEmpty())
            enderStack = handleDeviceOutput(player, enderStack, inventoryRouters, inventoryEnderRouters);

        return enderStack;
    }

    private static boolean handleEnderRouterPickup(Player player, ItemEntity item, Map<Integer, ItemStack> inventoryEnderRouters) {
        if (item.getItem().is(FETags.ENDER_ROUTER_IGNORE_ITEMS)) return false;
        for (Map.Entry<Integer, ItemStack> entry : inventoryEnderRouters.entrySet()) {
            ItemStack router = entry.getValue();

            if (router.isEmpty()) continue;
            if (!matchesDeviceFilter(router, item.getItem(), true)) continue;

            ItemStack originalStack = item.getItem().copy();
            RouterItem.Result result = EnderRouterItem.addItemToEnderChest(player, router, item.getItem());
            int pickedUp = originalStack.getCount() - result.stack().getCount();

            if (pickedUp > 0) {
                player.take(item, pickedUp);
                player.awardStat(Stats.ITEM_PICKED_UP.get(originalStack.getItem()), pickedUp);
                player.onItemPickup(item);
            }

            player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());

            if (result.stack().isEmpty()) {
                item.discard();
                return true;
            }

            item.getItem().setCount(result.stack().getCount());
        }

        return false;
    }

    public static EnderResult handleRouterPickup(Player player, EnderStack enderStack, Map<Integer, ItemStack> inventoryRouters) {
        if (enderStack.stack().is(FETags.ROUTER_IGNORE_ITEMS)) return new EnderResult(enderStack.stack(), false);
        for (Map.Entry<Integer, ItemStack> entry : inventoryRouters.entrySet()) {
            ItemStack router = entry.getValue();

            if (router.isEmpty()) continue;
            if (!matchesDeviceFilter(router, enderStack.stack(), true)) continue;

            ItemStack originalStack = enderStack.stack().copy();
            RouterItem.Result result = RouterItem.addItemToShulker(router, enderStack.stack());
            int pickedUp = originalStack.getCount() - result.stack().getCount();

            if (!enderStack.isEnder() && pickedUp > 0) {
                player.take(enderStack.entity(), pickedUp);
                //player.awardStat(Stats.ITEM_PICKED_UP.get(originalStack.getItem()), pickedUp);
                player.onItemPickup(enderStack.entity());
            }

            if (enderStack.isEnder())
                player.getEnderChestInventory().getItems().set(entry.getKey(), result.device());
            else
                player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.device());

            if (result.stack().isEmpty()) {
                if (!enderStack.isEnder())
                    enderStack.entity().discard();
                return new EnderResult(enderStack.stack(), true);
            }

            enderStack.stack().setCount(result.stack().getCount());
        }

        return new EnderResult(enderStack.stack(), false);
    }

    public record EnderResult(ItemStack result, boolean getResult) {}

    public record EnderStack(boolean isEnder, ItemEntity entity, ItemStack stack) {
        @Override
        public ItemStack stack() {
            return isEnder ? stack : entity == null ? stack : entity.getItem();
        }
    }
}
