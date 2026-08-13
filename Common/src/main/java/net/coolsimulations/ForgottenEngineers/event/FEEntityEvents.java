package net.coolsimulations.ForgottenEngineers.event;

public class FEEntityEvents {

    public static final FEEvent<IFEEntityEvent.PlayerItemEntityPickupEvent> PLAYER_ITEM_ENTITY_PICKUP = new FEEvent<>(listeners -> (player, item) -> {
        for(var listener : listeners)
            if(listener.handle(player, item))
                return true;
        return false;
    });
}
