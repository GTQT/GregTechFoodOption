package gregtechfoodoption.potion;

import gregtech.api.util.GTControlledRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class LacingEntry {
    public static GTControlledRegistry<String, LacingEntry> LACING_REGISTRY = new GTControlledRegistry<>(255);
    private final ItemStack lacingItem;
    private final PotionEffect appliedEffect;
    private final String nbtKey;

    public LacingEntry(ItemStack lacingItem, PotionEffect appliedEffect, String nbtKey) {
        this.lacingItem = lacingItem;
        this.appliedEffect = appliedEffect;
        this.nbtKey = nbtKey;
    }

    public ItemStack getLacingItem() {
        return lacingItem;
    }

    public PotionEffect getAppliedEffect() {
        return new PotionEffect(appliedEffect);
    }

    public String getNbtKey() {
        return nbtKey;
    }
}
