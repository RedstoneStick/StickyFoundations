package net.guwy.sticky_foundations.index;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeModeTabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab("sticky_foundations_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.STICK);
        }
    };
}
