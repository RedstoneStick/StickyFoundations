package net.guwy.sticky_foundations.events;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.client.view_bobbing.ViewBobbing;
import net.guwy.sticky_foundations.egg.redstone_stick.dragon.fangs.DragonFangsRenderer;
import net.guwy.sticky_foundations.events.client_events.*;
import net.guwy.sticky_foundations.index.SFEntityTypes;
import net.guwy.sticky_foundations.index.SFMinerals;
import net.guwy.sticky_foundations.mechanics.air_density.AirDensitySystem;
import net.guwy.sticky_foundations.mechanics.water_pressure.WaterPressureSystem;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModClientEvents {
    @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void ClientPlayerTick(TickEvent.PlayerTickEvent event){
            if(event.side == LogicalSide.CLIENT && event.phase == TickEvent.Phase.END){
                PlayerClientTickEventsOrganizer.init(event);
            }
        }

        @SubscribeEvent
        public static void onPlayerInteract(PlayerInteractEvent.RightClickEmpty event) {
            if(event.getSide() == LogicalSide.CLIENT) {
                PlayerInteractRightClickEmptyHandler.init(event);
            }
        }

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            OnKeyInputHandler.init(event);
        }

        @SubscribeEvent
        public static void onTooltipDisplay(ItemTooltipEvent event){
            TooltipEventHandler.init(event);
        }

    }



    @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents{

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            RegisterGuiOverlaysEventHandler.init(event);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            OnKeyRegisterHandler.init(event);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(SFEntityTypes.DRAGON_FANGS.get(), DragonFangsRenderer::new);
        }

    }
}
