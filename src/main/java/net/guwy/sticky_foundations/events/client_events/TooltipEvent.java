package net.guwy.sticky_foundations.events.client_events;

import net.guwy.sticky_foundations.mechanics.hazard.GetRadiationVal;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipEvent {
    public static void HazardSystemTooltips(ItemTooltipEvent event){
        //if(event.getItemStack().is(ModTags.Items.PLANKS)){
        //    event.getToolTip().add(Component.literal("hello").withStyle(ChatFormatting.AQUA));
        //}

        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();

        double radiationVal = GetRadiationVal.getVal(itemStack);

        if(radiationVal != 0){
            Component tooltip = Component.literal(
                    Double.toString(radiationVal)).append("RAD/s")
                    .withStyle(ChatFormatting.GOLD);

            event.getToolTip().add(tooltip);
        }

    }
}
