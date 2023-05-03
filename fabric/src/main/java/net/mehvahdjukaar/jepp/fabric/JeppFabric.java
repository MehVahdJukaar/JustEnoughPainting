package net.mehvahdjukaar.jepp.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.mehvahdjukaar.jepp.Jepp;

public class JeppFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if(!FabricLoader.getInstance().isModLoaded("jei") && !FabricLoader.getInstance().isModLoaded("roughlyenoughitems")){
            Jepp.LOGGER.error("Jepp requires either JEI or REI mods. None of them was found");
        }
    }
}
