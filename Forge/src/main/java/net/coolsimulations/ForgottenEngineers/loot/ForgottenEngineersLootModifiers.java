package net.coolsimulations.ForgottenEngineers.loot;

import com.mojang.serialization.MapCodec;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.ForgottenEngineersGlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ForgottenEngineersLootModifiers {

    public static final DeferredRegister <MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ForgottenEngineersCommon.MOD_ID);

    public static final RegistryObject<MapCodec<ForgottenEngineersGlobalLootModifierProvider.AddItemModifier>> ADD_ITEM = LOOT_MODIFIERS.register("add_item", () -> ForgottenEngineersGlobalLootModifierProvider.AddItemModifier.CODEC);
}
