package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Sugar;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static net.minecraft.init.Items.WHEAT;

public class SoySauceChain {
    public static void init() {
        //化学精洗 大豆=豆渣+干净大豆
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(SOYBEAN_SEED)
                .fluidInputs(Materials.DistilledWater.getFluid(100))
                .output(CLEAN_SOYBEANS)
                .chancedOutput(BEAN_DREGS,3000,1000)
                .duration(20)
                .EUt(VA[LV])
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(20).duration(400)
                .input(CLEAN_SOYBEANS, 1)
                .output(BEAN_DREGS, 2)
                .duration(20)
                .EUt(VA[LV])
                .buildAndRegister();

        //高压釜 干净大豆+蒸汽=熟化大豆
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(CLEAN_SOYBEANS)
                .fluidInputs(Materials.Steam.getFluid(1000))
                .output(MATURE_SOYBEANS)
                .duration(100)
                .EUt(VA[LV])
                .buildAndRegister();

        //化反 熟化大豆+小麦=成曲
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(MATURE_SOYBEANS)
                .input(WHEAT)
                .fluidOutputs(Koji.getFluid(200))
                .duration(60)
                .EUt(VA[LV])
                .buildAndRegister();

        //发酵 成曲 + 盐水 = 酱醪
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Koji.getFluid(100))
                .fluidInputs(Materials.SaltWater.getFluid(50))
                .fluidOutputs(Mash.getFluid(100))
                .duration(200)
                .EUt(VA[LV])
                .buildAndRegister();

        //离心 酱醪 = 粗酱油 + 酱渣
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(Mash.getFluid(100))
                .fluidOutputs(CrudeSoySauce.getFluid(100))
                .fluidOutputs(SoySauceResidue.getFluid(100))
                .duration(40)
                .EUt(VA[LV])
                .buildAndRegister();

        //蒸馏塔 粗酱油 = 酱油 + 其他气体（硫 甲烷）
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrudeSoySauce.getFluid(1000))
                .fluidOutputs(SoySauce.getFluid(800))
                .fluidOutputs(Materials.SulfurDioxide.getFluid(100))
                .fluidOutputs(Materials.Methane.getFluid(100))
                .duration(200)
                .EUt(VA[LV])
                .buildAndRegister();

        //酱渣处理 发酵 酱渣+水生物质
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SoySauceResidue.getFluid(100))
                .fluidInputs(Materials.Water.getFluid(1000))
                .fluidOutputs(Materials.Biomass.getFluid(1000))
                .duration(200)
                .EUt(VA[LV])
                .buildAndRegister();

        //豆渣做豆浆
        MIXER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(BEAN_DREGS,4)
                .input(OrePrefix.dust,Sugar)
                .fluidInputs(Materials.Water.getFluid(1000))
                .fluidOutputs(SoyMilk.getFluid(1000))
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(Items.GLASS_BOTTLE)
                .fluidInputs(SoyMilk.getFluid(200))
                .outputs(SOY_MILK.getStackForm())
                .buildAndRegister();

    }
}
