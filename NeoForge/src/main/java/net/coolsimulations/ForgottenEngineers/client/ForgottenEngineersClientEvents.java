package net.coolsimulations.ForgottenEngineers.client;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersClientCommon;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.event.FENetworkEvents;
import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.coolsimulations.ForgottenEngineers.item.InductionFurnaceItem;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPayload;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPayload;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterConditionalItemModelPropertyEvent;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

@EventBusSubscriber(modid = ForgottenEngineersCommon.MOD_ID, value = Dist.CLIENT)
public class ForgottenEngineersClientEvents {

    @SubscribeEvent
    public static void registerItemProperties(RegisterConditionalItemModelPropertyEvent event) {
        FERenderEvents.ITEM_PROPERTIES.post().handle(event::register);
    }

    @SubscribeEvent
    public static void registerTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        FERenderEvents.TOOLTIP_COMPONENT.post().handle(event::register);
    }

    @SubscribeEvent
    public static void register(RegisterClientPayloadHandlersEvent event) {

        event.register(InductionRecipeSyncPayload.TYPE, ForgottenEngineersClientEvents::handleInductionRecipes);
        event.register(CompressorRecipeSyncPayload.TYPE, ForgottenEngineersClientEvents::handleCompressorRecipes);
    }

    private static void handleInductionRecipes(InductionRecipeSyncPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ForgottenEngineersClientCommon.handleInductionRecipes(payload);
        });
    }

    private static void handleCompressorRecipes(CompressorRecipeSyncPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ForgottenEngineersClientCommon.handleCompressorRecipes(payload);
        });
    }

    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        FENetworkEvents.CLIENT_LOGOUT.post().handle();
    }
}
