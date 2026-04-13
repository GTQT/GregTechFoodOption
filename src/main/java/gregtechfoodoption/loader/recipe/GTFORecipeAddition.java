package gregtechfoodoption.loader.recipe;

import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.api.utils.GTFOValues;
import gregtechfoodoption.integration.bop.BiomesOPlentyWoodRecipe;
import gregtechfoodoption.integration.enderio.GTFOEIORecipeHandler;
import gregtechfoodoption.loader.recipe.chain.*;
import gregtechfoodoption.common.item.tools.GTFOToolItems;
import gregtechfoodoption.worldgen.trees.GTFOTree;
import net.minecraftforge.fml.common.Loader;

public class GTFORecipeAddition {
    public static void init() {
        CoreChain.init();
        FatChain.init();
        GTFOTree.TREES.forEach(GTFOTree::initRecipes);
        MobExtractionChain.init();
        SeedsChain.init();
        CheeseChain.init();
        BananaProcessingChain.init();
        MicrowaveChain.init();
        GTFOMachineRecipes.init();
        GTFOToolItems.registerCustomRecipes();
        KebabChain.init();
        IceCreamChain.init();
        VanillinChain.init();
        DyeChain.init();
        BritishChain.init();
        PotatoProcessingChain.init();
        AlcoholChain.init();
        PurpleDrinkChain.init();
        CapletChain.init();
        IVBagChain.init();
        ItalianChain.init();
        PastaChain.init();
        PlateChain.init();
        LithiumChain.init();
        BerryChain.init();
        RussianChain.init();
        ChorusChain.init();
        SorbetChain.init();
        SoySauceChain.init();
        VinegarChain.init();

        if (Loader.isModLoaded(GTFOValues.MODID_GCYS)) {
            PopcornChain.init();
            MineralWaterChain.init();
        }
        if(Loader.isModLoaded("biomesoplenty")){
            BiomesOPlentyWoodRecipe.init();
        }
    }

    public static void lowInit() {
    }

    public static void compatInit() {
        BreadsChain.init();
        if (GTFOConfig.gtfoVanillaOverridesConfig.vanillaOverrideChain)
            VanillaOverrideChain.init();
        CoffeeChain.init();
        SmogusChain.init();
        if (Loader.isModLoaded(GTFOValues.MODID_EIO)) {
            GTFOEIORecipeHandler.enderios();
        }
    }
}
