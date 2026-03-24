package gregtechfoodoption.recipe.chain;

import forestry.api.arboriculture.*;
import forestry.arboriculture.ModuleArboriculture;
import forestry.arboriculture.blocks.BlockRegistryArboriculture;
import forestry.arboriculture.genetics.TreeDefinition;
import gregtech.api.util.Mods;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ForestryTreeHandler {
    public static List<ItemStack> treeist = new ArrayList<>();
    public static Map<ItemStack, List<ItemStack>> products = new HashMap<ItemStack, List<ItemStack>>();

    public static void generateForestryTrees() {
        for (TreeDefinition tree : TreeDefinition.values()) {
            String speciesUID = tree.getUID();
            int logMeta = tree.getMetadata();

            // 获取树苗
            ItemStack sapling = tree.getMemberStack(EnumGermlingType.SAPLING);

            // 获取原木
            ItemStack log = ItemStack.EMPTY;
            EnumForestryWoodType woodType = EnumForestryWoodType.byMetadata(logMeta);
            if (woodType != null) {
                log = TreeManager.woodAccess.getStack(woodType, WoodBlockKind.LOG, false);
            }

            // 获取树叶
            var leaves = ModuleArboriculture.getBlocks().getDecorativeLeaves(speciesUID);

            treeist.add(sapling);
            treeist.add(log);

            // 创建该树苗对应的产物列表
            List<ItemStack> productList = new ArrayList<>();
            productList.add(log);
            productList.add(leaves);

            // 获取果实
            ITree individual = tree.getIndividual();
            if (individual.canBearFruit()) {
                // 添加普通产物
                if (individual.getProducts().keySet().size() > 0) {
                    individual.getProducts().keySet().forEach(product -> {
                        if (!product.isEmpty()) {
                            productList.add(product.copy());
                        }
                    });
                }

                // 添加特殊产物
                if (individual.getSpecialties().keySet().size() > 0) {
                    individual.getSpecialties().keySet().forEach(specialty -> {
                        if (!specialty.isEmpty()) {
                            productList.add(specialty.copy());
                        }
                    });
                }
            }

            // 将树苗和对应的产物列表添加到products映射中
            products.put(sapling, productList);
        }
    }
}