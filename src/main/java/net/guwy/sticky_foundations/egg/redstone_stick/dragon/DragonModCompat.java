package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.egg.Users;
import net.guwy.sticky_foundations.egg.redstone_stick.dragon.fangs.DragonFangs;
import net.guwy.sticky_foundations.index.SFDamageSources;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import virtuoel.pehkui.api.ScaleTypes;

public class DragonModCompat {

    public static void serverTick(Player player){
        if(Users.checkUUID(player, Users.REDSTONE_STICK)){

            //if(player.getFoodData().getFoodLevel() <= 6 || getPlayerSize(player) != 1.5f) updateScale(player);
            updateScale(player);

            // by being called once a second it is equal to Hunger(I)
            player.getFoodData().addExhaustion((float) Math.max(0, Math.min(4, (Math.pow(getPlayerSize(player), 2) - 1) * 0.05)));
            //player.sendSystemMessage(Component.literal("exhaustion: " + player.getFoodData().getExhaustionLevel()));
        }
    }

    public static void updateScale(Player player){
        if(isModsLoaded()){
            DragonPehkui.updateScale(player);
        }
    }

    public static boolean fangAttack(Player player, Entity target, Level level){
        if(player.isCrouching() && player.getFoodData().needsFood()
                && target instanceof LivingEntity livingTarget && livingTarget.getMobType() != MobType.UNDEAD){
            float playerSize = DragonModCompat.getPlayerSize(player);

            if(playerSize > 1 && !player.swinging){
                float mobHealth = livingTarget.getHealth();
                float playerHunger = 20 - player.getFoodData().getFoodLevel();

                float maxDamage = (float) (Math.pow(playerSize, 3) - 1);
                maxDamage = Math.min(playerHunger, Math.min(maxDamage, mobHealth));

                DragonFangs evokerFangs = new DragonFangs(level,
                        livingTarget.getX(), livingTarget.getY() + (livingTarget.getBbHeight() / 2), livingTarget.getZ(), 0, 0, player);
                evokerFangs.setXRot(player.getXRot());
                evokerFangs.setYRot(player.getYRot());
                ScaleTypes.BASE.getScaleData(evokerFangs).setScale(playerSize);
                level.addFreshEntity(evokerFangs);

                livingTarget.hurt(SFDamageSources.DragonBite(evokerFangs, player), maxDamage / DragonModCompat.getPlayerAttack(player));
                player.getFoodData().eat(Math.round(maxDamage), 0f);

                level.playSound(null, player, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1, 0.8f);
                player.swing(InteractionHand.MAIN_HAND);

                return true;
            }
        }
        return false;
    }

    public static float getPlayerSize(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerSize(player) : 1;
    }

    public static float getPlayerAttack(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerAttack(player) : 1;
    }
    public static float getPlayerAttackSpeed(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerAttackSpeed(player) : 1;
    }
    public static float getPlayerAttackKnockback(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerAttackKnockback(player) : 1;
    }
    public static float getPlayerDefense(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerDefense(player) : 1;
    }
    public static float getPlayerMiningSpeed(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerMiningSpeed(player) : 1;
    }
    public static float getPlayerJumpHeight(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerJumpHeight(player) : 1;
    }
    public static float getPlayerStepHeight(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerStepHeight(player) : 1;
    }

    /** returns whether the core mods required to run this compat are installed */
    public static boolean isModsLoaded(){
        return StickyFoundations.isPehkuiLoaded();
    }

    public static class ArsNoveau {
        public static void increaseMana(Player player, double mana){
            if(StickyFoundations.isArsNouveauLoaded()) DragonArsNoveau.increaseMana(player, mana);
        }
    }

    public static class IronsSpellbooks {
        public static void increaseMana(Player player, float mana){
            if(StickyFoundations.isIronsSpelbooksLoaded()) DragonIronsSpellbooks.increaseMana(player, mana);
        }
    }

    public static class MahouTsukai {
        public static void increaseMana(Player player, int mana){
            if(StickyFoundations.isMahouTsukaiLoaded()) DragonMahouTsukai.increaseMana(player, mana);
        }
    }


}
