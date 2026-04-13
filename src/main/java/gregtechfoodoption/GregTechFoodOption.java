package gregtechfoodoption;

import gregtech.api.GregTechAPI;
import gregtech.api.util.Mods;
import gregtechfoodoption.api.unification.materials.GTFOMaterialHandler;
import gregtechfoodoption.api.utils.GTFOValues;
import gregtechfoodoption.common.CommonProxy;
import gregtechfoodoption.common.GTFODropsEventHandler;
import gregtechfoodoption.common.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.common.covers.GTFOCoverBehaviors;
import gregtechfoodoption.common.entity.GTFOEntities;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.integration.top.GTFOTOPCompatibility;
import gregtechfoodoption.common.machines.GTFOTileEntities;
import gregtechfoodoption.common.machines.farmer.FarmerModeRegistry;
import gregtechfoodoption.network.PacketAppleCoreFoodDivisorUpdate;
import gregtechfoodoption.api.utils.GTFOConfigOverrider;
import gregtechfoodoption.worldgen.GTFODungeonLootLoader;
import gregtechfoodoption.worldgen.GTFOWorldGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GregTechFoodOption.MODID, name = GregTechFoodOption.NAME, version = GregTechFoodOption.VERSION,
        dependencies = "required-after:gregtech@[2.9.0-beta,);" + "after:" + GTFOValues.MODID_NU)

public class GregTechFoodOption {
    public static final String MODID = "gregtechfoodoption";
    public static final String NAME = "GregTech Food Option";
    public static final String VERSION = Tags.VERSION;

    @Mod.Instance
    public static GregTechFoodOption instance;

    public static ResourceLocation location(String name) {
        return new ResourceLocation(GregTechFoodOption.MODID, name);
    }

    @SidedProxy(modId = MODID, clientSide = "gregtechfoodoption.client.ClientProxy", serverSide = "gregtechfoodoption.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onStartup(FMLConstructionEvent event) {
        GTFOConfigOverrider.init();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GregTechAPI.networkHandler.registerPacket(PacketAppleCoreFoodDivisorUpdate.class);
        proxy.preLoad();

        MinecraftForge.EVENT_BUS.register(new GTFODropsEventHandler());

        GTFOMetaBlocks.init();
        GTFOTileEntities.init();

        MinecraftForge.EVENT_BUS.register(new GTFOEntities()); // For entity registration through EntityEntries!

        if (GTFOConfig.gtfoOtherFoodModConfig.appleCoreCompat)
            MinecraftForge.EVENT_BUS.register(new GTFOAppleCoreCompat());

        GTFOClientHandler.registerSounds();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FarmerModeRegistry.registerDefaultModes();
        try {
            proxy.onLoad();
        } catch (Exception e) {
        }
        if (Loader.isModLoaded(Mods.Names.THE_ONE_PROBE)) {
            GTFOTOPCompatibility.registerCompatibility();
        }
        GTFOCoverBehaviors.init();
        GameRegistry.registerWorldGenerator(GTFOWorldGenerator.INSTANCE, 1);
        GTFOMaterialHandler.registerPropertyTooltips();

        GTFODungeonLootLoader.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.onPostLoad();
    }
}
