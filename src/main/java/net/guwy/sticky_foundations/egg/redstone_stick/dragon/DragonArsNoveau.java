package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import com.hollingsworth.arsnouveau.common.potions.ModPotions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class DragonArsNoveau {
    public static void increaseMana(Player player, double magic){
        CapabilityRegistry.getMana(player).ifPresent((iMana) -> {
            iMana.addMana(magic);
        });
    }
}
