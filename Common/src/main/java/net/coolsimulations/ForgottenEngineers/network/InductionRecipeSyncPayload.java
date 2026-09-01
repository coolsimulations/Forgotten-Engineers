package net.coolsimulations.ForgottenEngineers.network;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record InductionRecipeSyncPayload(List<RecipeData> recipes) implements CustomPacketPayload {

    public static final Type<InductionRecipeSyncPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "induction_recipe_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, InductionRecipeSyncPayload> STREAM_CODEC = RecipeData.STREAM_CODEC.apply(ByteBufCodecs.list()).map(InductionRecipeSyncPayload::new, InductionRecipeSyncPayload::recipes);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public record RecipeData(Item input, Item output, int cookingTime, float experience) {
        public static final StreamCodec<RegistryFriendlyByteBuf, RecipeData> STREAM_CODEC =
                StreamCodec.composite(ByteBufCodecs.registry(BuiltInRegistries.ITEM.key()),
                        RecipeData::input,
                        ByteBufCodecs.registry(BuiltInRegistries.ITEM.key()),
                        RecipeData::output,
                        ByteBufCodecs.VAR_INT,
                        RecipeData::cookingTime,
                        ByteBufCodecs.FLOAT,
                        RecipeData::experience,
                        RecipeData::new
                );
    }
}