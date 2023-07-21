package net.guwy.sticky_foundations.index;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.content.network_packets.CreateAirDensitySyncC2SPacket;
import net.guwy.sticky_foundations.content.network_packets.WaterPressureDamageRequestC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class SFNetworking {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(StickyFoundations.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;



         net.messageBuilder(CreateAirDensitySyncC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                 .decoder(CreateAirDensitySyncC2SPacket::new)
                 .encoder(CreateAirDensitySyncC2SPacket::toBytes)
                 .consumerMainThread(CreateAirDensitySyncC2SPacket::handle)
                 .add();

        net.messageBuilder(WaterPressureDamageRequestC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WaterPressureDamageRequestC2SPacket::new)
                .encoder(WaterPressureDamageRequestC2SPacket::toBytes)
                .consumerMainThread(WaterPressureDamageRequestC2SPacket::handle)
                .add();



    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
