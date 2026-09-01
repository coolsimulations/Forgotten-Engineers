package net.coolsimulations.ForgottenEngineers.network;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record CompressorRecipeSyncPayload(List<RecipeData> recipes) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<CompressorRecipeSyncPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID,"compressor_recipe_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CompressorRecipeSyncPayload> STREAM_CODEC = StreamCodec.composite(RecipeData.STREAM_CODEC.apply(ByteBufCodecs.list()), CompressorRecipeSyncPayload::recipes, CompressorRecipeSyncPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public record RecipeData(ItemStack input, ItemStack output) {
        public static final StreamCodec<RegistryFriendlyByteBuf, RecipeData> STREAM_CODEC =
                StreamCodec.composite(
                        ItemStack.STREAM_CODEC,
                        RecipeData::input,
                        ItemStack.STREAM_CODEC,
                        RecipeData::output,
                        RecipeData::new
                );
    }
}