package net.guwy.sticky_foundations.index;

import net.guwy.sticky_foundations.StickyFoundations;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModMinerals {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, StickyFoundations.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StickyFoundations.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StickyFoundations.MOD_ID);






    public static final RegistryObject<Block> FLUORITE_ORE = registerBlock("fluorite_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> FLUORITE_ORE_DEEPSLATE = registerBlock("fluorite_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Item> FLUORITE_CLUSTER = ITEMS.register("fluorite_cluster",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> FLUORITE_DUST = ITEMS.register("fluorite_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));



    public static final RegistryObject<Block> NITER_ORE = registerBlock("niter_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> NITER_ORE_DEEPSLATE = registerBlock("niter_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Item> NITER = ITEMS.register("niter",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));



    public static final RegistryObject<Block> MAGNESIUM_ORE = registerBlock("magnesium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> MAGNESIUM_ORE_DEEPSLATE = registerBlock("magnesium_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Item> RAW_MAGNESIUM = ITEMS.register("raw_magnesium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> CRUSHED_MAGNESIUM = ITEMS.register("crushed_magnesium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_NUGGET = ITEMS.register("magnesium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_DUST = ITEMS.register("magnesium_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> BURNING_MAGNESIUM = ITEMS.register("burning_magnesium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));



    public static final RegistryObject<Block> PALLADIUM_ORE = registerBlock("palladium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(3,7)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> PALLADIUM_ORE_DEEPSLATE = registerBlock("palladium_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(3,7)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Item> PALLADIUM_NUGGET = ITEMS.register("palladium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));



    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(3,7)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> PLATINUM_ORE_DEEPSLATE = registerBlock("platinum_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(3,7)), ModCreativeModeTabs.MAIN);



    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);



    public static final RegistryObject<Block> THORIUM_ORE = registerBlock("thorium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> THORIUM_ORE_DEEPSLATE = registerBlock("thorium_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(0,4)), ModCreativeModeTabs.MAIN);



    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(3,7)), ModCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> URANIUM_ORE_DEEPSLATE = registerBlock("uranium_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(3,7)), ModCreativeModeTabs.MAIN);






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return ModMinerals.BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltipKey){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltipKey){
        return ModMinerals.BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(Component.literal(tooltipKey));
            }
        });
    }


    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block){
        return BLOCKS.register(name, block);
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
        ITEMS.register(eventBus);
    }
}
