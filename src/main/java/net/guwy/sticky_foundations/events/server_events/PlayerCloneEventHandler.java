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

            if(Users.checkUUID(oldPlayer, Users.REDSTONE_STICK)
                    && !newPlayer.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)){

                for(int i = 0; i < oldPlayer.getInventory().getContainerSize(); i++){
                    ItemStack oldStack = oldPlayer.getInventory().getItem(i);
                    if(oldStack.getItem() == SpecialItems.DRAGON_CORE.get()){
                        newPlayer.getInventory().setItem(i, oldStack);
                        oldPlayer.getInventory().setItem(i, ItemStack.EMPTY);
                    }
                }
            }
        }
    }
}
