package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.data.FELoot;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPayload;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;

import java.util.List;

public class ForgottenEngineers implements ModInitializer {

    public static ForgottenEngineersCommon proxy = new ForgottenEngineersCommon();

    @Override
    public void onInitialize() {
        proxy.init();

        LootTableEvents.MODIFY.register((key, builder, source, holder) -> {
            FELoot.generateChestLoot(wisdomLoot -> {
                if (wisdomLoot.lootTables().contains(key.identifier()))
                    builder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(wisdomLoot.item())).add(EmptyLootItem.emptyItem().setWeight(wisdomLoot.weight())));
            });

            FELoot.generateArcheologyLoot(wisdomLoot -> {
                if (wisdomLoot.lootTables().contains(key.identifier()))
                    builder.modifyPools(modifier -> modifier.add(LootItem.lootTableItem(wisdomLoot.item()).setWeight(wisdomLoot.weight())));
            });
        });

        ForgottenEngineersItems.generateCreativeTabListing((listing) ->
                CreativeModeTabEvents.modifyOutputEvent(listing.tab()).register(content ->
                    content.insertAfter(listing.beforeItem(), listing.item())
                ));

        PayloadTypeRegistry.clientboundPlay().register(InductionRecipeSyncPayload.TYPE, InductionRecipeSyncPayload.STREAM_CODEC);
        PayloadTypeRegistry.clientboundPlay().register(CompressorRecipeSyncPayload.TYPE, CompressorRecipeSyncPayload.STREAM_CODEC);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            syncRecipesToPlayer(player);
        });

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((srv, serverResourceManager, success) -> {
                if (success)
                    syncRecipesToAllPlayers(srv);
            });
        });
    }

    private static void syncRecipesToPlayer(ServerPlayer player) {
        List<InductionRecipeSyncPayload.RecipeData> inductionRecipes = ForgottenEngineersCommon.createInductionRecipeSyncData();
        List<CompressorRecipeSyncPayload.RecipeData> compressorRecipes = ForgottenEngineersCommon.createCompressorRecipeSyncData();

        ServerPlayNetworking.send(player, new InductionRecipeSyncPayload(inductionRecipes));
        ServerPlayNetworking.send(player, new CompressorRecipeSyncPayload(compressorRecipes));
    }

    private static void syncRecipesToAllPlayers(net.minecraft.server.MinecraftServer server) {
        List<InductionRecipeSyncPayload.RecipeData> inductionRecipes = ForgottenEngineersCommon.createInductionRecipeSyncData();
        List<CompressorRecipeSyncPayload.RecipeData> compressorRecipes = ForgottenEngineersCommon.createCompressorRecipeSyncData();

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            ServerPlayNetworking.send(player, new InductionRecipeSyncPayload(inductionRecipes));
            ServerPlayNetworking.send(player, new CompressorRecipeSyncPayload(compressorRecipes));
        }
    }
}
