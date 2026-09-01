package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.FEServices;
import net.coolsimulations.ForgottenEngineers.item.tooltip.CombustorTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class CombustorItem extends StorageDeviceItem {

    public CombustorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull Optional<TooltipComponent> getTooltipImage(final ItemStack bundle) {
        TooltipDisplay display = bundle.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        return !display.shows(DataComponents.BUNDLE_CONTENTS) ? Optional.empty() : Optional.ofNullable(bundle.get(DataComponents.BUNDLE_CONTENTS)).map(CombustorTooltip::new);
    }

    @Override
    public @NonNull InteractionResult use(final @NonNull Level level, final Player player, final @NonNull InteractionHand hand) {
        ItemStack combustor = player.getItemInHand(hand);

        if (!player.getCooldowns().isOnCooldown(combustor)) {
            if (hasFuelForLarge(combustor) && hasFuelForShot(combustor)) {
                consumeFuel(combustor, true);
                return shootFireball(combustor, level, player, true);
            } else if (hasFuelForShot(combustor)) {
                consumeFuel(combustor, false);
                return shootFireball(combustor, level, player, false);
            }
        }
        player.startUsingItem(hand);
        return InteractionResult.SUCCESS;
    }

    public InteractionResult shootFireball(ItemStack combustor, Level level, Player player, boolean isLarge) {
        Vec3 viewVector = player.getViewVector(1.0F);
        Fireball entity;
        if (isLarge)
            entity = new LargeFireball(level, player, viewVector.normalize(), 1);
        else
            entity = new SmallFireball(level, player, viewVector.normalize());
        entity.setPos(player.getX() + viewVector.x, player.getY(0.5F) + (isLarge ? (double)-0.5F : (double)0.5F), entity.getZ() + viewVector.z);
        level.addFreshEntity(entity);
        if (!player.isCreative())
            player.getCooldowns().addCooldown(combustor, 80);
        level.playSound(null, player.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 2.0F, (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F + 1.0F);
        return InteractionResult.SUCCESS;
    }

    public void consumeFuel(ItemStack combustor, boolean isLarge) {
        BundleContents contents = combustor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);
        mutable.items.stream().filter(stack -> stack.is(Items.BLAZE_POWDER)).findFirst().ifPresent(stack -> stack.shrink(1));
        mutable.items.stream().filter(stack -> stack.is(FEServices.REGISTRY.getGunpowders())).findFirst().ifPresent(stack -> stack.shrink(1));
        if (isLarge)
            mutable.items.stream().filter(stack -> stack.is(ItemTags.COALS)).findFirst().ifPresent(stack -> stack.shrink(1));
        mutable.items.removeIf(ItemStack::isEmpty);
        combustor.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
    }

    public static boolean hasFuelForLarge(ItemStack combustor) {
        BundleContents contents = combustor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (!contents.isEmpty())
            return contents.itemCopyStream().anyMatch(stack -> stack.is(ItemTags.COALS));
        return false;
    }

    public static boolean hasFuelForShot(ItemStack combustor) {
        BundleContents contents = combustor.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (!contents.isEmpty())
            return contents.itemCopyStream().anyMatch(stack -> stack.is(Items.BLAZE_POWDER)) && contents.itemCopyStream().anyMatch(stack -> stack.is(FEServices.REGISTRY.getGunpowders()));
        return false;
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;

        return stack.is(ItemTags.COALS) || stack.is(FEServices.REGISTRY.getGunpowders()) || stack.is(Items.BLAZE_POWDER);
    }
}
