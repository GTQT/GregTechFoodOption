package gregtechfoodoption.integration.nutrition;

import ca.wescook.nutrition.nutrients.Nutrient;
import ca.wescook.nutrition.nutrients.NutrientList;
import gregtechfoodoption.common.item.GTFOFoodStats;
import gregtechfoodoption.common.item.GTFOMetaItem;
import gregtechfoodoption.common.item.GTFOMetaItems;
import gregtechfoodoption.api.utils.GTFOUtils;

public class GTFONutritionCompatibility {
    public static void init() {
        for (GTFOMetaItem.GTFOMetaValueItem item : GTFOMetaItems.META_ITEM.getAllItems()) {
            GTFOFoodStats stats = GTFOUtils.getGTFOFoodStats(item.getStackForm());
            if (stats != null) {
                stats.nutrients.forEach((nutrient, amount) -> {
                    Nutrient nut = NutrientList.getByName(nutrient);
                    if (nut != null)
                        nut.foodItems.add(new Nutrient.ScaledItemStack(item.getStackForm(), amount));
                });
            }
        }
    }
}
