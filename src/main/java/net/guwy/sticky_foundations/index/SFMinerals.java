package net.guwy.sticky_foundations.index;

import net.guwy.sticky_foundations.StickyFoundations;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FallingBlock;
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

public class SFMinerals {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, StickyFoundations.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StickyFoundations.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StickyFoundations.MOD_ID);



    public static final RegistryObject<Item> IRON_DUST
            = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> IRON_FOIL
            = ITEMS.register("iron_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> IRON_WIRE
            = ITEMS.register("iron_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> IRON_COIL
            = ITEMS.register("iron_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Item> COPPER_DUST
            = ITEMS.register("copper_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> COPPER_FOIL
            = ITEMS.register("copper_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> COPPER_WIRE
            = ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> COPPER_COIL
            = ITEMS.register("copper_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Item> GOLD_DUST
            = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> GOLD_FOIL
            = ITEMS.register("gold_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> GOLD_WIRE
            = ITEMS.register("gold_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> GOLD_COIL
            = ITEMS.register("gold_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> FLUORITE_ORE = registerBlock("fluorite_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(0,4)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> FLUORITE_ORE_DEEPSLATE = registerBlock("fluorite_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(0,4)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Item> FLUORITE_CLUSTER = ITEMS.register("fluorite_cluster",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> FLUORITE_DUST = ITEMS.register("fluorite_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> NITER_ORE = registerBlock("niter_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(0,1)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> NITER_ORE_DEEPSLATE = registerBlock("niter_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(0,1)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Item> NITER = ITEMS.register("niter",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> MAGNESIUM_ORE = registerBlock("magnesium_ore", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> MAGNESIUM_ORE_DEEPSLATE = registerBlock("magnesium_ore_deepslate", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> RAW_MAGNESIUM_BLOCK = registerBlock("raw_magnesium_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.COLOR_BLUE).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> MAGNESIUM_BLOCK = registerBlock("magnesium_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Item> RAW_MAGNESIUM = ITEMS.register("raw_magnesium",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CRUSHED_MAGNESIUM = ITEMS.register("crushed_magnesium",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_NUGGET = ITEMS.register("magnesium_nugget",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_DUST = ITEMS.register("magnesium_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> BURNING_MAGNESIUM = ITEMS.register("burning_magnesium",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)){
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.literal("[HOT]").withStyle(ChatFormatting.DARK_RED));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
                @Override
                public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                    pEntity.setSecondsOnFire(20);
                    super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
                }
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                    return 2400;
                }
            });

    public static final RegistryObject<Item> MAGNESIUM_PLATE = ITEMS.register("magnesium_plate",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_FOIL = ITEMS.register("magnesium_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_WIRE = ITEMS.register("magnesium_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> MAGNESIUM_COIL = ITEMS.register("magnesium_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> PALLADIUM_ORE = registerBlock("palladium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(1,4)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> PALLADIUM_ORE_DEEPSLATE = registerBlock("palladium_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(1,4)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> PALLADIUM_BLOCK = registerBlock("palladium_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Item> PALLADIUM_NUGGET = ITEMS.register("palladium_nugget",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_INGOT= ITEMS.register("palladium_ingot",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_DUST = ITEMS.register("palladium_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_PLATE = ITEMS.register("palladium_plate",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_FOIL = ITEMS.register("palladium_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_WIRE = ITEMS.register("palladium_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_COIL = ITEMS.register("palladium_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> PLATINUM_ORE_DEEPSLATE = registerBlock("platinum_ore_deepslate", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> PLATINUM_BLOCK = registerBlock("platinum_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PLATINUM_DUST = ITEMS.register("platinum_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PLATINUM_PLATE = ITEMS.register("platinum_plate",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PLATINUM_FOIL = ITEMS.register("platinum_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PLATINUM_WIRE = ITEMS.register("platinum_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> PLATINUM_COIL = ITEMS.register("platinum_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> RUTILE_BLOCK = registerBlock("rutile_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_RED).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL).noOcclusion()), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CRUSHED_TITANIUM = ITEMS.register("crushed_titanium",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_DUST = ITEMS.register("titanium_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_FOIL = ITEMS.register("titanium_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_WIRE = ITEMS.register("titanium_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_COIL = ITEMS.register("titanium_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> THORIUM_ORE = registerBlock("thorium_ore", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE)));

    public static final RegistryObject<Block> THORIUM_ORE_DEEPSLATE = registerBlock("thorium_ore_deepslate", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Item> RAW_THORIUM = ITEMS.register("raw_thorium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRUSHED_THORIUM = ITEMS.register("crushed_thorium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THORIUM_INGOT = ITEMS.register("thorium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THORIUM_NUGGET = ITEMS.register("thorium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THORIUM_DUST = ITEMS.register("thorium_dust",
            () -> new Item(new Item.Properties()));



    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE), UniformInt.of(1,2)));

    public static final RegistryObject<Block> URANIUM_ORE_DEEPSLATE = registerBlock("uranium_ore_deepslate", () ->
            new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE), UniformInt.of(1,2)));

    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRUSHED_URANIUM = ITEMS.register("crushed_uranium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> URANIUM_DUST = ITEMS.register("uranium_dust",
            () -> new Item(new Item.Properties()));



    public static final RegistryObject<Block> BAUXITE_POOR = registerBlock("bauxite_poor", () ->
            new FallingBlock(BlockBehaviour.Properties.of(Material.SAND).
                    strength(1f).explosionResistance(1f).
                    sound(SoundType.SAND)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> BAUXITE_NORMAL = registerBlock("bauxite_normal", () ->
            new FallingBlock(BlockBehaviour.Properties.of(Material.SAND).
                    strength(1f).explosionResistance(1f).
                    sound(SoundType.SAND)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerBlock("aluminum_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)), SFCreativeModTabs.MAIN);

    //public static final RegistryObject<Item> CRUSHED_ALUMINUM = ITEMS.register("crushed_aluminum",
    //        () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> ALUMINUM_DUST = ITEMS.register("aluminum_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> ALUMINUM_PLATE = ITEMS.register("aluminum_plate",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> ALUMINUM_FOIL = ITEMS.register("aluminum_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> ALUMINUM_WIRE = ITEMS.register("aluminum_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> ALUMINUM_COIL = ITEMS.register("aluminum_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Block> LEAD_ORE = registerBlock("lead_ore", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).
                    strength(3f).explosionResistance(3f).requiresCorrectToolForDrops().
                    sound(SoundType.STONE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> LEAD_ORE_DEEPSLATE = registerBlock("lead_ore_deepslate", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).
                    strength(3f).explosionResistance(6f).requiresCorrectToolForDrops().
                    sound(SoundType.DEEPSLATE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> RAW_LEAD_BLOCK = registerBlock("raw_lead_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Block> LEAD_BLOCK = registerBlock("lead_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).
                    strength(6f).explosionResistance(5f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)), SFCreativeModTabs.MAIN);

    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> CRUSHED_LEAD = ITEMS.register("crushed_lead",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> LEAD_DUST = ITEMS.register("lead_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> LEAD_PLATE = ITEMS.register("lead_plate",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> LEAD_FOIL = ITEMS.register("lead_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> LEAD_WIRE = ITEMS.register("lead_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> LEAD_COIL = ITEMS.register("lead_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Item> SULFUR_DUST = ITEMS.register("sulfur_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_DUST = ITEMS.register("steel_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_FOIL = ITEMS.register("steel_foil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_WIRE = ITEMS.register("steel_wire",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_COIL = ITEMS.register("steel_coil",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));



    public static final RegistryObject<Item> COAL_DUST = ITEMS.register("coal_dust",
            () -> new Item(new Item.Properties().tab(SFCreativeModTabs.MAIN)));






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    /** No creative mod tab */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return SFMinerals.BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    /** No creative mod tab */
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return SFMinerals.BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
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
