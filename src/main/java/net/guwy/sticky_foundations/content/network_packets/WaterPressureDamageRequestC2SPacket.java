package net.guwy.sticky_foundations.content.network_packets;

import net.guwy.sticky_foundations.index.SFDamageSources;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WaterPressureDamageRequestC2SPacket {
    int damage;

    public WaterPressureDamageRequestC2SPacket(int damage) {
        this.damage = damage;
    }

    public WaterPressureDamageRequestC2SPacket(FriendlyByteBuf buf) {
        this.damage = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.damage);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            // Randomize damage type for funni death message
            if(Math.random() < 0.95){
                player.hurt(SFDamageSources.WATER_OVERPRESSURE, this.damage);
            } else {
                player.hurt(SFDamageSources.WATER_OVERPRESSURE_EASTER_EGG, this.damage);
            }

        });
        return true;
    }
}
