package net.mehvahdjukaar.jepp.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import static net.mehvahdjukaar.jepp.PaintingCategory.LOCALIZED_NAME;

public class PaintingInfoRecipeCategory extends EmiRecipeCategory {

    public PaintingInfoRecipeCategory(ResourceLocation id) {
        super(id, EmiStack.of(Items.PAINTING));
    }

    @Override
    public Component getName() {
        return LOCALIZED_NAME;
    }

}
