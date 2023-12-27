package net.guwy.sticky_foundations.mechanics.air_density;

import com.mojang.blaze3d.systems.RenderSystem;
import net.guwy.sticky_foundations.StickyFoundations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class TunnelVisionOverlay {
    private static final ResourceLocation TEXTURE = new ResourceLocation(StickyFoundations.MOD_ID, "textures/overlay/tunnel_vision.png");
    private static final int TEXTURE_WIDTH = 1920;
    private static final int TEXTURE_HEIGHT = 1080;

    private static final int MAX_ZOOM = 1500;



    public static final IGuiOverlay TUNNEL_VISION_OVERLAY = (((gui, poseStack, partialTick, screenWidth, screenHeight) -> {

        // Only display when the oxygen isn't full
        if(AirDensitySystem.BreathingAltitudes.OXYGEN_SUPPLY < AirDensitySystem.BreathingAltitudes.OXYGEN_CAPACITY.get()){

            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.setShaderTexture(0, TEXTURE);



            // Render tunnel vision

            double oxygenPercentage = AirDensitySystem.BreathingAltitudes.OXYGEN_SUPPLY / AirDensitySystem.BreathingAltitudes.OXYGEN_CAPACITY.get();
            int zoom = (int) (MAX_ZOOM * oxygenPercentage);

            int xPos = 0 - zoom;
            int yPos = 0 - zoom;

            int width = screenWidth + (zoom * 2);
            int height = screenHeight + (zoom * 2);

            GuiComponent.blit(poseStack, xPos, yPos
                    , 0, 0
                    , width, height
                    , width, height);


            RenderSystem.disableBlend();
        }
    }));
}
