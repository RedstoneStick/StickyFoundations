package net.guwy.sticky_foundations.events.client_events;

import net.guwy.sticky_foundations.content.network_packets.VisorWipeC2SPacket;
import net.guwy.sticky_foundations.index.SFNetworking;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PlayerInteractRightClickEmptyHandler {

    public static void init(PlayerInteractEvent.RightClickEmpty event){
        Player player = event.getEntity();
        Level level = player.getLevel();

        SFNetworking.sendToServer(new VisorWipeC2SPacket());
    }
}
