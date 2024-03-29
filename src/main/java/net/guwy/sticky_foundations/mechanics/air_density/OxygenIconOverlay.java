package net.guwy.sticky_foundations.mechanics.air_density;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.client.onscreen_message.SFMessagesOnDisplay;
import net.guwy.sticky_foundations.client.view_bobbing.ViewBobbing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class OxygenIconOverlay {
    public static final ResourceLocation TEXTURE = new ResourceLocation(StickyFoundations.MOD_ID, "textures/overlay/hud_elements.png");



    public static final IGuiOverlay OXYGEN_OVERLAY = (((gui, poseStack, partialTick, screenWidth, screenHeight) -> {

        // Only display when the oxygen isn't full
        if(AirDensitySystem.BreathingAltitudes.OXYGEN_SUPPLY < AirDensitySystem.BreathingAltitudes.OXYGEN_CAPACITY.get()){

            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            RenderSystem.setShaderColor(1, 1, 1, 0.8f);
            RenderSystem.setShaderTexture(0, TEXTURE);


            int bobX = (int) (ViewBobbing.MouseBobbing.GetMouseX() * 10);
            int bobY = (int) (ViewBobbing.MouseBobbing.GetMouseY() * 10);


            // Render Background
            GuiComponent.blit(poseStack, screenWidth / 2 - 25 + bobX, screenHeight / 2 + 24 + bobY
                    , 0, 0
                    , 49, 9
                    , 128, 128);


            // Render Bar
            int barWidth = (int) (31 * (AirDensitySystem.BreathingAltitudes.OXYGEN_SUPPLY / AirDensitySystem.BreathingAltitudes.OXYGEN_CAPACITY.get()));
            GuiComponent.blit(poseStack, screenWidth / 2 - 8 + bobX, screenHeight / 2 + 26 + bobY
                    , 0, 10
                    , barWidth, 5
                    , 128, 128);


            RenderSystem.disableBlend();
        }
    }));
}
