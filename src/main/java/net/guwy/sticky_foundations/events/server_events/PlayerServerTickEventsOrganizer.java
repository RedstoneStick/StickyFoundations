package net.guwy.sticky_foundations.events.server_events;

import net.guwy.sticky_foundations.egg.redstone_stick.dragon.DragonModCompat;
import net.guwy.sticky_foundations.mechanics.visor.VisorWearTick;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;

public class PlayerServerTickEventsOrganizer {

    public static void init(TickEvent.PlayerTickEvent event){
        int counter = event.player.tickCount;
        Player player = event.player;

        VisorWearTick.process(player);

        // every second offset to 7th tick (random offset)
        if(player.tickCount % 20 == 7){
            if(DragonModCompat.isModsLoaded()) DragonModCompat.serverTick(player);
        }
    }
}
