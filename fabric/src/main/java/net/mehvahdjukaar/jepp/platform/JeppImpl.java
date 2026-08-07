package net.mehvahdjukaar.jepp.platform;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.mehvahdjukaar.jepp.Jepp;

public class JeppImpl implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (!FabricLoader.getInstance().isModLoaded("jei")
                && !FabricLoader.getInstance().isModLoaded("emi")
                && !FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            Jepp.LOGGER.error("Jepp requires either JEI, EMI or REI mods. None of them was found");
        }
    }
}
