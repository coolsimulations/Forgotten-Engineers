package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.client.ForgottenEngineersClientEvents;
import net.coolsimulations.ForgottenEngineers.data.ForgottenEngineersDataGeneration;
import net.coolsimulations.ForgottenEngineers.event.FEEntityEvents;
import net.coolsimulations.ForgottenEngineers.item.CompressorItem;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.item.InductionFurnaceItem;
import net.coolsimulations.ForgottenEngineers.loot.ForgottenEngineersLootModifiers;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPacket;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPayload;
import net.coolsimulations.ForgottenEngineers.network.FENetwork;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPacket;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.util.Result;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.List;

@Mod(value = ForgottenEngineersCommon.MOD_ID)
@Mod.EventBusSubscriber(modid = ForgottenEngineersCommon.MOD_ID)
public class ForgottenEngineers {

    public static ForgottenEngineersCommon proxy = FMLEnvironment.dist.isClient() ?  new ForgottenEngineersClientCommon() : new ForgottenEngineersCommon();

    public ForgottenEngineers(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();
        proxy.init();
        FENetwork.register();
        ForgottenEngineersLootModifiers.LOOT_MODIFIERS.register(modBusGroup);
        GatherDataEvent.getBus(modBusGroup).addListener(ForgottenEngineersDataGeneration::gatherData);
        FMLClientSetupEvent.getBus(modBusGroup).addListener(ForgottenEngineers::setupClient);
        FMLClientSetupEvent.getBus(modBusGroup).addListener(ForgottenEngineersClientEvents::setupClient);
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        proxy.init();
    }

    @SubscribeEvent
    public static void creativeTabs(BuildCreativeModeTabContentsEvent event) {
        ForgottenEngineersItems.generateCreativeTabListing((listing) -> {
            if (event.getTabKey() == listing.tab())
                event.getEntries().putAfter(listing.beforeItem().getDefaultInstance(), listing.item().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });
    }

    @SubscribeEvent
    public static void onPlayerPickUpItems(EntityItemPickupEvent event) {
        if (!FEEntityEvents.PLAYER_ITEM_ENTITY_PICKUP.post().handle(event.getEntity(), event.getItem())) {
            event.getItem().setDefaultPickUpDelay(); //This seems stupid, but Forge doesn't actually cancel the pickup
            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        List<InductionRecipeSyncPacket.RecipeData> inductionRecipes = createInductionRecipeSyncData();
        List<CompressorRecipeSyncPacket.RecipeData> compressorRecipes = createCompressorRecipeSyncData();
        InductionRecipeSyncPacket inductionPacket = new InductionRecipeSyncPacket(inductionRecipes);
        CompressorRecipeSyncPacket compressorPacket = new CompressorRecipeSyncPacket(compressorRecipes);

        if (event.getPlayer() != null) {
            FENetwork.sendInductionToPlayer(event.getPlayer(), inductionPacket);
            FENetwork.sendCompressorToPlayer(event.getPlayer(), compressorPacket);
        } else {
            FENetwork.sendInductionToAll(inductionPacket);
            FENetwork.sendCompressorToAll(compressorPacket);
        }
    }

    public static List<InductionRecipeSyncPacket.RecipeData> createInductionRecipeSyncData() {

        return InductionFurnaceItem.INDUCTION_RECIPES.entrySet().stream().map(entry -> {

                    Item input = entry.getKey();
                    InductionFurnaceItem.InductionRecipe recipe = entry.getValue();

                    return new InductionRecipeSyncPacket.RecipeData(
                            BuiltInRegistries.ITEM.getKey(input),
                            BuiltInRegistries.ITEM.getKey(recipe.output().getItem()),
                            recipe.cookingTime(),
                            recipe.experience()
                    );
                }).toList();
    }

    public static List<CompressorRecipeSyncPacket.RecipeData> createCompressorRecipeSyncData() {
        return CompressorItem.COMPRESSOR_RECIPES.entrySet().stream().map(entry -> new CompressorRecipeSyncPacket.RecipeData(
                        entry.getKey().copy(),
                        entry.getValue().copy()
                )).toList();
    }
}
