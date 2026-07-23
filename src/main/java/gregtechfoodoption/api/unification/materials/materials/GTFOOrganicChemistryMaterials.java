package gregtechfoodoption.api.unification.materials.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttributes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtechfoodoption.api.unification.materials.GTFOMaterials.gtfoId;
import static gregtechfoodoption.api.utils.GTFOUtils.averageRGB;

public class GTFOOrganicChemistryMaterials {

    public static void register() {
        GTFOMaterials.IsopropylChloride = new Material.Builder(200, gtfoId("isopropyl_chloride"))
                .liquid()
                .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
                .build()
                .setFormula("(CH3)2CHCl", true);

        GTFOMaterials.Stearin = new Material.Builder(201, gtfoId("stearin"))
                .liquid()
                .color(0xffcc66)
                .flags(DISABLE_DECOMPOSITION)
                .components(Carbon, 57, Hydrogen, 110, Oxygen, 6)
                .build();

        GTFOMaterials.SodiumStearate = new Material.Builder(202, gtfoId("sodium_stearate"))
                .liquid()
                .components(Carbon, 18, Hydrogen, 35, Oxygen, 2, Sodium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C17H35COONa", true);

        GTFOMaterials.CitricAcid = new Material.Builder(203, gtfoId("citric_acid"))
                .liquid(new FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xccbd61)
                .components(Carbon, 5, Hydrogen, 7, Oxygen, 5)
                .build()
                .setFormula("HOC(CH2CO2H)2", true);

        GTFOMaterials.Guaiacol = new Material.Builder(204, gtfoId("guaiacol"))
                .liquid()
                .color(0xa63a00)
                .components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
                .build();

        GTFOMaterials.Acetaldehyde = new Material.Builder(205, gtfoId("acetaldehyde"))
                .liquid()
                .color(0xf3f2f1)
                .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
                .build();

        GTFOMaterials.Glyoxal = new Material.Builder(206, gtfoId("glyoxal"))
                .liquid()
                .color(0xc9c7ab)
                .components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
                .build();

        GTFOMaterials.GlyoxylicAcid = new Material.Builder(207, gtfoId("glyoxylic_acid"))
                .liquid(new FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xd9d5a0)
                .components(Carbon, 2, Hydrogen, 2, Oxygen, 3)
                .build();

        GTFOMaterials.Aniline = new Material.Builder(208, gtfoId("aniline"))
                .liquid()
                .color(0x4c911d)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C6H5NH2", true);

        GTFOMaterials.AceticAnhydride = new Material.Builder(209, gtfoId("acetic_anhydride"))
                .liquid()
                .components(Carbon, 4, Hydrogen, 6, Oxygen, 3)
                .build();

        GTFOMaterials.Nitrophenols = new Material.Builder(210, gtfoId("nitrophenols"))
                .liquid()
                .color(0xFFFFFF)
                .build()
                .setFormula("(C6H5NO3)(C6H5NO3)", true);

        GTFOMaterials.XPhenothiazineIiPropylChloride = new Material.Builder(211, gtfoId("x_phenothiazine_ii_propyl_chloride"))
                .liquid()
                .components(Carbon, 15, Hydrogen, 14, Nitrogen, 1, Sulfur, 1, Chlorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        GTFOMaterials.CoughSyrup = new Material.Builder(212, gtfoId("cough_syrup"))
                .liquid()
                .color(0x5c1b5e)
                .build();

        GTFOMaterials.Promethazine = new Material.Builder(213, gtfoId("promethazine"))
                .dust()
                .color(0xfadef2)
                .build()
                .setFormula("C17H20N2S");

        GTFOMaterials.Codeine = new Material.Builder(214, gtfoId("codeine"))
                .dust()
                .color(0xfadef2)
                .build()
                .setFormula("C18H21NO3");

        GTFOMaterials.Phenothiazine = new Material.Builder(215, gtfoId("phenothiazine"))
                .dust()
                .color(0xfadef2)
                .build()
                .setFormula("C12H9NS");

        GTFOMaterials.Diphenylamine = new Material.Builder(216, gtfoId("diphenylamine"))
                .dust()
                .color(0xfadef2)
                .build()
                .setFormula("C12H11N");

        GTFOMaterials.VanillylmandelicAcid = new Material.Builder(217, gtfoId("vanillylmandelic_acid"))
                .dust()
                .color(0xf2efbd)
                .build()
                .setFormula("C9H10O5");

        GTFOMaterials.VanilglycolicAcid = new Material.Builder(218, gtfoId("vanilglycolic_acid"))
                .dust()
                .color(0xebe7a4)
                .build()
                .setFormula("C9H8O5");

        GTFOMaterials.Vanillin = new Material.Builder(219, gtfoId("vanillin"))
                .dust()
                .color(0xfbfbfb)
                .build()
                .setFormula("C8H8O3");

        GTFOMaterials.Aminophenol = new Material.Builder(220, gtfoId("aminophenol"))
                .fluid()
                .color(0xFF7F50)
                .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
                .build()
                .setFormula("HOC6H4NH2", true);

        GTFOMaterials.IVNitrophenol = new Material.Builder(221, gtfoId("ivnitrophenol"))
                .dust()
                .color(0xFFFFE0)
                .build()
                .setFormula("C6H5NO3");

        GTFOMaterials.IINitrophenol = new Material.Builder(222, gtfoId("iinitrophenol"))
                .dust()
                .color(0xFFFF00)
                .build()
                .setFormula("C6H5NO3");

        GTFOMaterials.Paracetamol = new Material.Builder(223, gtfoId("paracetamol"))
                .dust()
                .color(0x0045A0).iconSet(MaterialIconSet.SHINY)
                .components(Carbon, 8, Hydrogen, 9, Nitrogen, 1, Oxygen, 2)
                .build();

        GTFOMaterials.EnderSugarSolution = new Material.Builder(224, gtfoId("ender_sugar_solution"))
                .liquid()
                .components(Beryllium, 1, Nitrogen, 2, Potassium, 1, Water, 1)
                .color(averageRGB(2, EnderPearl.getMaterialRGB(), Beryllium.getMaterialRGB()))
                .build();
    }
}
