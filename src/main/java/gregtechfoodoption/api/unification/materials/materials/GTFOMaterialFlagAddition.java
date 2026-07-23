package gregtechfoodoption.api.unification.materials.materials;

import gregtechfoodoption.api.unification.materials.CleanerProperty;
import gregtechfoodoption.api.unification.materials.FertilizerProperty;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtechfoodoption.api.unification.materials.GTFOMaterials.*;

public class GTFOMaterialFlagAddition {

    public static void register() {
        // Add flags to base GT materials
        Iron.addFlags(GENERATE_FRAME);
        BismuthBronze.addFlags(GENERATE_FRAME);
        Aluminium.addFlags(GENERATE_DENSE);
        StainlessSteel.addFlags(GENERATE_DENSE, GENERATE_SPRING_SMALL);
        Titanium.addFlags(GENERATE_DENSE);

        // Assign fertilizer properties
        Water.setProperty(GTFOMaterials.FERTILIZER, new FertilizerProperty(5));
        Blood.setProperty(GTFOMaterials.FERTILIZER, new FertilizerProperty(30));
        FertilizerSolution.setProperty(GTFOMaterials.FERTILIZER, new FertilizerProperty(15));

        // Assign cleaner properties
        DistilledWater.setProperty(GTFOMaterials.CLEANER, new CleanerProperty(2));
        SodiumStearate.setProperty(GTFOMaterials.CLEANER, new CleanerProperty(16));

        // Set formula for special materials
        ChorusJuice.setFormula("(C6H12O6)?", true);
    }
}
