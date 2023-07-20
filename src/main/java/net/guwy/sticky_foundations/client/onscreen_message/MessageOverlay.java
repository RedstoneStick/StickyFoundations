package net.guwy.sticky_foundations.client.onscreen_message;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class MessageOverlay {
    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("minecraft:textures/block/gray_concrete.png");
    public static final ResourceLocation BACKGROUND_BORDER_TEXTURE = new ResourceLocation("minecraft:textures/block/light_gray_concrete.png");
    private static final int X = 10, Y = 10, OFFSET = 3, TEXT_HEIGHT = 8, SPACE_BETWEEN_MESSAGES = 4, BORDER_THICKNESS = 2;



    public static final IGuiOverlay MESSAGE_OVERLAY = (((gui, poseStack, partialTick, screenWidth, screenHeight) -> {

        // Only display the borders when there are messages to display
        if(SFMessagesOnDisplay.getMessageCount() > 0){

            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);


            // Background Pos and Size
            int XPos = X - OFFSET;
            int YPos = Y - OFFSET;
            int Width = SFMessagesOnDisplay.getLongestMessageWidth() + (OFFSET * 2);
            int Height = (((TEXT_HEIGHT + SPACE_BETWEEN_MESSAGES) * SFMessagesOnDisplay.getMessageCount()) - SPACE_BETWEEN_MESSAGES) + (OFFSET * 2);


            // Render Background Borders
            RenderSystem.setShaderColor(1, 1, 1, 0.8f);
            RenderSystem.setShaderTexture(0, BACKGROUND_BORDER_TEXTURE);

            GuiComponent.blit(poseStack, XPos - BORDER_THICKNESS, YPos - BORDER_THICKNESS, 0, 0
                    , Width + (BORDER_THICKNESS * 2), Height + (BORDER_THICKNESS * 2)
                    , Width + (BORDER_THICKNESS * 2), Height + (BORDER_THICKNESS * 2));


            // Render Background
            RenderSystem.setShaderColor(1, 1, 1, 0.8f);
            RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);

            GuiComponent.blit(poseStack, XPos, YPos, 0, 0, Width, Height, Width, Height);


            // Render Text
            renderMessages(poseStack);


            RenderSystem.disableBlend();
        }
    }));



    private static void renderMessages(PoseStack poseStack){
        int messageCount = SFMessagesOnDisplay.getMessageCount();

        for(int index = 0; index < messageCount; index++){


            /** "5" is x offset, "10" is y offset
             * "(textHeight + spaceBetweenMessages) * index" is the Y position of each message
             */
            int XPos = X;
            int YPos = Y + ((TEXT_HEIGHT + SPACE_BETWEEN_MESSAGES) * index);


            /** Color Data here
             * message starts to fade away when the remaining duration is shorter than 20 ticks
             */
            int R = 255, G = 83, B = 27;
            int A = (int) Math.min(255,  SFMessagesOnDisplay.MessageTimers.get(index) *  (255.0 / 20));

            //Draw the message
            Minecraft.getInstance().font.draw(poseStack, SFMessagesOnDisplay.Messages.get(index), XPos, YPos, FastColor.ARGB32.color(A, R, G, B));
        }
    }
}
