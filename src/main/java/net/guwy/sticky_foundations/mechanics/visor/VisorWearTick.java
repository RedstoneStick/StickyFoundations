package net.guwy.sticky_foundations.mechanics.visor;

import net.guwy.sticky_foundations.index.SFTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class VisorWearTick {
    private static final double HANDLE_EVERY_X_AMOUNT_OF_SECONDS = 0.5;   // used for adjusting overall work rate (timings below are for 1.0) (Use with tick call amount)

    private static final double SAND_ACCUMULATION_RATE = 0.0017 * HANDLE_EVERY_X_AMOUNT_OF_SECONDS;    // 0 to 1 in 10 min (0.0017)
    private static final double DIRT_ACCUMULATION_RATE = 0.0017 * HANDLE_EVERY_X_AMOUNT_OF_SECONDS;    // 0 to 1 in 10 min (0.0017)
    private static final double MUD_ACCUMULATION_RATE = 0.003 * HANDLE_EVERY_X_AMOUNT_OF_SECONDS;      // 0 to 1 in 5 min (0.003)
    private static final double SOOT_ACCUMULATION_RATE = 0.017 * HANDLE_EVERY_X_AMOUNT_OF_SECONDS;    // 0 to 1 in 1 min (0.017)

    private static final double WATER_CLEANSING_RATE_ABOVE_T = -0.2 * HANDLE_EVERY_X_AMOUNT_OF_SECONDS;       // (-0.1)
    private static final double WATER_CLEANSING_RATE_BELOW_T = -0.017 * HANDLE_EVERY_X_AMOUNT_OF_SECONDS;     // (-0.017)
    private static final double WATER_THRESHOLD = 0.201; // Used for water's effective accumulation and cleansing (0.2)

    /** Processing of Screen Gunk **/
    public static void process(Player player){

        // Process every x amount of seconds
        if(player.tickCount % (20 * HANDLE_EVERY_X_AMOUNT_OF_SECONDS) == 0){

            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.HEAD);
            Level level = player.getLevel();

            if(itemStack.is(SFTags.Items.VISORS_THAT_GET_DIRTY)){

                // Get the block right under the player
                BlockPos pos = new BlockPos(player.getX(), player.getY() - 0.1, player.getZ());
                BlockState onBlock = level.getBlockState(pos);

                // Visor will get wet up to a certain point in rain
                boolean underRain = player.getLevel().isRainingAt(player.getOnPos().offset(0, 1, 0));
                if(underRain){
                    double water = VisorGunk.getOuterWater(itemStack);
                    if(water < 0.40) VisorGunk.addOuterWater(itemStack, 0.01);
                }



                // When the player is underwater handle cleaning
                if(player.isUnderWater()){
                    VisorGunk.setOuterWater(itemStack, 1);

                    // Water Cleans faster if there is a large quantity of gunk
                    // and slower if there is less
                    if(VisorGunk.getOuterSand(itemStack) > WATER_THRESHOLD) VisorGunk.addOuterSand(itemStack, WATER_CLEANSING_RATE_ABOVE_T);
                    else VisorGunk.addOuterSand(itemStack, WATER_CLEANSING_RATE_BELOW_T);

                    if(VisorGunk.getOuterDirt(itemStack) > WATER_THRESHOLD) VisorGunk.addOuterDirt(itemStack, WATER_CLEANSING_RATE_ABOVE_T);
                    else VisorGunk.addOuterDirt(itemStack, WATER_CLEANSING_RATE_BELOW_T);

                    if(VisorGunk.getOuterMud(itemStack) > WATER_THRESHOLD) VisorGunk.addOuterMud(itemStack, WATER_CLEANSING_RATE_ABOVE_T);
                    else VisorGunk.addOuterMud(itemStack, WATER_CLEANSING_RATE_BELOW_T);

                    if(VisorGunk.getOuterSoot(itemStack) > WATER_THRESHOLD) VisorGunk.addOuterSoot(itemStack, WATER_CLEANSING_RATE_ABOVE_T);
                    else VisorGunk.addOuterSoot(itemStack, WATER_CLEANSING_RATE_BELOW_T);

                }
                // If the player is out of water handle accumulation
                else {

                    // Gunk accumulates faster if the visor is wet
                    double wetMultiplier = Math.max(1, VisorGunk.getOuterWater(itemStack) * 10);

                    // Increases the corresponding gunk type depending on the block the player is standing on
                    if(onBlock.is(SFTags.Blocks.MASK_SAND)) VisorGunk.addOuterSand(itemStack, SAND_ACCUMULATION_RATE * wetMultiplier);
                    if(onBlock.is(SFTags.Blocks.MASK_DIRT)) VisorGunk.addOuterDirt(itemStack, DIRT_ACCUMULATION_RATE * wetMultiplier);
                    if(onBlock.is(SFTags.Blocks.MASK_MUD)) VisorGunk.addOuterMud(itemStack, MUD_ACCUMULATION_RATE * wetMultiplier);
                    if(onBlock.is(SFTags.Blocks.MASK_SOOT)) VisorGunk.addOuterSoot(itemStack, SOOT_ACCUMULATION_RATE * wetMultiplier);


                    // Water drips away if it isn't raining
                    if(!underRain){

                        // decreases the water accumulation on screen
                        // faster if there is more water slower if there is less
                        if(VisorGunk.getOuterWater(itemStack) > WATER_THRESHOLD) VisorGunk.addOuterWater(itemStack, WATER_CLEANSING_RATE_ABOVE_T);
                        else VisorGunk.addOuterWater(itemStack, WATER_CLEANSING_RATE_BELOW_T);
                    }
                }

                // Debug
                // player.sendSystemMessage(Component.literal("sand: " + IVisorItem.getOuterSand(itemStack)));
                // player.sendSystemMessage(Component.literal("dirt: " + IVisorItem.getOuterDirt(itemStack)));
                // player.sendSystemMessage(Component.literal("mud: " + IVisorItem.getOuterMud(itemStack)));
                // player.sendSystemMessage(Component.literal("sooth: " + IVisorItem.getOuterSoot(itemStack)));
                // player.sendSystemMessage(Component.literal("water: " + IVisorItem.getOuterWater(itemStack)));
                // player.sendSystemMessage(Component.literal("block: " + onBlock.getBlock().getName().getString()));
                // player.sendSystemMessage(Component.literal("-----"));
            }
        }
    }
}
