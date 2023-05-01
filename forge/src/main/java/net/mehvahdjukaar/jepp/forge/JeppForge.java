package net.mehvahdjukaar.jepp.forge;

import net.mehvahdjukaar.jepp.Jepp;
import net.mehvahdjukaar.jepp.JeppClient;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

/**
 * Author: MehVahdJukaar
 */
@Mod(Jepp.MOD_ID)
public class JeppForge {

    public JeppForge() {
        Jepp.commonInit();

        if (PlatformHelper.getEnv().isClient()) {
            JeppClient.init();
        }
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JeppForge::setup);
    }

    public static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(Jepp::commonSetup);
    }


    public static final Capability<RamBreakingCap> RAM_BREAK_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });


    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(RamBreakingCap.class);
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Level> event) {
        if (event.getObject() instanceof ServerLevel serverLevel) {
            RamBreakingCap capability = new RamBreakingCap(serverLevel);
            event.addCapability(Jepp.res("ram_break_progress"), capability);
            event.addListener(capability::invalidate);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void tickEvent(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.level instanceof ServerLevel level) {
            level.getCapability(RAM_BREAK_CAP).ifPresent(RamBreakingCap::validateAll);
        }
    }


    @SubscribeEvent
    public void onRemapBlocks(MissingMappingsEvent event) {
        for (var v : event.getMappings(ForgeRegistries.BLOCKS.getRegistryKey(), "ohmygoat")) {
            String name = v.getKey().getPath();
            if (name.equals("ram_block")) {
                v.remap(Jepp.RAM_BLOCK.get());
            }
        }
        for (var v : event.getMappings(ForgeRegistries.ITEMS.getRegistryKey(), "ohmygoat")) {
            String name = v.getKey().getPath();
            switch (name) {
                case "ram_block" -> v.remap(Jepp.RAM_BLOCK.get().asItem());
                case "chevon" -> v.remap(Jepp.RAW_CHEVON.get().asItem());
                case "cooked_chevon" -> v.remap(Jepp.COOKED_CHEVON.get().asItem());
                case "barbaric_helmet" -> v.remap(Jepp.BARBARIC_HELMET.get().asItem());
                case "geep_spawn_egg" -> v.remap(Jepp.GEEP_SPAWN_EGG.get().asItem());
            }
        }
        for (var v : event.getMappings(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), "ohmygoat")) {
            String name = v.getKey().getPath();
            if (name.equals("geep")) {
                v.remap(Jepp.GEEP.get());
            }
        }
    }
}

