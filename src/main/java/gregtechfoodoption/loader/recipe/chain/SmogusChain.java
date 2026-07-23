package gregtechfoodoption.loader.recipe.chain;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;
import gregtechfoodoption.api.unification.materials.GTFOOreDictItems;
import gregtechfoodoption.api.utils.GTFOUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.api.unification.materials.GTFOMaterials.CaneSyrup;
import static gregtechfoodoption.common.item.GTFOMetaItem.*;
import static gregtechfoodoption.loader.recipe.GTFORecipeMaps.*;


public class SmogusChain {
    public static void init() {
        removeRecipesByInputs(MACERATOR_RECIPES, new ItemStack(Items.DYE, 1, 3));

        ItemStack[] smoresout = {
                SMORE_SMINGOT.getStackForm(),
                MORESMORE_DOUBLESMINGOT.getStackForm(),
                FOURSMORE_QUADSMINGOT.getStackForm(),
                EIGHT_SMORE.getStackForm(),
                SIXTEEN_SMORE.getStackForm(),
                THIRTY_TWO_SMORE.getStackForm(),
                SIXTY_FOUR_SMORE.getStackForm(),
                SMOGUS.getStackForm(),
                MORE_SMOGUS.getStackForm(),
                FOUR_SMOGUS.getStackForm(),
                HEART_SMOGUS.getStackForm()};
        ItemStack[] smoresin = {
                GRAHAM_CRACKER.getStackForm(2),
                SMORE_SMINGOT.getStackForm(2),
                MORESMORE_DOUBLESMINGOT.getStackForm(2),
                FOURSMORE_QUADSMINGOT.getStackForm(2),
                EIGHT_SMORE.getStackForm(2),
                SIXTEEN_SMORE.getStackForm(2),
                THIRTY_TWO_SMORE.getStackForm(2),
                SIXTY_FOUR_SMORE.getStackForm(2),
                SMOGUS.getStackForm(2),
                MORE_SMOGUS.getStackForm(2),
                FOUR_SMOGUS.getStackForm(2)};
        int euPerTick = 1920;
        int ticks = 25;
        for (int i = 0; i < 3 + 8 * GTFOUtils.boolToInt(GTFOConfig.gtfoncConfig.addSmogus); i++) {
            if (i < 7) {
                FORMING_PRESS_RECIPES.recipeBuilder()
                        .inputs(MARSHMALLOW.getStackForm())
                        .inputs(GTFOOreDictItems.HOT_MILK_CHOCOLATE.getItemStack())
                        .inputs(smoresin[i])
                        .outputs(smoresout[i])
                        .EUt(euPerTick)
                        .duration(ticks)
                        .buildAndRegister();

            } else {
                GTFOUtils.stellarForgeProxy().recipeBuilder()
                        .inputs(MARSHMALLOW.getStackForm())
                        .inputs(GTFOOreDictItems.HOT_MILK_CHOCOLATE.getItemStack())
                        .inputs(smoresin[i])
                        .outputs(smoresout[i])
                        .EUt(euPerTick)
                        .duration(ticks)
                        .buildAndRegister();
            }
            if (euPerTick < GTValues.VA[GTValues.UV] || GregTechAPI.isHighTier()) {
                euPerTick *= 4;
            } else {
                ticks *= 2;
            }
            ticks *= 2;
        }

        MICROWAVE_RECIPES.recipeBuilder()
                .inputs(MILK_CHOCOLATE.getStackForm())
                .outputs(GTFOOreDictItems.HOT_MILK_CHOCOLATE.getItemStack())
                .EUt(120)
                .duration(100)
                .buildAndRegister();


        BLAST_RECIPES.recipeBuilder()
                .inputs(MILK_CHOCOLATE.getStackForm())
                .outputs(GTFOOreDictItems.HOT_MILK_CHOCOLATE.getItemStack())
                .blastFurnaceTemp(600)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.MATTER_MARSHMALLOW.getItemStack())
                .notConsumable(MetaItems.SHAPE_EXTRUDER_ROD)
                .outputs(MARSHMALLOW.getStackForm(2))
                .EUt(90)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.CRACKER_GRAHAM_UNGRADED.getItemStack())
                .notConsumable(MetaItems.SENSOR_LV.getStackForm())
                .chancedOutput(GRAHAM_CRACKER.getStackForm(), 7500, 100)
                .EUt(30)
                .duration(40)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterials.MoltenMilkChocolate.getFluid(144))
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                .outputs(MILK_CHOCOLATE.getStackForm())
                .EUt(32)
                .duration(200)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterials.MarshmallowFoam.getFluid(1000))
                .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
                .outputs(GTFOOreDictItems.MATTER_MARSHMALLOW.getItemStack())
                .EUt(60)
                .duration(100)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.WAFER_GRAHAM_HOT.getItemStack())
                .outputs(GTFOOreDictItems.CRACKER_GRAHAM_UNGRADED.getItemStack())
                .EUt(60)
                .duration(20)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterials.MoltenDarkChocolate.getFluid(864))
                .fluidInputs(Milk.getFluid(288))
                .fluidOutputs(GTFOMaterials.MoltenMilkChocolate.getFluid(1152))
                .EUt(120)
                .duration(280)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Sugar, 1)
                .fluidInputs(GTFOMaterials.CocoaButter.getFluid(144))
                .fluidInputs(GTFOMaterials.MoltenUnsweetenedChocolate.getFluid(1008))
                .fluidOutputs(GTFOMaterials.MoltenDarkChocolate.getFluid(1152))
                .EUt(120)
                .duration(160)
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterials.MarshmallowSyrupMixture.getFluid(1000))
                .circuitMeta(1)
                .fluidOutputs(GTFOMaterials.MarshmallowFoam.getFluid(5000))
                .EUt(30)
                .duration(300)
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.CHUNK_GRAHAM_HOT.getItemStack())
                .outputs(GTFOOreDictItems.WAFER_GRAHAM_HOT.getItemStack(9))
                .EUt(60)
                .duration(200)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Cocoa)
                .fluidOutputs(GTFOMaterials.MoltenUnsweetenedChocolate.getFluid(144))
                .EUt(30)
                .duration(20)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.PRESS_CAKE_GRADED.getItemStack())
                .output(OrePrefix.dust, Cocoa, 1)
                .EUt(120)
                .duration(120)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .inputs(GELATIN.getStackForm(2))
                .fluidInputs(GTFOMaterials.Albumen.getFluid(500))
                .fluidInputs(GTFOMaterials.SweetenedDilutedCaneSyrupMixture.getFluid(5000))
                .fluidOutputs(GTFOMaterials.MarshmallowSyrupMixture.getFluid(5500))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(2))
                .EUt(120)
                .duration(300)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.PRESS_CAKE.getItemStack())
                .notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(GTFOOreDictItems.PRESS_CAKE_GRADED.getItemStack(), 5000, 100)
                .EUt(480)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.PRESS_CAKE_DUTCHED.getItemStack())
                .notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(GTFOOreDictItems.PRESS_CAKE_GRADED.getItemStack(), 7500, 300)
                .EUt(480)
                .duration(40)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.MATTER_GRAHAM_HOT.getItemStack())
                .circuitMeta(1)
                .outputs(GTFOOreDictItems.CHUNK_GRAHAM_HOT.getItemStack())
                .EUt(45)
                .duration(80)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_PRESSED.getItemStack(2))
                .outputs(GTFOOreDictItems.PRESS_CAKE.getItemStack())
                .fluidOutputs(GTFOMaterials.CocoaButter.getFluid(144))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_DUTCHED_PRESSED.getItemStack(2))
                .outputs(GTFOOreDictItems.PRESS_CAKE_DUTCHED.getItemStack())
                .fluidOutputs(GTFOMaterials.CocoaButter.getFluid(144))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(GTFOOreDictItems.MATTER_GRAHAM.getItemStack(), GTFOOreDictItems.MATTER_GRAHAM_HOT.getItemStack(), 400, 450, 3);

        Item[] gelatins = {Items.BONE, Items.LEATHER, Items.PORKCHOP, Items.BEEF, Items.CHICKEN, Items.RABBIT, Items.MUTTON};

        for (Item gelatinProducer : gelatins) {
            EXTRACTOR_RECIPES.recipeBuilder()
                    .input(gelatinProducer)
                    .outputs(GELATIN.getStackForm())
                    .EUt(120)
                    .duration(100)
                    .buildAndRegister();
        }

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(FishOil.getFluid(500))
                .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                .outputs(GELATIN.getStackForm())
                .EUt(60)
                .duration(100)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(Items.SUGAR)
                .input(OrePrefix.dust, Wheat, 3)
                .input(OrePrefix.dust, SodiumBicarbonate)
                .fluidInputs(GTFOMaterials.Butter.getFluid(2000))
                .fluidInputs(Milk.getFluid(500))
                .outputs(GTFOOreDictItems.MATTER_GRAHAM.getItemStack(10))
                .EUt(80)
                .duration(200)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_REFINED.getItemStack())
                .outputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_PRESSED.getItemStack())
                .EUt(360)
                .duration(80)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_DUTCHED.getItemStack())
                .outputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_DUTCHED_PRESSED.getItemStack())
                .EUt(360)
                .duration(80)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(Items.SUGAR)
                .fluidInputs(Water.getFluid(5000))
                .fluidInputs(CaneSyrup.getFluid(5000))
                .fluidOutputs(GTFOMaterials.SweetenedDilutedCaneSyrupMixture.getFluid(10000))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .EUt(120)
                .duration(260)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .notConsumable(GTFOMaterials.SodiumCarbonateSolution.getFluid(1000))
                .inputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_REFINED.getItemStack())
                .outputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_DUTCHED.getItemStack())
                .EUt(540)
                .duration(160)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000))
                .input(OrePrefix.dust, SodaAsh)
                .fluidOutputs(GTFOMaterials.SodiumCarbonateSolution.getFluid(1000))
                .EUt(8)
                .duration(40)
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.CHOCOLATE_LIQUOR.getItemStack())
                .outputs(GTFOOreDictItems.CHOCOLATE_LIQUOR_REFINED.getItemStack())
                .EUt(200)
                .duration(60)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(COCOA_NIBS.getStackForm())
                .outputs(GTFOOreDictItems.CHOCOLATE_LIQUOR.getItemStack())
                .EUt(270)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.COCOA_NIB.getItemStack())
                .outputs(COCOA_NIBS.getStackForm())
                .EUt(30)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOOreDictItems.COCOA_HULL.getItemStack())
                .output(OrePrefix.dustSmall, Wood, 2)
                .EUt(8)
                .duration(15)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(ROASTED_COCOA_BEANS.getStackForm(6))
                .fluidInputs(Steam.getFluid(10000))
                .outputs(GTFOOreDictItems.COCOA_HULL.getItemStack(6))
                .outputs(GTFOOreDictItems.COCOA_NIB.getItemStack(6))
                .EUt(360)
                .duration(90)
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder()
                .input(Items.DYE, 8, 3)
                .circuitMeta(1)
                .outputs(ROASTED_COCOA_BEANS.getStackForm(8))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(120)
                .duration(30)
                .buildAndRegister();
    }

}
