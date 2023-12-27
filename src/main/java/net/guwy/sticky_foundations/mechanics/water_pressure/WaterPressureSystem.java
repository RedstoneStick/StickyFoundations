package net.guwy.sticky_foundations.mechanics.water_pressure;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.compat.mekanism.SFMekanismWaterPressureCompat;
import net.guwy.sticky_foundations.content.network_packets.WaterPressureDamageRequestC2SPacket;
import net.guwy.sticky_foundations.index.SFConfigs;
import net.guwy.sticky_foundations.index.SFNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;

import java.util.function.Supplier;

public class WaterPressureSystem {
    /** In ocean biomes the depth is taken relative to sea level (y 62)
     * In other biomes its relative to water blocks above your head
     * Lower than 20m is too much depth for the player without water breathing
     */
    private static final Supplier<Integer> SEA_LEVEL = SFConfigs.Client.WATER_PRESSURE_SEA_LEVEL,
            OVERPRESSURE_DEPTH = SFConfigs.Client.WATER_PRESSURE_OVERPRESSURE_DEPTH,
            BASE_DAMAGE = SFConfigs.Client.WATER_PRESSURE_BASE_DAMAGE;

    private static final Supplier<Boolean> SHOULD_WATER_PRESSURE_SYSTEM_WORK = SFConfigs.Client.ACTIVATE_WATER_PRESSURE_MECHANICS;



    public static void OnClientPlayerTickEvent(TickEvent.PlayerTickEvent event){
        Player player = Minecraft.getInstance().player;

        // Check if the player is the one who runs the game
        if(player == event.player){

            if (SHOULD_WATER_PRESSURE_SYSTEM_WORK.get()) {

                if (IsUnderTooMuchPressure(event.player)){

                    if (!isPlayerPressureProof(event.player)){

                        // Handle onscreen message
                        SFMessagesOnDisplay.addNewMessage(Component.translatable("onscreen_message.sticky_foundations.overpressure").getString());

                        // Handle damaging of player
                        if(event.player.tickCount % 20 == 0) SFNetworking.sendToServer(new WaterPressureDamageRequestC2SPacket(getDamageFromDepth(player)));
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
            return BASE_DAMAGE.get() + ((SEA_LEVEL.get() - OVERPRESSURE_DEPTH.get() - player.blockPosition().getY()) / 5 * BASE_DAMAGE.get());
        }

        // Handle for Non Ocean Biomes
        else {

            return BASE_DAMAGE.get();
        }
    }



    public static boolean IsUnderTooMuchPressure(Player player){
        Level level = player.getLevel();
        boolean isTooMuchPressure = false;

        // Is Underwater?
        if(player.isUnderWater()){

            // Handle for Ocean Biomes
            if(level.getBiome(player.getOnPos()).is(BiomeTags.IS_OCEAN)){
                if (player.getY() < SEA_LEVEL.get() - OVERPRESSURE_DEPTH.get()){

                    isTooMuchPressure = true;
                }
            }

            // Handle for Non Ocean Biomes
            else if(level.getBlockState(player.getOnPos().offset(0, OVERPRESSURE_DEPTH.get(), 0)).getBlock() == Blocks.WATER){

                isTooMuchPressure = true;
            }


            // Do a pass to check if the player's head is in a bubble column
            BlockState blockState = player.getLevel().getBlockState(player.getOnPos().offset(0, 1, 0));

            if(blockState.getBlock() instanceof BubbleColumnBlock){
                isTooMuchPressure = false;
            }
        }

        return isTooMuchPressure;
    }



    private static boolean isPlayerPressureProof(Player player){
        boolean mekanismCompat = false;
        if(StickyFoundations.isMekanismLoaded()) mekanismCompat = SFMekanismWaterPressureCompat.shouldNegateWaterPressure(player);

        return player.hasEffect(MobEffects.WATER_BREATHING)
                || player.hasEffect(MobEffects.CONDUIT_POWER)
                || mekanismCompat;
    }
}
