package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.capabilities.magic.PlayerMagicProvider;
import net.minecraft.world.entity.player.Player;

public class DragonIronsSpellbooks {
    public static void increaseMana(Player player, float mana){
        player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(magic -> {
            magic.addMana(mana);
        });
    }
}
