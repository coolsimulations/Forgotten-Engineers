package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;

public class ForgottenEngineersClient implements ClientModInitializer {

    public static ForgottenEngineersClientCommon proxy = new ForgottenEngineersClientCommon();

    @Override
    public void onInitializeClient() {
        proxy.init();

        FERenderEvents.TOOLTIP_COMPONENT.post().handle((clazz, component) -> ClientTooltipComponentCallback.EVENT.register((register -> {
            if (register.getClass().equals(clazz))
                return component.apply(register);
            return null;
        })));
    }
}
