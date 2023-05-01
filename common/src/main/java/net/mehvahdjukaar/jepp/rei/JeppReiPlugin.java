package net.mehvahdjukaar.jepp.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.decoration.PaintingVariant;

//@REIPluginClient
public class JeppReiPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<PaintingInfoDisplay> PAINTING_INFO_TYPE = CategoryIdentifier.of(Jepp.res("painting"));

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new PaintingRecipeCategory());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        for (PaintingVariant painting : Registry.PAINTING_VARIANT) {
            PaintingInfo recipe = new PaintingInfoDisplay(painting);
            registry.add(recipe);
        }
    }

}
