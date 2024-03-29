package net.guwy.sticky_foundations.index;

import net.guwy.sticky_foundations.StickyFoundations;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StickyFoundations.MOD_ID);

    /* Example
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModTabs.MATERIALS)));
     */

    // public static final RegistryObject<Item> ADMIN_HELMET = ITEMS.register("admin_helmet",
    //         () -> new AdminArmorItem(ArmorMaterials.DIAMOND, EquipmentSlot.HEAD, new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Item> ASH = ITEMS.register("ash",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)){
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.sticky_foundations.ash.1"));
                    pTooltipComponents.add(Component.translatable("tooltip.sticky_foundations.ash.2"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });

    public static final RegistryObject<Item> SCRAP_METAL = ITEMS.register("scrap_metal",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> EMPTY_COIL = ITEMS.register("empty_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_BASIC = ITEMS.register("circuitry_basic",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_ENHANCED = ITEMS.register("circuitry_enhanced",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_ADVANCED = ITEMS.register("circuitry_advanced",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_INDUSTRIAL = ITEMS.register("circuitry_industrial",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_CAPACITIVE = ITEMS.register("circuitry_capacitive",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_AVIONICS = ITEMS.register("circuitry_avionics",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_OVERCLOCKED = ITEMS.register("circuitry_overclocked",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_RELIABLE = ITEMS.register("circuitry_reliable",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_RELIABLE_ASSEMBLY = ITEMS.register("circuitry_reliable_assembly",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MOTOR = ITEMS.register("motor",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MOLDING_BOWL = ITEMS.register("molding_bowl",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TINY_PILE_OF_GUNPOWDER = ITEMS.register("tiny_pile_of_gunpowder",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> REINFORCED_GLASS = ITEMS.register("reinforced_glass",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> INSULATOR = ITEMS.register("insulator",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
