package net.mehvahdjukaar.jepp.forge;

import mezz.jei.forge.JustEnoughItems;
import net.mehvahdjukaar.jepp.Jepp;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

/**
 * Author: MehVahdJukaar
 */
@Mod(Jepp.MOD_ID)
public class JeppForge {

    public JeppForge() {
        if(!ModList.get().isLoaded("jei") && !ModList.get().isLoaded("roughlyenoughitems")){
            Jepp.LOGGER.error("Jepp requires either JEI or REI mods. None of them was found");
        }
    }


}

