package net.guwy.sticky_foundations.compat.create;

import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.content.network_packets.CreateAirDensitySyncC2SPacket;
import net.guwy.sticky_foundations.index.SFNetworking;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SFCreateAirDensityCompat {
    public static final String CREATE_BACKTANK_AIR_TAG_KEY = "Air";
    private static final int BACKTANK_LOW_AIR_THRESHOLD = 60;   // 1 min

    public static boolean shouldNegateAirConsumption(Player player){
        boolean negate = false;

        ItemStack helmetItem = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);

        // Check if the player is wearing a valid diving suit
        if(chestItem.getItem() instanceof BacktankItem
        && helmetItem.getItem() instanceof DivingHelmetItem){

            // Check if the backtank has air
            int remainingAir = ItemTagUtils.getInt(chestItem, CREATE_BACKTANK_AIR_TAG_KEY);
            if(remainingAir > 0){

                negate = true;

                // Removes 1 air every second due to create handling it every second as well
                if(player.tickCount % 20 == 0){

                    // Calls a network packet to tell the server to handle it cuz we're on the client due to... reasons :D
                    SFNetworking.sendToServer(new CreateAirDensitySyncC2SPacket());
                }

                // Sends a notification to the player if the backtank air is low
                if(remainingAir <= BACKTANK_LOW_AIR_THRESHOLD){
                    SFMessagesOnDisplay.addNewMessage(Component.translatable("onscreen_message.sticky_foundations.thin_air.equipment.low_air").getString());
                }
            }
        }


        return negate;
    }
}
