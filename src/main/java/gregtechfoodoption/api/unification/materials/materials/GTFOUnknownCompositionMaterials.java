package gregtechfoodoption.api.unification.materials.materials;

import gregtech.api.unification.material.Material;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;

import static gregtechfoodoption.api.unification.materials.GTFOMaterials.gtfoId;

public class GTFOUnknownCompositionMaterials {

    public static void register() {
        GTFOMaterials.CookedMinceMeat = new Material.Builder(500, gtfoId("cooked_mince_meat"))
                .dust()
                .color(0x462b25)
                .build()
                .setFormula("Meat");

        GTFOMaterials.ShreddedParmesan = new Material.Builder(501, gtfoId("shredded_parmesan"))
                .dust()
                .color(0xdbd6b4)
                .build()
                .setFormula("Parm");

        GTFOMaterials.BlackPepper = new Material.Builder(502, gtfoId("black_pepper"))
                .dust()
                .color(0x02030f)
                .build()
                .setFormula("Pepper");

        GTFOMaterials.Nutmeg = new Material.Builder(503, gtfoId("nutmeg"))
                .dust()
                .color(0x391A0C)
                .build()
                .setFormula("Nutmeg");

        GTFOMaterials.GratedHorseradishRoot = new Material.Builder(504, gtfoId("grated_horseradish_root"))
                .dust()
                .color(0xE5D2C1)
                .build()
                .setFormula("Horseradish");

        GTFOMaterials.BoneChinaClay = new Material.Builder(505, gtfoId("bone_china_clay"))
                .dust()
                .color(0xEEC1C1)
                .build()
                .setFormula("BoneChinaClay");

        GTFOMaterials.PenicilliumRoqueforti = new Material.Builder(506, gtfoId("penicillium_roqueforti"))
                .dust()
                .color(0x2a7b5a)
                .build()
                .setFormula("Bacteria");

        GTFOMaterials.BurntBananaPeel = new Material.Builder(507, gtfoId("burnt_banana_peel"))
                .dust()
                .color(0x121110)
                .build()
                .setFormula("Bananananan");

        GTFOMaterials.MashedPotato = new Material.Builder(508, gtfoId("mashed_potato"))
                .dust()
                .color(0xf5d89f)
                .build()
                .setFormula("Potato");

        GTFOMaterials.ToughMeat = new Material.Builder(509, gtfoId("tough_meat"))
                .dust()
                .color(0xa63028)
                .build()
                .setFormula("Meat");

        GTFOMaterials.KubideMeat = new Material.Builder(510, gtfoId("kubide_meat"))
                .dust()
                .color(0x9B0600)
                .build()
                .setFormula("Meat");

        GTFOMaterials.BargMeat = new Material.Builder(511, gtfoId("barg_meat"))
                .dust()
                .color(0x7F0000)
                .build()
                .setFormula("Meat");
    }
}
