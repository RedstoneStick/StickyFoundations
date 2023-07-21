package net.guwy.sticky_foundations.client.view_bobbing;

import net.guwy.sticky_foundations.index.SFConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;

import java.util.function.Supplier;

/** Multiply the get values by the amount of pixels you want the bobbing to be pushed by
 */
public class ViewBobbing {

    private static final Supplier<Double> BOBBING_MULTIPLIER_CONFIG = SFConfigs.Client.HUD_SWAY_MULTIPLIER;



    public static class MouseBobbing {

        private static final double MAX_BOBBING = 10;

        private static double mouseXVel = 0;
        private static double mouseYVel = 0;

        private static double mouseXOld = 0;
        private static double mouseYOld = 0;

        public static void ClientTickAccessor(TickEvent.PlayerTickEvent event){

            // Push X out of the 0 point depending on mouse movement
            mouseXVel -= (Minecraft.getInstance().mouseHandler.xpos() - mouseXOld) / 30;
            mouseXOld = Minecraft.getInstance().mouseHandler.xpos();

            // Push Y out of the 0 point depending on mouse movement
            mouseYVel -= (Minecraft.getInstance().mouseHandler.ypos() - mouseYOld) / 30;
            mouseYOld = Minecraft.getInstance().mouseHandler.ypos();


            // Pull X Towards the 0 point
            if(mouseXVel > 0){
                mouseXVel = Math.min(MAX_BOBBING, Math.max( -MAX_BOBBING, mouseXVel - (mouseXVel / 3) - 0.01));
            } else if (mouseXVel < 0){
                mouseXVel = Math.min(MAX_BOBBING, Math.max( -MAX_BOBBING, mouseXVel + (-mouseXVel / 3) + 0.01));
            }

            // Pull Y Towards the 0 point
            if(mouseYVel > 0){
                mouseYVel = Math.min(MAX_BOBBING, Math.max( -MAX_BOBBING, mouseYVel - (mouseYVel / 3) - 0.01));
            } else if (mouseYVel < 0){
                mouseYVel = Math.min(MAX_BOBBING, Math.max( -MAX_BOBBING, mouseYVel + (-mouseYVel / 3) + 0.01));
            }


        }



        public static double GetMouseX(){

            if(Minecraft.getInstance().options.bobView().get()){
                return mouseXVel / 10 * BOBBING_MULTIPLIER_CONFIG.get();
            } else {
                return 0;
            }
        }

        public static double GetMouseY(){

            if(Minecraft.getInstance().options.bobView().get()){
                return mouseYVel / 10 * BOBBING_MULTIPLIER_CONFIG.get();
            } else {
                return 0;
            }
        }
    }



    public static double GetCombinedX(){

        if(Minecraft.getInstance().options.bobView().get()){
            return MouseBobbing.mouseXVel / 10 * BOBBING_MULTIPLIER_CONFIG.get();
        } else {
            return 0;
        }
    }

    public static double GetCombinedY(){

        if(Minecraft.getInstance().options.bobView().get()){
            return MouseBobbing.mouseYVel / 10 * BOBBING_MULTIPLIER_CONFIG.get();
        } else {
            return 0;
        }
    }
}
