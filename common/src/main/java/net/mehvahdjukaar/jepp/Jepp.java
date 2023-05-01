package net.mehvahdjukaar.jepp;

import net.mehvahdjukaar.jepp.common.*;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Author: MehVahdJukaar
 */
public class Jepp {

    public static final String MOD_ID = "goated";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void commonInit() {
        RegHelper.addAttributeRegistration(Jepp::registerEntityAttributes);
        PackProvider.INSTANCE.register();
    }

    public static void commonSetup() {

    }


    private static void registerEntityAttributes(RegHelper.AttributeEvent event) {
        event.register(GEEP.get(), Geep.createAttributes());
    }

    public static final TagKey<Block> BREAK_BLACKLIST = TagKey.create(Registry.BLOCK_REGISTRY, res("ram_block_blacklist"));

    public static final Supplier<SoundEvent> HURT_SOUND = RegHelper.registerSound(res("geep.hurt"));
    public static final Supplier<SoundEvent> DEATH_SOUND = RegHelper.registerSound(res("geep.death"));
    public static final Supplier<SoundEvent> AMBIENT_SOUND = RegHelper.registerSound(res("geep.ambient"));
    public static final Supplier<SoundEvent> MILK_SOUND = RegHelper.registerSound(res("geep.milk"));
    public static final Supplier<SoundEvent> EAT_SOUND = RegHelper.registerSound(res("geep.eat"));
    public static final Supplier<SoundEvent> LONG_JUMP_SOUND = RegHelper.registerSound(res("geep.long_jump"));
    public static final Supplier<SoundEvent> INFESTATION_SOUND = RegHelper.registerSound(res("geep.infestation"));
    public static final Supplier<SensorType<GeepAdultSensor>> GEEP_ADULT_SENSOR = RegHelper.registerSensor(
            res("geep_adult"), () -> new SensorType<>(GeepAdultSensor::new));

    public static final Supplier<EntityType<Geep>> GEEP = regEntity("geep", Geep::new,
            MobCategory.CREATURE, 0.9f, 1.3f, 10, true, 3);

    public static final Supplier<Item> GEEP_SPAWN_EGG = RegHelper.registerItem(res("geep_spawn_egg"), () ->
            PlatformHelper.newSpawnEgg(GEEP, 0xd6c4b5, 0xd6c4b5,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final Supplier<Block> RAM_BLOCK = regWithItem(
            "ram_block",
            () -> new RamBlock(BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK)
                    .strength(4.0f)),
            CreativeModeTab.TAB_DECORATIONS);

    public static final Supplier<Item> BARBARIC_HELMET = RegHelper.registerItem(
            res("barbaric_helmet"),
            () -> new BarbaricHelmetItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final Supplier<Item> RAW_CHEVON = RegHelper.registerItem(
            res("chevon"),
            () -> new Item(new Item.Properties().tab(getTabFood())
                    .food(Foods.MUTTON)));

    public static final Supplier<Item> COOKED_CHEVON = RegHelper.registerItem(
            res("cooked_chevon"),
            () -> new Item(new Item.Properties().tab(getTabFood())
                    .food(Foods.COOKED_MUTTON)));

    public static final Map<RegHelper.VariantType, Supplier<Block>> THATCH_BLOCKS =
            RegHelper.registerReducedBlockSet(res("thatch"), BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)
                    .color(MaterialColor.TERRACOTTA_BROWN), false);


    private static CreativeModeTab getTabFood() {
        return PlatformHelper.isModLoaded("windswept") ? null : CreativeModeTab.TAB_FOOD;
    }


    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> blockFactory, CreativeModeTab tab) {
        return regWithItem(name, blockFactory, new Item.Properties().tab(tab), 0);
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> blockFactory, Item.Properties properties, int burnTime) {
        Supplier<T> block = regBlock(name, blockFactory);
        regBlockItem(name, block, properties, burnTime);
        return block;
    }

    public static Supplier<BlockItem> regBlockItem(String name, Supplier<? extends Block> blockSup, Item.Properties properties, int burnTime) {
        return RegHelper.registerItem(res(name), () -> new BlockItem(blockSup.get(), properties));
    }

    public static <T extends Block> Supplier<T> regBlock(String name, Supplier<T> sup) {
        return RegHelper.registerBlock(res(name), sup);
    }


    public static <T extends Entity> Supplier<EntityType<T>> regEntity(
            String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height,
            int clientTrackingRange, boolean velocityUpdates, int updateInterval) {
        return RegHelper.registerEntityType(res(name), () ->
                PlatformHelper.newEntityType(name, factory, category, width, height,
                        clientTrackingRange, velocityUpdates, updateInterval));
    }


}