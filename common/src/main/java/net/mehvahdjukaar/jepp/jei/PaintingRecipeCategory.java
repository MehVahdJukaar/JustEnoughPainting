package net.mehvahdjukaar.jepp.jei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.core.NonNullList;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.List;

public class PaintingRecipeCategory implements IRecipeCategory<PaintingInfo> {


    public static final int RECIPE_WIDTH = 160;
    public static final int RECIPE_HEIGHT = 125;

    private final IDrawable background;
    private final IDrawable icon;

    private final Component localizedName;

    public PaintingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(RECIPE_WIDTH, RECIPE_HEIGHT);

        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.PAINTING));

        this.localizedName = Component.translatable("jepp.category.paintings_info");
    }

    @Override
    public RecipeType<PaintingInfo> getRecipeType() {
        return JeppJeiPlugin.PAINTING_INFO_TYPE;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PaintingInfo paintingInfoRecipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 0,0)
                .addIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.PAINTING));
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void draw(PaintingInfo recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {

        int yPos = RECIPE_HEIGHT - 8;

        Font font = Minecraft.getInstance().font;

        MutableComponent name = (MutableComponent) recipe.getName();
        name.setStyle(Style.EMPTY.withBold(true));//.withColor(TextColor.fromRgb(color)));
        float centerX = RECIPE_WIDTH / 2f - font.width(name) / 2f;
        font.draw(poseStack, Language.getInstance().getVisualOrder(name), centerX, 0, 0xFF000000);

        FormattedText descriptionLine = recipe.getDescription();
        centerX = RECIPE_WIDTH / 2f - font.width(descriptionLine) / 2f;
        font.draw(poseStack, Language.getInstance().getVisualOrder(descriptionLine), centerX, yPos, 0xFF000000);


        //render painting
        PaintingVariant motive = recipe.getPainting();

        float spacing = 12;
        float maxWidth = RECIPE_WIDTH;
        float maxHeight = RECIPE_HEIGHT - spacing - 12;

        poseStack.translate(maxWidth / 2f, spacing + maxHeight / 2f, 0);

        int pWidth = motive.getWidth();
        int pHeight = motive.getHeight();

        float ratio = pHeight / (float) pWidth;
        float screenRatio = maxHeight / maxWidth;

        float scale = ratio < screenRatio ? maxWidth / pWidth : maxHeight / pHeight;


        poseStack.scale(scale, scale, scale);

        ResourceLocation texture = Minecraft.getInstance().getPaintingTextures().getBackSprite().atlas().location();
        PaintingTextureManager paintingtexturemanager = Minecraft.getInstance().getPaintingTextures();
        TextureAtlasSprite sprite = paintingtexturemanager.get(motive);

        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);


        poseStack.translate(-pWidth / 2f, -pHeight / 2f, 0);
        GuiComponent.blit(poseStack, 0, 0, 0, pWidth, pHeight, sprite);

        RenderSystem.applyModelViewMatrix();


    }
}
