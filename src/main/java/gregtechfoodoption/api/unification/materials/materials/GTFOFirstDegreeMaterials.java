package gregtechfoodoption.api.unification.materials.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttributes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtechfoodoption.api.unification.materials.GTFOMaterials.gtfoId;
import static gregtechfoodoption.api.utils.GTFOUtils.averageRGB;

public class GTFOFirstDegreeMaterials {

    public static void register() {
        GTFOMaterials.SodiumSulfate = new Material.Builder(0, gtfoId("sodium_sulfate"))
                .dust()
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .build();

        GTFOMaterials.PerchloricAcid = new Material.Builder(1, gtfoId("perchloric_acid"))
                .liquid(new FluidBuilder().attribute(FluidAttributes.ACID))
                .components(Hydrogen, 1, Chlorine, 1, Oxygen, 4)
                .build();

        GTFOMaterials.ChloroauricAcid = new Material.Builder(2, gtfoId("chloroauric_acid"))
                .liquid(new FluidBuilder().attribute(FluidAttributes.ACID))
                .components(Hydrogen, 1, Gold, 1, Chlorine, 4)
                .build();

        GTFOMaterials.HydrogenCyanide = new Material.Builder(3, gtfoId("hydrogen_cyanide"))
                .liquid()
                .color(0x6e6a5e)
                .components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
                .build();

        GTFOMaterials.SodiumArseniteSolution = new Material.Builder(4, gtfoId("sodium_arsenite_solution"))
                .liquid()
                .color(averageRGB(2, SodaAsh.getMaterialRGB(), Arsenic.getMaterialRGB()))
                .components(Sodium, 1, Arsenic, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        GTFOMaterials.BlueVitriol = new Material.Builder(5, gtfoId("blue_vitriol"))
                .liquid()
                .color(0x4242DE)
                .components(Copper, 1, Sulfur, 1, Oxygen, 4)
                .build();

        GTFOMaterials.BakingSodaSolution = new Material.Builder(6, gtfoId("baking_soda_solution"))
                .liquid()
                .color(SodiumBicarbonate.getMaterialRGB())
                .components(SodiumBicarbonate, 1, Water, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        GTFOMaterials.SodiumPerchlorate = new Material.Builder(7, gtfoId("sodium_perchlorate"))
                .dust()
                .color(0x121110)
                .build()
                .setFormula("NaClO4");

        GTFOMaterials.SodiumChlorate = new Material.Builder(8, gtfoId("sodium_chlorate"))
                .dust()
                .color(0x121110)
                .build()
                .setFormula("NaClO3");

        GTFOMaterials.SodiumCyanide = new Material.Builder(9, gtfoId("sodium_cyanide"))
                .dust()
                .color(0x5F7C8C)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Carbon, 1, Nitrogen, 1)
                .build();

        GTFOMaterials.LithiumOxide = new Material.Builder(10, gtfoId("lithium_oxide"))
                .dust()
                .color(0x121110)
                .build()
                .setFormula("LiO");

        GTFOMaterials.LithiumCarbonate = new Material.Builder(11, gtfoId("lithium_carbonate"))
                .dust()
                .color(0x121110)
                .components(Lithium, 2, Carbon, 1, Oxygen, 3)
                .build();

        GTFOMaterials.AmmoniumPerchlorate = new Material.Builder(12, gtfoId("ammonium_perchlorate"))
                .dust()
                .color(0x121110)
                .components(Ammonia, 1, Chlorine, 1, Oxygen, 4)
                .build();

        GTFOMaterials.PotassiumPerchlorate = new Material.Builder(13, gtfoId("potassium_perchlorate"))
                .dust()
                .color(0x121110)
                .components(Potassium, 1, Chlorine, 1, Oxygen, 4)
                .build();

        GTFOMaterials.SodiumCarbonateSolution = new Material.Builder(14, gtfoId("sodium_carbonate_solution"))
                .liquid()
                .color(averageRGB(2, 0xaaaaaa, SodaAsh.getMaterialRGB()))
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Water, 1, SodaAsh, 1)
                .build();

        GTFOMaterials.MoistAir = new Material.Builder(15, gtfoId("moist_air"))
                .gas(new FluidBuilder().temperature(273))
                .color(0x82c8ff)
                .build();

        GTFOMaterials.ColdMoistAir = new Material.Builder(16, gtfoId("cold_moist_air"))
                .gas(new FluidBuilder().temperature(243))
                .color(0x72a2ff)
                .build();

        GTFOMaterials.EnderPearlSolution = new Material.Builder(17, gtfoId("ender_pearl_solution"))
                .liquid()
                .components(EnderPearl, 1, Water, 1)
                .color(EnderPearl.getMaterialRGB())
                .build();
    }
}
