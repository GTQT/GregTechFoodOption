package gregtechfoodoption.loader.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;
import gregtechfoodoption.common.item.GTFOMetaItem;

public class SorbetChain {
    public static void init() {
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .circuitMeta(1)
                .outputs(GTFOMetaItem.SORBET_PLAIN.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterials.AppleExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_APPLE.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterials.ApricotExtract.getFluid(40), GTFOMaterials.LemonExtract.getFluid(10))
                .outputs(GTFOMetaItem.SORBET_APRICOT.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterials.GrapeExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_GRAPE.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterials.LimeExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_LIME.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterials.ChorusJuice.getFluid(40), GTFOMaterials.LemonExtract.getFluid(10))
                .outputs(GTFOMetaItem.SORBET_CHORUS.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterials.VibrantExtract.getFluid(40), GTFOMaterials.LemonExtract.getFluid(10))
                .outputs(GTFOMetaItem.SORBET_VIBRANT.getStackForm(4))
                .buildAndRegister();


        /*RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterials.OrangeExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_ORANGE.getStackForm(4))
                .buildAndRegister();*/
    }
}
