package net.mehvahdjukaar.jepp.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.mehvahdjukaar.jepp.Jepp;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

@EmiEntrypoint
public class EMIPlugin implements EmiPlugin {

    public static final ResourceLocation EFFECTS_INFO_CATEGORY = Jepp.res("paintings");
    public static final PaintingInfoRecipeCategory CATEGORY = new PaintingInfoRecipeCategory(EFFECTS_INFO_CATEGORY);


    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);

        for (Holder<PaintingVariant> painting : Jepp.getPaintings()) {
            registry.addRecipe(new EmiPaintingInfoRecipe(painting));
        }

    }


}
