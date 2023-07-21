package net.guwy.sticky_foundations.content.network_packets;

import net.guwy.sticky_foundations.mechanics.visor.IVisorItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class VisorWipeC2SPacket {

    public VisorWipeC2SPacket() {
    }

    public VisorWipeC2SPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            IVisorItem.WipeVisor(player);
        });
        return true;
    }
}
