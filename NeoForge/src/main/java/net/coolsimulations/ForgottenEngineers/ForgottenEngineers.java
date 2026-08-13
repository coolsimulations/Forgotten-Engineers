package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.data.ForgottenEngineersDataGeneration;
import net.coolsimulations.ForgottenEngineers.event.FEEntityEvents;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.loot.ForgottenEngineersLootModifiers;
import net.minecraft.util.TriState;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

@Mod(value = ForgottenEngineersCommon.MOD_ID)
@EventBusSubscriber(modid = ForgottenEngineersCommon.MOD_ID)
public class ForgottenEngineers {

    public static ForgottenEngineersCommon proxy = FMLEnvironment.getDist().isClient() ? new ForgottenEngineersClientCommon() : new ForgottenEngineersCommon();

    public ForgottenEngineers(IEventBus modBus) {
        proxy.init();
        modBus.addListener(ForgottenEngineersDataGeneration::gatherData);
        ForgottenEngineersLootModifiers.LOOT_MODIFIERS.register(modBus);
        modBus.addListener(ForgottenEngineers::setupClient);
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        proxy.init();
    }

    @SubscribeEvent
    public static void creativeTabs(BuildCreativeModeTabContentsEvent event) {
        ForgottenEngineersItems.generateCreativeTabListing((listing) -> {
            if (event.getTabKey() == listing.tab())
                event.insertAfter(listing.beforeItem().getDefaultInstance(), listing.item().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        });
    }

    @SubscribeEvent
    public static void onPlayerPickUpItems(ItemEntityPickupEvent.Pre event) {
        if (!FEEntityEvents.PLAYER_ITEM_ENTITY_PICKUP.post().handle(event.getPlayer(), event.getItemEntity()))
            event.setCanPickup(TriState.FALSE);
    }
}
