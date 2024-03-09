package net.guwy.sticky_foundations.events.server_events;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.egg.Users;
import net.guwy.sticky_foundations.egg.redstone_stick.dragon.DragonModCompat;
import net.guwy.sticky_foundations.egg.redstone_stick.dragon.fangs.DragonFangs;
import net.guwy.sticky_foundations.index.SFDamageSources;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import virtuoel.pehkui.api.ScaleTypes;

public class PlayerInteractEventHandler {

    public static void EntityInteract(PlayerInteractEvent.EntityInteract event){
        Player player = event.getEntity();
        Entity target = event.getTarget();
        Level level = player.level;

        if( DragonModCompat.isModsLoaded() && Users.checkUUID(player, Users.REDSTONE_STICK) && event.getItemStack().isEmpty()) {
            event.setCanceled(DragonModCompat.fangAttack(player, target, level));
        }
    }
}
