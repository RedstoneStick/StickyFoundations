package net.guwy.sticky_foundations.index;

import net.minecraft.world.damagesource.DamageSource;

public class SFDamageSources {
    public static DamageSource WATER_OVERPRESSURE = new DamageSource("water_overpressure")
            .bypassArmor().bypassEnchantments().bypassMagic();

    public static DamageSource WATER_OVERPRESSURE_EASTER_EGG = new DamageSource("water_overpressure_easter_egg")
            .bypassArmor().bypassEnchantments().bypassMagic();
}
