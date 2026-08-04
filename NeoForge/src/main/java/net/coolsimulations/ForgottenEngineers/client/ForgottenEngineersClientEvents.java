package net.coolsimulations.ForgottenEngineers.client;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@EventBusSubscriber(modid = ForgottenEngineersCommon.MOD_ID, value = Dist.CLIENT)
public class ForgottenEngineersClientEvents {

    @SubscribeEvent
    public static void registerTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        FERenderEvents.TOOLTIP_COMPONENT.post().handle(event::register);
    }
}
