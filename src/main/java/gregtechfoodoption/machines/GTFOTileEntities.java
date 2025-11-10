package gregtechfoodoption.machines;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
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

import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gregtechfoodoption.GregTechFoodOption.location;

/* Takes up IDs 8500 - 8599 */
public class GTFOTileEntities {
    public static final MetaTileEntityMobAgeSorter[] MOB_AGE_SORTER = new MetaTileEntityMobAgeSorter[4];
    public static final MetaTileEntityMobExterminator[] MOB_EXTERMINATOR = new MetaTileEntityMobExterminator[4];
    public static final MetaTileEntityMobExtractor[] MOB_EXTRACTOR = new MetaTileEntityMobExtractor[GTValues.UV];
    public static final MetaTileEntityFarmer[] FARMER = new MetaTileEntityFarmer[4];
    //public static MetaTileEntityBioReactor[] BIOREACTOR = new MetaTileEntityBioReactor[GTValues.V.length];
    public static SimpleMachineMetaTileEntity[] SLICER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] CUISINE_ASSEMBLER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] ELECTRIC_BAKING_OVEN = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] MULTICOOKER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];

    public static MetaTileEntityMicrowave[] MICROWAVE = new MetaTileEntityMicrowave[GTValues.V.length - 1];

    public static MetaTileEntityBakingOven LARGE_BAKING_OVEN;
    public static MetaTileEntityElectricBakingOven LARGE_ELECTRIC_BAKING_OVEN;
    public static MetaTileEntitySteamBakingOven STEAM_BAKING_OVEN;
    public static MetaTileEntityGreenhouse GREENHOUSE;
    public static MetaTileEntityKitchen KITCHEN;

    public static void init() {
/*
        BIOREACTOR[2] = MetaTileEntities.registerMetaTileEntity(00, new MetaTileEntityBioReactor(location("bioreactor.hv"), 3));
        BIOREACTOR[3] = MetaTileEntities.registerMetaTileEntity(01, new MetaTileEntityBioReactor(location("bioreactor.ev"), 4));
        BIOREACTOR[4] = MetaTileEntities.registerMetaTileEntity(02, new MetaTileEntityBioReactor(location("bioreactor.iv"), 5));
*/

        registerSimpleMetaTileEntity(SLICER, 0, "slicer", GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, true, GregTechFoodOption::location, GTUtility.hvCappedTankSizeFunction);
        registerSimpleMetaTileEntity(CUISINE_ASSEMBLER, 15, "cuisine_assembler", GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES, GTFOClientHandler.CUISINE_ASSEMBLER_OVERLAY, true, GregTechFoodOption::location, GTUtility.hvCappedTankSizeFunction);
        registerSimpleMetaTileEntity(MULTICOOKER, 30, "multicooker", GTFORecipeMaps.MULTICOOKER_RECIPES, GTFOClientHandler.MULTICOOKER_OVERLAY, true, GregTechFoodOption::location, GTUtility.hvCappedTankSizeFunction);
        registerSimpleMetaTileEntity(ELECTRIC_BAKING_OVEN, 45, "electric_baking_oven", GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES, GTFOClientHandler.ELECTRIC_OVEN_OVERLAY, true, GregTechFoodOption::location, GTUtility.hvCappedTankSizeFunction);

        int endPos = getMidTier("microwave") ? (getHighTier("microwave") ? 12 : 7) : 4;
        for (int i = 0; i < endPos; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            MICROWAVE[i] = registerMetaTileEntity(60 + i, new MetaTileEntityMicrowave(location("microwave." + voltageName), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, i));
        }

        // Mob Age Sorters, IDs 45-48
        int[] suckingRange = {1, 3, 5, 9};
        for (int i = 0; i < MOB_AGE_SORTER.length; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            MOB_AGE_SORTER[i] = registerMetaTileEntity(75 + i, new MetaTileEntityMobAgeSorter(location("mob_age_sorter." + voltageName), i + 1, suckingRange[i]));
        }

        // Mob Exterminators, IDs 49-52
        for (int i = 0; i < MOB_EXTERMINATOR.length; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            MOB_EXTERMINATOR[i] = registerMetaTileEntity(90 + i, new MetaTileEntityMobExterminator(location("mob_exterminator." + voltageName), i + 1));
        }

        // Mob Extractor, IDs 53-60
        for (int i = 0; i < MOB_EXTRACTOR.length; i++) {
            if (i > 4 && !getMidTier("mob_extractor")) continue;

            String voltageName = GTValues.VN[i + 1].toLowerCase();
            MOB_EXTRACTOR[i] = registerMetaTileEntity(105 + i,
                    new MetaTileEntityMobExtractor(location(String.format("%s.%s", "mob_extractor", voltageName)), GTFORecipeMaps.MOB_EXTRACTOR_RECIPES, GTFOClientHandler.MOB_EXTRACTOR_OVERLAY, i + 1, false, GTUtility.largeTankSizeFunction));
        }

        // Farmer, IDs 61-64
        int[] ticksPerAction = {20, 10, 5, 2};
        for (int i = 0; i < FARMER.length; i++) {
            String voltageName = GTValues.VN[i + 1].toLowerCase();
            FARMER[i] = registerMetaTileEntity(120 + i, new MetaTileEntityFarmer(location("farmer." + voltageName), i + 1, ticksPerAction[i]));
        }

        GREENHOUSE = registerMetaTileEntity(200, new MetaTileEntityGreenhouse(location("greenhouse")));

        LARGE_BAKING_OVEN = registerMetaTileEntity(201, new MetaTileEntityBakingOven(location("large_baking_oven")));
        STEAM_BAKING_OVEN = registerMetaTileEntity(202, new MetaTileEntitySteamBakingOven(location("steam_baking_oven")));
        LARGE_ELECTRIC_BAKING_OVEN = registerMetaTileEntity(203, new MetaTileEntityElectricBakingOven(location("large_electric_baking_oven")));

        KITCHEN = registerMetaTileEntity(204, new MetaTileEntityKitchen(location("kitchen")));
    }
}
