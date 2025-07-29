package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtechfoodoption.GTFOMaterialHandler;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;

public class VinegarChain {
    public static void init() {
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .fluidInputs(Materials.AceticAcid, 1000)
                .fluidInputs(Materials.Water.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.Vinegar.getFluid(1000))
                .duration(200)
                .EUt(VA[LV])
                .buildAndRegister();

    }
}
