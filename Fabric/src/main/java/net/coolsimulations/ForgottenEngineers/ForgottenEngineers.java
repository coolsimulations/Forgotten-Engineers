package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.data.FELoot;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public class ForgottenEngineers implements ModInitializer {

    public static ForgottenEngineersCommon proxy = new ForgottenEngineersCommon();

    @Override
    public void onInitialize() {
        proxy.init();

        LootTableEvents.MODIFY.register((key, builder, source, holder) -> FELoot.generateWisdomLoot((wisdomLoot -> {
            if (wisdomLoot.lootTables().contains(key.identifier())) {
                builder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(wisdomLoot.item())).add(EmptyLootItem.emptyItem().setWeight(wisdomLoot.weight())));
            }
        })));

        ForgottenEngineersItems.generateCreativeTabListing((listing) ->
                CreativeModeTabEvents.modifyOutputEvent(listing.tab()).register(content ->
                    content.insertAfter(listing.beforeItem(), listing.item())
                ));
    }
}
