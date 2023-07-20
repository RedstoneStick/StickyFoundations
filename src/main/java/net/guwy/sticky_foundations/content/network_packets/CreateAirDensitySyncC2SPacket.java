package net.guwy.sticky_foundations.content.network_packets;

import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.guwy.sticky_foundations.compat.create.SFCreateAirDensityCompat.CREATE_BACKTANK_AIR_TAG_KEY;

public class CreateAirDensitySyncC2SPacket {
    /**
     * Key Binding Transmitter to the server for keybinding handling
     *
     * Should probably be reformatted to be like the weapon key binding
     */

    public CreateAirDensitySyncC2SPacket() {

    }

    public CreateAirDensitySyncC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);

            int remainingAir = ItemTagUtils.getInt(chestItem, CREATE_BACKTANK_AIR_TAG_KEY);
            ItemTagUtils.putInt(chestItem, CREATE_BACKTANK_AIR_TAG_KEY, remainingAir - 1);

        });
        return true;
    }
}
