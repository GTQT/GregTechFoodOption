package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOMaterialHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static gregtech.api.unification.material.Materials.Steel;
import static gregtechfoodoption.recipe.GTFORecipeMaps.GREENHOUSE_RECIPES;

public class GreenhouseChain {

    public static void initVanilla() {
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 0), new ItemStack(Blocks.LOG, 1, 0),
                new ItemStack(Blocks.LEAVES, 1, 0), new ItemStack(Items.APPLE));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 1), new ItemStack(Blocks.LOG, 1, 1),
                new ItemStack(Blocks.LEAVES, 1, 1), new ItemStack(Items.STICK));
        registerVanillaLargeTreeRecipe(new ItemStack(Blocks.SAPLING, 1, 1), new ItemStack(Blocks.LOG, 1, 1),
                new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 2), new ItemStack(Blocks.LOG, 1, 2),
                new ItemStack(Blocks.LEAVES, 1, 2), new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 3), new ItemStack(Blocks.LOG, 1, 3),
                new ItemStack(Blocks.LEAVES, 1, 3), new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 4), new ItemStack(Blocks.LOG2, 1, 0),
                new ItemStack(Blocks.LEAVES2, 1, 0), new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(Blocks.LOG2, 1, 1),
                new ItemStack(Blocks.LEAVES2, 1, 1), new ItemStack(Items.STICK));
        registerVanillaLargeTreeRecipe(new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(Blocks.LOG2, 1, 1),
                new ItemStack(Items.STICK));

        //种蘑菇
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(1000)
                .input(Blocks.BROWN_MUSHROOM)
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(2000))
                .output(Blocks.BROWN_MUSHROOM,16)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(1000)
                .input(Blocks.RED_MUSHROOM)
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(2000))
                .output(Blocks.RED_MUSHROOM,16)
                .buildAndRegister();
    }

    public static void initGregTech(){
        registerTappingRecipes(new ItemStack(MetaBlocks.RUBBER_SAPLING, 1, 0), new ItemStack(MetaBlocks.RUBBER_LOG, 1, 0),
                new ItemStack(MetaBlocks.RUBBER_LEAVES, 1, 0), GTFOMaterialHandler.RubberSap.getFluid());
        // Sap processing
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(8).duration(160)
                .fluidInputs(GTFOMaterialHandler.RubberSap.getFluid(100))
                .notConsumable(MetaItems.SHAPE_MOLD_BALL)
                .output(MetaItems.STICKY_RESIN)
                .buildAndRegister();
    }

    public static void initForestry() {
        // 确保林业树数据已生成
        ForestryTreeHandler.generateForestryTrees();

        // 为每个林业树注册配方
        for (Map.Entry<ItemStack, List<ItemStack>> entry : ForestryTreeHandler.products.entrySet()) {
            ItemStack sapling = entry.getKey();
            List<ItemStack> products = entry.getValue();

            if (products.size() >= 2) {
                ItemStack log = products.get(0);  // 第一个是原木
                ItemStack leaves = products.get(1); // 第二个是树叶

                // 获取果实（从第三个开始）
                List<ItemStack> fruits = new ArrayList<>();
                if (products.size() > 2) {
                    for (int i = 2; i < products.size(); i++) {
                        ItemStack fruit = products.get(i);
                        if (!fruit.isEmpty()) {
                            fruits.add(fruit);
                        }
                    }
                }

                // 注册林业树配方
                if (!log.isEmpty() && !leaves.isEmpty()) {
                    registerForestryTreeRecipes(sapling, log, leaves, fruits);
                }
            }
        }
    }

    public static void init() {
        initVanilla();
        initGregTech();
        initForestry();
    }

    public static void registerTappingRecipes(ItemStack sapling, ItemStack log, ItemStack leaves, Fluid sap) {
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(6, log), sapling)
                .chancedOutput(sapling, 2000, 1000)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(5, log))
                .chancedOutput(sapling, 1000, 1000)
                .outputs(GTUtility.copy(20, leaves))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(90).duration(3000)
                .inputs(sapling)
                .notConsumable(OrePrefix.toolHeadChainsaw, Steel)
                .circuitMeta(3)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(5, log))
                .chancedOutput(sapling, 8000, 200)
                .fluidOutputs(new FluidStack(sap, 4000))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(90).duration(4000)
                .inputs(sapling, MetaItems.FERTILIZER.getStackForm(1))
                .notConsumable(OrePrefix.toolHeadChainsaw, Steel)
                .circuitMeta(4)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(8, log))
                .chancedOutput(sapling, 8000, 200)
                .fluidOutputs(new FluidStack(sap, 16000))
                .buildAndRegister();
    }

    public static void registerVanillaTreeRecipes(ItemStack sapling, ItemStack log, ItemStack leaves, ItemStack crop) {
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(6, log), crop, sapling)
                .chancedOutput(sapling, 2000, 1000)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(5, log), crop)
                .chancedOutput(sapling, 1000, 1000)
                .outputs(GTUtility.copy(20, leaves))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(3)
                .fluidInputs(Materials.Water.getFluid(20000))
                .outputs(GTUtility.copy(5, log))
                .chancedOutput(sapling, 8000, 200)
                .outputs(GTUtility.copy(3, crop))
                .chancedOutput(GTUtility.copy(2, crop), 4000, 500)
                .buildAndRegister();
    }

    public static void registerVanillaLargeTreeRecipe(ItemStack sapling, ItemStack log, ItemStack crop) {
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(6000)
                .inputs(GTUtility.copy(4, sapling), MetaItems.FERTILIZER.getStackForm(4))
                .circuitMeta(4)
                .fluidInputs(Materials.Water.getFluid(40000))
                .outputs(GTUtility.copy(64, log))
                .outputs(GTUtility.copy(6, sapling))
                .chancedOutput(GTUtility.copy(4, sapling), 1000, 500)
                .outputs(GTUtility.copy(4, crop))
                .buildAndRegister();
    }

    public static void registerForestryTreeRecipes(ItemStack sapling, ItemStack log, ItemStack leaves, List<ItemStack> fruits) {
        // 电路1：基础生长，产出原木和树苗
        RecipeBuilder<SimpleRecipeBuilder> builder1 = GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(6, log), sapling)
                .chancedOutput(sapling, 2000, 1000);

        // 如果有果实，添加果实输出
        if (!fruits.isEmpty()) {
            builder1.outputs(fruits.get(0).copy()); // 只添加第一个果实
        }
        builder1.buildAndRegister();

        // 电路2：产出原木、树叶和树苗
        RecipeBuilder<SimpleRecipeBuilder> builder2 = GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copy(5, log))
                .chancedOutput(sapling, 1000, 1000)
                .outputs(GTUtility.copy(20, leaves));

        // 如果有果实，添加果实输出
        if (!fruits.isEmpty()) {
            builder2.outputs(fruits.get(0).copy());
        }
        builder2.buildAndRegister();

        // 电路3：高产果实模式
        if (!fruits.isEmpty()) {
            GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                    .inputs(sapling)
                    .circuitMeta(3)
                    .fluidInputs(Materials.Water.getFluid(20000))
                    .outputs(GTUtility.copy(5, log))
                    .chancedOutput(sapling, 8000, 200)
                    .outputs(GTUtility.copy(3, fruits.get(0)))
                    .chancedOutput(GTUtility.copy(2, fruits.get(0)), 4000, 500)
                    .buildAndRegister();
        }

    }
}