package net.coolsimulations.ForgottenEngineers.mixin;

import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.item.RouterItem;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.world.item.BundleItem.*;

@Mixin(BundleItem.class)
public class BundleItemMixin {

    @Redirect(method = "overrideStackedOnOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playRemoveOneSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOtherRemoveOneSound(Entity entity) {
        if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.RESTORER))
            entity.playSound(FESounds.RESTORER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FETags.ROUTERS) || new ItemStack(((BundleItem) (Object)this)).is(FEItems.ENDER_ROUTER))
            RouterItem.playRemoveOneSound(entity);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMPRESSOR))
            entity.playSound(FESounds.COMPRESSOR_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.FUEL_CARRIER))
            entity.playSound(FESounds.FUEL_CARRIER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.INDUCTION_FURNACE))
            entity.playSound(FESounds.INDUCTION_FURNACE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.MENDER))
            entity.playSound(FESounds.MENDER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.STRIPPER))
            entity.playSound(FESounds.STRIPPER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMBUSTOR))
            entity.playSound(FESounds.COMBUSTOR_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else
            playRemoveOneSound(entity);
    }

    @Redirect(method = "overrideOtherStackedOnMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playRemoveOneSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOnMeRemoveOneSound(Entity entity) {
        if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.RESTORER))
            entity.playSound(FESounds.RESTORER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FETags.ROUTERS) || new ItemStack(((BundleItem) (Object)this)).is(FEItems.ENDER_ROUTER))
            RouterItem.playRemoveOneSound(entity);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMPRESSOR))
            entity.playSound(FESounds.COMPRESSOR_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.FUEL_CARRIER))
            entity.playSound(FESounds.FUEL_CARRIER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.INDUCTION_FURNACE))
            entity.playSound(FESounds.INDUCTION_FURNACE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.MENDER))
            entity.playSound(FESounds.MENDER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.STRIPPER))
            entity.playSound(FESounds.STRIPPER_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMBUSTOR))
            entity.playSound(FESounds.COMBUSTOR_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else
            playRemoveOneSound(entity);
    }

    @Redirect(method = "overrideStackedOnOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playInsertSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOtherInsertSound(Entity entity) {
        if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.RESTORER))
            entity.playSound(FESounds.RESTORER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FETags.ROUTERS) || new ItemStack(((BundleItem) (Object)this)).is(FEItems.ENDER_ROUTER))
            RouterItem.playInsertSound(entity);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMPRESSOR))
            entity.playSound(FESounds.COMPRESSOR_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.FUEL_CARRIER))
            entity.playSound(FESounds.FUEL_CARRIER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.INDUCTION_FURNACE) || new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMBUSTOR))
            entity.playSound(FESounds.INDUCTION_FURNACE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.MENDER))
            entity.playSound(FESounds.MENDER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.STRIPPER))
            entity.playSound(FESounds.STRIPPER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else
            playInsertSound(entity);
    }

    @Redirect(method = "overrideOtherStackedOnMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playInsertSound(Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceOnMeInsertSound(Entity entity) {
        if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.RESTORER))
            entity.playSound(FESounds.RESTORER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FETags.ROUTERS) || new ItemStack(((BundleItem) (Object)this)).is(FEItems.ENDER_ROUTER))
            RouterItem.playInsertSound(entity);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMPRESSOR))
            entity.playSound(FESounds.COMPRESSOR_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.FUEL_CARRIER))
            entity.playSound(FESounds.FUEL_CARRIER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.INDUCTION_FURNACE) || new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMBUSTOR))
            entity.playSound(FESounds.INDUCTION_FURNACE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.MENDER))
            entity.playSound(FESounds.MENDER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.STRIPPER))
            entity.playSound(FESounds.STRIPPER_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else
            playInsertSound(entity);
    }

    @Redirect(method = "dropContent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BundleItem;playDropContentsSound(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)V"))
    private void replaceDropContentsSound(Level level, Entity entity) {
        if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.RESTORER))
            level.playSound(null, entity.blockPosition(), FESounds.RESTORER_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FETags.ROUTERS) || new ItemStack(((BundleItem) (Object)this)).is(FEItems.ENDER_ROUTER))
            level.playSound(null, entity.blockPosition(), FESounds.ROUTER_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMPRESSOR))
            level.playSound(null, entity.blockPosition(), FESounds.COMPRESSOR_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.FUEL_CARRIER))
            level.playSound(null, entity.blockPosition(), FESounds.FUEL_CARRIER_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.INDUCTION_FURNACE))
            level.playSound(null, entity.blockPosition(), FESounds.INDUCTION_FURNACE_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.MENDER))
            level.playSound(null, entity.blockPosition(), FESounds.MENDER_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.STRIPPER))
            level.playSound(null, entity.blockPosition(), FESounds.STRIPPER_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else if (new ItemStack(((BundleItem) (Object)this)).is(FEItems.COMBUSTOR))
            level.playSound(null, entity.blockPosition(), FESounds.COMBUSTOR_DROP_CONTENTS, SoundSource.PLAYERS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
        else
            playDropContentsSound(level, entity);
    }
}
