package net.guwy.sticky_foundations.events;

import com.google.common.eventbus.DeadEvent;
import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.events.server_events.*;
import net.guwy.sticky_foundations.mechanics.water_pressure.WaterPressureSystem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.ChunkDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;


    public class ModServerEvents {

        @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID)
        public static class ForgeEvents {

            @SubscribeEvent
            public static void ServerPlayerTick(TickEvent.PlayerTickEvent event){
                if(event.side == LogicalSide.SERVER){
                    if(event.phase == TickEvent.Phase.END){

                        PlayerServerTickEventsOrganizer.init(event);
                    }
                }
            }

            @SubscribeEvent
            public static void InteractEntity(PlayerInteractEvent.EntityInteract event){
                if(event.getSide() == LogicalSide.SERVER){
                    PlayerInteractEventHandler.EntityInteract(event);
                }
            }

            @SubscribeEvent
            public static void onPlayerCloned(PlayerEvent.Clone event) {
                PlayerCloneEventHandler.init(event);
            }

            @SubscribeEvent
            public static void onEntityJoin(EntityJoinLevelEvent event) {
                EntityJoinLevelEventHandler.init(event);
            }

            @SubscribeEvent
            public static void onLivingDeath(LivingDeathEvent event) {
                LivingDeathEventHandler.init(event);
            }

            @SubscribeEvent
            public static void onLivingUseItemStart(LivingEntityUseItemEvent.Start event) {
                LivingEntityUseItemEventHandler.init(event);
            }

        }

        @Mod.EventBusSubscriber(modid = StickyFoundations.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ModEventBusEvents {

        }
    }

