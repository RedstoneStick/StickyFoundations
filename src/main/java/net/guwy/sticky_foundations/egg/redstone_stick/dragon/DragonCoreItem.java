package net.guwy.sticky_foundations.egg.redstone_stick.dragon;

import net.guwy.sticky_foundations.egg.Users;
import net.guwy.sticky_foundations.index.SFDamageSources;
import net.guwy.sticky_foundations.index.SFNetworking;
import net.guwy.sticky_foundations.utils.NumberUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DragonCoreItem extends Item {
    private final int
            DEPLETED = 0,
            SUPPRESSED = 1,
            ON_1 = 2,
            ON_2 = 3,
            ON_3 = 4,
            ON_4 = 5,
            ON_5 = 6;

    private String C_DATA = "CustomModelData";

    public DragonCoreItem(Properties pProperties) {
        super(pProperties.rarity(Rarity.EPIC).fireResistant().stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if(pEntity instanceof Player player){
            if(!pEntity.level.isClientSide){

                if(DragonModCompat.isModsLoaded()){
                    if(Users.checkUUID(player, Users.REDSTONE_STICK)){
                        CompoundTag nbt = pStack.getTag() != null ? pStack.getTag() : new CompoundTag();
                        int state = nbt.getInt(C_DATA);

                        // check if the food is low
                        if(player.getFoodData().getFoodLevel() <= 6){
                            state = DEPLETED;
                        } else if (state == DEPLETED){
                            state = ON_1;
                        }

                        nbt.putInt(C_DATA, state);
                        pStack.setTag(nbt);


                        // print state on action bar if holding
                        if(player.getItemInHand(InteractionHand.MAIN_HAND) == pStack || player.getItemInHand(InteractionHand.OFF_HAND) == pStack){
                            player.displayClientMessage(Component.literal(getStateText(pStack)), true);
                        }
                    } else {
                        player.hurt(SFDamageSources.DRAGON_OVERLOAD, Float.MAX_VALUE);
                        pLevel.playSound(null, player, SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1, 1);
                    }

                    if(player.getFoodData().getFoodLevel() > 6){
                        // Ars noveau
                        DragonModCompat.ArsNoveau.increaseMana(player, 10);

                        // Iron's spellbooks
                        DragonModCompat.IronsSpellbooks.increaseMana(player, 1);

                        // Mahou tsukai
                        DragonModCompat.MahouTsukai.increaseMana(player, 10000);

                        // Mana n Artifice
                        DragonModCompat.ManaNArtifice.restoreMana(player);
                    }
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pPlayer.level.isClientSide){
            ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

            if(Users.checkUUID(pPlayer, Users.REDSTONE_STICK)
                    && pPlayer.getFoodData().getFoodLevel() > 6
                    && !pPlayer.getCooldowns().isOnCooldown(itemStack.getItem())
                    && DragonModCompat.isModsLoaded()){

                pPlayer.getCooldowns().addCooldown(itemStack.getItem(), 20);
                CompoundTag nbt = itemStack.getTag() != null ? itemStack.getTag() : new CompoundTag();
                int state = nbt.getInt(C_DATA);

                state += pPlayer.isCrouching() ? -1 : +1;

                if(state == ON_5) {
                    // plays on the feet
                    pLevel.playSound(null, pPlayer, new SoundEvent(SoundEvents.ENDER_DRAGON_GROWL.getLocation(), 500), SoundSource.PLAYERS, 1, 1.5f);
                    pLevel.playSound(null, pPlayer.getOnPos().offset(0, 30, 0), SoundEvents.ENDER_DRAGON_GROWL, SoundSource.PLAYERS, 1, 1.5f);
                }
                if(state == SUPPRESSED) pLevel.playSound(null, pPlayer, SoundEvents.CONDUIT_DEACTIVATE, SoundSource.PLAYERS, 1, 0.6f);

                state = Math.max(SUPPRESSED, Math.min(ON_5, state));

                nbt.putInt(C_DATA, state);
                itemStack.setTag(nbt);

                DragonModCompat.updateScale(pPlayer);
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        Player player = (pLevel != null && Minecraft.getInstance().player != null) ? pLevel.getPlayerByUUID(Minecraft.getInstance().player.getUUID()) : null;

        // player height
        pTooltipComponents.add(Component.literal(
                Component.translatable("item.sticky_foundations.dragon_core.tooltip.state").getString() + ": " + getStateText(pStack)
        ).withStyle(ChatFormatting.GRAY));

        // detailed modifiers
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.literal("Attack"
                    + ": " + NumberUtils.quickRound(DragonModCompat.getPlayerAttack(player), 0.01))
                    .withStyle(ChatFormatting.DARK_GRAY));
            pTooltipComponents.add(Component.literal("Attack Speed"
                    + ": " + NumberUtils.quickRound(DragonModCompat.getPlayerAttackSpeed(player), 0.01))
                    .withStyle(ChatFormatting.DARK_GRAY));
            pTooltipComponents.add(Component.literal("Knockback" +
                    ": " + NumberUtils.quickRound(DragonModCompat.getPlayerAttackKnockback(player), 0.01))
                    .withStyle(ChatFormatting.DARK_GRAY));
            pTooltipComponents.add(Component.literal("Defense" +
                    ": " + NumberUtils.quickRound(DragonModCompat.getPlayerDefense(player), 0.01))
                    .withStyle(ChatFormatting.DARK_GRAY));
            pTooltipComponents.add(Component.literal("Mining Speed"
                    + ": " + NumberUtils.quickRound(DragonModCompat.getPlayerMiningSpeed(player), 0.01))
                    .withStyle(ChatFormatting.DARK_GRAY));
            pTooltipComponents.add(Component.literal("Jump Height"
                    + ": " + NumberUtils.quickRound(DragonModCompat.getPlayerJumpHeight(player), 0.01))
                    .withStyle(ChatFormatting.DARK_GRAY));
            pTooltipComponents.add(Component.literal("Step Height"
                    + ": " + NumberUtils.quickRound(DragonModCompat.getPlayerStepHeight(player), 0.01))
                    .withStyle(ChatFormatting.DARK_GRAY));
        } else {
            pTooltipComponents.add(Component.translatable("item.sticky_foundations.dragon_core.tooltip.shift").withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    private String getStateText(ItemStack pStack){
        CompoundTag nbt = pStack.getTag() != null ? pStack.getTag() : new CompoundTag();
        int state = nbt.getInt(C_DATA);
        String text = "";
        if(state == DEPLETED) text = Component.translatable("item.sticky_foundations.dragon_core.tooltip.state.depleted").getString() + " (!<1.8m)";
        else if(state == SUPPRESSED) text = Component.translatable("item.sticky_foundations.dragon_core.tooltip.state.suppressed").getString() + " (~1.8m)";
        else if(state == ON_1) text = "1/5 (3m)";
        else if(state == ON_2) text = "2/5 (5m)";
        else if(state == ON_3) text = "3/5 (10m)";
        else if(state == ON_4) text = "4/5 (20m)";
        else if(state == ON_5) text = "5/5 (50m)";

        return text;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem() != newStack.getItem();
    }
}
