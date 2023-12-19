package net.guwy.sticky_foundations.events.client_events;

import com.mojang.blaze3d.platform.InputConstants;
import net.guwy.sticky_foundations.content.network_packets.CrawlC2SPacket;
import net.guwy.sticky_foundations.index.SFKeyBindings;
import net.guwy.sticky_foundations.index.SFNetworking;
import net.minecraftforge.client.event.InputEvent;

public class OnKeyInputHandler {
    public static void init(InputEvent.Key event){

        //if(event.getKey() == SFKeyBindings.CRAWL_KEY.getKey().getValue()){
        //    if(event.getAction() == InputConstants.PRESS)
        //        SFNetworking.sendToServer(new CrawlC2SPacket(true));
        //    if(event.getAction() == InputConstants.RELEASE)
        //        SFNetworking.sendToServer(new CrawlC2SPacket(false));
        //}
    }
}
