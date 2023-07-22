package net.guwy.sticky_foundations.world.feature;

import com.google.common.base.Suppliers;
import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.index.SFMinerals;
import net.guwy.sticky_foundations.index.SFTags;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, StickyFoundations.MOD_ID);

    private static final RuleTest NATURAL_SOIL = new TagMatchTest(SFTags.Blocks.NATURAL_SOIL);
    private static final RuleTest NATURAL_FOLIAGE = new TagMatchTest(SFTags.Blocks.NATURAL_FOLIAGE);
    private static final RuleTest SNOW_LAYER = new BlockMatchTest(Blocks.SNOW);
    private static final RuleTest GRASS = new BlockMatchTest(Blocks.GRASS);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_FLUORITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.FLUORITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.FLUORITE_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FLUORITE_ORE = CONFIGURED_FEATURES.register("fluorite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_FLUORITE_ORES.get(),8)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_NITER_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.NITER_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.NITER_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NITER_ORE = CONFIGURED_FEATURES.register("niter_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_NITER_ORES.get(),6)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_MAGNESIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.MAGNESIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.MAGNESIUM_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MAGNESIUM_ORE = CONFIGURED_FEATURES.register("magnesium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_MAGNESIUM_ORES.get(),10)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_PALLADIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.PALLADIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.PALLADIUM_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PALLADIUM_ORE = CONFIGURED_FEATURES.register("palladium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_PALLADIUM_ORES.get(),6)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_PLATINUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.PLATINUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.PLATINUM_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PLATINUM_ORE = CONFIGURED_FEATURES.register("platinum_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_PLATINUM_ORES.get(),6)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TITANIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.TITANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.TITANIUM_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TITANIUM_ORE = CONFIGURED_FEATURES.register("titanium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORES.get(),10)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_THORIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.THORIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.THORIUM_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> THORIUM_ORE = CONFIGURED_FEATURES.register("thorium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_THORIUM_ORES.get(),9)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_URANIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.URANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.URANIUM_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> URANIUM_ORE = CONFIGURED_FEATURES.register("uranium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_URANIUM_ORES.get(),6)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_BAUXITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(NATURAL_FOLIAGE, SFMinerals.BAUXITE_POOR.get().defaultBlockState()),
            OreConfiguration.target(NATURAL_SOIL, SFMinerals.BAUXITE_NORMAL.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.NATURAL_STONE, SFMinerals.BAUXITE_NORMAL.get().defaultBlockState()),
            OreConfiguration.target(GRASS, Blocks.AIR.defaultBlockState()),
            OreConfiguration.target(SNOW_LAYER, Blocks.AIR.defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BAUXITE_ORE = CONFIGURED_FEATURES.register("bauxite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_BAUXITE_ORES.get(),64)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_LEAD_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SFMinerals.LEAD_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SFMinerals.LEAD_ORE_DEEPSLATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEAD_ORE = CONFIGURED_FEATURES.register("lead_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_LEAD_ORES.get(),6)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
