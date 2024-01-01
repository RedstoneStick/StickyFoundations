package net.guwy.sticky_foundations.events.client_events;

import net.guwy.sticky_foundations.index.SFTags;
import net.guwy.sticky_foundations.mechanics.visor.VisorGunk;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

public class TooltipEventHandler {
    public static void init(ItemTooltipEvent event){
        ItemStack itemStack = event.getItemStack();

        // Visor Gunk Tooltip
        if(itemStack.is(SFTags.Items.VISORS_THAT_GET_DIRTY)){
            event.getToolTip().addAll(1, VisorGunk.VisorGunkTooltip(itemStack, event.getToolTip(), 1));
        }
    }
}
