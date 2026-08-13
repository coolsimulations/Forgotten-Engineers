package net.coolsimulations.ForgottenEngineers.mixin;

import net.coolsimulations.ForgottenEngineers.event.FEEntityEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method = "playerTouch", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;getCount()I", ordinal = 0), cancellable = true)
    public void playerTouch(Player player, CallbackInfo info) {
        if (!FEEntityEvents.PLAYER_ITEM_ENTITY_PICKUP.post().handle(player, (ItemEntity) (Object)this))
            info.cancel();
    }
}
