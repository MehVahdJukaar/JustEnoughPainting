package net.mehvahdjukaar.jepp;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.decoration.PaintingVariant;

public abstract class PaintingCategory {

    public static final int RECIPE_WIDTH = 160;
    public static final int RECIPE_HEIGHT = 125;
    protected final MutableComponent localizedName;

    protected PaintingCategory() {
        this.localizedName = Component.translatable("jepp.category.paintings_info");
    }

    protected static void renderPainting(PaintingVariant motive, GuiGraphics graphics, int width, int height) {
        //render painting
        float spacing = 12;
        float maxWidth = width;
        float maxHeight = height - spacing - 12;


        int pWidth = motive.getWidth();
        int pHeight = motive.getHeight();

        float ratio = pHeight / (float) pWidth;
        float screenRatio = maxHeight / maxWidth;

        float scale = ratio < screenRatio ? maxWidth / pWidth : maxHeight / pHeight;


        graphics.pose().scale(scale, scale, scale);

        PaintingTextureManager paintingtexturemanager = Minecraft.getInstance().getPaintingTextures();
        TextureAtlasSprite sprite = paintingtexturemanager.get(motive);

        graphics.pose().translate(-pWidth / 2f, -pHeight / 2f, 0);
        graphics.blit(0, 0, 0, pWidth, pHeight, sprite);

        RenderSystem.applyModelViewMatrix();
    }
}
