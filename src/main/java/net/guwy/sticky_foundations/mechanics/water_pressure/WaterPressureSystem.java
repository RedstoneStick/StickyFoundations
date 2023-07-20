package net.guwy.sticky_foundations.mechanics.water_pressure;

import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.index.SFDamageSources;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;

public class WaterPressureSystem {
    /** In ocean biomes the depth is taken relative to sea level (y 62)
     * In other biomes its relative to water blocks above your head
     * Lower than 20m is too much depth for the player without water breathing
     */
    private static final int SEA_LEVEL = 62, OVERPRESSURE_DEPTH = 20;



    public static void OnClientPlayerTickEvent(TickEvent.PlayerTickEvent event){
        if (IsUnderTooMuchPressure(event.player)){
            if (!isPlayerPressureProof(event.player)){
                SFMessagesOnDisplay.addNewMessage(Component.translatable("onscreen_message.sticky_foundations.overpressure").getString());
            }
        }
    }

    public static void OnServerPlayerTickEvent(TickEvent.PlayerTickEvent event){
        Player player = event.player;

        // Operate Every Second
        if(player.tickCount % 20 == 0){
            if (IsUnderTooMuchPressure(event.player)){
                if (!isPlayerPressureProof(event.player)){

                    // Randomize damage type for funni death message
                    if(Math.random() < 0.95){
                        player.hurt(SFDamageSources.WATER_OVERPRESSURE, getDamageFromDepth(player));
                    } else {
                        player.hurt(SFDamageSources.WATER_OVERPRESSURE_EASTER_EGG, getDamageFromDepth(player));
                    }

                }
            }
        }
    }



    public static int getDamageFromDepth(Player player){
        Level level = player.getLevel();

        // Handle for Ocean Biomes
        if(level.getBiome(player.getOnPos()).is(BiomeTags.IS_OCEAN)){

            // Damage will increase by 2 for every 5 blocks you're under the depth limit
            return 2 + ((SEA_LEVEL - OVERPRESSURE_DEPTH - player.blockPosition().getY()) / 5 * 2);
        }

        // Handle for Non Ocean Biomes
        else {

            return 2;
        }
    }



    public static boolean IsUnderTooMuchPressure(Player player){
        Level level = player.getLevel();
        boolean isTooMuchPressure = false;

        // Is Underwater?
        if(player.isUnderWater()){

            // Handle for Ocean Biomes
            if(level.getBiome(player.getOnPos()).is(BiomeTags.IS_OCEAN)){
                if (player.getY() < SEA_LEVEL - OVERPRESSURE_DEPTH){

                    isTooMuchPressure = true;
                }
            }

            // Handle for Non Ocean Biomes
            else if(level.getBlockState(player.getOnPos().offset(0, OVERPRESSURE_DEPTH, 0)).getBlock() == Blocks.WATER){

                isTooMuchPressure = true;
            }
        }

        return isTooMuchPressure;
    }



    private static boolean isPlayerPressureProof(Player player){
        return player.hasEffect(MobEffects.WATER_BREATHING) || player.hasEffect(MobEffects.CONDUIT_POWER);
    }
}
