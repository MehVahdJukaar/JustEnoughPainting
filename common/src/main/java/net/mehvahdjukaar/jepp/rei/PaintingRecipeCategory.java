package net.mehvahdjukaar.jepp.rei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaintingRecipeCategory implements DisplayCategory<PaintingInfoDisplay> {

    public static final int RECIPE_WIDTH = 160;
    public static final int RECIPE_HEIGHT = 125;
    private final MutableComponent localizedName;

    public PaintingRecipeCategory() {
        this.localizedName = Component.translatable("jepp.category.paintings_info");

    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Items.PAINTING);
    }


    @Override
    public int getDisplayWidth(PaintingInfoDisplay display) {
        return RECIPE_WIDTH;
    }

    @Override
    public int getDisplayHeight() {
        return RECIPE_HEIGHT;
    }

    @Override
    public Component getTitle() {
        return localizedName;
    }

    @Override
    public CategoryIdentifier<? extends PaintingInfoDisplay> getCategoryIdentifier() {
        return JeppReiPlugin.PAINTING_INFO_TYPE;
    }

    @Override
    public List<Widget> setupDisplay(PaintingInfoDisplay display, Rectangle bounds) {
        final List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(new PaintingWidget(bounds, display.getPainting()));

        /*
        Point origin = bounds.getLocation();

        Rectangle bgBounds = FarmersDelightModREI.centeredIntoRecipeBase(origin, 116, 56);
        widgets.add(Widgets.createTexturedWidget(GUI_TEXTURE, bgBounds, 29, 16));

        List<EntryIngredient> ingredientEntries = display.getIngredientEntries();
        if (ingredientEntries != null) {
            for (int i = 0; i < ingredientEntries.size(); i++) {
                Point slotLoc = new Point(bgBounds.x + 1 + i % 3 * 18, bgBounds.y + 1 + (i / 3) * 18);
                widgets.add(Widgets.createSlot(slotLoc).entries(ingredientEntries.get(i)).markInput().disableBackground());
            }
        }

        widgets.add(Widgets.createSlot(new Point(bgBounds.x + 63, bgBounds.y + 39))
                .entries(display.getContainerOutput()).markInput().disableBackground());

        widgets.add(Widgets.createSlot(new Point(bgBounds.x + 95, bgBounds.y + 12))
                .entries(display.getOutputEntries().get(0)).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(bgBounds.x + 95, bgBounds.y + 39))
                .entries(display.getOutputEntries().get(0)).markOutput().disableBackground());

        widgets.add(Widgets.createTexturedWidget(GUI_TEXTURE,
                new Rectangle(bgBounds.x + 18, bgBounds.y + 39, 17, 15), 176, 0));
        Arrow cookArrow = Widgets.createArrow(new Point(bgBounds.x + 61, bgBounds.y + 10))
                .animationDurationTicks(display.getCookTime());
        widgets.add(cookArrow);
        widgets.add(Widgets.createLabel(new Point(
                                cookArrow.getBounds().x + cookArrow.getBounds().width / 2, cookArrow.getBounds().y - 8),
                        Text.literal(display.getCookTime() + " t"))
                .noShadow().centered().tooltip(Text.literal("Ticks"))
                .color(Formatting.DARK_GRAY.getColorValue(), Formatting.GRAY.getColorValue()));


         */
        return widgets;

    }

    private static class PaintingWidget extends WidgetWithBounds {

        private final Rectangle bounds;
        private final PaintingVariant painting;

        public PaintingWidget(Rectangle bounds, PaintingVariant paintingVariant) {
            this.bounds = new Rectangle(Objects.requireNonNull(bounds));
            this.painting = paintingVariant;
        }

        @Override
        public void render(PoseStack poseStack, int i, int j, float f) {
            //render painting

            float spacing = 12;
            float maxWidth = RECIPE_WIDTH;
            float maxHeight = RECIPE_HEIGHT - spacing - 12;

            poseStack.translate(bounds.getX() + maxWidth / 2f, bounds.getY() + spacing + maxHeight / 2f, 0);

            int pWidth = painting.getWidth();
            int pHeight = painting.getHeight();

            float ratio = pHeight / (float) pWidth;
            float screenRatio = maxHeight / maxWidth;

            float scale = ratio < screenRatio ? maxWidth / pWidth : maxHeight / pHeight;


            poseStack.scale(scale, scale, scale);

            ResourceLocation texture = Minecraft.getInstance().getPaintingTextures().getBackSprite().atlas().location();
            PaintingTextureManager paintingtexturemanager = Minecraft.getInstance().getPaintingTextures();
            TextureAtlasSprite sprite = paintingtexturemanager.get(painting);

            RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, texture);


            poseStack.translate(-pWidth / 2f, -pHeight / 2f, 0);
            GuiComponent.blit(poseStack, 0, 0, 0, pWidth, pHeight, sprite);

            RenderSystem.applyModelViewMatrix();
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return List.of();
        }

        @Override
        public boolean isDragging() {
            return false;
        }

        @Override
        public void setDragging(boolean isDragging) {
        }

        @Nullable
        @Override
        public GuiEventListener getFocused() {
            return null;
        }

        @Override
        public void setFocused(@Nullable GuiEventListener focused) {
        }

        @Override
        public Rectangle getBounds() {
            return bounds;
        }
    }


}