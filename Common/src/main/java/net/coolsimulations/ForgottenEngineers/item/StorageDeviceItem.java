package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class StorageDeviceItem extends BundleItem {

    public StorageDeviceItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideStackedOnOther(@NotNull ItemStack restorer, Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        return checkStackIsValidOrEmpty(player, slot.getItem()) && super.overrideStackedOnOther(restorer, slot, action, player);
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack restorer, @NotNull ItemStack stack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        return checkStackIsValidOrEmpty(player, stack) && super.overrideOtherStackedOnMe(restorer, stack, slot, action, player, access);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return getWeightSafe(contents).compareTo(Fraction.ONE) >= 0 ? ARGB.color(255, 199, 160, 106) : ARGB.color(255, 109, 176, 166);
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        return true;
    }

    @Override
    public boolean dropContent(final ItemStack bundle, final @NonNull Player player) {
        BundleContents contents = bundle.get(DataComponents.BUNDLE_CONTENTS);
        if (contents != null && !contents.isEmpty()) {
            Optional<ItemStack> itemStack = removeOneItemFromBundle(bundle, player, contents);
            if (itemStack.isPresent()) {
                player.drop(itemStack.get(), true);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static Optional<ItemStack> removeOneItemFromBundle(final ItemStack self, final Player player, final BundleContents initialContents) {
        BundleContents.Mutable contents = new BundleContents.Mutable(initialContents);
        ItemStack removed = contents.removeOne();
        if (removed != null) {
            if (self.is(FEItems.RESTORER))
                player.playSound(FESounds.RESTORER_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FETags.ROUTERS) || self.is(FEItems.ENDER_ROUTER))
                RouterItem.playRemoveOneSound(player);
            else if (self.is(FEItems.COMPRESSOR))
                player.playSound(FESounds.COMPRESSOR_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FEItems.FUEL_CARRIER))
                player.playSound(FESounds.FUEL_CARRIER_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FEItems.INDUCTION_FURNACE))
                player.playSound(FESounds.INDUCTION_FURNACE_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FEItems.MENDER))
                player.playSound(FESounds.MENDER_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FEItems.STRIPPER))
                player.playSound(FESounds.STRIPPER_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FEItems.COMBUSTOR))
                player.playSound(FESounds.COMBUSTOR_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else
                playRemoveOneSound(player);
            self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
            return Optional.of(removed);
        } else {
            return Optional.empty();
        }
    }
}
