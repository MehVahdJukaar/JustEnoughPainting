package net.mehvahdjukaar.jepp.platform;

import net.mehvahdjukaar.jepp.Jepp;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

/**
 * Author: MehVahdJukaar
 */
@Mod(Jepp.MOD_ID)
public class JeppImpl {

    public JeppImpl() {
        if (!ModList.get().isLoaded("jei")
                && !ModList.get().isLoaded("emi")
                && !ModList.get().isLoaded("roughlyenoughitems")) {
            Jepp.LOGGER.error("Jepp requires either JEI, EMI or REI mods. None of them was found");
        }
    }
}
