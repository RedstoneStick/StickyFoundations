package net.guwy.sticky_foundations.egg;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.egg.redstone_stick.dragon.DragonCoreItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpecialItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StickyFoundations.MOD_ID);

    public static final RegistryObject<Item> DRAGON_CORE
            = ITEMS.register("dragon_core",
            () -> new DragonCoreItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
