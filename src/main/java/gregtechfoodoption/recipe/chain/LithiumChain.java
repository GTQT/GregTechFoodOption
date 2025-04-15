package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.utils.GTFOUtils;

import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.LithiumCarbonate;
import static gregtechfoodoption.GTFOMaterialHandler.LithiumOxide;


public class LithiumChain {
    public static void init() {
        GTFOUtils.roasterProxy().recipeBuilder()
                .input(dust, Materials.Lithium, 2)
                .fluidInputs(Materials.Oxygen.getFluid(1000))
                .output(dust,LithiumOxide,3)
                .EUt(60)
                .duration(60)
                .buildAndRegister();

        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust,LithiumOxide,3)
                .fluidInputs(Materials.CarbonDioxide.getFluid(1000))
                .output(dust,LithiumCarbonate,6)
                .EUt(16)
                .duration(140)
                .buildAndRegister();
    }
}
