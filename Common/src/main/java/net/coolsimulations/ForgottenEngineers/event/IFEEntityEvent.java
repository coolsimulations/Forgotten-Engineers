package net.coolsimulations.ForgottenEngineers.event;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public interface IFEEntityEvent extends IFEEvent {

    interface PlayerItemEntityPickupEvent extends IFEEntityEvent {
        boolean handle(Player player, ItemEntity item);
    }
}
