package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import net.guwy.sticky_foundations.egg.SpecialItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class DragonPehkui {
    public static float getPlayerSize(Player player){
        return ScaleTypes.BASE.getScaleData(player).getScale();
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

    public static void updateScale(Player player){
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
        ScaleData scaleReach = ScaleTypes.REACH.getScaleData(player);
        ScaleData scaleMiningSpeed = ScaleTypes.MINING_SPEED.getScaleData(player);
        ScaleData scaleMoveSpeed = ScaleTypes.MOTION.getScaleData(player);
        ScaleData scaleJumpHeight = ScaleTypes.JUMP_HEIGHT.getScaleData(player);
        ScaleData scaleStepHeight = ScaleTypes.STEP_HEIGHT.getScaleData(player);

        scaleFallDamage.setScale(0.1f);
        scaleHealth.setScale(10);
        scaleHBHeight.setScale(0.95f);
        scaleHBWidth.setScale(0.85f);
        scaleVisibility.setScale(scaleBase.getScale());
        scaleAttack.setScale((float) Math.pow(scaleBase.getScale(), 2));
        scaleAttackSpeed.setScale(scaleBase.getScale());
        scaleAttackKnockback.setScale(scaleBase.getScale());
        scaleDefense.setScale(scaleBase.getScale() * 2);
        scaleMiningSpeed.setScale((float) Math.pow(scaleBase.getScale(), 2));



        //scaleBase.setTargetScale(1.5f);


        // if hungry
        if(player.getFoodData().getFoodLevel() <= 6){
            float size = Math.max(0.1f, player.getFoodData().getFoodLevel() / 7f);
            // shrink
            scaleBase.setTargetScale(size);
            // increase jump
            scaleJumpHeight.setScale(1 + (3 * (1 - size)));
            scaleStepHeight.setScale(1 + (8 * (1 - size)));
        } else {
            boolean bool = false;
            // Scale from the dragon core
            Inventory inventory = player.getInventory();
            for(ItemStack itemStack : inventory.items){
                if(itemStack.getItem() == SpecialItems.DRAGON_CORE.get()){
                    ItemStack dragonHearth = itemStack;

                    CompoundTag nbt = dragonHearth.getTag() != null ? dragonHearth.getTag() : new CompoundTag();
                    int state = nbt.getInt("CustomModelData");
                    float scale = 0;

                    switch (state){
                        case 1 -> scale = 1;        // 2m
                        default -> scale = 1.5f;    // 3m
                        case 3 -> scale = 2.5f;     // 5m
                        case 4 -> scale = 5;        // 10m
                        case 5 -> scale = 10;       // 20m
                        case 6 -> scale = 25;       // 50m
                    }

                    scaleBase.setTargetScale(scale);
                    bool = true;
                }
            }
            if(!bool) scaleBase.setTargetScale(1.5f);

            scaleStepHeight.setScale(1.2f);
            scaleJumpHeight.setScale(1);
        }
    }
}
