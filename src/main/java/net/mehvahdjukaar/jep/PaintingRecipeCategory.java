package net.mehvahdjukaar.jep;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class PaintingRecipeCategory implements IRecipeCategory<PaintingInfoRecipe> {

    public static final ResourceLocation UID = Jepp.res("paintings");

    public static final int recipeWidth = 160;
    public static final int recipeHeight = 125;

    private final IDrawable background;
    private final IDrawable icon;

    private final Component localizedName;

    public PaintingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(recipeWidth, recipeHeight);

        this.icon = guiHelper.createDrawableIngredient(new ItemStack(Items.PAINTING));

        this.localizedName = new TranslatableComponent("jepp.category.paintings_info");
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends PaintingInfoRecipe> getRecipeClass() {
        return PaintingInfoRecipe.class;
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
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setIngredients(PaintingInfoRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, List.of(new ItemStack(Items.PAINTING)));
    }

    @Override
    public void draw(PaintingInfoRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {

        int yPos = recipeHeight - 8;

        Font font = Minecraft.getInstance().font;

        BaseComponent name = recipe.getName();
        name.setStyle(Style.EMPTY.withBold(true));//.withColor(TextColor.fromRgb(color)));
        float centerX = recipeWidth / 2f - font.width(name) / 2f;
        font.draw(matrixStack, Language.getInstance().getVisualOrder(name), centerX, 0, 0xFF000000);

        FormattedText descriptionLine = recipe.getDescription();
        centerX = recipeWidth / 2f - font.width(descriptionLine) / 2f;
        font.draw(matrixStack, Language.getInstance().getVisualOrder(descriptionLine), centerX, yPos, 0xFF000000);


        //render painting
        Motive motive = recipe.getMotive();

        float spacing = 12;
        float maxWidth = recipeWidth;
        float maxHeight = recipeHeight - spacing - 12;

        matrixStack.translate(maxWidth / 2f, spacing + maxHeight / 2f, 0);

        int pWidth = motive.getWidth();
        int pHeight = motive.getHeight();

        float ratio = pHeight / (float) pWidth;
        float screenRatio = maxHeight / maxWidth;

        float scale = ratio < screenRatio ? maxWidth / (float) pWidth : maxHeight / pHeight;


        matrixStack.scale(scale, scale, scale);

        ResourceLocation texture = Minecraft.getInstance().getPaintingTextures().getBackSprite().atlas().location();
        PaintingTextureManager paintingtexturemanager = Minecraft.getInstance().getPaintingTextures();
        TextureAtlasSprite sprite = paintingtexturemanager.get(motive);

        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);


        matrixStack.translate(-pWidth / 2f, -pHeight / 2f, 0);
        GuiComponent.blit(matrixStack, 0, 0, 0, pWidth, pHeight, sprite);

        RenderSystem.applyModelViewMatrix();


    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, PaintingInfoRecipe recipe, IIngredients ingredients) {

    }
}
