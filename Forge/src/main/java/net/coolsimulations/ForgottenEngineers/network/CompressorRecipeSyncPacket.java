package net.coolsimulations.ForgottenEngineers.network;

import net.coolsimulations.ForgottenEngineers.client.ForgottenEngineersClientEvents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.List;

public record CompressorRecipeSyncPacket(List<RecipeData> recipes) {

    public static final StreamCodec<RegistryFriendlyByteBuf, CompressorRecipeSyncPacket> STREAM_CODEC =
            StreamCodec.composite(
                    RecipeData.STREAM_CODEC.apply(ByteBufCodecs.list()),
                    CompressorRecipeSyncPacket::recipes,
                    CompressorRecipeSyncPacket::new
            );

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

    public static void handle(CompressorRecipeSyncPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            if (context.isClientSide())
                ForgottenEngineersClientEvents.handleCompressorRecipes(packet);
        });

        context.setPacketHandled(true);
    }
}
