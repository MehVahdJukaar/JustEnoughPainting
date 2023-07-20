package net.mehvahdjukaar.jepp;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author: MehVahdJukaar
 */
public class Jepp {

    public static final String MOD_ID = "jepp";
    public static final Logger LOGGER = LogManager.getLogger("Jepp");

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

}