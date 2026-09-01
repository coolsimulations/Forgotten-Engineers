package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.client.FEClientEvents;
import net.coolsimulations.ForgottenEngineers.event.FENetworkEvents;
import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPayload;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;

public class ForgottenEngineersClient implements ClientModInitializer {

    public static ForgottenEngineersClientCommon proxy = new ForgottenEngineersClientCommon();

    @Override
    public void onInitializeClient() {
        proxy.init();

        FERenderEvents.ITEM_PROPERTIES.post().handle(ConditionalItemModelProperties.ID_MAPPER::put);

        FERenderEvents.TOOLTIP_COMPONENT.post().handle((clazz, component) -> ClientTooltipComponentCallback.EVENT.register((register -> {
            if (register.getClass().equals(clazz))
                return component.apply(register);
            return null;
        })));

        ClientPlayNetworking.registerGlobalReceiver(InductionRecipeSyncPayload.TYPE, ForgottenEngineersClient::handleInductionRecipes);
        ClientPlayNetworking.registerGlobalReceiver(CompressorRecipeSyncPayload.TYPE, ForgottenEngineersClient::handleCompressorRecipes);

        ClientPlayConnectionEvents.DISCONNECT.register((handler, minecraft) -> FENetworkEvents.CLIENT_LOGOUT.post().handle());
    }

    private static void handleInductionRecipes(InductionRecipeSyncPayload payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            ForgottenEngineersClientCommon.handleInductionRecipes(payload);
        });
    }

    private static void handleCompressorRecipes(CompressorRecipeSyncPayload payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            ForgottenEngineersClientCommon.handleCompressorRecipes(payload);
        });
    }
}
