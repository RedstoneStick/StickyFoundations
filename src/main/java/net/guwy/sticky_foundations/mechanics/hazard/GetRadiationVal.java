package net.guwy.sticky_foundations.mechanics.hazard;

import blusunrize.immersiveengineering.common.register.IEItems;
import com.simibubi.create.content.contraptions.components.press.MechanicalPressTileEntity;
import com.simibubi.create.foundation.block.ITE;
import net.guwy.sticky_foundations.index.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

public class GetRadiationVal {
    public static double getVal(ItemStack itemStack){
        Item item = itemStack.getItem();

        if(item instanceof RadiatedItem){
            RadiatedItem radiatedItem = (RadiatedItem) item;
            return radiatedItem.radiationVal();
        }



        else if (itemStack.is(ModTags.Items.URANIUM_INGOT)) {return 0.35;}
        else if (itemStack.is(ModTags.Items.URANIUM_NUGGET)) {return 0.035;}
        else if (itemStack.is(ModTags.Items.URANIUM_RAW)) {return 0.02;}
        else if (itemStack.is(ModTags.Items.URANIUM_PLATE)) {return 0.35;}
        else if (itemStack.is(ModTags.Items.URANIUM_DUST)) {return 1.05;}
        else if (itemStack.is(ModTags.Items.URANIUM_ORE)) {return 0.015;}
        else if (itemStack.is(ModTags.Items.URANIUM_BLOCK)) {return 3.5;}
        else if (itemStack.is(ModTags.Items.URANIUM_BLOCK_RAW)) {return 0.2;}



        else {
            return 0;
        }
    }

    public static double getStackVal(ItemStack itemStack){
        return getVal(itemStack) * itemStack.getCount();
    }
}


