package net.guwy.sticky_foundations.mechanics.air_density;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.compat.create.SFCreateAirDensityCompat;
import net.guwy.sticky_foundations.compat.mekanism.SFMekanismAirDensityCompat;
import net.guwy.sticky_foundations.index.SFConfigs;
import net.guwy.sticky_foundations.utils.NumberUtils;
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

    /** Unused
     * */
    public static class AtmosphericDensity {

        public static double GetAtmosphericDensity(int yLevel){

            return Math.min(1, Math.max(0, ( 1 - ((yLevel - 128) / 128.0) )));
        }


    }



    /** This one is mainly operated on the client side
     * both for performance and simplicity sake
     */
    public static class BreathingAltitudes {

        static final Supplier<Double> OXYGEN_CAPACITY = SFConfigs.Client.AIR_CAPACITY;
        static double OXYGEN_SUPPLY = OXYGEN_CAPACITY.get();

        private static final Supplier<Double> IDLE_CONSUMPTION = SFConfigs.Client.AIR_CONSUMPTION_IDLE;
        private static final Supplier<Double> RUNNING_CONSUMPTION = SFConfigs.Client.AIR_EXTRA_CONSUMPTION_RUNNING;
        private static final Supplier<Integer> ALT_MAX_REGEN = SFConfigs.Client.AIR_MAX_ALT_FOR_MAX_REGEN;
        private static final Supplier<Integer> ALT_MAX_RUNNING = SFConfigs.Client.AIR_MAX_ALT_FOR_RUNNING_REGEN;
        private static final Supplier<Integer> ALT_MAX_IDLE = SFConfigs.Client.AIR_MAX_ALT_FOR_IDLE_REGEN;
        private static final Supplier<Integer> ALT_NO_REGEN = SFConfigs.Client.AIR_ALT_FOR_NO_REGEN;

        private static final Supplier<Boolean> SHOULD_OXYGEN_SYSTEM_WORK = SFConfigs.Client.ACTIVATE_BREATHABLE_AIR_MECHANICS;



        public static void OnClientPlayerTickEvent(TickEvent.PlayerTickEvent event){
            Player player = Minecraft.getInstance().player;

            // Check if the player is the one who runs the game
            if(player == event.player){

                if(SHOULD_OXYGEN_SYSTEM_WORK.get()
                        && player.getLevel().dimension() == Level.OVERWORLD){

                    // Don't work when unnecessary or when underwater
                    if(player.getY() >= ALT_MAX_RUNNING.get() || OXYGEN_SUPPLY < OXYGEN_CAPACITY.get()
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
                    OXYGEN_SUPPLY = OXYGEN_CAPACITY.get();
                }
            }
        }



        private static void AddOxygen(double val){
            OXYGEN_SUPPLY = Math.max(0, Math.min( OXYGEN_CAPACITY.get(), OXYGEN_SUPPLY + val));
        }



        private static double GetOxygenRegen(double yLevel){
            double val = 0;

            int altMaxRegen = ALT_MAX_REGEN.get(),
            altMaxRunning = Math.max(altMaxRegen, ALT_MAX_RUNNING.get()),
            altMaxIdle = Math.max(altMaxRunning, ALT_MAX_IDLE.get()),
            altMax = Math.max(altMaxIdle, ALT_NO_REGEN.get());
            double idleConsumption = IDLE_CONSUMPTION.get(),
            runningConsumption = idleConsumption + RUNNING_CONSUMPTION.get();

            if (yLevel < altMaxRegen){
                val = runningConsumption * 4;
            }

            else if (yLevel < altMaxRunning){
                val = NumberUtils.map.mapDouble(yLevel, altMaxRegen, altMaxRunning, 10, runningConsumption);
            }

            else if (yLevel < altMaxIdle){
                val = NumberUtils.map.mapDouble(yLevel, altMaxRunning, altMaxIdle, runningConsumption, idleConsumption);
            }

            else if (yLevel < altMax){
                val = NumberUtils.map.mapDouble(yLevel, altMaxIdle, altMax, idleConsumption, 0);
            }

            else {
                val = 0;
            }

            return val;
        }



        /** This one gets the player to handle oxygen masks from other mods as well
         */
        private static double GetOxygenConsumption(Player player){
            double consumption;

            // Base consumption
            consumption = IDLE_CONSUMPTION.get();

            // Extra consumption when running
            if(player.isSprinting()) consumption += RUNNING_CONSUMPTION.get();

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
