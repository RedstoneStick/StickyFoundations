package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import net.minecraft.world.entity.player.Player;
import stepsword.mahoutsukai.capability.mahou.MahouProvider;

public class DragonMahouTsukai {
    public static void increaseMana(Player player, int mana){
        player.getCapability(MahouProvider.MAHOU).ifPresent(iMahou -> {
            iMahou.setStoredMana(Math.min(iMahou.getStoredMana() + mana, iMahou.getMaxMana()));
        });
    }
}
