package net.mehvahdjukaar.jepp.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.TextWidget;
import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.mehvahdjukaar.jepp.PaintingCategory.*;

public class EmiPaintingInfoRecipe extends PaintingInfo implements EmiRecipe {

    private final ResourceLocation id;
    protected List<EmiIngredient> PAINTING = List.of(EmiStack.of(Items.PAINTING));

    protected EmiPaintingInfoRecipe(Holder<PaintingVariant> paintingVariant) {
        super(paintingVariant);
        this.id = paintingVariant.unwrapKey().get().location();
    }

    @Override
    public List<EmiIngredient> getCatalysts() {
        return PAINTING;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EMIPlugin.CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return PAINTING;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(EmiStack.of(Items.PAINTING));
    }

    @Override
    public int getDisplayWidth() {
        return RECIPE_WIDTH - 4;
    }

    @Override
    public int getDisplayHeight() {
        return RECIPE_HEIGHT;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {

        int centerX = widgets.getWidth() / 2;
        int centerY = widgets.getHeight() / 2;
        Bounds b = new Bounds(centerX - (RECIPE_WIDTH - 14) / 2,
                centerY - (RECIPE_HEIGHT - 14) / 2,
                RECIPE_WIDTH - 14, RECIPE_HEIGHT - 14);
        widgets.add(new Widget() {
            @Override
            public Bounds getBounds() {
                return b;
            }

            @Override
            public void render(GuiGraphics guiGraphics, int i, int i1, float v) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(centerX, centerY, 0);
                renderPainting(getPainting().value(), guiGraphics, RECIPE_WIDTH - 14, RECIPE_HEIGHT - 14);
                guiGraphics.pose().popPose();
            }
        });

        MutableComponent name = (MutableComponent) getName();
        name.setStyle(Style.EMPTY.withBold(true));
        Font font = Minecraft.getInstance().font;

        int nameX = (int) (centerX - font.width(name) / 2f);
        widgets.addText(name,nameX, 0, -1, true);
        Component description = getDescription();
        int name2X = (int) (centerX - font.width(description) / 2f);

        widgets.addText(description,name2X, widgets.getHeight() - 8 - 6, 0xFF404040, false);
    }

}
