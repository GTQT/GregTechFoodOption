package gregtechfoodoption.integration.enderio;

import crazypants.enderio.base.init.ModObject;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

public class GTFOEIORecipeHandler {
    public static void enderios() {
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .fluidInputs(GTFOMaterialHandler.EnderSugarSolution.getFluid(100))
                .fluidInputs(Materials.Milk.getFluid(500))
                .input(Items.WHEAT_SEEDS, 2)
                .output(ModObject.itemEnderFood.getItem())
                .buildAndRegister();
    }

    public static void removeEnderios() {
        ModHandler.removeRecipeByName(new ResourceLocation("enderio:enderios"));
    }
}
