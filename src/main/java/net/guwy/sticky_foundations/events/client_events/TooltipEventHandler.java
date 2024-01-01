package net.guwy.sticky_foundations.events.client_events;

import net.guwy.sticky_foundations.index.SFTags;
import net.guwy.sticky_foundations.mechanics.visor.VisorGunk;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipEventHandler {
    public static void init(ItemTooltipEvent event){
        ItemStack itemStack = event.getItemStack();

        // Visor Gunk Tooltip
        if(itemStack.is(SFTags.Items.VISORS_THAT_GET_DIRTY)){
            event.getToolTip().addAll(1, VisorGunk.VisorGunkTooltip(itemStack, event.getToolTip(), 1));
        }
    }
}
