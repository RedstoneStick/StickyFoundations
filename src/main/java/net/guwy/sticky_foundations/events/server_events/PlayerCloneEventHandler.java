package net.guwy.sticky_foundations.events.server_events;

import net.guwy.sticky_foundations.egg.SpecialItems;
import net.guwy.sticky_foundations.egg.Users;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerCloneEventHandler {
    public static void init(PlayerEvent.Clone event) {
        if(event.isWasDeath()){
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();
        }
    }
}
