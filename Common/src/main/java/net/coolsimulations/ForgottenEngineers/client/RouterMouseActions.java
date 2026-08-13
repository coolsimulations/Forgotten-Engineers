package net.coolsimulations.ForgottenEngineers.client;

import net.coolsimulations.ForgottenEngineers.item.RouterItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ScrollWheelHandler;
import net.minecraft.client.gui.BundleMouseActions;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.game.ServerboundSelectBundleItemPacket;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.joml.Vector2i;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class RouterMouseActions extends BundleMouseActions {

    public RouterMouseActions(Minecraft minecraft) {
        super(minecraft);
    }

    @Override
    public boolean onMouseScrolled(final double scrollX, final double scrollY, final int slotIndex, final ItemStack itemStack) {
        List<? extends ItemInstance> items = itemStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).items();
        List<Integer> visualContentsIndices = RouterItem.getVisualContentsIndices(items);

        if (visualContentsIndices.isEmpty()) return false;

        Vector2i wheelXY = this.scrollWheelHandler.onMouseScroll(scrollX, scrollY);
        int wheel = wheelXY.y == 0 ? -wheelXY.x : wheelXY.y;

        if (wheel != 0) {
            int selectedContentsIndex = RouterItem.getSelectedItemIndex(itemStack);
            int selectedVisualIndex = visualContentsIndices.indexOf(selectedContentsIndex);
            int updatedVisualIndex = ScrollWheelHandler.getNextScrollWheelSelection(wheel, selectedVisualIndex, visualContentsIndices.size());
            int updatedContentsIndex = visualContentsIndices.get(updatedVisualIndex);

            if (selectedContentsIndex != updatedContentsIndex)
                this.toggleSelectedBundleItem(itemStack, slotIndex, updatedContentsIndex);
        }

        return true;
    }

    @Override
    public void toggleSelectedBundleItem(final @NonNull ItemStack router, final int slotIndex, final int selectedItem) {
        if (this.minecraft.getConnection() != null) {
            ClientPacketListener connection = this.minecraft.getConnection();
            BundleItem.toggleSelectedItem(router, selectedItem);
            connection.send(new ServerboundSelectBundleItemPacket(slotIndex, selectedItem));
        }
    }


    @Override
    public boolean matches(final Slot slot) {
        return slot.getItem().getItem() instanceof RouterItem;
    }
}
