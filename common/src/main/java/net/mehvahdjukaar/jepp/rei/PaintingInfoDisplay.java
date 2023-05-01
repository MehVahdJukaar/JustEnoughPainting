package net.mehvahdjukaar.jepp.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.mehvahdjukaar.jepp.PaintingInfo;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.List;

public class PaintingInfoDisplay extends PaintingInfo implements Display {

    public PaintingInfoDisplay(PaintingVariant painting) {
        super(painting);
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of();
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return JeppReiPlugin.PAINTING_INFO_TYPE;
    }
}
