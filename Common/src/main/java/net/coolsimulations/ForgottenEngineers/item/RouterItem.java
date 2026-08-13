package net.coolsimulations.ForgottenEngineers.item;

import com.mojang.serialization.DataResult;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.tooltip.RouterTooltip;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class RouterItem extends BundleItem {

    private static Map<Item, Integer> SHULKER_SIZE_CACHE = new LinkedHashMap<>();

    public RouterItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideStackedOnOther(final ItemStack self, final @NonNull Slot slot, final @NonNull ClickAction clickAction, final @NonNull Player player) {
        BundleContents initialContents = self.get(DataComponents.BUNDLE_CONTENTS);
        if (initialContents == null) {
            return false;
        } else {
            ItemStack other = slot.getItem();
            BundleContents.Mutable contents = new BundleContents.Mutable(initialContents);
            if (clickAction == ClickAction.PRIMARY && !other.isEmpty()) {
                if (tryTransfer(contents, slot, player) > 0)
                    playInsertSound(player);
                else
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
                    else
                        playRemoveOneSound(player);
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
                    if (slot.allowModification(player) && tryInsert(contents, other) > 0)
                        playInsertSound(player);
                    else
                        playInsertFailSound(player);

                    self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
                    this.broadcastChangesOnContainerMenu(player);
                    return true;
                } else if (clickAction == ClickAction.SECONDARY && other.isEmpty()) {
                    if (slot.allowModification(player)) {
                        ItemStack removed = removeOne(contents);
                        if (removed != null) {
                            playRemoveOneSound(player);
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
    public boolean isBarVisible(final ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (hasShulkerBox(contents.items()))
            return getShulkerContentWeight(RouterItem.getShulker(contents.items())).compareTo(Fraction.ZERO) > 0;
        return getWeightSafe(contents).compareTo(Fraction.ZERO) > 0;
    }

    public int getBarWidth(final ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (hasShulkerBox(contents.items()))
            return Math.min(1 + Mth.mulAndTruncate(getShulkerContentWeight(RouterItem.getShulker(contents.items())), 12), 13);
        return Math.min(1 + Mth.mulAndTruncate(getWeightSafe(contents), 12), 13);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        List<ItemStackTemplate> items = contents.items();
        boolean hasShulker = hasShulkerBox(items);

        if ((!hasShulker && getWeightSafe(contents).compareTo(Fraction.ONE) >= 0) || (hasShulker && getShulkerContentWeight(RouterItem.getShulker(items)).compareTo(Fraction.ONE) >= 0))
            return ARGB.color(255, 199, 160, 106);

        if (hasShulker)
            return ARGB.color(255, 151, 106, 151);

        return ARGB.color(255, 109, 176, 166);
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    @Override
    public @NonNull Optional<TooltipComponent> getTooltipImage(final ItemStack bundle) {
        TooltipDisplay display = bundle.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        return !display.shows(DataComponents.BUNDLE_CONTENTS) ? Optional.empty() : Optional.ofNullable(bundle.get(DataComponents.BUNDLE_CONTENTS)).map(RouterTooltip::new);
    }

    public static void playRemoveOneSound(final Entity entity) {
        entity.playSound(FESounds.ROUTER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    public static void playInsertSound(final Entity entity) {
        entity.playSound(FESounds.ROUTER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    public int tryTransfer(BundleContents.Mutable contents, Slot slot, final Player player) {
        ItemStack other = slot.getItem();
        boolean isShulker = isShulker(other);
        DataResult<Fraction> itemWeight = BundleContents.getWeight(other);
        if (isShulker && hasShulkerBox(contents.items)) {
            return 0;
        } else if (itemWeight.isError()) {
            return 0;
        } else {
            int maxAmount = isShulker ? 1 : contents.getMaxAmountToAdd(itemWeight.getOrThrow());
            return (BundleContents.canItemBeInBundle(other) || isShulker) && !hasFilterItemAlready(other.getItem(), contents.items) && !other.is(FETags.ROUTER_IGNORE_ITEMS) ? tryInsert(contents, slot.safeTake(1, maxAmount, player)) : 0;
        }
    }

    public int tryInsert(BundleContents.Mutable contents, ItemStack itemsToAdd) {
        boolean isShulker = isShulker(itemsToAdd);
        if ((!BundleContents.canItemBeInBundle(itemsToAdd) && !isShulker) || hasFilterItemAlready(itemsToAdd.getItem(), contents.items) || itemsToAdd.is(FETags.ROUTER_IGNORE_ITEMS)) {
            return 0;
        } else {
            if (isShulker && hasShulkerBox(contents.items)) {
                return 0;
            }
            DataResult<Fraction> maybeItemWeight = BundleContents.getWeight(itemsToAdd);
            if (maybeItemWeight.isError()) {
                return 0;
            } else {
                Fraction itemWeight = maybeItemWeight.getOrThrow();
                int amountToAdd = isShulker ? 1 : Math.min(1, contents.getMaxAmountToAdd(itemWeight));
                if (amountToAdd == 0) {
                    return 0;
                } else {
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
            }
        }
    }

    private static Optional<ItemStack> removeOneItemFromBundle(final ItemStack self, final Player player, final BundleContents initialContents) {
        BundleContents.Mutable contents = new BundleContents.Mutable(initialContents);
        ItemStack removed = removeOne(contents);
        if (removed != null) {
            playRemoveOneSound(player);
            self.set(DataComponents.BUNDLE_CONTENTS, contents.toImmutable());
            return Optional.of(removed);
        } else {
            return Optional.empty();
        }
    }

    public static @Nullable ItemStack removeOne(BundleContents.Mutable contents) {
        if (contents.items.isEmpty()) return null;

        int shulkerIndex = hasShulkerBox(contents.items) ? getShulkerBoxIndex(contents.items) : -1;
        int removeIndex = 0;

        if (!contents.indexIsOutsideAllowedBounds(contents.selectedItem))
            removeIndex = contents.selectedItem;
        else if (shulkerIndex != -1)
            removeIndex = shulkerIndex;

        ItemStack stack = contents.items.remove(removeIndex);

        contents.weight = contents.weight.subtract(BundleContents.getWeight(stack).getOrThrow().multiplyBy(Fraction.getFraction(stack.getCount(), 1)));
        contents.toggleSelectedItem(-1);

        return stack.copy();
    }

    public static List<Integer> getVisualContentsIndices(List<? extends ItemInstance> items) {
        List<Integer> result = new ArrayList<>();
        int shulkerIndex = getShulkerBoxIndex(items);

        if (shulkerIndex >= 0)
            result.add(shulkerIndex);

        for (int i = 0; i < items.size() && i < 10 + (hasShulkerBox(items) ? 1 : 0); i++) {
            if (i == shulkerIndex)
                continue;
            result.add(i);
        }

        return result;
    }

    public static Result addItemToShulker(ItemStack router, ItemStack stack) {
        BundleContents contents = router.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);
        int shulkerIndex = RouterItem.getShulkerBoxIndex(mutable.items);
        ItemStack shulker = RouterItem.getShulker(contents.items());
        List<ItemStack> shulkerItems = getShulkerContents(shulker);

        if (!RouterItem.hasShulkerBox(contents.items()) || !stack.getItem().canFitInsideContainerItems()) return new Result(router, stack);

        for (int i = 0; i < getShulkerSize(shulker) && !stack.isEmpty(); i++) {
            ItemStack slot = shulkerItems.get(i);

            if (slot.isEmpty()) {
                int amountToAdd = Math.min(stack.getCount(), stack.getMaxStackSize());

                ItemStack newStack = stack.copy();
                newStack.setCount(amountToAdd);
                shulkerItems.set(i, newStack);
                stack.shrink(amountToAdd);

                continue;
            }

            if (canMergeItems(slot, stack)) {
                int space = slot.getMaxStackSize() - slot.getCount();
                int amountToAdd = Math.min(stack.getCount(), space);

                if (space <= 0)
                    continue;
                slot.grow(amountToAdd);
                stack.shrink(amountToAdd);
            }
        }

        shulker.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(shulkerItems));

        if (shulkerIndex >= 0)
            mutable.items.set(shulkerIndex, ItemStackTemplate.fromStack(shulker).create());

        router.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());

        return new Result(router, stack);
    }

    public static float getFullnessDisplay(final ItemStack itemStack) {
        BundleContents contents = itemStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (hasShulkerBox(contents.items()))
            return getShulkerContentWeight(RouterItem.getShulker(contents.items())).floatValue();
        return getWeightSafe(contents).floatValue();
    }

    public static Fraction getShulkerContentWeight(ItemStack shulker) {
        int fullStacks = getShulkerContents(shulker).stream().filter(stack -> stack.getCount() == stack.getMaxStackSize()).mapToInt(stack -> 1).sum();
        int partialStacks = getShulkerContents(shulker).stream().filter(stack -> stack.getCount() != stack.getMaxStackSize()).mapToInt(ItemStack::getCount).sum();
        int weight = fullStacks * 64 + partialStacks;
        int max = Math.max(getShulkerSize(shulker) * 64, 1);

        return Fraction.getFraction(weight, max);
    }


    public static List<ItemStack> getShulkerContents(ItemStack shulker) {
        ItemContainerContents contents = shulker.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
        List<ItemStack> items = new ArrayList<>(Collections.nCopies(getShulkerSize(shulker), ItemStack.EMPTY));

        for (int i = 0; i < getShulkerSize(shulker); i++)
            if (i < contents.allItemsCopyStream().toList().size())
                items.set(i, contents.allItemsCopyStream().toList().get(i).copy());

        return items;
    }

    public static boolean canMergeItems(ItemStack target, ItemStack stack) {
        return target.getCount() <= target.getMaxStackSize() && ItemStack.isSameItemSameComponents(target, stack);
    }

    public static boolean matchesFilter(ItemStack filter, ItemStack stack) {
        return ItemStack.isSameItem(filter, stack);
    }

    public static boolean isShulker(ItemStack item) {
        BlockState state = Block.byItem(item.getItem()).defaultBlockState();
        return item.is(ItemTags.SHULKER_BOXES) || state.is(BlockTags.SHULKER_BOXES) || item.is(FETags.createCommonItemTag("shulker_boxes")) || state.is(FETags.createCommonBlockTag("shulker_boxes"));
    }

    public static int getShulkerBoxIndex(List<? extends ItemInstance> items) {
        for (int i = 0; i <= items.size() - 1; i++)
            if (isShulker(convertFromItemInstance(items.get(i))))
                return i;
        return 0;
    }

    public static boolean hasShulkerBox(List<? extends ItemInstance> items) {
        if (items.isEmpty()) return false;
        return items.stream().anyMatch(item -> item != null && isShulker(convertFromItemInstance(item)));
    }

    public static ItemStack getShulker(List<? extends ItemInstance> items) {
        return ((ItemStackTemplate)items.get(getShulkerBoxIndex(items))).create();
    }

    public static int getShulkerSize(ItemStack shulker) {
        for (Map.Entry<Item, Integer> entry : SHULKER_SIZE_CACHE.entrySet()) {
            if (shulker.is(entry.getKey()))
                return entry.getValue();
        }
        BlockState state = Block.byItem(shulker.getItem()).defaultBlockState();
        if (state.hasBlockEntity()) {
            BlockEntity temp = ((EntityBlock) state.getBlock()).newBlockEntity(BlockPos.ZERO, state);
            if (temp instanceof Container) {
                SHULKER_SIZE_CACHE.put(shulker.getItem(), ((Container) temp).getContainerSize());
                return ((Container) temp).getContainerSize();
            }
        }
        return 27;
    }

    public static List<? extends ItemInstance> getFilterItems(List<? extends ItemInstance> items) {
        if (items.isEmpty()) return new ArrayList<>();
        return items.stream().filter(item -> !isShulker(convertFromItemInstance(item))).toList();
    }

    public static boolean hasFilterItemAlready(ItemLike itemToAdd, List<ItemStack> items) {
        if (items.isEmpty()) return false;
        return items.stream().anyMatch(item -> item != null && item.is(itemToAdd.asItem()));
    }

    public static ItemStack convertFromItemInstance(ItemInstance item) {
        return item instanceof ItemStack ? (ItemStack) item : ((ItemStackTemplate)item).create();
    }

    public record Result(ItemStack router, ItemStack stack) {}
}
