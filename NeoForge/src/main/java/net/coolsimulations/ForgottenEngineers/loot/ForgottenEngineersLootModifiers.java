package net.coolsimulations.ForgottenEngineers.loot;

import com.mojang.serialization.MapCodec;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.ForgottenEngineersGlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ForgottenEngineersLootModifiers {

    public static final DeferredRegister <MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ForgottenEngineersCommon.MOD_ID);

    public static final Supplier<MapCodec<ForgottenEngineersGlobalLootModifierProvider.AddItemModifier>> ADD_ITEM = LOOT_MODIFIERS.register("add_item", () -> ForgottenEngineersGlobalLootModifierProvider.AddItemModifier.CODEC);
}
