package net.guwy.sticky_foundations.events.client_events;

import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.client.view_bobbing.ViewBobbing;
import net.guwy.sticky_foundations.mechanics.air_density.AirDensitySystem;
import net.guwy.sticky_foundations.mechanics.visor.VisorWearTick;
import net.guwy.sticky_foundations.mechanics.water_pressure.WaterPressureSystem;
import net.minecraftforge.event.TickEvent;

public class PlayerClientTickEventsOrganizer {

    public static void init(TickEvent.PlayerTickEvent event){
        int counter = event.player.tickCount;

        // Every tick
        SFMessagesOnDisplay.onClientTick();

        WaterPressureSystem.OnClientPlayerTickEvent(event);
        AirDensitySystem.BreathingAltitudes.OnClientPlayerTickEvent(event);

        ViewBobbing.MouseBobbing.ClientTickAccessor(event);



        //Every Second
        // if((counter % 20) == 0){
        // }
    }
}
