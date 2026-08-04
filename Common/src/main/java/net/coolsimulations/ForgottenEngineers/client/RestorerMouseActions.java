package net.coolsimulations.ForgottenEngineers.client;

import net.coolsimulations.ForgottenEngineers.item.RestorerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BundleMouseActions;
import net.minecraft.world.inventory.Slot;

public class RestorerMouseActions extends BundleMouseActions {

    public RestorerMouseActions(Minecraft minecraft) {
        super(minecraft);
    }

    @Override
    public boolean matches(final Slot slot) {
        return slot.getItem().getItem() instanceof RestorerItem;
    }
}
