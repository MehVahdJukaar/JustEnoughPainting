package net.mehvahdjukaar.jepp.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

/**
 * Author: MehVahdJukaar
 */
@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation ID = Jepp.res("jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }


    public static final RecipeType<PaintingInfo> PAINTING_INFO_TYPE =
            RecipeType.create(Jepp.MOD_ID, "painting", PaintingInfo.class);

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
        registration.addRecipeCatalyst(new ItemStack(Items.PAINTING), PAINTING_INFO_TYPE);
    }


}
