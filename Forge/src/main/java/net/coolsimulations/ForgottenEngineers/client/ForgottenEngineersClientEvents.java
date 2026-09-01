package net.coolsimulations.ForgottenEngineers.client;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.event.FENetworkEvents;
import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.coolsimulations.ForgottenEngineers.item.CompressorItem;
import net.coolsimulations.ForgottenEngineers.item.InductionFurnaceItem;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPacket;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPacket;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ForgottenEngineersCommon.MOD_ID, value = Dist.CLIENT)
public class ForgottenEngineersClientEvents {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        FERenderEvents.ITEM_PROPERTIES.post().handle(ConditionalItemModelProperties.ID_MAPPER::put);
    }

    @SubscribeEvent
    public static void registerTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        FERenderEvents.TOOLTIP_COMPONENT.post().handle(event::register);
    }

    public static void handleInductionRecipes(InductionRecipeSyncPacket packet) {
        InductionFurnaceItem.INDUCTION_RECIPES.clear();

        for (InductionRecipeSyncPacket.RecipeData data : packet.recipes()) {

            Item input = BuiltInRegistries.ITEM.get(data.input()).get().value();
            Item output = BuiltInRegistries.ITEM.get(data.output()).get().value();

            if (input == null || output == null)
                continue;

            InductionFurnaceItem.INDUCTION_RECIPES.put(input, new InductionFurnaceItem.InductionRecipe(new ItemStack(output), data.cookingTime(), data.experience()));
        }
    }

    public static void handleCompressorRecipes(CompressorRecipeSyncPacket packet) {
        CompressorItem.COMPRESSOR_RECIPES.clear();

        for (CompressorRecipeSyncPacket.RecipeData recipe : packet.recipes())
            CompressorItem.COMPRESSOR_RECIPES.put(recipe.input().copy(), recipe.output().copy());
    }

    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        FENetworkEvents.CLIENT_LOGOUT.post().handle();
    }
}
