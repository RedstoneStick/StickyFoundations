package net.guwy.sticky_foundations.index;

import net.guwy.sticky_foundations.StickyFoundations;
import net.guwy.sticky_foundations.egg.redstone_stick.dragon.fangs.DragonFangs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SFEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StickyFoundations.MOD_ID);

    // Entities
    public static final RegistryObject<EntityType<DragonFangs>> DRAGON_FANGS = ENTITY_TYPES.register("dragon_fangs",
            () -> EntityType.Builder.of((EntityType.EntityFactory<DragonFangs>) DragonFangs::new,
                    MobCategory.MISC).sized(0.5F, 0.8F).build("dragon_fangs"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
