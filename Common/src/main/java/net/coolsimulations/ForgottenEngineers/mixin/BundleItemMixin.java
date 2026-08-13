package net.coolsimulations.ForgottenEngineers.mixin;

import net.coolsimulations.ForgottenEngineers.item.RestorerItem;
import net.coolsimulations.ForgottenEngineers.item.RouterItem;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.world.item.BundleItem.playDropContentsSound;
import static net.minecraft.world.item.BundleItem.playRemoveOneSound;
import static net.minecraft.world.item.BundleItem.playInsertSound;

@Mixin(BundleItem.class)
public class BundleItemMixin {

    @Redirect(method = "overrideStackedOnOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playRemoveOneSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOtherRemoveOneSound(Entity entity) {
        if (((BundleItem) (Object)this) instanceof RestorerItem)
            entity.playSound(FESounds.RESTORER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        if (((BundleItem) (Object)this) instanceof RouterItem)
            RouterItem.playRemoveOneSound(entity);
        playRemoveOneSound(entity);
    }

    @Redirect(method = "overrideOtherStackedOnMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playRemoveOneSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOnMeRemoveOneSound(Entity entity) {
        if (((BundleItem) (Object)this) instanceof RestorerItem)
            entity.playSound(FESounds.RESTORER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        if (((BundleItem) (Object)this) instanceof RouterItem)
            RouterItem.playRemoveOneSound(entity);
        playRemoveOneSound(entity);
    }

    @Redirect(method = "overrideStackedOnOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playInsertSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOtherInsertSound(Entity entity) {
        if (((BundleItem) (Object)this) instanceof RestorerItem)
            entity.playSound(FESounds.RESTORER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        if (((BundleItem) (Object)this) instanceof RouterItem)
            RouterItem.playInsertSound(entity);
        playInsertSound(entity);
    }

    @Redirect(method = "overrideOtherStackedOnMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playInsertSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOnMeInsertSound(Entity entity) {
        if (((BundleItem) (Object)this) instanceof RestorerItem)
            entity.playSound(FESounds.RESTORER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        if (((BundleItem) (Object)this) instanceof RouterItem)
            RouterItem.playInsertSound(entity);
        playInsertSound(entity);
    }

    @Redirect(method = "dropContent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playDropContentsSound(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceDropContentsSound(Level level, Entity entity) {
        if (((BundleItem) (Object)this) instanceof RestorerItem)
            level.playSound(null, entity.blockPosition(), FESounds.RESTORER_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        if (((BundleItem) (Object)this) instanceof RouterItem)
            level.playSound(null, entity.blockPosition(), FESounds.ROUTER_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        playDropContentsSound(level, entity);
    }
}
