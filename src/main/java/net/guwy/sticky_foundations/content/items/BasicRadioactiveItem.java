package net.guwy.sticky_foundations.content.items;

import net.guwy.sticky_foundations.mechanics.hazard.RadiatedItem;
import net.minecraft.world.item.Item;

public class BasicRadioactiveItem extends Item implements RadiatedItem {
    double radiationVal;

    public BasicRadioactiveItem(Properties pProperties, double radiationVal) {
        super(pProperties);
        this.radiationVal = radiationVal;
    }

    @Override
    public double radiationVal() {
        return radiationVal;
    }
}
