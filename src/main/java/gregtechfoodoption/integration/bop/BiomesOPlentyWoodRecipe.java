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

import java.util.ArrayList;
import java.util.List;

import static gregtech.loaders.recipe.WoodRecipeLoader.registerWoodTypeRecipe;
import static gregtech.loaders.recipe.WoodRecipeLoader.registerWoodUnificationInfo;

public class BiomesOPlentyWoodRecipe {

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;

    private static final String[] SPECIES = {
            "sacred_oak", "cherry", "umbran", "fir",
            "ethereal", "magic", "mangrove", "palm",
            "redwood", "willow", "pine", "hellbark",
            "jacaranda", "mahogany", "ebony", "eucalyptus"
    };

    private static ItemStack bopItem(String path, int meta) {
        return Mods.BiomesOPlenty.getItem(path, meta, 1);
    }

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) {
            final String mcModId = Mods.Names.BIOMES_O_PLENTY;
            List<WoodTypeEntry> entries = new ArrayList<>(SPECIES.length);
            for (int i = 0; i < SPECIES.length; i++) {
                String wood = SPECIES[i];
                entries.add(new WoodTypeEntry.Builder(mcModId, wood)
                        .log(bopItem("log_" + (i / 4), 4 + (i % 4)))
                        .removeCharcoalRecipe()
                        .planks(bopItem("planks_0", i), wood + "_planks")
                        .slab(bopItem("wood_slab_" + (i / 8), i % 8), wood + "_wooden_slab")
                        .stairs(bopItem(wood + "_stairs", 0), wood + "_stairs")
                        .fenceGate(bopItem(wood + "_fence_gate", 0), wood + "_fence_gate")
                        .fence(bopItem(wood + "_fence", 0), wood + "_fence")
                        .door(bopItem(wood + "_door", 0), wood + "_door")
                        .boat(bopItem("boat_" + wood, 0), "boat_" + wood)
                        .registerAllUnificationInfo()
                        .build());
            }
            DEFAULT_ENTRIES = entries;
        }
        return DEFAULT_ENTRIES;
    }

    public static void init() {
        for (WoodTypeEntry entry : getDefaultEntries()) {
            removePlankRecipe(false, entry);

            registerWoodTypeRecipe("biomesoplenty", entry);
            registerWoodUnificationInfo(entry);
        }

        // Remove Dead Wood Smelting
        removeCharcoalRecipe(bopItem("log_4", 5));
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