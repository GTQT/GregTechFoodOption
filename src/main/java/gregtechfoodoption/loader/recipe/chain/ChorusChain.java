package gregtechfoodoption.loader.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;
import gregtechfoodoption.common.item.GTFOMetaItem;
import gregtechfoodoption.loader.recipe.GTFORecipeMaps;
import net.minecraft.init.Items;

import static gregtech.api.GTValues.HV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.CANNER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.common.item.GTFOMetaItem.*;

public class ChorusChain {
    public static void init() {
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(200)
                .input(Items.CHORUS_FRUIT)
                .fluidOutputs(GTFOMaterials.ChorusJuice.getFluid(50))
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(100)
                .input(Items.CHORUS_FRUIT_POPPED)
                .fluidOutputs(GTFOMaterials.ChorusJuice.getFluid(100))
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(7).duration(200)
                .input(OrePrefix.dust, EnderPearl)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(GTFOMaterials.EnderPearlSolution.getFluid(1000))
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(500)
                .fluidInputs(GTFOMaterials.EnderPearlSolution.getFluid(1000))
                .fluidOutputs(GTFOMaterials.EnderSugarSolution.getFluid(1000))
                .chancedOutput(OrePrefix.dust, Potassium, 3, 3000, 1000)
                .chancedFluidOutput(Nitrogen.getFluid(3000), 1000, 1500)
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(VA[HV]).duration(400)
                .fluidInputs(GTFOMaterials.EnderSugarSolution.getFluid(1000))
                .fluidInputs(GTFOMaterials.FermentedChorusJuice.getFluid(25))
                .fluidOutputs(GTFOMaterials.VibrantExtract.getFluid(1000))
                .buildAndRegister();

        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().EUt(2).duration(1000)
                .notConsumable(GTFOMaterials.LacticAcidBacteria.getFluid(1))
                .input(Items.CHORUS_FRUIT)
                .outputs(GTFOMetaItem.FERMENTED_CHORUS.getStackForm())
                .buildAndRegister();
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().EUt(2).duration(800)
                .notConsumable(GTFOMaterials.LacticAcidBacteria.getFluid(1))
                .input(Items.CHORUS_FRUIT_POPPED)
                .outputs(GTFOMetaItem.FERMENTED_CHORUS.getStackForm())
                .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(GTFOMetaItem.FERMENTED_CHORUS.getStackForm())
                .fluidOutputs(GTFOMaterials.FermentedChorusJuice.getFluid(50))
                .buildAndRegister();
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(PIE_CRUST.getStackForm())
                .fluidInputs(GTFOMaterials.FermentedChorusJuice.getFluid(25))
                .outputs(FERMENTED_CHORUS_PIE.getStackForm())
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(60).duration(20)
                .fluidInputs(GTFOMaterials.FermentedChorusJuice.getFluid(50))
                .input(Items.SUGAR, 9)
                .fluidInputs(GTFOMaterials.CarbonatedWater.getFluid(1000))
                .fluidOutputs(GTFOMaterials.Antaf.getFluid(2000))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterials.Antaf.getFluid(500))
                .inputs(PLASTIC_BOTTLE.getStackForm())
                .outputs(ANTAF.getStackForm())
                .EUt(30)
                .duration(20)
                .buildAndRegister();
    }
}
