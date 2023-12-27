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
        public static final ForgeConfigSpec.ConfigValue<Double> AIR_CAPACITY;
        public static final ForgeConfigSpec.ConfigValue<Double> AIR_CONSUMPTION_IDLE;
        public static final ForgeConfigSpec.ConfigValue<Double> AIR_EXTRA_CONSUMPTION_RUNNING;
        public static final ForgeConfigSpec.ConfigValue<Integer> AIR_MAX_ALT_FOR_MAX_REGEN;
        public static final ForgeConfigSpec.ConfigValue<Integer> AIR_MAX_ALT_FOR_RUNNING_REGEN;
        public static final ForgeConfigSpec.ConfigValue<Integer> AIR_MAX_ALT_FOR_IDLE_REGEN;
        public static final ForgeConfigSpec.ConfigValue<Integer> AIR_ALT_FOR_NO_REGEN;

        public static final ForgeConfigSpec.ConfigValue<Double> HUD_SWAY_MULTIPLIER;
        public static final ForgeConfigSpec.ConfigValue<Boolean> DRAW_VISOR_GUNK;


        static {
            BUILDER.push("Client Configs for Sticky Foundations");


            BUILDER.push("Mechanics");

            BUILDER.push("Water Pressure");
            BUILDER.comment(".");
            ACTIVATE_WATER_PRESSURE_MECHANICS = BUILDER
                    .comment("Whether or not to activate the water pressure mechanics")
                    .define("Water Pressure Mechanics", false);
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
                    .comment("Damage in ocean and under: BaseDamage ((SeaLevel - OverpressureDepth - playerY) / 5 * BaseDamage)")
                    .comment("Damage everywhere else: BaseDamage")
                    .defineInRange("Base Damage", 2, 0, Integer.MAX_VALUE);
            BUILDER.pop();
            BUILDER.comment(".");

            BUILDER.push("Air Density");
            BUILDER.comment(".");
            ACTIVATE_BREATHABLE_AIR_MECHANICS = BUILDER
                    .comment("Whether or not to deplete oxygen in high altitudes")
                    .define("Breathable Air Mechanics", false);
            BUILDER.comment(".");
            AIR_CAPACITY = BUILDER
                    .comment("Air capacity the player has")
                    .defineInRange("Air Capacity", 100, 0, Double.MAX_VALUE);
            BUILDER.comment(".");
            AIR_CONSUMPTION_IDLE = BUILDER
                    .comment("Constant air consumption")
                    .defineInRange("Idle Consumption", 2, 0, Double.MAX_VALUE);
            BUILDER.comment(".");
            AIR_EXTRA_CONSUMPTION_RUNNING = BUILDER
                    .comment("Additional air consumption when running")
                    .defineInRange("Extra Consumption When Running", 3, 0, Double.MAX_VALUE);
            BUILDER.comment(".");
            AIR_MAX_ALT_FOR_MAX_REGEN = BUILDER
                    .comment("The maximum altitude the air regen hits peak")
                    .comment("4 x (running + idle consumption) units per tick")
                    .define("Alt for Max Regen", 64);
            BUILDER.comment(".");
            AIR_MAX_ALT_FOR_RUNNING_REGEN = BUILDER
                    .comment("The maximum altitude the air regen equals running + idle air consumption")
                    .comment("Cannot be more than Alt for Max Regen")
                    .define("Alt for Running", 128);
            BUILDER.comment(".");
            AIR_MAX_ALT_FOR_IDLE_REGEN = BUILDER
                    .comment("The maximum altitude the air regen equals idle air consumption")
                    .comment("Cannot be more than Alt for Running")
                    .define("Alt for Idle", 256);
            BUILDER.comment(".");
            AIR_ALT_FOR_NO_REGEN = BUILDER
                    .comment("The maximum altitude the air regen is 0")
                    .comment("Cannot be more than Alt for Idle")
                    .define("Alt for Regen", 1028);
            BUILDER.pop();
            BUILDER.comment(".");

            BUILDER.pop();


            BUILDER.push("Display");
            BUILDER.comment(".");
            HUD_SWAY_MULTIPLIER = BUILDER.comment("")
                    .comment("The amount to multiply the hud sway")
                    .comment("0 will turn off the hud sway, turning View Bobbing off in game will do the same thing as well")
                    .defineInRange("Hud sway multiplier", 1.0, 0.0, 100.0);
            BUILDER.comment(".");
            DRAW_VISOR_GUNK = BUILDER.comment("")
                    .comment("Whether or not to draw the visor gunk")
                    .comment("Even if disabled the gunk will accumulate, so expect a surprise once you turn it on again")
                    .define("Draw Visor Gunk", true);
            BUILDER.pop();


            BUILDER.pop();
            SPEC = BUILDER.build();
        }
    }
}
