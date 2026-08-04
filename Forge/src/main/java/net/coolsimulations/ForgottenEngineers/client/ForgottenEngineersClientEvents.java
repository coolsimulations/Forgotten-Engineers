package net.coolsimulations.ForgottenEngineers.client;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ForgottenEngineersCommon.MOD_ID, value = Dist.CLIENT)
public class ForgottenEngineersClientEvents {

    @SubscribeEvent
    public static void registerTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        FERenderEvents.TOOLTIP_COMPONENT.post().handle(event::register);
    }
}
