package net.guwy.sticky_foundations.events.client_events;

import net.guwy.sticky_foundations.client.onscreen_message.MessageOverlay;
import net.guwy.sticky_foundations.mechanics.air_density.OxygenIconOverlay;
import net.guwy.sticky_foundations.mechanics.air_density.TunnelVisionOverlay;
import net.guwy.sticky_foundations.mechanics.visor.VisorOuterGunkOverlay;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;

public class RegisterGuiOverlaysEventHandler {
    public static void init(final RegisterGuiOverlaysEvent event){

        // Hud Icons
        event.registerAboveAll("sf_oxygen_bar", OxygenIconOverlay.OXYGEN_OVERLAY);


        // Internal Screen Covers
        event.registerAboveAll("sf_tunnel_vision", TunnelVisionOverlay.TUNNEL_VISION_OVERLAY);


        // Messages
        event.registerAboveAll("sf_messages", MessageOverlay.MESSAGE_OVERLAY);


        //Helmet Outer
        event.registerBelowAll("sf_visor_outer_gunk", VisorOuterGunkOverlay.OUTER_GUNK_OVERLAY);
    }
}
