package gregtechfoodoption.item;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaOreDictItem;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;

public class GTFOProxyItem {
    private final Supplier<ItemStack> itemPreparer;
    private final boolean isOverriden;
    private ItemStack preferredItem;

    public GTFOProxyItem(Supplier<MetaOreDictItem.OreDictValueItem> gtfoItem, int gtfoItemID, String name, Supplier<ItemStack> otherItem) {
        isOverriden = GregTechAPI.materialManager.getMaterial(name) != null;
        if (isOverriden) {
            itemPreparer = otherItem;
        } else {
            itemPreparer = () -> SHAPED_ITEM.getItem((short) gtfoItemID).getStackForm();
            gtfoItem.get(); // Initialize it, but don't use it yet.
        }
    }

    public ItemStack getItemStack() {
        if (preferredItem == null)
            preferredItem = itemPreparer.get();
        return preferredItem.copy();
    }

    public ItemStack getItemStack(int count) {
        ItemStack copy = this.getItemStack();
        copy.setCount(count);
        return copy;
    }

    public boolean isOverriden() {
        return isOverriden;
    }
}
