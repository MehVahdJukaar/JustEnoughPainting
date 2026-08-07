package net.mehvahdjukaar.jepp.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static net.mehvahdjukaar.jepp.PaintingCategory.*;

public class PaintingRecipeCategory implements IRecipeCategory<PaintingInfo> {

    private final IDrawable icon;

    public PaintingRecipeCategory(IGuiHelper guiHelper) {
        super();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.PAINTING));
    }

    @Override
    public IRecipeType<PaintingInfo> getRecipeType() {
        return JEIPlugin.PAINTING_INFO_TYPE;
    }

    @Override
    public Component getTitle() {
        return LOCALIZED_NAME;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public int getWidth() {
        return RECIPE_WIDTH;
    }

    @Override
    public int getHeight() {
        return RECIPE_HEIGHT;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PaintingInfo paintingInfoRecipe, IFocusGroup iFocusGroup) {
        builder.addInvisibleIngredients(RecipeIngredientRole.INPUT)
                .add(VanillaTypes.ITEM_STACK, new ItemStack(Items.PAINTING));
        builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT)
                .add(VanillaTypes.ITEM_STACK, new ItemStack(Items.PAINTING));
        builder.addInvisibleIngredients(RecipeIngredientRole.CRAFTING_STATION)
                .add(VanillaTypes.ITEM_STACK, new ItemStack(Items.PAINTING));
    }

    @Override
    public void draw(PaintingInfo recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        MutableComponent name = (MutableComponent) recipe.getName();
        name.setStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.WHITE));
        int centerX = (int) (RECIPE_WIDTH / 2f - font.width(name) / 2f);
        graphics.text(font, Language.getInstance().getVisualOrder(name), centerX, 0, 0xFFFFFFFF, false);

        FormattedText descriptionLine = recipe.getDescription();
        centerX = (int) (RECIPE_WIDTH / 2f - font.width(descriptionLine) / 2f);
        graphics.text(font, Language.getInstance().getVisualOrder(descriptionLine), centerX, RECIPE_HEIGHT - 8, 0xFF404040, false);

        renderPainting(recipe.getPainting().value(), graphics, RECIPE_WIDTH / 2, RECIPE_HEIGHT / 2, RECIPE_WIDTH, RECIPE_HEIGHT);
    }


}
