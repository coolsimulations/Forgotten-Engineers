package net.coolsimulations.ForgottenEngineers.item;

import com.mojang.serialization.DataResult;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.tooltip.FilterDeviceTooltip;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.TooltipDisplay;
import org.apache.commons.lang3.math.Fraction;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class FilterDeviceItem extends StorageDeviceItem {

    public FilterDeviceItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideStackedOnOther(final @NonNull ItemStack self, final @NonNull Slot slot, final @NonNull ClickAction clickAction, final @NonNull Player player) {
        ItemStack other = slot.getItem();
        if (!checkStackIsValidOrEmpty(player, other)) return false;
        BundleContents initialContents = self.get(DataComponents.BUNDLE_CONTENTS);
        if (initialContents == null) {
            return false;
        } else {
            BundleContents.Mutable contents = new BundleContents.Mutable(initialContents);
            if (clickAction == ClickAction.PRIMARY && !other.isEmpty()) {
                if (tryTransfer(contents, slot, player) > 0) {
                    if (self.is(FEItems.COMPRESSOR))
                        player.playSound(FESounds.COMPRESSOR_INSERT, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                    else if (self.is(FEItems.INDUCTION_FURNACE))
                        player.playSound(FESounds.INDUCTION_FURNACE_INSERT, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                    else if (self.is(FEItems.STRIPPER))
                        player.playSound(FESounds.STRIPPER_INSERT, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                    else
                        RouterItem.playInsertSound(player);
                } else
                    playInsertFailSound(player);

                self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
                this.broadcastChangesOnContainerMenu(player);
                return true;
            } else if (clickAction == ClickAction.SECONDARY && other.isEmpty()) {
                ItemStack itemStack = removeOne(contents);
                if (itemStack != null) {
                    ItemStack remainder = slot.safeInsert(itemStack);
                    if (remainder.getCount() > 0)
                        tryInsert(contents, remainder);
                    else {
                        if (self.is(FEItems.COMPRESSOR))
                            player.playSound(FESounds.COMPRESSOR_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                        else if (self.is(FEItems.INDUCTION_FURNACE))
                            player.playSound(FESounds.INDUCTION_FURNACE_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                        else if (self.is(FEItems.STRIPPER))
                            player.playSound(FESounds.STRIPPER_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                        else
                            RouterItem.playRemoveOneSound(player);
                    }
                }

                self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
                this.broadcastChangesOnContainerMenu(player);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(final @NonNull ItemStack self, final @NonNull ItemStack other, final @NonNull Slot slot, final @NonNull ClickAction clickAction, final @NonNull Player player, final @NonNull SlotAccess carriedItem) {
        if (!checkStackIsValidOrEmpty(player, other)) return false;
        if (clickAction == ClickAction.PRIMARY && other.isEmpty()) {
            toggleSelectedItem(self, -1);
            return false;
        } else {
            BundleContents initialContents = self.get(DataComponents.BUNDLE_CONTENTS);
            if (initialContents == null) {
                return false;
            } else {
                BundleContents.Mutable contents = new BundleContents.Mutable(initialContents);
                if (clickAction == ClickAction.PRIMARY && !other.isEmpty()) {
                    if (slot.allowModification(player) && tryInsert(contents, other) > 0) {
                        if (self.is(FEItems.COMPRESSOR))
                            player.playSound(FESounds.COMPRESSOR_INSERT, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                        else if (self.is(FEItems.INDUCTION_FURNACE))
                            player.playSound(FESounds.INDUCTION_FURNACE_INSERT, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                        else if (self.is(FEItems.STRIPPER))
                            player.playSound(FESounds.STRIPPER_INSERT, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                        else
                            RouterItem.playInsertSound(player);
                    } else
                        playInsertFailSound(player);

                    self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
                    this.broadcastChangesOnContainerMenu(player);
                    return true;
                } else if (clickAction == ClickAction.SECONDARY && other.isEmpty()) {
                    if (slot.allowModification(player)) {
                        ItemStack removed = removeOne(contents);
                        if (removed != null) {
                            if (self.is(FEItems.COMPRESSOR))
                                player.playSound(FESounds.COMPRESSOR_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                            else if (self.is(FEItems.INDUCTION_FURNACE))
                                player.playSound(FESounds.INDUCTION_FURNACE_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                            else if (self.is(FEItems.STRIPPER))
                                player.playSound(FESounds.STRIPPER_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                            else
                                RouterItem.playRemoveOneSound(player);
                            carriedItem.set(removed);
                        }
                    }

                    self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
                    this.broadcastChangesOnContainerMenu(player);
                    return true;
                } else {
                    toggleSelectedItem(self, -1);
                    return false;
                }
            }
        }
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

    @Override
    public @NonNull Optional<TooltipComponent> getTooltipImage(final ItemStack bundle) {
        TooltipDisplay display = bundle.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        return !display.shows(DataComponents.BUNDLE_CONTENTS) ? Optional.empty() : Optional.ofNullable(bundle.get(DataComponents.BUNDLE_CONTENTS)).map(FilterDeviceTooltip::new);
    }

    public int tryTransfer(BundleContents.Mutable contents, Slot slot, final Player player) {
        ItemStack other = slot.getItem();
        DataResult<Fraction> itemWeight = BundleContents.getWeight(other);
        if (itemWeight.isError()) {
            return 0;
        } else {
            int maxAmount = contents.getMaxAmountToAdd(itemWeight.getOrThrow());
            return BundleContents.canItemBeInBundle(other) && !ForgottenEngineersCommon.hasFilterItemAlready(other.getItem(), contents.items) && !other.is(FETags.STRIPPER_IGNORE_ITEMS) ? tryInsert(contents, slot.safeTake(1, maxAmount, player)) : 0;
        }
    }

    public int tryInsert(BundleContents.Mutable contents, ItemStack itemsToAdd) {
        if (!BundleContents.canItemBeInBundle(itemsToAdd) || ForgottenEngineersCommon.hasFilterItemAlready(itemsToAdd.getItem(), contents.items) || itemsToAdd.is(FETags.STRIPPER_IGNORE_ITEMS)) return 0;

        DataResult<Fraction> maybeItemWeight = BundleContents.getWeight(itemsToAdd);
        if (maybeItemWeight.isError()) return 0;

        Fraction itemWeight = maybeItemWeight.getOrThrow();
        int amountToAdd = Math.min(1, contents.getMaxAmountToAdd(itemWeight));
        if (amountToAdd == 0) return 0;

        contents.weight = contents.weight.add(itemWeight.multiplyBy(Fraction.getFraction(amountToAdd, 1)));
        int stackIndex = contents.findStackIndex(itemsToAdd);
        if (stackIndex != -1) {
            ItemStack removedStack = contents.items.remove(stackIndex);
            ItemStack mergedStack = removedStack.copyWithCount(removedStack.getCount() + amountToAdd);
            itemsToAdd.shrink(amountToAdd);
            contents.items.addFirst(mergedStack);
        } else {
            contents.items.addFirst(itemsToAdd.split(amountToAdd));
        }

        return amountToAdd;
    }

    private static Optional<ItemStack> removeOneItemFromBundle(final ItemStack self, final Player player, final BundleContents initialContents) {
        BundleContents.Mutable contents = new BundleContents.Mutable(initialContents);
        ItemStack removed = removeOne(contents);
        if (removed != null) {
            if (self.is(FEItems.COMPRESSOR))
                player.playSound(FESounds.COMPRESSOR_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FEItems.INDUCTION_FURNACE))
                player.playSound(FESounds.INDUCTION_FURNACE_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else if (self.is(FEItems.STRIPPER))
                player.playSound(FESounds.STRIPPER_REMOVE_ONE, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
            else
                RouterItem.playRemoveOneSound(player);
            self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
            return Optional.of(removed);
        } else {
            return Optional.empty();
        }
    }

    public static @Nullable ItemStack removeOne(BundleContents.Mutable contents) {
        if (contents.items.isEmpty()) return null;

        int removeIndex = contents.indexIsOutsideAllowedBounds(contents.selectedItem) ? 0 : (contents.items.size() - 1 - contents.selectedItem);
        ItemStack stack = contents.items.remove(removeIndex).copy();
        contents.weight = contents.weight.subtract(BundleContents.getWeight(stack).getOrThrow().multiplyBy(Fraction.getFraction(stack.getCount(), 1)));
        contents.toggleSelectedItem(-1);
        return stack;
    }
}
