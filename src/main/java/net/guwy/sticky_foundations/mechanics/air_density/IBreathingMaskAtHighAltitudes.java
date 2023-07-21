package net.guwy.sticky_foundations.mechanics.air_density;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;


/** Only works with helmets */
public interface IBreathingMaskAtHighAltitudes {

    /** Called every tick on the client side
     *
     * if you implement like an air supply, you can handle the decreasing in here
     */
    default boolean SupplyAirAtHighAltitudesClientCheck(Player clientPlayer){
        return true;
    }
}
