package net.coolsimulations.ForgottenEngineers.network;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;

public class FENetwork {

    private static final int PROTOCOL_VERSION = 1;

    public static final SimpleChannel CHANNEL = ChannelBuilder.named(
            Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "main"
            )).networkProtocolVersion(PROTOCOL_VERSION)
            .clientAcceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION))
            .serverAcceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION)).simpleChannel();

    private static int packetId = 0;

    public static void register() {

        CHANNEL.messageBuilder(InductionRecipeSyncPacket.class, packetId++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(InductionRecipeSyncPacket::encode)
                .decoder(InductionRecipeSyncPacket::decode)
                .consumerMainThread(InductionRecipeSyncPacket::handle)
                .add();

        CHANNEL.messageBuilder(CompressorRecipeSyncPacket.class, packetId++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder((packet, buffer) -> CompressorRecipeSyncPacket.STREAM_CODEC.encode(buffer, packet))
                .decoder(CompressorRecipeSyncPacket.STREAM_CODEC::decode)
                .consumerMainThread(CompressorRecipeSyncPacket::handle)
                .add();
    }

    public static void sendInductionToPlayer(ServerPlayer player, InductionRecipeSyncPacket packet) {
        CHANNEL.send(packet, PacketDistributor.PLAYER.with(player));
    }

    public static void sendCompressorToPlayer(ServerPlayer player, CompressorRecipeSyncPacket packet) {
        CHANNEL.send(packet, PacketDistributor.PLAYER.with(player));
    }

    public static void sendInductionToAll(InductionRecipeSyncPacket packet) {
        CHANNEL.send(packet, PacketDistributor.ALL.noArg());
    }

    public static void sendCompressorToAll(CompressorRecipeSyncPacket packet) {
        CHANNEL.send(packet, PacketDistributor.ALL.noArg());
    }
}