package gregtechfoodoption.loader.recipe.chain;

import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneVariantBlock;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;
import gregtechfoodoption.api.unification.materials.GTFOOreDictItems;
import gregtechfoodoption.common.block.GTFOBlockCasing;
import gregtechfoodoption.common.block.GTFOMetaBlocks;
import gregtechfoodoption.common.item.GTFOMetaItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.api.unification.materials.GTFOMaterials.BoneChinaClay;

public class PlateChain {
    public static void init() {
        GTRecipeItemInput[] feldspars = new GTRecipeItemInput[]{
                new GTRecipeItemInput(OreDictUnifier.get(OrePrefix.dust, Materials.Stone, 8)),
                new GTRecipeItemInput(new ItemStack[]{OreDictUnifier.get(OrePrefix.dust, Materials.Granite),
                        OreDictUnifier.get(OrePrefix.dust, Materials.GraniteRed),
                        OreDictUnifier.get(OrePrefix.dust, Materials.GraniteBlack)}, 3),
                new GTRecipeItemInput(OreDictUnifier.get(OrePrefix.dust, Materials.PotassiumFeldspar))
        };

        GTRecipeItemInput[] calciums = new GTRecipeItemInput[]{
                new GTRecipeItemInput(OreDictUnifier.get(OrePrefix.dust, Materials.Apatite, 6)),
                new GTRecipeItemInput(OreDictUnifier.get(OrePrefix.dust, Materials.TricalciumPhosphate, 4)),
                new GTRecipeItemInput(new ItemStack(Items.DYE, 3, 15)),
                new GTRecipeItemInput(GTFOOreDictItems.BoneAsh.getItemStack())
        };

        FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(Items.DYE, 1, 15), GTFOOreDictItems.BoneAsh.getItemStack(), 0f);

        for (GTRecipeItemInput feldspar : feldspars) {
            for (GTRecipeItemInput calcium : calciums) {
                MIXER_RECIPES.recipeBuilder().EUt(8).duration(100)
                        .input(Items.CLAY_BALL)
                        .input(feldspar)
                        .input(calcium)
                        .circuitMeta(11)
                        .output(dust,BoneChinaClay)
                        .buildAndRegister();
            }
        }

        COMPRESSOR_RECIPES.recipeBuilder().EUt(8).duration(80)
                .input(dust,BoneChinaClay,4)
                .outputs(GTFOMetaItem.UNFIRED_PLATE.getStackForm())
                .buildAndRegister();
        LATHE_RECIPES.recipeBuilder().EUt(8).duration(80)
                .input(dust,BoneChinaClay,6)
                .outputs(GTFOMetaItem.UNFIRED_BOWL.getStackForm())
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(16).duration(800)
                .input(dust, Glass)
                .inputs(GTFOMetaItem.UNFIRED_PLATE.getStackForm())
                .outputs(GTFOMetaItem.PLATE.getStackForm())
                .buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(16).duration(800)
                .input(dust, Glass)
                .inputs(GTFOMetaItem.UNFIRED_BOWL.getStackForm())
                .outputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(8).duration(800)
                .inputs(GTFOMetaItem.DIRTY_PLATE.getStackForm())
                .fluidInputs(Water.getFluid(2000))
                .outputs(GTFOMetaItem.PLATE.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(8).duration(800)
                .inputs(GTFOMetaItem.DIRTY_CERAMIC_BOWL.getStackForm())
                .fluidInputs(Water.getFluid(2000))
                .outputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(GTFOMetaItem.DIRTY_PLATE.getStackForm())
                .fluidInputs(GTFOMaterials.SodiumStearate.getFluid(100))
                .outputs(GTFOMetaItem.PLATE.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(GTFOMetaItem.DIRTY_CERAMIC_BOWL.getStackForm())
                .fluidInputs(GTFOMaterials.SodiumStearate.getFluid(100))
                .outputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm())
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().EUt(28).duration(160)
                .input(dust,BoneChinaClay,2)
                .notConsumable(MetaItems.SHAPE_MOLD_PLATE.getStackForm())
                .outputs(GTFOOreDictItems.UnfiredPorcelainTile.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(800).blastFurnaceTemp(1600)
                .inputs(GTFOOreDictItems.UnfiredPorcelainTile.getItemStack())
                .outputs(GTFOOreDictItems.BiscuitPorcelainTile.getItemStack())
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(500).blastFurnaceTemp(1600)
                .inputs(GTFOOreDictItems.BiscuitPorcelainTile.getItemStack())
                .circuitMeta(1)
                .input(dust, Glass)
                .outputs(GTFOOreDictItems.GlazedPorcelainTile.getItemStack())
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(500).blastFurnaceTemp(1600)
                .inputs(GTFOOreDictItems.BiscuitPorcelainTile.getItemStack())
                .input(dust, Glass)
                .fluidInputs(DyeBlack.getFluid(100))
                .outputs(GTFOOreDictItems.BlackGlazedPorcelainTile.getItemStack())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .inputs(MetaBlocks.STONE_BLOCKS.get(StoneVariantBlock.StoneVariant.SMOOTH)
                        .getItemVariant(StoneVariantBlock.StoneType.CONCRETE_LIGHT))
                .inputs(GTFOOreDictItems.GlazedPorcelainTile.getItemStack(6))
                .outputs(GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.PORCELAIN_TILE))
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .inputs(MetaBlocks.STONE_BLOCKS.get(StoneVariantBlock.StoneVariant.SMOOTH)
                        .getItemVariant(StoneVariantBlock.StoneType.CONCRETE_LIGHT))
                .inputs(GTFOOreDictItems.BlackGlazedPorcelainTile.getItemStack(6))
                .outputs(GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.DARK_PORCELAIN_TILE))
                .buildAndRegister();
    }

}
