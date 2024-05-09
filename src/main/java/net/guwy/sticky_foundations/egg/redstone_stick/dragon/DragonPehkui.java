package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import net.guwy.sticky_foundations.egg.SpecialItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class DragonPehkui {
    public static float getPlayerSize(Player player){
        return ScaleTypes.BASE.getScaleData(player).getScale();
    }
    public static float getPlayerTargetSize(Player player){
        return ScaleTypes.BASE.getScaleData(player).getTargetScale();
    }
    public static float getPlayerAttack(Player player){
        return ScaleTypes.ATTACK.getScaleData(player).getScale();
    }
    public static float getPlayerAttackSpeed(Player player){
        return ScaleTypes.ATTACK_SPEED.getScaleData(player).getScale();
    }
    public static float getPlayerAttackKnockback(Player player){
        return ScaleTypes.KNOCKBACK.getScaleData(player).getScale();
    }
    public static float getPlayerDefense(Player player){
        return ScaleTypes.DEFENSE.getScaleData(player).getScale();
    }
    public static float getPlayerMiningSpeed(Player player){
        return ScaleTypes.MINING_SPEED.getScaleData(player).getScale();
    }
    public static float getPlayerJumpHeight(Player player){
        return ScaleTypes.JUMP_HEIGHT.getScaleData(player).getScale();
    }
    public static float getPlayerStepHeight(Player player){
        return ScaleTypes.STEP_HEIGHT.getScaleData(player).getScale();
    }

    public static void changeScale(Player player, float scale){
        ScaleData scaleBase = ScaleTypes.BASE.getScaleData(player);
        ScaleData scaleHBHeight = ScaleTypes.HITBOX_HEIGHT.getScaleData(player);
        ScaleData scaleHBWidth = ScaleTypes.HITBOX_WIDTH.getScaleData(player);
        ScaleData scaleVisibility = ScaleTypes.VISIBILITY.getScaleData(player);
        ScaleData scaleAttack = ScaleTypes.ATTACK.getScaleData(player);
        ScaleData scaleAttackSpeed = ScaleTypes.ATTACK_SPEED.getScaleData(player);
        ScaleData scaleAttackKnockback = ScaleTypes.KNOCKBACK.getScaleData(player);
        ScaleData scaleDefense = ScaleTypes.DEFENSE.getScaleData(player);
        ScaleData scaleHealth = ScaleTypes.HEALTH.getScaleData(player);
        ScaleData scaleFallDamage = ScaleTypes.FALLING.getScaleData(player);
        //ScaleData scaleReach = ScaleTypes.REACH.getScaleData(player);
        ScaleData scaleMiningSpeed = ScaleTypes.MINING_SPEED.getScaleData(player);
        //ScaleData scaleMoveSpeed = ScaleTypes.MOTION.getScaleData(player);
        ScaleData scaleJumpHeight = ScaleTypes.JUMP_HEIGHT.getScaleData(player);
        ScaleData scaleStepHeight = ScaleTypes.STEP_HEIGHT.getScaleData(player);

        scaleBase.setTargetScale(scale);

        scaleFallDamage.setScale(scale==1 ? 1 : 0.1f);
        scaleHealth.setScale(scale <= 1 ? 1 : 10);
        scaleHBHeight.setScale(0.95f);
        scaleHBWidth.setScale(0.85f);
        scaleVisibility.setScale(scale);
        scaleAttack.setScale((float) Math.pow(scale, 2));
        scaleAttackSpeed.setScale(scale);
        scaleAttackKnockback.setScale(scale);
        scaleDefense.setScale((float) Math.pow(scale, 1.6));
        scaleMiningSpeed.setScale((float) Math.pow(scale, 2));
        float stepH = scale == 1.5f ? 1.2f : 1f;
        scaleStepHeight.setScale(stepH);
        scaleJumpHeight.setScale(stepH);
    }

    public static void updateScale(Player player){
        ScaleData scaleJumpHeight = ScaleTypes.JUMP_HEIGHT.getScaleData(player);
        ScaleData scaleStepHeight = ScaleTypes.STEP_HEIGHT.getScaleData(player);

        // if hungry
        if(player.getFoodData().getFoodLevel() <= 6){
            float size = Math.max(0.1f, player.getFoodData().getFoodLevel() / 7f);
            // shrink
            changeScale(player, size);
            // increase jump
            scaleJumpHeight.setScale(1 + (3 * (1 - size)));
            scaleStepHeight.setScale(1 + (8 * (1 - size)));
        }
    }
}
