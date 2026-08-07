package net.mehvahdjukaar.jepp.rei;

import me.shedaniel.rei.forge.REIPluginClient;

/**
 * REI discovers plugins through these annotations on NeoForge, while Fabric uses the mod json entrypoints.
 */
public final class REIPluginsNeoForge {

    @REIPluginClient
    public static class Client extends REIPlugin {
    }
}
