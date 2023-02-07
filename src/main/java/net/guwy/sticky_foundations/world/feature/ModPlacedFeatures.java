package net.guwy.sticky_foundations.world.feature;

import net.guwy.sticky_foundations.StickyFoundations;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, StickyFoundations.MOD_ID);



    public static final RegistryObject<PlacedFeature> FLUORITE_ORE_PLACED = PLACED_FEATURES.register("fluorite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.FLUORITE_ORE.getHolder().get(),
                    commonOrePlacement(9, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-70), VerticalAnchor.absolute(40)))));

    public static final RegistryObject<PlacedFeature> NITER_ORE_PLACED = PLACED_FEATURES.register("niter_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NITER_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-10), VerticalAnchor.absolute(50)))));

    public static final RegistryObject<PlacedFeature> MAGNESIUM_ORE_PLACED = PLACED_FEATURES.register("magnesium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.MAGNESIUM_ORE.getHolder().get(),
                    commonOrePlacement(3, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(60)))));

    public static final RegistryObject<PlacedFeature> PALLADIUM_ORE_PLACED = PLACED_FEATURES.register("palladium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.PALLADIUM_ORE.getHolder().get(),
                    commonOrePlacement(5, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-100), VerticalAnchor.absolute(-20)))));

    public static final RegistryObject<PlacedFeature> PLATINUM_ORE_PLACED = PLACED_FEATURES.register("platinum_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.PLATINUM_ORE.getHolder().get(),
                    commonOrePlacement(5, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-100), VerticalAnchor.absolute(-20)))));

    public static final RegistryObject<PlacedFeature> TITANIUM_ORE_PLACED = PLACED_FEATURES.register("titanium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.TITANIUM_ORE.getHolder().get(),
                    commonOrePlacement(7, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-66), VerticalAnchor.absolute(24)))));

    public static final RegistryObject<PlacedFeature> THORIUM_ORE_PLACED = PLACED_FEATURES.register("thorium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.THORIUM_ORE.getHolder().get(),
                    commonOrePlacement(10, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-70), VerticalAnchor.absolute(30)))));

    public static final RegistryObject<PlacedFeature> URANIUM_ORE_PLACED = PLACED_FEATURES.register("uranium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.URANIUM_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-70), VerticalAnchor.absolute(-22)))));

    public static final RegistryObject<PlacedFeature> BAUXITE_ORE_PLACED = PLACED_FEATURES.register("bauxite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.BAUXITE_ORE.getHolder().get(),
                    rareOrePlacement(18, // Veins Every x amount of Chunks
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(50), VerticalAnchor.absolute(150)))));






    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
