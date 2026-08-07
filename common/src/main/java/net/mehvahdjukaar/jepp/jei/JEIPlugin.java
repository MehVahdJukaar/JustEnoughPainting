package net.mehvahdjukaar.jepp.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.item.Items;

import java.util.List;

/**
 * Author: MehVahdJukaar
 */
@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final Identifier ID = Jepp.res("jei_plugin");

    @Override
    public Identifier getPluginUid() {
        return ID;
    }


    public static final IRecipeType<PaintingInfo> PAINTING_INFO_TYPE =
            IRecipeType.create(Jepp.MOD_ID, "painting", PaintingInfo.class);

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new PaintingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        for (Holder<PaintingVariant> painting : Jepp.getPaintings()) {
            PaintingInfo recipe = new PaintingInfo(painting);
            registry.addRecipes(PAINTING_INFO_TYPE, List.of(recipe));
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(PAINTING_INFO_TYPE, Items.PAINTING);
    }


}
