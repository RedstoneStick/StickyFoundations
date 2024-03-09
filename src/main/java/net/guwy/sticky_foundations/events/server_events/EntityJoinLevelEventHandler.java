package net.guwy.sticky_foundations.events.server_events;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.egg.SpecialItems;
import net.guwy.sticky_foundations.egg.Users;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

public class EntityJoinLevelEventHandler {
    public static void init(EntityJoinLevelEvent event) {

        if(event.getEntity() instanceof Player player && Users.checkUUID(player, Users.REDSTONE_STICK) && StickyFoundations.isPehkuiLoaded()){
            boolean hasCore = false;
            for(int i = 0; i < player.getInventory().getContainerSize(); i++){
                if(player.getInventory().getItem(i).getItem() == SpecialItems.DRAGON_CORE.get()) hasCore = true;
            }
            if(!hasCore) player.getInventory().add(new ItemStack(SpecialItems.DRAGON_CORE.get()));
        }
    }
}
