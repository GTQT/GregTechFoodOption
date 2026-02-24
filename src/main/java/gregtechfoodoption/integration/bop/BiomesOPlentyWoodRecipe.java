package gregtechfoodoption.integration.bop;

import gregtech.api.recipes.ModHandler;
import gregtech.api.util.Mods;
import gregtech.common.ConfigHolder;
import gregtech.loaders.recipe.WoodTypeEntry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static gregtech.loaders.recipe.WoodRecipeLoader.registerWoodTypeRecipe;
import static gregtech.loaders.recipe.WoodRecipeLoader.registerWoodUnificationInfo;

public class BiomesOPlentyWoodRecipe {

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) {
            final String mcModId = Mods.Names.BIOMES_O_PLENTY;
            return DEFAULT_ENTRIES = Arrays.asList(
                    new WoodTypeEntry.Builder(mcModId, "sacred_oak")
                            .log(Mods.BiomesOPlenty.getItem("log_0", 1, 4)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 0), "sacred_oak_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 0), "sacred_oak_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("sacred_oak_stairs", 1), "sacred_oak_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("sacred_oak_fence_gate", 1), "sacred_oak_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("sacred_oak_fence", 1), "sacred_oak_fence")
                            .door(Mods.BiomesOPlenty.getItem("sacred_oak_door", 1), "sacred_oak_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_sacred_oak", 1), "boat_sacred_oak")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cherry")
                            .log(Mods.BiomesOPlenty.getItem("log_0", 1, 5)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 1), "cherry_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 1), "cherry_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("cherry_stairs", 1), "cherry_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("cherry_fence_gate", 1), "cherry_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("cherry_fence", 1), "cherry_fence")
                            .door(Mods.BiomesOPlenty.getItem("cherry_door", 1), "cherry_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_cherry", 1), "boat_cherry")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "umbran")
                            .log(Mods.BiomesOPlenty.getItem("log_0", 1, 6)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 2), "umbran_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 2), "umbran_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("umbran_stairs", 1), "umbran_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("umbran_fence_gate", 1), "umbran_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("umbran_fence", 1), "umbran_fence")
                            .door(Mods.BiomesOPlenty.getItem("umbran_door", 1), "umbran_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_umbran", 1), "boat_umbran")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "fir")
                            .log(Mods.BiomesOPlenty.getItem("log_0", 1, 7)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 3), "fir_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 3), "fir_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("fir_stairs", 1), "fir_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("fir_fence_gate", 1), "fir_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("fir", 1), "fir")
                            .door(Mods.BiomesOPlenty.getItem("fir_door", 1), "fir_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_fir", 1), "boat_fir")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ethereal")
                            .log(Mods.BiomesOPlenty.getItem("log_1", 1, 4)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 4), "ethereal_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 4), "ethereal_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("ethereal_stairs", 1), "ethereal_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("ethereal_fence_gate", 1), "ethereal_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("ethereal_fence", 1), "ethereal_fence")
                            .door(Mods.BiomesOPlenty.getItem("ethereal_door", 1), "ethereal_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_ethereal", 1), "boat_ethereal")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "magic")
                            .log(Mods.BiomesOPlenty.getItem("log_1", 1, 5)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 5), "magic_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 5), "magic_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("magic_stairs", 1), "magic_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("magic_fence_gate", 1), "magic_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("magic_fence", 1), "magic_fence")
                            .door(Mods.BiomesOPlenty.getItem("magic_door", 1), "magic_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_magic", 1), "boat_magic")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mangrove")
                            .log(Mods.BiomesOPlenty.getItem("log_1", 1, 6)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 6), "mangrove_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 6), "mangrove_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("mangrove_stairs", 1), "mangrove_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("mangrove_fence_gate", 1), "mangrove_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("mangrove_fence", 1), "mangrove_fence")
                            .door(Mods.BiomesOPlenty.getItem("mangrove_door", 1), "mangrove_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_mangrove", 1), "boat_mangrove")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "palm")
                            .log(Mods.BiomesOPlenty.getItem("log_1", 1, 7)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 7), "palm_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_0", 1, 7), "palm_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("palm_stairs", 1), "palm_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("palm_fence_gate", 1), "palm_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("palm_fence", 1), "palm_fence")
                            .door(Mods.BiomesOPlenty.getItem("palm_door", 1), "palm_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_palm", 1), "boat_palm")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "redwood")
                            .log(Mods.BiomesOPlenty.getItem("log_2", 1, 4)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 8), "redwood_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 0), "redwood_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("redwood_stairs", 1), "redwood_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("redwood_fence_gate", 1), "redwood_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("redwood_fence", 1), "redwood_fence")
                            .door(Mods.BiomesOPlenty.getItem("redwood_door", 1), "redwood_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_redwood", 1), "boat_redwood")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "willow")
                            .log(Mods.BiomesOPlenty.getItem("log_2", 1, 5)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 9), "willow_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 1), "willow_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("willow_stairs", 1), "willow_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("willow_fence_gate", 1), "willow_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("willow_fence", 1), "willow_fence")
                            .door(Mods.BiomesOPlenty.getItem("willow_door", 1), "willow_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_willow", 1), "boat_willow")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "pine")
                            .log(Mods.BiomesOPlenty.getItem("log_2", 1, 6)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 10), "pine_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 2), "pine_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("pine_stairs", 1), "pine_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("pine_fence_gate", 1), "pine_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("pine_fence", 1), "pine_fence")
                            .door(Mods.BiomesOPlenty.getItem("pine_door", 1), "pine_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_pine", 1), "boat_pine")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "hellbark")
                            .log(Mods.BiomesOPlenty.getItem("log_2", 1, 7)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 11), "hellbark_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 3), "hellbark_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("hellbark_stairs", 1), "hellbark_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("hellbark_fence_gate", 1), "hellbark_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("hellbark_fence", 1), "hellbark_fence")
                            .door(Mods.BiomesOPlenty.getItem("hellbark_door", 1), "hellbark_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_hellbark", 1), "boat_hellbark")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "jacaranda")
                            .log(Mods.BiomesOPlenty.getItem("log_3", 1, 4)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 12), "jacaranda_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 4), "jacaranda_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("jacaranda_stairs", 1), "jacaranda_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("jacaranda_fence_gate", 1), "jacaranda_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("jacaranda_fence", 1), "jacaranda_fence")
                            .door(Mods.BiomesOPlenty.getItem("jacaranda_door", 1), "jacaranda_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_jacaranda", 1), "boat_jacaranda")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mahogany")
                            .log(Mods.BiomesOPlenty.getItem("log_3", 1, 5)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 13), "mahogany_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 5), "mahogany_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("mahogany_stairs", 1), "mahogany_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("mahogany_fence_gate", 1), "mahogany_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("mahogany_fence", 1), "mahogany_fence")
                            .door(Mods.BiomesOPlenty.getItem("mahogany_door", 1), "mahogany_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_mahogany", 1), "boat_mahogany")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ebony")
                            .log(Mods.BiomesOPlenty.getItem("log_3", 1, 6)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 14), "ebony_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 6), "ebony_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("ebony_stairs", 1), "ebony_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("ebony_fence_gate", 1), "ebony_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("ebony_fence", 1), "ebony_fence")
                            .door(Mods.BiomesOPlenty.getItem("ebony_door", 1), "ebony_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_ebony", 1), "boat_ebony")
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "eucalyptus")
                            .log(Mods.BiomesOPlenty.getItem("log_3", 1, 7)).removeCharcoalRecipe()
                            .planks(Mods.BiomesOPlenty.getItem("planks_0", 1, 15), "eucalyptus_planks")
                            .slab(Mods.BiomesOPlenty.getItem("wood_slab_1", 1, 7), "eucalyptus_wooden_slab")
                            .stairs(Mods.BiomesOPlenty.getItem("eucalyptus_stairs", 1), "eucalyptus_stairs")
                            .fenceGate(Mods.BiomesOPlenty.getItem("eucalyptus_fence_gate", 1), "eucalyptus_fence_gate")
                            .fence(Mods.BiomesOPlenty.getItem("eucalyptus_fence", 1), "eucalyptus_fence")
                            .door(Mods.BiomesOPlenty.getItem("eucalyptus_door", 1), "eucalyptus_door")
                            .boat(Mods.BiomesOPlenty.getItem("boat_eucalyptus", 1), "boat_eucalyptus")
                            .registerAllUnificationInfo()
                            .build());
        }
        return DEFAULT_ENTRIES;
    }

    public static void init() {
        for (WoodTypeEntry entry : getDefaultEntries()) {
            removePlankRecipe(false, entry);

            registerWoodTypeRecipe(false, entry);
            registerWoodUnificationInfo(entry);
        }

        // Remove Dead Wood Smelting
        removeCharcoalRecipe(Mods.BiomesOPlenty.getItem("log_4", 1, 5));
    }
    public static void removePlankRecipe(boolean removeSawRecipes, @NotNull WoodTypeEntry entry) {
        removePlankRecipe(removeSawRecipes, entry, entry.modid);
    }

    public static void removePlankRecipe(boolean removeSawRecipes, @NotNull WoodTypeEntry entry,
                                         @NotNull String modId) {
        final String name = entry.woodName;
        final boolean hasPlanksRecipe = entry.planksRecipeName != null;

        ModHandler.removeRecipeByName(
                new ResourceLocation(modId, hasPlanksRecipe ? entry.planksRecipeName : name + "_planks"));

        if (!removeSawRecipes) return;
        ModHandler.removeRecipeByName(
                new ResourceLocation(modId, hasPlanksRecipe ? entry.planksRecipeName + "_saw" : name + "_planks_saw"));
    }
    public static void removeCharcoalRecipe(@NotNull ItemStack log) {
        if (!ConfigHolder.recipes.harderCharcoalRecipe) return;

        final ItemStack outputStack = FurnaceRecipes.instance().getSmeltingResult(log);
        if (outputStack.getItem() == Items.COAL && outputStack.getItemDamage() == 1) {
            ModHandler.removeFurnaceSmelting(log);
        }
    }
}