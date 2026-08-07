package net.mehvahdjukaar.jepp.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Optional;

public class PaintingInfoDisplay extends PaintingInfo implements Display {

    public PaintingInfoDisplay(Holder<PaintingVariant> painting) {
        super(painting);
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(EntryIngredients.of(Items.PAINTING));
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(EntryIngredients.of(Items.PAINTING));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REIPlugin.PAINTING_INFO_TYPE;
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return Optional.empty();
    }

    //built fresh on the client every world load, never perssted
    @Override
    public DisplaySerializer<? extends Display> getSerializer() {
        return null;
    }
}
