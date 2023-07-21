package net.guwy.sticky_foundations.events.server_events;

import net.guwy.sticky_foundations.mechanics.visor.VisorWearTick;
import net.minecraftforge.event.TickEvent;

public class PlayerServerTickEventsOrganizer {

    public static void init(TickEvent.PlayerTickEvent event){
        int counter = event.player.tickCount;
        
        //Every Second
        // if((counter % 20) == 0){
        // }
    }
}
