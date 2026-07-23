package gregtechfoodoption.api.unification.materials.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtechfoodoption.api.unification.materials.GTFOMaterials;

import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.api.unification.materials.GTFOMaterials.*;
import static gregtechfoodoption.api.utils.GTFOUtils.averageRGB;

public class GTFOFoodMaterials {

    public static void register() {
        registerExtracts();
        registerOils();
        registerDairy();
        registerSauces();
        registerSoySauceProcess();
        registerBeverages();
        registerWineProcess();
        registerSyrupsAndSweets();
        registerSoupsAndMeals();
        registerSpecialFluids();
    }

    private static void registerExtracts() {
        GTFOMaterials.LemonExtract = fluidBuilderFood(700, "lemon_extract")
                .color(0xfce80a).build();

        GTFOMaterials.LimeExtract = fluidBuilderFood(701, "lime_extract")
                .color(0x85f218).build();

        GTFOMaterials.OrangeExtract = fluidBuilderFood(702, "orange_extract")
                .color(0xff6100).build();

        GTFOMaterials.AppleExtract = fluidBuilderFood(703, "apple_extract")
                .color(0xe9ba58).build();

        GTFOMaterials.MelonExtract = fluidBuilderFood(704, "melon_extract")
                .color(0xfc7996).build();

        GTFOMaterials.ApricotExtract = fluidBuilderFood(705, "apricot_extract")
                .color(0xe3de9d).build();

        GTFOMaterials.GrapeExtract = fluidBuilderFood(706, "grape_extract")
                .color(0xa83351).build();

        GTFOMaterials.CranberryExtract = fluidBuilderFood(707, "cranberry_extract")
                .color(0x8C0D22).build();

        GTFOMaterials.VibrantExtract = fluidBuilderFood(708, "vibrant_extract")
                .color(0x3dfff7).build();

        GTFOMaterials.SoyLecithin = fluidBuilderFood(709, "soy_lecithin")
                .color(0xa6963a).build();
    }

    private static void registerOils() {
        GTFOMaterials.RawSoybeanOil = fluidBuilderFood(710, "raw_soybean_oil")
                .color(0xad5418).build();

        GTFOMaterials.HydratedSoybeanOil = fluidBuilderFood(711, "hydrated_soybean_oil")
                .color(0xc99c7d).build();

        GTFOMaterials.SoybeanOil = fluidBuilderFood(712, "soybean_oil")
                .color(0xe8e4a9).build();

        GTFOMaterials.OliveOil = fluidBuilderFood(713, "olive_oil")
                .color(0xd1db5a).build();

        GTFOMaterials.FryingOil = fluidBuilderFood(714, "frying_oil")
                .color(0xffe3a1).build();

        GTFOMaterials.HotFryingOil = fluidBuilderFood(715, "hot_frying_oil", 483)
                .color(0xffd166).build();
    }

    private static void registerDairy() {
        GTFOMaterials.ItalianBuffaloMilk = fluidBuilderFood(720, "italian_buffalo_milk")
                .color(0xfcfbf5).build();

        GTFOMaterials.CrudeRennetSolution = fluidBuilderFood(721, "crude_rennet_solution")
                .color(0xb0631a).build();

        GTFOMaterials.Whey = fluidBuilderFood(722, "whey")
                .color(0xf5ef9a).build();

        GTFOMaterials.ActivatedBuffaloMilk = fluidBuilderFood(723, "activated_buffalo_milk")
                .color(0xfff8cc).build();

        GTFOMaterials.WheySaltWaterMix = fluidBuilderFood(724, "whey_salt_water_mix")
                .color(0xecfc7e).build();

        GTFOMaterials.HeatedRicottaStarter = fluidBuilderFood(725, "heated_ricotta_starter", 348)
                .color(0xdef72f).build();

        GTFOMaterials.AcidicMilkSolution = fluidBuilderFood(726, "acidic_milk_solution")
                .color(0xb2c71c).build();

        GTFOMaterials.CoagulatingRicottaSolution = fluidBuilderFood(727, "coagulating_ricotta_solution")
                .color(0xeff5c9).build();

        GTFOMaterials.SkimmedMilk = fluidBuilderFood(728, "skimmed_milk")
                .color(0xf7ffe3).build();

        GTFOMaterials.PasteurizedMilk = fluidBuilderFood(729, "pasteurized_milk")
                .color(0xfefdf3).build();

        GTFOMaterials.MilkColloid = fluidBuilderFood(730, "milk_colloid")
                .color(0xe0d7bf).build();

        GTFOMaterials.Cream = fluidBuilderFood(731, "cream")
                .color(0xced2d9).build();

        GTFOMaterials.Butter = fluidBuilderFood(732, "butter")
                .color(0xffef82).build();

        GTFOMaterials.SourCream = fluidBuilderFood(733, "sour_cream")
                .color(0xe3de9d).build();

        GTFOMaterials.UnpasteurizedSkimmedMilk = fluidBuilderFood(734, "unpasteurized_skimmed_milk")
                .color(0xfcfcf0).build();

        GTFOMaterials.ParmigianoReggianoStarter = fluidBuilderFood(735, "parmigiano_reggiano_starter")
                .color(0xf0eac0).build();

        GTFOMaterials.CurdlingParmigianoReggiano = fluidBuilderFood(736, "curdling_parmigiano_reggiano")
                .color(0xfff4ab).build();

        GTFOMaterials.LacticAcidBacteria = fluidBuilderFood(737, "lactic_acid_bacteria")
                .color(0x371040).build();

        GTFOMaterials.FungalRennetSolution = fluidBuilderFood(738, "fungal_rennet_solution")
                .color(0x2a7b5a).build();

        GTFOMaterials.Albumen = fluidBuilderFood(739, "albumen")
                .color(0xfffef7).build();

        GTFOMaterials.Yolk = fluidBuilderFood(740, "yolk")
                .color(0xffdf00).build();

        GTFOMaterials.Egg = fluidBuilderFood(741, "egg")
                .color(0xFFFF0F).build();
    }

    private static void registerSauces() {
        GTFOMaterials.TomatoSauce = fluidBuilderFood(750, "tomato_sauce")
                .color(0xfc2217).build();

        GTFOMaterials.SoySauce = fluidBuilderFood(751, "soy_sauce")
                .color(0x4A2C2C).build();

        GTFOMaterials.Vinegar = fluidBuilderFood(752, "vinegar")
                .color(0xF0E68C).build();

        GTFOMaterials.LightSoySauce = fluidBuilderFood(753, "light_soy_sauce")
                .color(0x8B4513).build();

        GTFOMaterials.OysterSauce = fluidBuilderFood(754, "oyster_sauce")
                .color(0x8B0000).build();

        GTFOMaterials.Agrodolce = fluidBuilderFood(755, "agrodolce")
                .color(0xba1430).build();

        GTFOMaterials.Pesto = fluidBuilderFood(756, "pesto")
                .color(0x309027).build();

        GTFOMaterials.BechamelSauce = fluidBuilderFood(757, "bechamel_sauce")
                .color(0xD1B7AC).build();

        GTFOMaterials.CarbonaraSauce = fluidBuilderFood(758, "carbonara_sauce")
                .color(0xCDAF44).build();

        GTFOMaterials.BologneseSauce = fluidBuilderFood(759, "bolognese_sauce")
                .color(0x782A14).build();

        GTFOMaterials.TomatoBologneseSauce = fluidBuilderFood(760, "tomato_bolognese_sauce")
                .color(0xCA190F).build();

        GTFOMaterials.ChickenBroth = fluidBuilderFood(761, "chicken_broth")
                .color(0xA4600D).build();

        GTFOMaterials.VitelloTonnatoSauce = fluidBuilderFood(762, "vitello_tonnato_sauce")
                .color(0xC6BABE).build();

        GTFOMaterials.VitelloTonnatoFlavorant = fluidBuilderFood(763, "vitello_tonnato_flavorant")
                .color(0xC2ACB0).build();

        GTFOMaterials.RafanataMixture = fluidBuilderFood(764, "rafanata_mixture")
                .color(0xDCB239).build();

        GTFOMaterials.BeerBatter = fluidBuilderFood(765, "beer_batter")
                .color(0xe4cfc0).build();
    }

    private static void registerSoySauceProcess() {
        GTFOMaterials.Koji = fluidBuilderFood(770, "koji")
                .color(0xD2B48C).build();

        GTFOMaterials.Mash = fluidBuilderFood(771, "mash")
                .color(0xCD853F).build();

        GTFOMaterials.CrudeSoySauce = fluidBuilderFood(772, "crude_soy_sauce")
                .color(0x8B4513).build();

        GTFOMaterials.SoySauceResidue = fluidBuilderFood(773, "soy_sauce_residue")
                .color(0xA0522D).build();

        GTFOMaterials.SoyMilk = fluidBuilderFood(774, "soy_milk")
                .color(0xFFFDD0).build();
    }

    private static void registerBeverages() {
        GTFOMaterials.AppleCider = fluidBuilderFood(780, "apple_cider")
                .color(averageRGB(2, 0xE9BA58, FermentedBiomass.getMaterialRGB())).build();

        GTFOMaterials.PurpleDrink = fluidBuilderFood(781, "purple_drink")
                .color(0xb405ff).build();

        GTFOMaterials.Leninade = fluidBuilderFood(782, "leninade")
                .color(0x82661d).build();

        GTFOMaterials.WhiteWine = fluidBuilderFood(783, "white_wine")
                .color(0xD7C259).build();

        GTFOMaterials.RedWine = fluidBuilderFood(784, "red_wine")
                .color(0x641126).build();

        GTFOMaterials.Vodka = fluidBuilderFood(785, "vodka")
                .color(0x7d6933).build();

        GTFOMaterials.Coffee = fluidBuilderFood(786, "coffee", 368)
                .color(0x36312e).build();

        GTFOMaterials.EnergizedCoffee = fluidBuilderFood(787, "energized_coffee", 368)
                .color(0x695934).build();

        GTFOMaterials.CarbonatedWater = fluidBuilderFood(788, "carbonated_water")
                .color(0xf5ffff).build();

        GTFOMaterials.PoorQualityBeer = fluidBuilderFood(789, "poor_quality_beer")
                .color(0xa87b58).build();

        GTFOMaterials.WheatyJuice = fluidBuilderFood(790, "wheaty_juice")
                .color(0xa87b58).build();

        GTFOMaterials.Nilk = fluidBuilderFood(791, "nilk")
                .color(0x252626).build();

        GTFOMaterials.Etirps = fluidBuilderFood(792, "etirps")
                .color(0xb0ff73).build();

        GTFOMaterials.EtirpsCranberry = fluidBuilderFood(793, "etirps_cranberry")
                .color(averageRGB(2, 0x8C0D22, 0xbbddbb)).build();

        GTFOMaterials.LemonLimeSodaSyrup = fluidBuilderFood(794, "lemon_lime_soda_syrup")
                .color(0x76ff0d).build();

        GTFOMaterials.LemonLimeSolution = fluidBuilderFood(795, "lemon_lime_solution")
                .color(0xbddb5a).build();

        GTFOMaterials.LemonLimeSludge = fluidBuilderFood(796, "lemon_lime_sludge")
                .color(0x779906).build();

        GTFOMaterials.CranberrySludge = fluidBuilderFood(797, "cranberry_sludge")
                .color(averageRGB(2, 0x8C0D22, 0x222222)).build();

        GTFOMaterials.CranberrySodaSyrup = fluidBuilderFood(798, "cranberry_soda_syrup")
                .color(averageRGB(2, 0x8C0D22, 0x333333)).build();

        GTFOMaterials.HeatedWater = fluidBuilderFood(799, "heated_water", 343)
                .color(0x024B86).build();

        GTFOMaterials.PotatoJuice = fluidBuilderFood(800, "potato_juice")
                .color(0x786b48).build();
    }

    private static void registerWineProcess() {
        GTFOMaterials.MaceratedWhiteGrapes = fluidBuilderFood(810, "macerated_white_grapes")
                .color(0x8C9D41).build();

        GTFOMaterials.PressedWhiteWort = fluidBuilderFood(811, "pressed_white_wort")
                .color(0xDEF37F).build();

        GTFOMaterials.ClarifiedWhiteWort = fluidBuilderFood(812, "clarified_white_wort")
                .color(0xD8E4A4).build();

        GTFOMaterials.RedGrapesMust = fluidBuilderFood(813, "red_grapes_must")
                .color(0xD32552).build();

        GTFOMaterials.FermentedRedGrapesMust = fluidBuilderFood(814, "fermented_red_grapes_must")
                .color(0xA83351).build();

        GTFOMaterials.AlcoholicRedGrapeJuice = fluidBuilderFood(815, "alcoholic_red_grape_juice")
                .color(0xA4002A).build();
    }

    private static void registerSyrupsAndSweets() {
        GTFOMaterials.UnheatedCaneSyrup = fluidBuilderFood(820, "unheated_cane_syrup")
                .color(0xf0efe4).build();

        GTFOMaterials.CaneSyrup = fluidBuilderFood(821, "cane_syrup")
                .color(0xf2f1dc).build();

        GTFOMaterials.MoltenUnsweetenedChocolate = fluidBuilderFood(822, "molten_unsweetened_chocolate", 370)
                .color(0x7b3f00).build();

        GTFOMaterials.CocoaButter = fluidBuilderFood(823, "cocoa_butter")
                .color(0xe5dbce).build();

        GTFOMaterials.MoltenDarkChocolate = fluidBuilderFood(824, "molten_dark_chocolate", 360)
                .color(0x490206).build();

        GTFOMaterials.MoltenMilkChocolate = fluidBuilderFood(825, "molten_milk_chocolate", 350)
                .color(0x84563c).build();

        GTFOMaterials.AppleSyrup = fluidBuilderFood(826, "apple_syrup")
                .color(0xf2e1ac).build();

        GTFOMaterials.AppleCandySyrup = fluidBuilderFood(827, "apple_candy_syrup")
                .color(0xe7f5ae).build();

        GTFOMaterials.GelatinSolution = fluidBuilderFood(828, "gelatin_solution")
                .color(0xD3D3D3).build();

        GTFOMaterials.IceCreamMixture = fluidBuilderFood(829, "ice_cream_mixture")
                .color(0xdebd80).build();

        GTFOMaterials.LingonberryJam = fluidBuilderFood(830, "lingonberry_jam")
                .color(0x61262D).build();

        GTFOMaterials.ElderberryJam = fluidBuilderFood(831, "elderberry_jam")
                .color(0x5F414F).build();
    }

    private static void registerSoupsAndMeals() {
        GTFOMaterials.MushroomSoup = fluidBuilderFood(840, "mushroom_soup", 343)
                .color(0xedcaaf).build();

        GTFOMaterials.BeetrootSoup = fluidBuilderFood(841, "beetroot_soup", 343)
                .color(0xc25132).build();

        GTFOMaterials.RabbitStew = fluidBuilderFood(842, "rabbit_stew", 343)
                .color(0xe0c0a0).build();

        GTFOMaterials.Polenta = fluidBuilderFood(843, "polenta")
                .color(0xBBA844).build();

        GTFOMaterials.PastaEFagioliBase = fluidBuilderFood(844, "pasta_e_fagioli_base", 343)
                .color(0xD4592F).build();

        GTFOMaterials.MixedPastaEFagioli = fluidBuilderFood(845, "mixed_pasta_e_fagioli", 343)
                .color(0xE48628).build();
    }

    private static void registerSpecialFluids() {
        GTFOMaterials.Sludge = fluidBuilderFood(850, "sludge")
                .color(0x24140b).build();

        GTFOMaterials.AlkalineExtract = fluidBuilderFood(851, "alkaline_extract")
                .color(0x121110).build();

        GTFOMaterials.Blood = fluidBuilderFood(852, "blood", 310)
                .color(0x691a15).build();

        GTFOMaterials.FertilizerSolution = fluidBuilderFood(853, "fertilizer_solution")
                .color(0x947760).build();

        GTFOMaterials.HighFructoseCornSyrupSolution = fluidBuilderFood(854, "hfcs_solution")
                .color(0xe3bc20).build();

        GTFOMaterials.RubberSap = fluidBuilderFood(855, "rubber_sap")
                .color(0xf7f6dc).build();

        GTFOMaterials.RainbowSap = fluidBuilderFoodCustom(856, "rainbow_sap")
                .color(0xffffff).build();

        GTFOMaterials.ChorusJuice = fluidBuilderFood(857, "chorus_juice")
                .color(0xa670e0).build();

        GTFOMaterials.FermentedChorusJuice = fluidBuilderFood(858, "fermented_chorus_juice")
                .color(0xb5e8e6).build();

        GTFOMaterials.Antaf = fluidBuilderFood(859, "antaf")
                .color(0xd4b5e8).build();

        GTFOMaterials.SweetenedDilutedCaneSyrupMixture = fluidBuilderFood(860, "sweetened_diluted_cane_syrup_mixture")
                .color(0xdedcc8).build();

        GTFOMaterials.MarshmallowSyrupMixture = fluidBuilderFood(861, "marshmallow_syrup_mixture")
                .color(0xe6e0dc).build();

        GTFOMaterials.MarshmallowFoam = fluidBuilderFood(862, "marshmallow_foam")
                .color(0xe6e0dc).build();

        GTFOMaterials.StarchFilledWater = fluidBuilderFood(863, "starch_filled_water")
                .color(0xd1cbbe).build();
    }
}
