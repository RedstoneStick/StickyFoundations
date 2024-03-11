package net.guwy.sticky_foundations;

import com.mojang.logging.LogUtils;
import net.guwy.sticky_foundations.egg.SpecialItems;
import net.guwy.sticky_foundations.index.*;
import net.guwy.sticky_foundations.world.feature.ModConfiguredFeatures;
import net.guwy.sticky_foundations.world.feature.ModPlacedFeatures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(StickyFoundations.MOD_ID)
public class StickyFoundations {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "sticky_foundations";



    // Is mod loaded variables
    private static boolean createLoaded = false;
    private static boolean mekanismLoaded = false;
    private static boolean pehkuiLoaded = false;
    private static boolean arsNouveauLoaded = false;
    private static boolean ironsSpelbooksLoaded = false;
    private static boolean mahouTsukaiLoaded = false;
    private static boolean manaNArtificeLoaded = false;



    public StickyFoundations() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SFItems.register(eventBus);
        SFMinerals.register(eventBus);

        SpecialItems.register(eventBus);
        SFEntityTypes.register(eventBus);

        ModConfiguredFeatures.register(eventBus);
        ModPlacedFeatures.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);

        // Config registries
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SFConfigs.Client.SPEC, "sticky_foundations-client.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Is mod loaded checks
        createLoaded = ModList.get().isLoaded("create");
        mekanismLoaded = ModList.get().isLoaded("mekanism");
        pehkuiLoaded = ModList.get().isLoaded("pehkui");
        arsNouveauLoaded = ModList.get().isLoaded("ars_nouveau");
        ironsSpelbooksLoaded = ModList.get().isLoaded("irons_spellbooks");
        mahouTsukaiLoaded = ModList.get().isLoaded("mahoutsukai");
        manaNArtificeLoaded = ModList.get().isLoaded("mna");
    }



    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            SFNetworking.register();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    // Is mod loaded returnals
    public static boolean isCreateLoaded()
    {
        return createLoaded;
    }
    public static boolean isMekanismLoaded()
    {
        return mekanismLoaded;
    }
    public static boolean isPehkuiLoaded()
    {
        return pehkuiLoaded;
    }
    public static boolean isArsNouveauLoaded()
    {
        return arsNouveauLoaded;
    }
    public static boolean isIronsSpelbooksLoaded()
    {
        return ironsSpelbooksLoaded;
    }
    public static boolean isMahouTsukaiLoaded()
    {
        return mahouTsukaiLoaded;
    }
    public static boolean isManaNArtificeLoaded()
    {
        return manaNArtificeLoaded;
    }
}
