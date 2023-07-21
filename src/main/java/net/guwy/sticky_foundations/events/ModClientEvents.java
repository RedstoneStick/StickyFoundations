package net.guwy.sticky_foundations.events;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.client.view_bobbing.ViewBobbing;
import net.guwy.sticky_foundations.events.client_events.RegisterGuiOverlaysEventHandler;
import net.guwy.sticky_foundations.mechanics.air_density.AirDensitySystem;
import net.guwy.sticky_foundations.mechanics.water_pressure.WaterPressureSystem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

public class ModClientEvents {
    @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void ClientPlayerTick(TickEvent.PlayerTickEvent event){
            if(event.side == LogicalSide.CLIENT){
                if(event.phase == TickEvent.Phase.END){

                    SFMessagesOnDisplay.onClientTick();
                    WaterPressureSystem.OnClientPlayerTickEvent(event);
                    AirDensitySystem.BreathingAltitudes.OnClientPlayerTickEvent(event);

                    ViewBobbing.MouseBobbing.ClientTickAccessor(event);
                }
            }
        }

    }



    @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents{

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            RegisterGuiOverlaysEventHandler.init(event);
        }

    }
}
