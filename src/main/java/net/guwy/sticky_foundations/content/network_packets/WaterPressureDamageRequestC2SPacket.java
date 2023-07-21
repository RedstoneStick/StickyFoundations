package net.guwy.sticky_foundations.content.network_packets;

import net.guwy.sticky_foundations.index.SFDamageSources;
import net.guwy.sticky_foundations.mechanics.water_pressure.WaterPressureSystem;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.guwy.sticky_foundations.compat.create.SFCreateAirDensityCompat.CREATE_BACKTANK_AIR_TAG_KEY;

public class WaterPressureDamageRequestC2SPacket {
    /**
     * Key Binding Transmitter to the server for keybinding handling
     *
     * Should probably be reformatted to be like the weapon key binding
     */

    public WaterPressureDamageRequestC2SPacket() {

    }

    public WaterPressureDamageRequestC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            // Randomize damage type for funni death message
            if(Math.random() < 0.95){
                player.hurt(SFDamageSources.WATER_OVERPRESSURE, WaterPressureSystem.getDamageFromDepth(player));
            } else {
                player.hurt(SFDamageSources.WATER_OVERPRESSURE_EASTER_EGG, WaterPressureSystem.getDamageFromDepth(player));
            }

        });
        return true;
    }
}
