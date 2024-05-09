package net.guwy.sticky_foundations.events.server_events;

import net.guwy.sticky_foundations.egg.redstone_stick.dragon.DragonModCompat;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

public class LivingEntityUseItemEventHandler {
    public static void init(LivingEntityUseItemEvent.Start event) {
        if(DragonModCompat.isModsLoaded()) DragonModCompat.startUsingItem(event);
    }
}
