package net.mehvahdjukaar.jepp;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Author: MehVahdJukaar
 */
public class Jepp {

    public static final String MOD_ID = "jepp";
    public static final Logger LOGGER = LogManager.getLogger("Jepp");

    public static ResourceLocation res(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static Collection<Holder<PaintingVariant>> getPaintings() {
        var l = Minecraft.getInstance().level;
        return l.registryAccess().lookupOrThrow(Registries.PAINTING_VARIANT)
                .getOrThrow(PaintingVariantTags.PLACEABLE)
                .stream().collect(Collectors.toSet());
    }
}