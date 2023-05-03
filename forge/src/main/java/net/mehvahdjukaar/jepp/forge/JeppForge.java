package net.mehvahdjukaar.jepp.forge;

import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.JeppClient;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Author: MehVahdJukaar
 */
@Mod(Jepp.MOD_ID)
public class JeppForge {

    public JeppForge() {
        Jepp.commonInit();

        JeppClient.init();

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JeppForge::setup);
    }

    public static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(Jepp::commonSetup);
    }


}

