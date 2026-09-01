package net.coolsimulations.ForgottenEngineers.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BundleMouseActions;
import net.minecraft.world.inventory.Slot;
import org.jspecify.annotations.NonNull;

import java.util.function.Predicate;

public class DeviceMouseActions extends BundleMouseActions {

    protected final Predicate<Slot> condition;

    public DeviceMouseActions(Minecraft minecraft, Predicate<Slot> condition) {
        super(minecraft);
        this.condition = condition;
    }

    @Override
    public boolean matches(final @NonNull Slot slot) {
        return condition.test(slot);
    }
}
