package net.coolsimulations.ForgottenEngineers.network;

import net.coolsimulations.ForgottenEngineers.client.ForgottenEngineersClientEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.List;

public record InductionRecipeSyncPacket(List<RecipeData> recipes) {

    public record RecipeData(Identifier input, Identifier output, int cookingTime, float experience) { }

    public static void encode(InductionRecipeSyncPacket packet, FriendlyByteBuf buffer) {
        buffer.writeVarInt(packet.recipes().size());

        for (RecipeData recipe : packet.recipes()) {
            buffer.writeIdentifier(recipe.input());
            buffer.writeIdentifier(recipe.output());
            buffer.writeVarInt(recipe.cookingTime());
            buffer.writeFloat(recipe.experience());
        }
    }

    public static InductionRecipeSyncPacket decode(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();

        List<RecipeData> recipes = new java.util.ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Identifier input = buffer.readIdentifier();
            Identifier output = buffer.readIdentifier();
            int cookingTime = buffer.readVarInt();
            float experience = buffer.readFloat();

            recipes.add(new RecipeData(input, output, cookingTime, experience));
        }

        return new InductionRecipeSyncPacket(recipes);
    }

    public static void handle(InductionRecipeSyncPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            if (context.isClientSide())
                ForgottenEngineersClientEvents.handleInductionRecipes(packet);
        });

        context.setPacketHandled(true);
    }
}