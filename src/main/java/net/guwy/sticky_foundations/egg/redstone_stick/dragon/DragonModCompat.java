package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import com.hollingsworth.arsnouveau.common.potions.ModPotions;
import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.egg.Users;
import net.guwy.sticky_foundations.egg.redstone_stick.dragon.fangs.DragonFangs;
import net.guwy.sticky_foundations.index.SFDamageSources;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import virtuoel.pehkui.api.ScaleTypes;

public class DragonModCompat {

    public static void serverTick(Player player){
        if(Users.checkUUID(player, Users.REDSTONE_STICK)){

            // every second on the 7th tick
            if(player.tickCount % 20 == 7){
                //if(player.getFoodData().getFoodLevel() <= 6 || getPlayerSize(player) != 1.5f) updateScale(player);
                //updateScale(player);

                if(getPlayerSize(player) > 1){
                    // hunger
                    if(!player.isCreative())
                        player.causeFoodExhaustion((float) Math.max(0, Math.min(4, (Math.pow(getPlayerSize(player), 2) - 1) * 0.05)));
                    //player.sendSystemMessage(Component.literal("exhaustion: " + player.getFoodData().getExhaustionLevel()));

                    // more oxygen
                    if(player.getAirSupply() != 0 && player.getAirSupply() != player.getMaxAirSupply())
                        player.setAirSupply(Math.min(player.getMaxAirSupply(), player.getAirSupply() + 19));

                    // Flight
                    player.getAbilities().mayfly = player.getFoodData().getFoodLevel() > 6 || player.isCreative();
                    if(player.getAbilities().flying && !player.getAbilities().mayfly) player.getAbilities().flying = false;
                    player.onUpdateAbilities();

                    // Passive regen
                    if(player.getHealth() < player.getMaxHealth()
                            //&& player.getFoodData().getFoodLevel() > 16
                            && !player.isCreative()){
                        player.setHealth(player.getHealth() + 2);
                        //player.causeFoodExhaustion(1);
                    }

                    // night vision (not gud)
                    //int bLight = player.level.getBrightness(LightLayer.BLOCK, player.getOnPos().offset(0, 1, 0));
                    //int sLight = player.level.getBrightness(LightLayer.SKY, player.getOnPos().offset(0, 1, 0));
                    //if(bLight < 2 && sLight < 4) player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 2, 0, true, false, false));

                    // Can't wear armor
                    updateArmor(player);
                } else {
                    player.getAbilities().mayfly = player.isCreative();
                    player.onUpdateAbilities();
                }
            }

            if(getPlayerSize(player) > 1){
                // fireproof
                if(getPlayerDefense(player) > 2)    // old val is 5
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5, 0, true, false, false));

                // freezeproof
                if(player.getTicksFrozen() > 0)
                    player.setTicksFrozen(0);

                // hunger consumption when flying
                if(!player.isCreative() && player.getAbilities().flying)
                    player.causeFoodExhaustion(0.3f);  //0.06 was too op
            }
        }
    }

    public static void updateScale(Player player){
        if(isModsLoaded()){
            DragonPehkui.updateScale(player);
        }
    }

    public static void changeScale(Player player, float scale){
        if(isModsLoaded()){
            DragonPehkui.changeScale(player, scale);
        }
    }

    public static void updateArmor(Player player){
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()){
            if(equipmentSlot.getType() == EquipmentSlot.Type.ARMOR){
                ItemStack itemStack = player.getItemBySlot(equipmentSlot);
                if(itemStack.getItem() instanceof ArmorItem armorItem && armorItem.getDefense() > 0){
                    player.getInventory().placeItemBackInInventory(itemStack);
                    player.setItemSlot(equipmentSlot, ItemStack.EMPTY);
                }
            }
        }
    }

    public static boolean fangAttack(Player player, Entity target, Level level){
        if(player.isCrouching() && player.getFoodData().needsFood()
                && target instanceof LivingEntity livingTarget && livingTarget.getMobType() != MobType.UNDEAD){
            float playerSize = DragonModCompat.getPlayerSize(player);

            if(playerSize > 1 && !player.swinging){
                float playerHunger = 20 - player.getFoodData().getFoodLevel();

                float maxDamage = (float) Math.pow(playerSize, 3);
                maxDamage = Math.min(playerHunger, maxDamage);

                DragonFangs evokerFangs = new DragonFangs(level,
                        livingTarget.getX(), livingTarget.getY() + (livingTarget.getBbHeight() / 2), livingTarget.getZ(), 0, -4, player, maxDamage);
                evokerFangs.setXRot(player.getXRot());
                evokerFangs.setYRot(player.getYRot());
                ScaleTypes.BASE.getScaleData(evokerFangs).setScale(playerSize);
                level.addFreshEntity(evokerFangs);

                // regen food is handled by the fangs
                //livingTarget.hurt(SFDamageSources.DragonBite(evokerFangs, player), maxDamage / DragonModCompat.getPlayerAttack(player));
                //player.getFoodData().eat(Math.round(maxDamage), 0f);

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
    public static float getPlayerTargetSize(Player player){
        return isModsLoaded() ? DragonPehkui.getPlayerTargetSize(player) : 1;
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

    public static class ManaNArtifice {
        public static void restoreMana(Player player){
            if(StickyFoundations.isManaNArtificeLoaded()) DragonManaNArtifice.restoreMana(player);
        }
    }

    public static void startUsingItem(LivingEntityUseItemEvent.Start event) {
        ItemStack itemStack = event.getItem();
        LivingEntity entity = event.getEntity();

        if(entity instanceof Player player && Users.checkUUID(player, Users.REDSTONE_STICK) && getPlayerSize(player) > 1 && itemStack.isEdible()){
            event.setDuration(5);
        }
    }


}
