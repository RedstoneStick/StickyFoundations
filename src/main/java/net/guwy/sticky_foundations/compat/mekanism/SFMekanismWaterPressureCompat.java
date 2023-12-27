package net.guwy.sticky_foundations.compat.mekanism;

import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.item.gear.ItemScubaMask;
import mekanism.common.item.gear.ItemScubaTank;
import mekanism.common.registries.MekanismModules;
import mekanism.common.util.ItemDataUtils;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class SFMekanismWaterPressureCompat {

    public static final String MEKANISM_SCUBA_TANK_ACTIVE_TAG_KEY = "running";
    private static final int SCUBA_TANK_LOW_AIR_THRESHOLD = 1200;   //  1 min



    public static boolean shouldNegateWaterPressure(Player player){
        boolean negate = false;

        ItemStack helmetItem = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);


        // Check if the player is wearing a valid Scuba Suit
        if(chestItem.getItem() instanceof ItemScubaTank
                && helmetItem.getItem() instanceof ItemScubaMask){


            // Check if the Scuba Tank is active
            if(ItemDataUtils.getBoolean(chestItem, MEKANISM_SCUBA_TANK_ACTIVE_TAG_KEY)){

                // Access capabilites to get the remaining air in the tank
                GasStack stored = GasStack.EMPTY;
                Optional<IGasHandler> capability = chestItem.getCapability(Capabilities.GAS_HANDLER).resolve();
                if (capability.isPresent()) {
                    IGasHandler gasHandlerItem = (IGasHandler)capability.get();
                    if (gasHandlerItem.getTanks() > 0) {
                        stored = gasHandlerItem.getChemicalInTank(0);
                    }
                }

                int remainingAir = (int) stored.getAmount();


                // Check if there's air left
                if(remainingAir > 0){

                    negate = true;

                    // Don't need to access server as scuba tank already has constant oxygen consumption

                    // Sends a notification to the player if the Scuba Tank air is low
                    if(remainingAir <= SCUBA_TANK_LOW_AIR_THRESHOLD){
                        SFMessagesOnDisplay.addNewMessage(Component.translatable("onscreen_message.sticky_foundations.thin_air.equipment.low_air").getString());
                    }
                }
            }
        }



        // Check for mekasuit
        else if(helmetItem.getItem() instanceof ItemMekaSuitArmor){
            AtomicBoolean val = new AtomicBoolean(false);
            helmetItem.getCapability(ForgeCapabilities.ENERGY).ifPresent(energy -> {
                if(energy.getEnergyStored() > 0){

                    // Check for breathing module
                    IModuleContainerItem moduleContainerItem = (IModuleContainerItem) helmetItem.getItem();
                    val.set(moduleContainerItem.isModuleEnabled(helmetItem, MekanismModules.ELECTROLYTIC_BREATHING_UNIT));
                }
            });
            negate = val.get();
        }


        return negate;
    }
}
