package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EnderRouterItem extends FilterDeviceItem {

    public EnderRouterItem(Properties properties) {
        super(properties);
    }

    public static Map<Integer, ItemStack> getEnderInventoryItems(Player player, Predicate<ItemStack> condition) {
        Map<Integer, ItemStack> matchingItems = new LinkedHashMap<>();
        NonNullList<ItemStack> inventoryItems = player.getEnderChestInventory().getItems();

        for (int i = 0; i < inventoryItems.size(); i++) {
            ItemStack stack = inventoryItems.get(i);
            if (condition.test(stack))
                matchingItems.put(i, stack);
        }

        return matchingItems;
    }

    public static RouterItem.Result addItemToEnderChest(Player player, ItemStack router, ItemStack stack) {
        if (!stack.getItem().canFitInsideContainerItems()) return new RouterItem.Result(router, stack);

        Map<Integer, ItemStack> inventoryRouters = getEnderInventoryItems(player, enderSlot -> enderSlot.is(FETags.ROUTERS));
        Map<Integer, ItemStack> inventoryStrippers = getEnderInventoryItems(player, enderSlot -> enderSlot.is(FEItems.STRIPPER));
        Map<Integer, ItemStack> inventoryCompressors = getEnderInventoryItems(player, enderSlot -> enderSlot.is(FEItems.COMPRESSOR));
        Map<Integer, ItemStack> inventoryFuelCarriers = getEnderInventoryItems(player, enderSlot -> enderSlot.is(FEItems.FUEL_CARRIER));
        Map<Integer, ItemStack> inventoryInductionFurnaces = getEnderInventoryItems(player, enderSlot -> enderSlot.is(FEItems.INDUCTION_FURNACE));

        ForgottenEngineersCommon.EnderResult stripperResult = ForgottenEngineersCommon.handleStripperPickup(player, new ForgottenEngineersCommon.EnderStack(true, null, stack), inventoryStrippers, inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, Collections.emptyMap());
        ForgottenEngineersCommon.EnderResult inductionFurnaceResult = ForgottenEngineersCommon.handleInductionFurnacePickup(player, new ForgottenEngineersCommon.EnderStack(true, null, stack), inventoryInductionFurnaces, inventoryFuelCarriers, inventoryCompressors, inventoryRouters, Collections.emptyMap());
        ForgottenEngineersCommon.EnderResult compressorResult = ForgottenEngineersCommon.handleCompressorPickup(player, new ForgottenEngineersCommon.EnderStack(true, null, stack), inventoryCompressors, inventoryInductionFurnaces, inventoryFuelCarriers, inventoryRouters, Collections.emptyMap());
        ForgottenEngineersCommon.EnderResult routerResult = ForgottenEngineersCommon.handleRouterPickup(player, new ForgottenEngineersCommon.EnderStack(true, null, stack), inventoryRouters);

        if (stripperResult.getResult())
            stack = stripperResult.result();

        if (inductionFurnaceResult.getResult())
            stack = inductionFurnaceResult.result();

        if (compressorResult.getResult())
            stack = compressorResult.result();

        if (routerResult.getResult())
            stack = routerResult.result();

        if (!stack.isEmpty()) {
            for (int i = 0; i < player.getEnderChestInventory().getContainerSize() && !stack.isEmpty(); i++) {
                ItemStack slot = player.getEnderChestInventory().getItem(i);

                if (slot.isEmpty()) {
                    int amountToAdd = Math.min(stack.getCount(), stack.getMaxStackSize());

                    ItemStack newStack = stack.copy();
                    newStack.setCount(amountToAdd);
                    player.getEnderChestInventory().setItem(i, newStack);
                    stack.shrink(amountToAdd);

                    continue;
                }

                if (RouterItem.canMergeItems(slot, stack)) {
                    int space = slot.getMaxStackSize() - slot.getCount();
                    int amountToAdd = Math.min(stack.getCount(), space);

                    if (space <= 0)
                        continue;
                    slot.grow(amountToAdd);
                    stack.shrink(amountToAdd);
                }
            }
        }

        return new RouterItem.Result(router, stack);
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;

        return !stack.is(FETags.ENDER_ROUTER_IGNORE_ITEMS);
    }
}
