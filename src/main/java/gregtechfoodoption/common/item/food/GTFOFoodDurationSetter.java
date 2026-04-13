package gregtechfoodoption.common.item.food;

import gregtechfoodoption.common.item.GTFOFoodStats;
import gregtechfoodoption.api.utils.GTFOUtils;
import net.minecraft.item.ItemStack;

public class GTFOFoodDurationSetter {
    public static int duration(ItemStack stack) {
        GTFOFoodStats stats = GTFOUtils.getGTFOFoodStats(stack);
        if (stats != null) {
            return stats.getEatingDuration();
        }
        return 32;
    }
}
