package net.mehvahdjukaar.jepp.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.JeppClient;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.fabric.FabricSetupCallbacks;

public class JeppFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        Jepp.commonInit();

        if (PlatformHelper.getEnv().isClient()) {
            FabricSetupCallbacks.CLIENT_SETUP.add(JeppClient::init);
        }

        FabricSetupCallbacks.COMMON_SETUP.add(Jepp::commonSetup);
    }


}
