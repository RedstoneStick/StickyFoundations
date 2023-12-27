package net.guwy.sticky_foundations.index;

import net.minecraftforge.common.ForgeConfigSpec;

public class SFConfigs {

    public static class Client{

        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;


        // Values to configure here
        public static final ForgeConfigSpec.ConfigValue<Boolean> ACTIVATE_WATER_PRESSURE_MECHANICS;
        public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PRESSURE_SEA_LEVEL;
        public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PRESSURE_OVERPRESSURE_DEPTH;
        public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PRESSURE_BASE_DAMAGE;
        public static final ForgeConfigSpec.ConfigValue<Boolean> ACTIVATE_BREATHABLE_AIR_MECHANICS;
        public static final ForgeConfigSpec.ConfigValue<Double> HUD_SWAY_MULTIPLIER;
        public static final ForgeConfigSpec.ConfigValue<Boolean> DRAW_VISOR_GUNK;


        static {
            BUILDER.push("Client Configs for Sticky Foundations");



            BUILDER.push("Mechanics");

            BUILDER.push("WaterPressure");
            BUILDER.comment(".");
            ACTIVATE_WATER_PRESSURE_MECHANICS = BUILDER
                    .comment("Whether or not to activate the water pressure mechanics")
                    .define("Water Pressure Mechanics", true);
            BUILDER.comment(".");
            WATER_PRESSURE_SEA_LEVEL = BUILDER
                    .comment("y level for handling pressure in ocean")
                    .define("Sea Level", 62);BUILDER.comment(".");
            WATER_PRESSURE_OVERPRESSURE_DEPTH = BUILDER
                    .comment("Depth at which you start to take damage")
                    .defineInRange("Overpressure Depth", 20, 0, Integer.MAX_VALUE);
            BUILDER.comment(".");
            WATER_PRESSURE_BASE_DAMAGE = BUILDER
                    .comment("Base number for depth calculations")
                    .comment("Damage in ocean and under: ((SEA_LEVEL - OVERPRESSURE_DEPTH - playerY) / 5 * BASE_DAMAGE)")
                    .comment("Damage everywhere else: BASE_DAMAGE")
                    .defineInRange("Base Damage", 2, 0, Integer.MAX_VALUE);
            BUILDER.pop();

            BUILDER.pop();

            ACTIVATE_BREATHABLE_AIR_MECHANICS = BUILDER.comment("")
                    .comment("Whether or not to deplete oxygen in high altitudes")
                    .define("Breathable Air Mechanics", true);

            HUD_SWAY_MULTIPLIER = BUILDER.comment("")
                    .comment("The amount to multiply the hud sway")
                    .comment("0 will turn off the hud sway, turning View Bobbing off in game will do the same thing as well")
                    .defineInRange("Hud sway multiplier", 1.0, 0.0, 100.0);

            DRAW_VISOR_GUNK = BUILDER.comment("")
                    .comment("Whether or not to draw the visor gunk")
                    .comment("Even if disabled the gunk will accumulate, so expect a surprise once you turn it on again")
                    .define("Draw Visor Gunk", true);


            BUILDER.pop();
            SPEC = BUILDER.build();
        }
    }
}
