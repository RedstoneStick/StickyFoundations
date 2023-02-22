package net.guwy.sticky_foundations.events;

import net.guwy.sticky_foundations.StickyFoundations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

public class ModClientEvents {
    @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {


        //@SubscribeEvent
        //public static void tooltipEvent(ItemTooltipEvent event) {
        //}



    }



    @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents{

    }
}
