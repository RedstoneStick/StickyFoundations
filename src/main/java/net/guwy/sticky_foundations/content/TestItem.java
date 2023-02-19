package net.guwy.sticky_foundations.content;

import net.guwy.sticky_foundations.mechanics.hazard.RadiatedItem;
import net.minecraft.world.item.Item;

public class TestItem extends Item implements RadiatedItem {
    public TestItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public double radiationVal() {
        return 200;
    }
}
