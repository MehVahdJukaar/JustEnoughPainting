package net.mehvahdjukaar.jepp.jei;

import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Motive;
import org.apache.commons.lang3.StringUtils;

public class PaintingInfoRecipe {

    private final Motive motive;
    private final BaseComponent name;
    private final BaseComponent description;

    public PaintingInfoRecipe(Motive painting) {
        ResourceLocation r = painting.getRegistryName();
        this.description = new TranslatableComponent("jepp.painting.description",
                formatName(r.getNamespace()),
                painting.getWidth(), painting.getHeight());
        String name = r.getPath();

        BaseComponent text = new TranslatableComponent(name);
        if (text.getString().equals(name)) text = formatName(name);

        this.name = text;
        this.motive = painting;
    }

    private BaseComponent formatName(String name) {
        name = name.replace("_", " ");
        name = StringUtils.capitalize(name);
        return new TextComponent(name);
    }

    public FormattedText getDescription() {
        return description;
    }

    public BaseComponent getName() {
        return name;
    }

    public Motive getMotive() {
        return motive;
    }


}