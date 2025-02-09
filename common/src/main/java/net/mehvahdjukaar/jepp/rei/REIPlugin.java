package net.mehvahdjukaar.jepp.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Items;

@REIPluginClient
public class REIPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<PaintingInfoDisplay> PAINTING_INFO_TYPE = CategoryIdentifier.of(Jepp.res("painting"));

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new PaintingRecipeCategory());
        registry.addWorkstations(PAINTING_INFO_TYPE, EntryStacks.of(Items.PAINTING));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        for (Holder<PaintingVariant> painting : Jepp.getPaintings()) {
            PaintingInfo recipe = new PaintingInfoDisplay(painting);
            registry.add(recipe);
        }
    }

}
