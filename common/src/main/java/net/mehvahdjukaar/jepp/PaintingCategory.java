package net.mehvahdjukaar.jepp;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

public final class PaintingCategory {

    public static final int RECIPE_WIDTH = 160;
    public static final int RECIPE_HEIGHT = 125;
    public static final MutableComponent LOCALIZED_NAME = Component.translatable("jepp.category.paintings_info");

    public static void renderPainting(PaintingVariant painting, GuiGraphicsExtractor graphics, int centerX, int centerY, int maxWidth, int maxHeight) {
        float spacing = 12;
        float boxWidth = maxWidth;
        float boxHeight = maxHeight - spacing - 12;

        int pWidth = painting.width() * 16;
        int pHeight = painting.height() * 16;

        float ratio = pHeight / (float) pWidth;
        float b = boxHeight / boxWidth;

        float scale = (ratio < b) ? boxWidth / pWidth : boxHeight / pHeight;

        int width = Math.round(pWidth * scale);
        int height = Math.round(pHeight * scale);

        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasManager()
                .getAtlasOrThrow(AtlasIds.PAINTINGS).getSprite(painting.assetId());

        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, centerX - width / 2, centerY - height / 2, width, height);
    }
}
