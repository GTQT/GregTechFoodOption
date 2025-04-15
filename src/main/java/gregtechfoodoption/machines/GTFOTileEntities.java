package gregtechfoodoption.machines;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.item.GTFOSimpleMachineMetaTileEntity;
import gregtechfoodoption.machines.farmer.MetaTileEntityFarmer;
import gregtechfoodoption.machines.multiblock.MetaTileEntityBakingOven;
import gregtechfoodoption.machines.multiblock.MetaTileEntityElectricBakingOven;
import gregtechfoodoption.machines.multiblock.MetaTileEntityGreenhouse;
import gregtechfoodoption.machines.multiblock.MetaTileEntitySteamBakingOven;
import gregtechfoodoption.machines.multiblock.kitchen.MetaTileEntityKitchen;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gregtechfoodoption.GregTechFoodOption.location;

/* Takes up IDs 8500 - 8599 */
public class GTFOTileEntities {
    public static final MetaTileEntityMobAgeSorter[] MOB_AGE_SORTER = new MetaTileEntityMobAgeSorter[4];
    public static final MetaTileEntityMobExterminator[] MOB_EXTERMINATOR = new MetaTileEntityMobExterminator[4];
    public static final MetaTileEntityMobExtractor[] MOB_EXTRACTOR = new MetaTileEntityMobExtractor[GTValues.UV];
    public static final MetaTileEntityFarmer[] FARMER = new MetaTileEntityFarmer[4];
    //public static MetaTileEntityBioReactor[] BIOREACTOR = new MetaTileEntityBioReactor[GTValues.V.length];
    public static GTFOSimpleMachineMetaTileEntity[] SLICER = new GTFOSimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static GTFOSimpleMachineMetaTileEntity[] CUISINE_ASSEMBLER = new GTFOSimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityMicrowave[] MICROWAVE = new MetaTileEntityMicrowave[GTValues.V.length - 1];
    public static GTFOSimpleMachineMetaTileEntity[] MULTICOOKER = new GTFOSimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityBakingOven BAKING_OVEN;
    public static MetaTileEntityElectricBakingOven ELECTRIC_BAKING_OVEN;
    public static MetaTileEntitySteamBakingOven STEAM_BAKING_OVEN;
    public static MetaTileEntityGreenhouse GREENHOUSE;
    public static MetaTileEntityKitchen KITCHEN;

    public static void init() {
/*
        BIOREACTOR[2] = MetaTileEntities.registerMetaTileEntity(00, new MetaTileEntityBioReactor(location("bioreactor.hv"), 3));
        BIOREACTOR[3] = MetaTileEntities.registerMetaTileEntity(01, new MetaTileEntityBioReactor(location("bioreactor.ev"), 4));
        BIOREACTOR[4] = MetaTileEntities.registerMetaTileEntity(02, new MetaTileEntityBioReactor(location("bioreactor.iv"), 5));
*/

        registerGTFOSimpleMetaTileEntity(SLICER, 3, "slicer", GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, true, GregTechFoodOption::location, GTUtility.hvCappedTankSizeFunction);
        registerGTFOSimpleMetaTileEntity(CUISINE_ASSEMBLER, 18, "cuisine_assembler", GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES, GTFOClientHandler.CUISINE_ASSEMBLER_OVERLAY, true, GregTechFoodOption::location, GTUtility.hvCappedTankSizeFunction);

        MICROWAVE[1] = registerMetaTileEntity(31, new MetaTileEntityMicrowave(location("microwave.lv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 1));
        MICROWAVE[2] = registerMetaTileEntity(32, new MetaTileEntityMicrowave(location("microwave.mv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 2));
        MICROWAVE[3] = registerMetaTileEntity(33, new MetaTileEntityMicrowave(location("microwave.hv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 3));
        MICROWAVE[4] = registerMetaTileEntity(34, new MetaTileEntityMicrowave(location("microwave.ev"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 4));
        MICROWAVE[5] = registerMetaTileEntity(35, new MetaTileEntityMicrowave(location("microwave.iv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 5));
        if (getMidTier("microwave")) {
            MICROWAVE[6] = registerMetaTileEntity(36, new MetaTileEntityMicrowave(location("microwave.luv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 6));
            MICROWAVE[7] = registerMetaTileEntity(37, new MetaTileEntityMicrowave(location("microwave.zpm"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 7));
            MICROWAVE[8] = registerMetaTileEntity(38, new MetaTileEntityMicrowave(location("microwave.uv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 8));
        }
        if (getHighTier("microwave")) {
            MICROWAVE[9] = registerMetaTileEntity(39, new MetaTileEntityMicrowave(location("microwave.uhv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 9));
            MICROWAVE[10] = registerMetaTileEntity(40, new MetaTileEntityMicrowave(location("microwave.uev"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 10));
            MICROWAVE[11] = registerMetaTileEntity(41, new MetaTileEntityMicrowave(location("microwave.uiv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 11));
            MICROWAVE[12] = registerMetaTileEntity(42, new MetaTileEntityMicrowave(location("microwave.uxv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 12));
            MICROWAVE[13] = registerMetaTileEntity(43, new MetaTileEntityMicrowave(location("microwave.opv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 13));
        }
        BAKING_OVEN = registerMetaTileEntity(16, new MetaTileEntityBakingOven(location("baking_oven")));
        ELECTRIC_BAKING_OVEN = registerMetaTileEntity(17, new MetaTileEntityElectricBakingOven(location("electric_baking_oven")));
        STEAM_BAKING_OVEN = registerMetaTileEntity(44, new MetaTileEntitySteamBakingOven(location("steam_baking_oven"), GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES, 10));

        // Mob Age Sorters, IDs 45-48
        MOB_AGE_SORTER[0] = registerMetaTileEntity(45, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.lv"), 1, 1));
        MOB_AGE_SORTER[1] = registerMetaTileEntity(46, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.mv"), 2, 3));
        MOB_AGE_SORTER[2] = registerMetaTileEntity(47, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.hv"), 3, 5));
        MOB_AGE_SORTER[3] = registerMetaTileEntity(48, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.ev"), 4, 9));

        // Mob Exterminators, IDs 49-52
        MOB_EXTERMINATOR[0] = registerMetaTileEntity(49, new MetaTileEntityMobExterminator(location("mob_exterminator.lv"), 1));
        MOB_EXTERMINATOR[1] = registerMetaTileEntity(50, new MetaTileEntityMobExterminator(location("mob_exterminator.mv"), 2));
        MOB_EXTERMINATOR[2] = registerMetaTileEntity(51, new MetaTileEntityMobExterminator(location("mob_exterminator.hv"), 3));
        MOB_EXTERMINATOR[3] = registerMetaTileEntity(52, new MetaTileEntityMobExterminator(location("mob_exterminator.ev"), 4));

        // Mob Extractor, IDs 53-60
        for (int i = 0; i < MOB_EXTRACTOR.length; i++) {
            if (i > 4 && !getMidTier("mob_extractor")) continue;

            String voltageName = GTValues.VN[i + 1].toLowerCase();
            MOB_EXTRACTOR[i] = registerMetaTileEntity(53 + i,
                    new MetaTileEntityMobExtractor(location(String.format("%s.%s", "mob_extractor", voltageName)), GTFORecipeMaps.MOB_EXTRACTOR_RECIPES, GTFOClientHandler.MOB_EXTRACTOR_OVERLAY, i + 1, false, GTUtility.largeTankSizeFunction));
        }

        // Farmer, IDs 61-64
        FARMER[0] = registerMetaTileEntity(61, new MetaTileEntityFarmer(location("farmer.lv"), 1, 20));
        FARMER[1] = registerMetaTileEntity(62, new MetaTileEntityFarmer(location("farmer.mv"), 2, 10));
        FARMER[2] = registerMetaTileEntity(63, new MetaTileEntityFarmer(location("farmer.hv"), 3, 5));
        FARMER[3] = registerMetaTileEntity(64, new MetaTileEntityFarmer(location("farmer.ev"), 4, 2));

        GREENHOUSE = registerMetaTileEntity(65, new MetaTileEntityGreenhouse(location("greenhouse")));

        registerGTFOSimpleMetaTileEntity(MULTICOOKER, 66, "multicooker", GTFORecipeMaps.MULTICOOKER_RECIPES, GTFOClientHandler.MULTICOOKER_OVERLAY, true, GregTechFoodOption::location, GTUtility.hvCappedTankSizeFunction);

        KITCHEN = registerMetaTileEntity(81, new MetaTileEntityKitchen(location("kitchen")));
    }

    public static void registerGTFOSimpleMetaTileEntity(GTFOSimpleMachineMetaTileEntity[] machines, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing, Function<String, ResourceLocation> resourceId, Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i < machines.length - 1; ++i) {
            if (i <= 4 || getMidTier(name)) {
                if (i > 7 && !getHighTier(name)) {
                    break;
                }

                String voltageName = GTValues.VN[i + 1].toLowerCase();
                machines[i + 1] = registerMetaTileEntity(startId + i, new GTFOSimpleMachineMetaTileEntity(resourceId.apply(String.format("%s.%s", name, voltageName)), map, texture, i + 1, hasFrontFacing, tankScalingFunction));
            }
        }

    }
}
