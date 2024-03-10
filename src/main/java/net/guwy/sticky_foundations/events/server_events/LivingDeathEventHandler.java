package net.guwy.sticky_foundations.events.server_events;

import net.guwy.sticky_foundations.egg.SpecialItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LivingDeathEventHandler {
    public static void init(LivingDeathEvent event) {

        if(event.getEntity() instanceof Player player){
            for(int i = 0; i < player.getInventory().getContainerSize(); i++){
                if(player.getInventory().getItem(i).getItem() == SpecialItems.DRAGON_CORE.get())
                    player.getInventory().setItem(i, ItemStack.EMPTY);
            }
        }
    }
}
