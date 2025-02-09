package net.mehvahdjukaar.jepp;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.apache.commons.lang3.StringUtils;

public class PaintingInfo {

    private final Holder<PaintingVariant> painting;
    private final Component name;
    private final Component description;

    public PaintingInfo(Holder<PaintingVariant> painting) {
        ResourceLocation location = painting.unwrapKey().get().location();
        this.description = Component.translatable("jepp.painting.description",
                formatName(location.getNamespace()),
                painting.value().width(), painting.value().height());
        String name = location.getPath();

        Component text = Component.translatable(name);
        if (text.getString().equals(name)) text = formatName(name);

        this.name = text;
        this.painting = painting;
    }

    private Component formatName(String name) {
        name = name.replace("_", " ");
        name = StringUtils.capitalize(name);
        return Component.literal(name);
    }

    public Component getDescription() {
        return description;
    }

    public Component getName() {
        return name;
    }

    public Holder<PaintingVariant> getPainting() {
        return painting;
    }


}