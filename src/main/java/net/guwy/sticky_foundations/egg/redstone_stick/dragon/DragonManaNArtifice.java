package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import com.mna.capabilities.playerdata.magic.PlayerMagicProvider;
import net.minecraft.world.entity.player.Player;

public class DragonManaNArtifice {
    public static void restoreMana(Player player){
        player.getCapability(PlayerMagicProvider.MAGIC).ifPresent(playerMagic -> {
            if(playerMagic.getCastingResource() != null)
                playerMagic.getCastingResource().restore(playerMagic.getCastingResource().getMaxAmount() / 10);
        });
    }
}
