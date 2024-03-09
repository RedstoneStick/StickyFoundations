package net.guwy.sticky_foundations.index;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.EvokerFangs;

public class SFDamageSources {
    public static DamageSource WATER_OVERPRESSURE = new DamageSource("water_overpressure")
            .bypassArmor().bypassEnchantments().bypassMagic();

    public static DamageSource WATER_OVERPRESSURE_EASTER_EGG = new DamageSource("water_overpressure_easter_egg")
            .bypassArmor().bypassEnchantments().bypassMagic();

    public static DamageSource DragonBite(EvokerFangs fangs, Entity entity){
        return new IndirectEntityDamageSource("dragon_bite", fangs, entity).bypassArmor().bypassEnchantments().bypassMagic();
    }
    public static DamageSource DRAGON_OVERLOAD = new DamageSource("dragon_overload")
            .bypassArmor().bypassEnchantments().bypassMagic();
}
