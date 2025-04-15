package gregtechfoodoption.recipe;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.integration.enderio.GTFOEIORecipeHandler;
import net.minecraftforge.fml.common.Loader;

public class GTFORecipeRemoval {
    public static void init()
    {
        if (Loader.isModLoaded(GTFOValues.MODID_EIO)) {
            GTFOEIORecipeHandler.removeEnderios();
        }
    }
}
