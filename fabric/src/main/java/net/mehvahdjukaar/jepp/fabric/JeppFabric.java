package net.mehvahdjukaar.jepp.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.JeppClient;
import net.mehvahdjukaar.moonlight.fabric.FabricSetupCallbacks;

public class JeppFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        Jepp.commonInit();

        JeppClient.init();

        FabricSetupCallbacks.COMMON_SETUP.add(Jepp::commonSetup);
    }


}
