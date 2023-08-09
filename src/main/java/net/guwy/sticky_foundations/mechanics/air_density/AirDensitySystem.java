package net.guwy.sticky_foundations.mechanics.air_density;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.compat.create.SFCreateAirDensityCompat;
import net.guwy.sticky_foundations.compat.mekanism.SFMekanismAirDensityCompat;
import net.guwy.sticky_foundations.index.SFConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;

import java.util.function.Supplier;

public class AirDensitySystem {
    /** Atmospheric density and breathing altitudes are unlinked for gameplay purposes
     * Allowing stuff like planes to reach much higher altitudes that is realistic
     * While making the vanilla mountain tops tall enough players without proper gear struggle
     *
     * Density is 1 when below 128 blocks
     * It lowers by 0.2 every 128 blocks above the limit
     * Reaching 0.8 at 256 blocks
     * Reaching 0.6 at 384 blocks
     *
     * above 128 blocks will make you run out of breath when you run
     * above 256 blocks you can't breathe when you don't run
     */



    public static class AtmosphericDensity {

        public static double GetAtmosphericDensity(int yLevel){

            return Math.min(1, Math.max(0, ( 1 - ((yLevel - 128) / 128.0) )));
        }


    }



    /** This one is mainly operated on the client side
     * both for performance and simplicity sake
     */
    public static class BreathingAltitudes {

        static final double OXYGEN_CAPACITY = 100;
        static double OXYGEN_SUPPLY = OXYGEN_CAPACITY;

        /** Will use a static value for 64 and below
         * Will use the same curve between 128 and 256 for anything above as well
         */
        private static final double OXYGEN_REGENERATION_AT_64_AND_BELOW = 10;
        private static final double OXYGEN_REGENERATION_AT_128 = 5;
        private static final double OXYGEN_REGENERATION_AT_256 = 2;

        private static final double OXYGEN_CONSUMPTION_BY_DEFAULT = 2;
        private static final double EXTRA_OXYGEN_CONSUMED_WHEN_RUNNING = 3;

        private static final double POINT_0 = 128 + (128 * OXYGEN_REGENERATION_AT_128 / (OXYGEN_REGENERATION_AT_128 - OXYGEN_REGENERATION_AT_256)); // The height at which the oxygen regen becomes 0
        private static final Supplier<Boolean> SHOULD_OXYGEN_SYSTEM_WORK = SFConfigs.Client.ACTIVATE_BREATHABLE_AIR_MECHANICS;



        public static void OnClientPlayerTickEvent(TickEvent.PlayerTickEvent event){
            Player player = Minecraft.getInstance().player;

            // Check if the player is the one who runs the game
            if(player == event.player){

                if(SHOULD_OXYGEN_SYSTEM_WORK.get()
                        && player.getLevel().dimension() == Level.OVERWORLD){

                    // Don't work when unnecessary or when underwater
                    if(player.getY() >= 128 || OXYGEN_SUPPLY < 100
                            && !player.isUnderWater()){

                        // Handle oxygen consumption
                        AddOxygen(-GetOxygenConsumption(player));

                        // Handle oxygen regen
                        AddOxygen(GetOxygenRegen(player.getY()));

                        // Handle onscreen messages
                        // Onscreen messages are handled with the oxygen consumption

                    }
                } else {

                    // Fill the oxygen supply if the player isn't in overwold or the config is false
                    OXYGEN_SUPPLY = OXYGEN_CAPACITY;
                }
            }
        }



        private static void AddOxygen(double val){
            OXYGEN_SUPPLY = Math.max(0, Math.min( OXYGEN_CAPACITY, OXYGEN_SUPPLY + val));
        }



        private static double GetOxygenRegen(double yLevel){
            double val = 0;

            if(yLevel < 64){

                val = 10;
            } else if(yLevel < 128){

                val = OXYGEN_REGENERATION_AT_64_AND_BELOW - ((yLevel - 64) / 64 * (OXYGEN_REGENERATION_AT_64_AND_BELOW - OXYGEN_REGENERATION_AT_128));
            } else if(yLevel < POINT_0){

                val = OXYGEN_REGENERATION_AT_128 - ((yLevel - 128) / (POINT_0 - 128) * OXYGEN_REGENERATION_AT_128);
            } else {

                val = 0;
            }

            return val;
        }



        /** This one gets the player to handle oxygen masks from other mods as well
         */
        private static double GetOxygenConsumption(Player player){
            double consumption;

            // Base consumption
            consumption = OXYGEN_CONSUMPTION_BY_DEFAULT;

            // Extra consumption when running
            if(player.isSprinting()) consumption += EXTRA_OXYGEN_CONSUMED_WHEN_RUNNING;



            // Consumption negation with vanilla features
            if(player.hasEffect(MobEffects.WATER_BREATHING)
            || player.hasEffect(MobEffects.CONDUIT_POWER)) {

                consumption = 0;
            }

            // Consumption negation with built in api supporting helmets
            else if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof IBreathingMaskAtHighAltitudes){

                IBreathingMaskAtHighAltitudes breathingMask = (IBreathingMaskAtHighAltitudes) player.getItemBySlot(EquipmentSlot.HEAD).getItem();

                // If the value is true consumption = 0, else just keep as is
                if(breathingMask.SupplyAirAtHighAltitudesClientCheck(player)) consumption = 0;
            }

            // If all fails check for any mod compatibility
            else {

                // Consumption negation with create mod
                if(StickyFoundations.isCreateLoaded()){

                    if(SFCreateAirDensityCompat.shouldNegateAirConsumption(player)) consumption = 0;
                }

                // Consumption negation with mekanism mod
                if(StickyFoundations.isMekanismLoaded()){

                    if(SFMekanismAirDensityCompat.shouldNegateAirConsumption(player)) consumption = 0;
                }
            }





            // Handle onscreen messages
            if(consumption > 0) HandleOnScreenMessages(player);



            return consumption;
        }



        private static void HandleOnScreenMessages(Player player){
            double playerY = player.getY();

            if(playerY > 256){
                SFMessagesOnDisplay.addNewMessage(Component.translatable("onscreen_message.sticky_foundations.thin_air.no_air").getString());
            }
            else if (playerY > 128 && player.isSprinting()){
                SFMessagesOnDisplay.addNewMessage(Component.translatable("onscreen_message.sticky_foundations.thin_air.low_air").getString());
            }
        }
    }
}
