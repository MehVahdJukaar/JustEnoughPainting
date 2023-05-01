package net.mehvahdjukaar.jepp;

import net.mehvahdjukaar.jepp.client.BarbaricHelmetModel;
import net.mehvahdjukaar.jepp.client.GeepModel;
import net.mehvahdjukaar.jepp.client.GeepRenderer;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class JeppClient {

    public static final ModelLayerLocation GEEP = loc("geep");
    public static final ModelLayerLocation GEEP_FUR = loc("geep_fur");
    public static final ModelLayerLocation BARBARIC_HELMET = loc("barbaric_helmet");

    public static void init() {
        ClientPlatformHelper.addModelLayerRegistration(JeppClient::registerLayers);
        ClientPlatformHelper.addEntityRenderersRegistration(JeppClient::registerEntityRenderers);
    }

    public static void setup() {
    }

    private static ModelLayerLocation loc(String name) {
        return new ModelLayerLocation(Jepp.res(name), name);
    }


    private static void registerLayers(ClientPlatformHelper.ModelLayerEvent event) {
        event.register(BARBARIC_HELMET, BarbaricHelmetModel::createBodyLayer);
        event.register(GEEP, GeepModel::createBodyLayer);
        event.register(GEEP_FUR, GeepModel::createFurLayer);
    }

    private static void registerEntityRenderers(ClientPlatformHelper.EntityRendererEvent event) {
        event.register(Jepp.GEEP.get(), GeepRenderer::new);
    }


}
