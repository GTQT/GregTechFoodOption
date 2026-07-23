package gregtechfoodoption.loader.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;
import gregtechfoodoption.api.unification.materials.GTFOOreDictItems;
import gregtechfoodoption.common.item.GTFOMetaItem;
import gregtechfoodoption.api.utils.GTFOUtils;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtechfoodoption.api.unification.materials.GTFOMaterials.*;
import static gregtechfoodoption.api.unification.materials.GTFOOreDictItems.*;
import static gregtechfoodoption.common.item.GTFOMetaItem.*;
import static gregtechfoodoption.loader.recipe.GTFORecipeMaps.*;

public class ItalianChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .input(dust,ShreddedParmesan)
                .inputs( GTFOMetaItem.BASIL.getStackForm(3), GTFOMetaItem.GARLIC_CLOVE.getStackForm())
                .input(dustTiny, Salt)
                .fluidInputs(GTFOMaterials.OliveOil.getFluid(500))
                .fluidOutputs(GTFOMaterials.Pesto.getFluid(500))
                .buildAndRegister();
        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .input(dust,Nutmeg)
                .input(dust, Wheat)
                .fluidInputs(GTFOMaterials.Butter.getFluid(50), Materials.Milk.getFluid(450))
                .fluidOutputs(GTFOMaterials.BechamelSauce.getFluid(500))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(8).duration(300)
                .input(dust, Materials.Sugar)
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidOutputs(GTFOMaterials.Agrodolce.getFluid(1000))
                .buildAndRegister();


        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOOreDictItems.BareCornKernel.getItemStack(10))
                .fluidInputs(GTFOMaterials.HeatedWater.getFluid(250))
                .fluidOutputs(GTFOMaterials.Polenta.getFluid(250))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(16).duration(40)
                .inputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm())
                .fluidInputs(GTFOMaterials.Polenta.getFluid(250))
                .outputs(GTFOMetaItem.POLENTA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(300)
                .circuitMeta(1)
                .inputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm(), GTFOMetaItem.CARROT_SLICE.getStackForm(3), GTFOMetaItem.OLIVE_SLICE.getStackForm(3), GTFOMetaItem.EGGPLANT_SLICE.getStackForm(3))
                .fluidInputs(GTFOMaterials.Agrodolce.getFluid(250), GTFOMaterials.OliveOil.getFluid(250))
                .outputs(GTFOMetaItem.CAPONATA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(300)
                .circuitMeta(1)
                .inputs(GTFOMetaItem.TOMATO_SLICE.getStackForm(3), GTFOMetaItem.BUN.getStackForm(1), GTFOMetaItem.GARLIC_CLOVE.getStackForm(1))
                .input(dustSmall, Salt)
                .fluidInputs(GTFOMaterials.OliveOil.getFluid(250))
                .outputs(GTFOMetaItem.BRUSCHETTA.getStackForm())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(24).duration(40)
                .input(Items.PORKCHOP)
                .input(dustSmall, Salt)
                .inputs(GTFOMetaItem.GARLIC_CLOVE.getStackForm())
                .outputs(GTFOMetaItem.SEASONED_PORK.getStackForm())
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.SEASONED_PORK.getStackForm(), GTFOMetaItem.PORCHETTA.getStackForm(), 2000, 470, 4);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(GTFOMetaItem.PORCHETTA.getStackForm())
                .notConsumable(GTFOMetaItem.SLICER_BLADE_FLAT.getStackForm())
                .outputs(PORCHETTA_SLICE.getStackForm(8))
                .buildAndRegister();
        ModHandler.addShapelessRecipe("gtfo_slice_porchetta", PORCHETTA_SLICE.getStackForm(4), 'k', PORCHETTA.getStackForm());

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(400)
                .inputs(GTFOMetaItem.DRIED_SPAGHETTI.getStackForm(), GTFOMetaItem.PLATE.getStackForm())
                .fluidInputs(TomatoSauce.getFluid(500))
                .outputs(GTFOMetaItem.SPAGHETTI_ALLASSASSINA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(400)
                .input(dust,BlackPepper,2)
                .inputs(GTFOMetaItem.RIGATONI.getStackForm(), GTFOMetaItem.PLATE.getStackForm(), MetaBlocks.CLEANROOM_CASING.getItemVariant(BlockCleanroomCasing.CasingType.FILTER_CASING))
                .fluidInputs(TomatoSauce.getFluid(500))
                .outputs(GTFOMetaItem.PASTA_ALLAMOGUS.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOMetaItem.RIGATONI.getStackForm(), GTFOMetaItem.PLATE.getStackForm(), GTFOMetaItem.EGGPLANT_SLICE.getStackForm(3), GTFOMetaItem.BASIL.getStackForm())
                .fluidInputs(TomatoSauce.getFluid(200))
                .outputs(GTFOMetaItem.PASTA_ALLA_NORMA.getStackForm())
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().EUt(16).duration(800)
                .input(ingot, Steel, 4)
                .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
                .outputs(BAKING_TRAY.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .input(dust,ShreddedParmesan,3)
                .input(dust,BlackPepper,1)
                .inputs(GTFOMetaItem.RAW_LASAGNA.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm())
                .input(dust, Meat, 2)
                .fluidInputs(TomatoSauce.getFluid(500))
                .outputs(GTFOMetaItem.LASAGNA_NAPOLETANA_RAW.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOMetaItem.RAW_LASAGNA.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm(), GTFOMetaItem.MOZZARELLA_BALL.getStackForm(1))
                .input(dust, Meat, 2)
                .fluidInputs(GTFOMaterials.Pesto.getFluid(250), GTFOMaterials.BechamelSauce.getFluid(250))
                .outputs(GTFOMetaItem.LASAGNA_PESTO_RAW.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOMetaItem.RAW_LASAGNA.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm(), GTFOMetaItem.CHUM.getStackForm(2), GTFOMetaItem.CHEDDAR_SLICE.getStackForm(1))
                .fluidInputs(TomatoSauce.getFluid(200))
                .outputs(GTFOMetaItem.LASAGNA_CHUM_RAW.getStackForm())
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.LASAGNA_NAPOLETANA_RAW.getStackForm(), GTFOMetaItem.LASAGNA_NAPOLETANA_COOKED.getStackForm(), 1500, 465, 2);
        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.LASAGNA_PESTO_RAW.getStackForm(), GTFOMetaItem.LASAGNA_PESTO_COOKED.getStackForm(), 1750, 465, 2);
        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.LASAGNA_CHUM_RAW.getStackForm(), GTFOMetaItem.LASAGNA_CHUM_COOKED.getStackForm(), 2000, 1570, 3);

        CANNER_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMetaItem.PLATE.getStackForm(4), GTFOMetaItem.LASAGNA_NAPOLETANA_COOKED.getStackForm())
                .outputs(GTFOMetaItem.LASAGNA_NAPOLETANA.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMetaItem.PLATE.getStackForm(4), GTFOMetaItem.LASAGNA_PESTO_COOKED.getStackForm())
                .outputs(GTFOMetaItem.LASAGNA_PESTO.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMetaItem.PLATE.getStackForm(4), GTFOMetaItem.LASAGNA_CHUM_COOKED.getStackForm())
                .outputs(GTFOMetaItem.LASAGNA_CHUM.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(16).duration(1000)
                .inputs(TORTELLINI.getStackForm(6), CERAMIC_BOWL.getStackForm())
                .fluidInputs(GTFOMaterials.ChickenBroth.getFluid(1000))
                .outputs(TORTELLINI_IN_BRODO.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(2000)
                .input(Items.FISH)
                .inputs(GARLIC_CLOVE.getStackForm(), ONION_SLICE.getStackForm())
                .fluidInputs(GTFOMaterials.WhiteWine.getFluid(20))
                .fluidOutputs(GTFOMaterials.VitelloTonnatoFlavorant.getFluid(200))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .fluidInputs(GTFOMaterials.VitelloTonnatoFlavorant.getFluid(200), GTFOMaterials.OliveOil.getFluid(200))
                .fluidOutputs(GTFOMaterials.VitelloTonnatoSauce.getFluid(400))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .fluidInputs(GTFOMaterials.VitelloTonnatoSauce.getFluid(100), GTFOMaterials.Yolk.getFluid(100))
                .input(Items.COOKED_BEEF)
                .inputs(PLATE.getStackForm())
                .outputs(VITELLO_TONNATO.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(2000)
                .input(dust,MashedPotato,4)
                .input(dust,GratedHorseradishRoot)
                .fluidInputs(GTFOMaterials.Egg.getFluid(600), GTFOMaterials.OliveOil.getFluid(200))
                .fluidOutputs(GTFOMaterials.RafanataMixture.getFluid(800))
                .buildAndRegister();
        MULTICOOKER_RECIPES.recipeBuilder().EUt(64).duration(500)
                .inputs(PLATE.getStackForm())
                .fluidInputs(GTFOMaterials.RafanataMixture.getFluid(200))
                .outputs(RAFANATA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(300)
                .input(dust,ShreddedParmesan)
                .inputs(TAGLIATELLE.getStackForm(), PLATE.getStackForm())
                .fluidInputs(GTFOMaterials.Butter.getFluid(200))
                .outputs(FETTUCCINE_ALFREDO.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(1000)
                .input(dust,ShreddedParmesan)
                .inputs(RICE.getStackForm(3), GTFOMetaItem.ONION_SLICE.getStackForm(), CERAMIC_BOWL.getStackForm())
                .fluidInputs(GTFOMaterials.ChickenBroth.getFluid(700), GTFOMaterials.Butter.getFluid(200), GTFOMaterials.WhiteWine.getFluid(5))
                .outputs(RISOTTO.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(2000)
                .input(dust,ShreddedParmesan)
                .fluidInputs(GTFOMaterials.Egg.getFluid(500))
                .fluidOutputs(GTFOMaterials.CarbonaraSauce.getFluid(500))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(PLATE.getStackForm(), SPAGHETTI.getStackForm(), BACON.getStackForm())
                .fluidInputs(GTFOMaterials.CarbonaraSauce.getFluid(200))
                .outputs(CARBONARA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(PLATE.getStackForm(), TOMATO_SLICE.getStackForm(2), SPAGHETTI.getStackForm(), BASIL.getStackForm())
                .fluidInputs(TomatoSauce.getFluid(300), OliveOil.getFluid(50))
                .outputs(PASTA_AL_POMODORO.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(1000)
                .inputs(CARROT_SLICE.getStackForm(4), GARLIC_CLOVE.getStackForm(2), ONION_SLICE.getStackForm(3))
                .fluidInputs(GTFOMaterials.ChickenBroth.getFluid(500), GTFOMaterials.OliveOil.getFluid(100), Water.getFluid(1400))
                .fluidOutputs(GTFOMaterials.PastaEFagioliBase.getFluid(2000))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(RAW_DITALINI.getStackForm(), BEANS.getStackForm())
                .fluidInputs(GTFOMaterials.PastaEFagioliBase.getFluid(500))
                .fluidOutputs(GTFOMaterials.MixedPastaEFagioli.getFluid(500))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(DRIED_DITALINI.getStackForm(), BEANS.getStackForm())
                .fluidInputs(GTFOMaterials.PastaEFagioliBase.getFluid(500))
                .fluidOutputs(GTFOMaterials.MixedPastaEFagioli.getFluid(500))
                .buildAndRegister();
        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(300)
                .inputs(CERAMIC_BOWL.getStackForm())
                .fluidInputs(GTFOMaterials.MixedPastaEFagioli.getFluid(250))
                .outputs(PASTA_E_FAGIOLI.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(16).duration(1500)
                .input(dust, Meat, 2)
                .inputs(CARROT_SLICE.getStackForm(4), ONION_SLICE.getStackForm(2))
                .fluidInputs(GTFOMaterials.ChickenBroth.getFluid(800), GTFOMaterials.OliveOil.getFluid(200))
                .fluidOutputs(GTFOMaterials.BologneseSauce.getFluid(1000))
                .buildAndRegister();
        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(500)
                .fluidInputs(GTFOMaterials.BologneseSauce.getFluid(100), GTFOMaterials.RedWine.getFluid(10), TomatoSauce.getFluid(100))
                .fluidOutputs(GTFOMaterials.TomatoBologneseSauce.getFluid(200))
                .buildAndRegister();
        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(500)
                .inputs(TAGLIATELLE.getStackForm(), PLATE.getStackForm())
                .fluidInputs(GTFOMaterials.TomatoBologneseSauce.getFluid(350), Milk.getFluid(50))
                .outputs(TAGLIATELLE_AL_RAGU.getStackForm())
                .buildAndRegister();
        MULTICOOKER_RECIPES.recipeBuilder().EUt(64).duration(1000)
                .input(dust,ShreddedParmesan,2)
                .inputs(EGGPLANT_SLICE.getStackForm(8), MOZZARELLA_SLICE.getStackForm(2), PLATE.getStackForm())
                .fluidInputs(TomatoSauce.getFluid(200))
                .outputs(PARMIGIANA.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(64).duration(3000)
                .inputs(ARTICHOKE_HEART.getStackForm(), GARLIC_CLOVE.getStackForm(), PLATE.getStackForm())
                .outputs(CARCIOFI_ALLA_ROMANA.getStackForm())
                .buildAndRegister();
    }
}
