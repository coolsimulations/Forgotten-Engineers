package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.data.ForgottenEngineersDataGeneration;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.loot.ForgottenEngineersLootModifiers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(value = ForgottenEngineersCommon.MOD_ID)
@Mod.EventBusSubscriber(modid = ForgottenEngineersCommon.MOD_ID)
public class ForgottenEngineers {

    public static ForgottenEngineersCommon proxy = FMLEnvironment.dist.isClient() ?  new ForgottenEngineersClientCommon() : new ForgottenEngineersCommon();

    public ForgottenEngineers(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();
        proxy.init();
        ForgottenEngineersLootModifiers.LOOT_MODIFIERS.register(modBusGroup);
        GatherDataEvent.getBus(modBusGroup).addListener(ForgottenEngineersDataGeneration::gatherData);
        FMLClientSetupEvent.getBus(modBusGroup).addListener(ForgottenEngineers::setupClient);
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
}
