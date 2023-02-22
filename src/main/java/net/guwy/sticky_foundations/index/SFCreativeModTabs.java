package net.guwy.sticky_foundations.index;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SFCreativeModTabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab("sticky_foundations_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SFMinerals.URANIUM_ORE.get());
        }
    };
}
