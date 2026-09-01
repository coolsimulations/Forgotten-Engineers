package net.coolsimulations.ForgottenEngineers.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector2i;
import org.jspecify.annotations.NonNull;

import java.util.function.Predicate;

public class FilterMouseActions extends DeviceMouseActions {

    public FilterMouseActions(Minecraft minecraft, Predicate<Slot> condition) {
        super(minecraft, condition);
    }

    @Override
    public boolean onMouseScrolled(final double scrollX, final double scrollY, final int slotIndex, final @NonNull ItemStack itemStack) {
        int amountOfShownItems = BundleItem.getNumberOfItemsToShow(itemStack);
        if (amountOfShownItems == 0) {
            return false;
        } else {
            Vector2i wheelXY = this.scrollWheelHandler.onMouseScroll(scrollX, scrollY);
            int wheel = wheelXY.y == 0 ? -wheelXY.x : wheelXY.y;
            if (wheel != 0) {
                int selectedItem = BundleItem.getSelectedItemIndex(itemStack);
                int updatedSelectedItem = getNextScrollWheelSelection(wheel, selectedItem, amountOfShownItems);
                if (selectedItem != updatedSelectedItem) {
                    this.toggleSelectedBundleItem(itemStack, slotIndex, updatedSelectedItem);
                }
            }

            return true;
        }
    }

    public static int getNextScrollWheelSelection(double wheel, int currentSelected, int limit) {
        int step = (int)Math.signum(wheel);
        currentSelected += step;
        currentSelected = Math.max(-1, currentSelected);

        while (currentSelected < 0)
            currentSelected += limit;
        while (currentSelected >= limit)
            currentSelected -= limit;
        return currentSelected;
    }

}
