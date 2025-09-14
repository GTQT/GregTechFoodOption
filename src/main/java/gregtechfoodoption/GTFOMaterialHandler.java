package gregtechfoodoption;

import gregtech.api.GregTechAPI;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttribute;
import gregtech.api.fluids.attribute.FluidAttributes;
import gregtech.api.items.metaitem.MetaOreDictItem;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.LocalizationUtils;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.materials.CleanerProperty;
import gregtechfoodoption.materials.FertilizerProperty;
import gregtechfoodoption.materials.LacingProperty;
import it.unimi.dsi.fastutil.ints.Int2BooleanArrayMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtechfoodoption.GTFOValues.Organic;
import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;
import static gregtechfoodoption.utils.GTFOUtils.averageRGB;

public class GTFOMaterialHandler {
    static int startID = 0;
    public static int getID()
    {
        return startID++;
    }
    public static final PropertyKey<FertilizerProperty> FERTILIZER = new PropertyKey<>("gtfo_fertilizer", FertilizerProperty.class);
    public static final PropertyKey<LacingProperty> LACING = new PropertyKey<>("gtfo_lacing", LacingProperty.class);
    public static final PropertyKey<CleanerProperty> CLEANER = new PropertyKey<>("gtfo_cleaner", CleanerProperty.class);

    public static final Material IsopropylChloride = fluidBuilder(getID(), "isopropyl_chloride")
            .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
            .build()
            .setFormula("(CH3)2CHCl", true);

    public static final Material StarchFilledWater = fluidBuilder(getID(), "starch_filled_water")
            .color(0xd1cbbe)
            .build();

    public static final Material ItalianBuffaloMilk = fluidBuilder(getID(), "italian_buffalo_milk")
            .color(0xfcfbf5)
            .build();

    public static final Material CrudeRennetSolution = fluidBuilder(getID(), "crude_rennet_solution")
            .color(0xb0631a)
            .build();

    public static final Material Whey = fluidBuilder(getID(), "whey")
            .color(0xf5ef9a)
            .build();

    public static final Material ActivatedBuffaloMilk = fluidBuilder(getID(), "activated_buffalo_milk")
            .color(0xfff8cc)
            .build();

    public static final Material WheySaltWaterMix = fluidBuilder(getID(), "whey_salt_water_mix")
            .color(0xecfc7e)
            .build();

    public static final Material HeatedRicottaStarter = fluidBuilder(getID(), "heated_ricotta_starter", 348)
            .color(0xdef72f)
            .build();

    public static final Material AcidicMilkSolution = fluidBuilder(getID(), "acidic_milk_solution")
            .color(0xb2c71c)
            .build();

    public static final Material CoagulatingRicottaSolution = fluidBuilder(getID(), "coagulating_ricotta_solution")
            .color(0xeff5c9)
            .build();

    public static final Material Sludge = fluidBuilder(getID(), "sludge")
            .color(0x24140b)
            .build();

    public static final Material AlkalineExtract = fluidBuilder(getID(), "alkaline_extract")
            .color(0x121110)
            .build();

    public static final Material PotatoJuice = fluidBuilder(getID(), "potato_juice")
            .color(0x786b48)
            .build();

    public static final Material Vodka = fluidBuilder(getID(), "vodka")
            .color(0x7d6933)
            .build();

    public static final Material PerchloricAcid = fluidBuilder(getID(), "perchloric_acid", FluidAttributes.ACID)
            .components(Hydrogen, 1, Chlorine, 1, Oxygen, 4)
            .build();

    public static final Material ChloroauricAcid = fluidBuilder(getID(), "chloroauric_acid", FluidAttributes.ACID)
            .components(Hydrogen, 1, Gold, 1, Chlorine, 4)
            .build();

    public static final Material MoistAir = gasBuilder(getID(), "moist_air", 273)
            .color(0x82c8ff)
            .build();

    public static final Material ColdMoistAir = gasBuilder(getID(), "cold_moist_air", 243)
            .color(0x72a2ff)
            .build();

    public static final Material Stearin = fluidBuilder(getID(), "stearin")
            .color(0xffcc66)
            .flags(DISABLE_DECOMPOSITION)
            .components(Carbon, 57, Hydrogen, 110, Oxygen, 6)
            .build();

    /*    public static final Material StearicAcid = fluidBuilder(21538, "stearic_acid", FluidTypes.ACID).color(0xfff7e6)
                .components(Carbon, 18, Hydrogen, 36, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("C17H35CO2H", true); // used as a food additive synthesized from Fat (Basically turning it into 3 parts)*/

    public static final Material SodiumStearate = fluidBuilder(getID(), "sodium_stearate")
            .components(Carbon, 18, Hydrogen, 35, Oxygen, 2, Sodium, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C17H35COONa", true);

    public static final Material CitricAcid = fluidBuilder(getID(), "citric_acid", FluidAttributes.ACID)
            .color(0xccbd61)
            .components(Carbon, 5, Hydrogen, 7, Oxygen, 5)
            .build()
            .setFormula("HOC(CH2CO2H)2", true); //good for processing food

    public static final Material HydrogenCyanide = fluidBuilder(getID(), "hydrogen_cyanide")
            .color(0x6e6a5e)
            .components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
            .build();

    public static final Material SoyLecithin = fluidBuilder(getID(), "soy_lecithin")
            .color(0xa6963a)
            .build();

    public static final Material RawSoybeanOil = fluidBuilder(getID(), "raw_soybean_oil")
            .color(0xad5418)
            .build();

    public static final Material HydratedSoybeanOil = fluidBuilder(getID(), "hydrated_soybean_oil")
            .color(0xc99c7d)
            .build();

    public static final Material SoybeanOil = fluidBuilder(getID(), "soybean_oil")
            .color(0xe8e4a9)
            .build();

    public static final Material Guaiacol = fluidBuilder(getID(), "guaiacol")
            .color(0xa63a00)
            .components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
            .build();

    public static final Material Acetaldehyde = fluidBuilder(getID(), "acetaldehyde")
            .color(0xf3f2f1)
            .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
            .build();

    public static final Material Glyoxal = fluidBuilder(getID(), "glyoxal")
            .color(0xc9c7ab)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
            .build();

    public static final Material GlyoxylicAcid = fluidBuilder(getID(), "glyoxylic_acid", FluidAttributes.ACID)
            .color(0xd9d5a0)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 3)
            .build();

    public static final Material SodiumArseniteSolution = fluidBuilder(getID(), "sodium_arsenite_solution")
            .color(averageRGB(2, SodaAsh.getMaterialRGB(), Arsenic.getMaterialRGB()))
            .components(Sodium, 1, Arsenic, 1, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build();

    public static final Material RubberSap = fluidBuilder(getID(), "rubber_sap")
            .color(0xf7f6dc)
            .build();

    public static final Material RainbowSap = fluidBuilderCustom(getID(), "rainbow_sap")
            .color(0xffffff)
            .build();

    public static final Material BlueVitriol = fluidBuilder(getID(), "blue_vitriol")
            .color(0x4242DE)
            .components(Copper, 1, Sulfur, 1, Oxygen, 4)
            .build();

    public static final Material BakingSodaSolution = fluidBuilder(getID(), "baking_soda_solution")
            .color(SodiumBicarbonate.getMaterialRGB())
            .components(SodiumBicarbonate, 1, Water, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build();

    public static final Material SodiumSulfate = new Material.Builder(getID(), gtfoId("sodium_sulfate"))
            .dust()
            .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
            .build();

    public static final Material Blood = fluidBuilder(getID(), "blood", 310)
            .color(0x691a15)
            .build();

    public static final Material FertilizerSolution = fluidBuilder(getID(), "fertilizer_solution")
            .color(0x947760)
            .build();

    public static final Material HighFructoseCornSyrupSolution = fluidBuilder(getID(), "hfcs_solution")
            .color(0xe3bc20)
            .build();

    public static final Material XPhenothiazineIiPropylChloride = fluidBuilder(getID(), "x_phenothiazine_ii_propyl_chloride")
            .components(Carbon, 15, Hydrogen, 14, Nitrogen, 1, Sulfur, 1, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build();

    public static final Material CoughSyrup = fluidBuilder(getID(), "cough_syrup")
            .color(0x5c1b5e)
            .build();

    public static final Material Aniline = fluidBuilder(getID(), "aniline")
            .color(0x4c911d)
            .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H5NH2", true);

    public static final Material AceticAnhydride = fluidBuilder(getID(), "acetic_anhydride")
            .components(Carbon, 4, Hydrogen, 6, Oxygen, 3)
            .build();

    public static final Material Nitrophenols = fluidBuilder(getID(), "nitrophenols")
            .color(0xFFFFFF)
            .build()
            .setFormula("(C6H5NO3)(C6H5NO3)", true);

    public static final Material ParmigianoReggianoStarter = fluidBuilder(getID(), "parmigiano_reggiano_starter")
            .color(0xf0eac0)
            .build();

    public static final Material CurdlingParmigianoReggiano = fluidBuilder(getID(), "curdling_parmigiano_reggiano")
            .color(0xfff4ab)
            .build();

    public static final Material WhiteWine = fluidBuilder(getID(), "white_wine")
            .color(0xD7C259)
            .build();

    public static final Material MaceratedWhiteGrapes = fluidBuilder(getID(), "macerated_white_grapes")
            .color(0x8C9D41)
            .build();

    public static final Material PressedWhiteWort = fluidBuilder(getID(), "pressed_white_wort")
            .color(0xDEF37F)
            .build();

    public static final Material ClarifiedWhiteWort = fluidBuilder(getID(), "clarified_white_wort")
            .color(0xD8E4A4)
            .build();

    public static final Material RedGrapesMust = fluidBuilder(getID(), "red_grapes_must")
            .color(0xD32552)
            .build();

    public static final Material FermentedRedGrapesMust = fluidBuilder(getID(), "fermented_red_grapes_must")
            .color(0xA83351)
            .build();

    public static final Material AlcoholicRedGrapeJuice = fluidBuilder(getID(), "alcoholic_red_grape_juice")
            .color(0xA4002A)
            .build();

    public static final Material RedWine = fluidBuilder(getID(), "red_wine")
            .color(0x641126)
            .build();

    public static final Material LacticAcidBacteria = fluidBuilder(getID(), "lactic_acid_bacteria")
            .color(0x371040)
            .build();

    public static final Material FungalRennetSolution = fluidBuilder(getID(), "fungal_rennet_solution")
            .color(0x2a7b5a)
            .build();

    public static final Material EnderPearlSolution = fluidBuilder(getID(), "ender_pearl_solution")
            .components(EnderPearl, 1, Water, 1)
            .color(EnderPearl.getMaterialRGB())
            .build();

    public static final Material EnderSugarSolution = fluidBuilder(getID(), "ender_sugar_solution")
            .components(Beryllium, 1, Nitrogen, 2, Potassium, 1, Water, 1)
            .color(averageRGB(2, EnderPearlSolution.getMaterialRGB(), Beryllium.getMaterialRGB()))
            .build();

    public static final Material ChorusJuice = fluidBuilder(getID(), "chorus_juice")
            .color(0xa670e0)
            .build();

    public static final Material FermentedChorusJuice = fluidBuilder(getID(), "fermented_chorus_juice")
            .color(0xb5e8e6)
            .build();

    public static final Material Antaf = fluidBuilder(getID(), "antaf")
            .color(0xd4b5e8)
            .build();

    public static final Material VibrantExtract = fluidBuilder(getID(), "vibrant_extract")
            .color(0x3dfff7)
            .build();

    public static final Material ApricotExtract = fluidBuilder(getID(), "apricot_extract")
            .color(0xe3de9d)
            .build();

    public static final Material GrapeExtract = fluidBuilder(getID(), "grape_extract")
            .color(0xa83351)
            .build();

    public static final Material SweetenedDilutedCaneSyrupMixture = fluidBuilder(getID(), "sweetened_diluted_cane_syrup_mixture")
            .color(0xdedcc8)
            .build();

    public static final Material MarshmallowSyrupMixture = fluidBuilder(getID(), "marshmallow_syrup_mixture")
            .color(0xe6e0dc)
            .build();

    public static final Material MarshmallowFoam = fluidBuilder(getID(), "marshmallow_foam")
            .color(0xe6e0dc)
            .build();

    public static final Material SodiumCarbonateSolution = fluidBuilder(getID(), "sodium_carbonate_solution")
            .color(averageRGB(2, 0xaaaaaa, SodaAsh.getMaterialRGB()))
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .components(Water, 1, SodaAsh, 1)
            .build();

    public static final Material CookedMinceMeat=new Material.Builder(getID(), gtfoId("cooked_mince_meat"))
            .dust()
            .color(0x462b25)
            .build()
            .setFormula("Meat");

    public static final Material ShreddedParmesan=new Material.Builder(getID(), gtfoId("shredded_parmesan"))
            .dust()
            .color(0xdbd6b4)
            .build()
            .setFormula("Parm");

    public static final Material BlackPepper=new Material.Builder(getID(), gtfoId("black_pepper"))
            .dust()
            .color(0x02030f)
            .build()
            .setFormula("Pepper");

    public static final Material Nutmeg=new Material.Builder(getID(), gtfoId("nutmeg"))
            .dust()
            .color(0x391A0C)
            .build()
            .setFormula("Nutmeg");

    public static final Material GratedHorseradishRoot=new Material.Builder(getID(), gtfoId("grated_horseradish_root"))
            .dust()
            .color(0xE5D2C1)
            .build()
            .setFormula("Horseradish");

    public static final Material BoneChinaClay=new Material.Builder(getID(), gtfoId("bone_china_clay"))
            .dust()
            .color(0xEEC1C1)
            .build()
            .setFormula("BoneChinaClay");

    public static final Material Promethazine = new Material.Builder(getID(), gtfoId("promethazine"))
            .dust()
            .color(0xfadef2)
            .build()
            .setFormula("C17H20N2S");

    public static final Material Codeine = new Material.Builder(getID(), gtfoId("codeine"))
            .dust()
            .color(0xfadef2)
            .build()
            .setFormula("C18H21NO3");

    public static final Material Phenothiazine = new Material.Builder(getID(), gtfoId("phenothiazine"))
            .dust()
            .color(0xfadef2)
            .build()
            .setFormula("C12H9NS");

    public static final Material Diphenylamine = new Material.Builder(getID(), gtfoId("diphenylamine"))
            .dust()
            .color(0xfadef2)
            .build()
            .setFormula("C12H11N");


    public static final Material PenicilliumRoqueforti = new Material.Builder(getID(), gtfoId("penicillium_roqueforti"))
            .dust()
            .color(0x2a7b5a)
            .build()
            .setFormula("Bacteria");

    public static final Material BurntBananaPeel= new Material.Builder(getID(), gtfoId("burnt_banana_peel"))
            .dust()
            .color(0x121110)
            .build()
            .setFormula("Bananananan");

    public static final Material AmmoniumPerchlorate = new Material.Builder(getID(), gtfoId("ammonium_perchlorate"))
            .dust()
            .color(0x121110)
            .build()
            .setFormula("NH4ClO4");

    public static final Material PotassiumPerchlorate = new Material.Builder(getID(), gtfoId("potassium_perchlorate"))
            .dust()
            .color(0x121110)
            .build()
            .setFormula("KClO4");

    public static final Material MashedPotato= new Material.Builder(getID(), gtfoId("mashed_potato"))
            .dust()
            .color(0xf5d89f)
            .build()
            .setFormula("Potato");

    public static final Material ToughMeat= new Material.Builder(getID(), gtfoId("tough_meat"))
            .dust()
            .color(0xa63028)
            .build()
            .setFormula("Meat");

    public static final Material KubideMeat= new Material.Builder(getID(), gtfoId("kubide_meat"))
            .dust()
            .color(0x9B0600)
            .build()
            .setFormula("Meat");

    public static final Material BargMeat= new Material.Builder(getID(), gtfoId("barg_meat"))
            .dust()
            .color(0x7F0000)
            .build()
            .setFormula("Meat");


    public static final Material SodiumPerchlorate = new Material.Builder(getID(), gtfoId("sodium_perchlorate"))
            .dust()
            .color(0x121110)
            .build()
            .setFormula("NaClO4");

    public static final Material SodiumChlorate = new Material.Builder(getID(), gtfoId("sodium_chlorate"))
            .dust()
            .color(0x121110)
            .build()
            .setFormula("NaClO3");

    public static final Material VanillylmandelicAcid= new Material.Builder(getID(), gtfoId("vanillylmandelic_acid"))
            .dust()
            .color(0xf2efbd)
            .build()
            .setFormula("C9H10O5");

    public static final Material VanilglycolicAcid= new Material.Builder(getID(), gtfoId("vanilglycolic_acid"))
            .dust()
            .color(0xebe7a4)
            .build()
            .setFormula("C9H8O5");

    public static final Material Vanillin = new Material.Builder(getID(), gtfoId("vanillin"))
            .dust()
            .color(0xfbfbfb)
            .build()
            .setFormula("C8H8O3");

    public static final Material Aminophenol = new Material.Builder(getID(), gtfoId("aminophenol"))
            .fluid()
            .color(0xFF7F50)
            .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1, Oxygen, 1)
            .build()
            .setFormula("HOC6H4NH2", true);

    public static final Material IVNitrophenol= new Material.Builder(getID(), gtfoId("ivnitrophenol"))
            .dust()
            .color(0xFFFFE0)
            .build()
            .setFormula("C6H5NO3");

    public static final Material IINitrophenol= new Material.Builder(getID(), gtfoId("iinitrophenol"))
            .dust()
            .color(0xFFFF00)
            .build()
            .setFormula("C6H5NO3");

    public static final Material SodiumCyanide= new Material.Builder(getID(), gtfoId("sodium_cyanide"))
            .dust()
            .color(0x5F7C8C)
            .iconSet(MaterialIconSet.METALLIC)
            .flags(DISABLE_DECOMPOSITION)
            .components(Sodium, 1, Carbon, 1, Nitrogen, 1)
            .build();

    public static final Material LithiumOxide= new Material.Builder(getID(), gtfoId("lithium_oxide"))
            .dust()
            .color(0x121110)
            .build()
            .setFormula("LiO");

    public static final Material LithiumCarbonate= new Material.Builder(getID(), gtfoId("lithium_carbonate"))
            .dust()
            .color(0x121110)
            .components(Lithium, 2, Carbon, 1, Oxygen, 3)
            .build()
            .setFormula("Li2CO3");

    public static final Material Paracetamol = new Material.Builder(getID(), gtfoId("paracetamol")).dust()
            .color(0x0045A0).iconSet(MaterialIconSet.SHINY)
            .components(Carbon, 8, Hydrogen, 9, Nitrogen, 1, Oxygen, 2)
            .build();

    private static final Int2BooleanMap FOOD_RELATED_MATERIALS = new Int2BooleanArrayMap();

    public static final Material LemonExtract = fluidBuilderFood(getID(), "lemon_extract")
            .color(0xfce80a)
            .build();

    public static final Material LimeExtract = fluidBuilderFood(getID(), "lime_extract")
            .color(0x85f218)
            .build();

    public static final Material OrangeExtract = fluidBuilderFood(getID(), "orange_extract")
            .color(0xff6100)
            .build();

    public static final Material AppleExtract = fluidBuilderFood(getID(), "apple_extract")
            .color(0xe9ba58)
            .build();

    public static final Material AppleCider = fluidBuilderFood(getID(), "apple_cider")
            .color(averageRGB(2, 0xE9BA58, FermentedBiomass.getMaterialRGB()))
            .build();

    public static final Material UnheatedCaneSyrup = fluidBuilderFood(getID(), "unheated_cane_syrup")
            .color(0xf0efe4)
            .build();

    public static final Material CaneSyrup = fluidBuilderFood(getID(), "cane_syrup")
            .color(0xf2f1dc)
            .build();

    public static final Material PurpleDrink = fluidBuilderFood(getID(), "purple_drink")
            .color(0xb405ff)
            .build();

    public static final Material FryingOil = fluidBuilderFood(getID(), "frying_oil")
            .color(0xffe3a1)
            .build();

    public static final Material HotFryingOil = fluidBuilderFood(getID(), "hot_frying_oil", 483)
            .color(0xffd166)
            .build();

    public static final Material MushroomSoup = fluidBuilderFood(getID(), "mushroom_soup", 343)
            .color(0xedcaaf)
            .build();

    public static final Material BeetrootSoup = fluidBuilderFood(getID(), "beetroot_soup", 343)
            .color(0xc25132)
            .build();

    public static final Material TomatoSauce = fluidBuilderFood(getID(), "tomato_sauce")
            .color(0xfc2217)
            .build();

    public static final Material OliveOil = fluidBuilderFood(getID(), "olive_oil")
            .color(0xd1db5a)
            .build();

    public static final Material Leninade = fluidBuilderFood(getID(), "leninade")
            .color(0x82661d)
            .build();

    public static final Material Albumen = fluidBuilderFood(getID(), "albumen")
            .color(0xfffef7)
            .build();

    public static final Material Yolk = fluidBuilderFood(getID(), "yolk")
            .color(0xffdf00)
            .build();

    public static final Material Butter = fluidBuilderFood(getID(), "butter")
            .color(0xffef82)
            .build();

    public static final Material RabbitStew = fluidBuilderFood(getID(), "rabbit_stew", 343)
            .color(0xe0c0a0)
            .build();

    public static final Material Cream = fluidBuilderFood(getID(), "cream")
            .color(0xced2d9)
            .build();

    public static final Material SkimmedMilk = fluidBuilderFood(getID(), "skimmed_milk")
            .color(0xf7ffe3)
            .build();

    //public static final GTFOOredictItem.OreDictValueItem SlimeIngot = SHAPED_ITEM.addOreDictItem(1120, "slime_ingot", 0x84C873, Organic, OrePrefix.ingot);

    public static final Material PasteurizedMilk = fluidBuilderFood(getID(), "pasteurized_milk")
            .color(0xfefdf3)
            .build();

    public static final Material MilkColloid = fluidBuilderFood(getID(), "milk_colloid")
            .color(0xe0d7bf)
            .build();

    public static final Material IceCreamMixture = fluidBuilderFood(getID(), "ice_cream_mixture")
            .color(0xdebd80)
            .build();

    public static final Material MelonExtract = fluidBuilderFood(getID(), "melon_extract")
            .color(0xfc7996)
            .build();

    public static final Material MoltenUnsweetenedChocolate = fluidBuilderFood(getID(), "molten_unsweetened_chocolate", 370)
            .color(0x7b3f00)
            .build();

    public static final Material CocoaButter = fluidBuilderFood(getID(), "cocoa_butter")
            .color(0xe5dbce)
            .build();

    public static final Material MoltenDarkChocolate = fluidBuilderFood(getID(), "molten_dark_chocolate", 360)
            .color(0x490206)
            .build();

    public static final Material MoltenMilkChocolate = fluidBuilderFood(getID(), "molten_milk_chocolate", 350)
            .color(0x84563c)
            .build();

    public static final Material BeerBatter = fluidBuilderFood(getID(), "beer_batter")
            .color(0xe4cfc0)
            .build();

    public static final Material WheatyJuice = fluidBuilderFood(getID(), "wheaty_juice")
            .color(0xa87b58)
            .build();

    public static final Material PoorQualityBeer = fluidBuilderFood(getID(), "poor_quality_beer")
            .color(0xa87b58)
            .build();

    public static final Material Nilk = fluidBuilderFood(getID(), "nilk")
            .color(0x252626)
            .build();

    public static final Material AppleSyrup = fluidBuilderFood(getID(), "apple_syrup")
            .color(0xf2e1ac)
            .build();

    public static final Material AppleCandySyrup = fluidBuilderFood(getID(), "apple_candy_syrup")
            .color(0xe7f5ae)
            .build();

    public static final Material Etirps = fluidBuilderFood(getID(), "etirps")
            .color(0xb0ff73)
            .build();

    public static final Material LemonLimeSodaSyrup = fluidBuilderFood(getID(), "lemon_lime_soda_syrup")
            .color(0x76ff0d)
            .build();

    public static final Material CarbonatedWater = fluidBuilderFood(getID(), "carbonated_water")
            .color(0xf5ffff)
            .build();

    public static final Material LemonLimeSolution = fluidBuilderFood(getID(), "lemon_lime_solution")
            .color(0xbddb5a)
            .build();

    public static final Material LemonLimeSludge = fluidBuilderFood(getID(), "lemon_lime_sludge")
            .color(0x779906)
            .build();

    public static final Material HeatedWater = fluidBuilderFood(getID(), "heated_water", 343)
            .color(0x024B86)
            .build();

    public static final Material GelatinSolution = fluidBuilderFood(getID(), "gelatin_solution")
            .color(0xD3D3D3)
            .build();

    public static final Material Egg = fluidBuilderFood(21587, "egg")
            .color(0xFFFF0F)
            .build();

    public static final Material UnpasteurizedSkimmedMilk = fluidBuilderFood(getID(), "unpasteurized_skimmed_milk")
            .color(0xfcfcf0)
            .build();

    public static final Material Agrodolce = fluidBuilderFood(getID(), "agrodolce")
            .color(0xba1430)
            .build();

    public static final Material Polenta = fluidBuilderFood(getID(), "polenta")
            .color(0xBBA844)
            .build();

    public static final Material Pesto = fluidBuilderFood(getID(), "pesto")
            .color(0x309027)
            .build();

    public static final Material BechamelSauce = fluidBuilderFood(getID(), "bechamel_sauce")
            .color(0xD1B7AC)
            .build();

    public static final Material ChickenBroth = fluidBuilderFood(getID(), "chicken_broth")
            .color(0xA4600D)
            .build();

    public static final Material VitelloTonnatoSauce = fluidBuilderFood(getID(), "vitello_tonnato_sauce")
            .color(0xC6BABE)
            .build();

    public static final Material VitelloTonnatoFlavorant = fluidBuilderFood(getID(), "vitello_tonnato_flavorant")
            .color(0xC2ACB0)
            .build();

    public static final Material RafanataMixture = fluidBuilderFood(getID(), "rafanata_mixture")
            .color(0xDCB239)
            .build();

    public static final Material CarbonaraSauce = fluidBuilderFood(getID(), "carbonara_sauce")
            .color(0xCDAF44)
            .build();

    public static final Material PastaEFagioliBase = fluidBuilderFood(getID(), "pasta_e_fagioli_base", 343)
            .color(0xD4592F)
            .build();

    public static final Material MixedPastaEFagioli = fluidBuilderFood(getID(), "mixed_pasta_e_fagioli", 343)
            .color(0xE48628)
            .build();

    public static final Material BologneseSauce = fluidBuilderFood(getID(), "bolognese_sauce")
            .color(0x782A14)
            .build();

    public static final Material TomatoBologneseSauce = fluidBuilderFood(getID(), "tomato_bolognese_sauce")
            .color(0xCA190F)
            .build();

    public static final Material CranberryExtract = fluidBuilderFood(getID(), "cranberry_extract")
            .color(0x8C0D22)
            .build();

    public static final Material EtirpsCranberry = fluidBuilderFood(getID(), "etirps_cranberry")
            .color(averageRGB(2, CranberryExtract.getMaterialRGB(), 0xbbddbb))
            .build();

    public static final Material CranberrySludge = fluidBuilderFood(getID(), "cranberry_sludge")
            .color(averageRGB(2, CranberryExtract.getMaterialRGB(), 0x222222))
            .build();

    public static final Material CranberrySodaSyrup = fluidBuilderFood(getID(), "cranberry_soda_syrup")
            .color(averageRGB(2, CranberryExtract.getMaterialRGB(), 0x333333))
            .build();

    public static final Material LingonberryJam = fluidBuilderFood(getID(), "lingonberry_jam")
            .color(0x61262D)
            .build();

    public static final Material ElderberryJam = fluidBuilderFood(getID(), "elderberry_jam")
            .color(0x5F414F)
            .build();

    public static final Material SourCream = fluidBuilderFood(getID(), "sour_cream")
            .color(0xe3de9d)
            .build();

    public static final Material Coffee = fluidBuilderFood(getID(), "coffee", 368)
            .color(0x36312e)
            .build();

    public static final Material EnergizedCoffee = fluidBuilderFood(getID(), "energized_coffee", 368)
            .color(0x695934)
            .build();

    //酱油
    public static final Material SoySauce = fluidBuilderFood(getID(), "soy_sauce")
            .color(0x4A2C2C)
            .build();

    //醋
    public static final Material Vinegar = fluidBuilderFood(getID(), "vinegar")
            .color(0xF0E68C)
            .build();

    //生抽
    public static final Material LightSoySauce = fluidBuilderFood(getID(), "light_soy_sauce")
            .color(0x8B4513)
            .build();

    //蚝油
    public static final Material OysterSauce = fluidBuilderFood(getID(), "oyster_sauce")
            .color(0x8B0000)
            .build();

    // 成曲 (Koji)
    public static final Material Koji = fluidBuilderFood(getID(), "koji")
            .color(0xD2B48C)
            .build();

    // 酱醪 (Mash)
    public static final Material Mash = fluidBuilderFood(getID(), "mash")
            .color(0xCD853F)
            .build();

    // 粗酱油 (Crude Soy Sauce)
    public static final Material CrudeSoySauce = fluidBuilderFood(getID(), "crude_soy_sauce")
            .color(0x8B4513)
            .build();

    // 酱渣 (Soy Sauce Residue)
    public static final Material SoySauceResidue = fluidBuilderFood(getID(), "soy_sauce_residue")
            .color(0xA0522D)
            .build();

    //豆浆
    public static final Material SoyMilk = fluidBuilderFood(getID(), "soy_milk")
            .color(0xFFFDD0)
            .build();


    // Oredict
    public static final GTFOOredictItem.OreDictValueItem COFFEE_GROUNDS = SHAPED_ITEM.addOreDictItem(1, "coffee_grounds", 0x1a1612, MaterialIconSet.DULL, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictValueItem SMALL_ROASTED_COFFEE = SHAPED_ITEM.addOreDictItem(2, "roasted_coffee_small", 0x1a1612, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_ROASTED_COFFEE = SHAPED_ITEM.addOreDictItem(3, "roasted_coffee_large", 0x1a1612, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_GRADED_COFFEE = SHAPED_ITEM.addOreDictItem(4, "graded_coffee_small", 0x635c55, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_GRADED_COFFEE = SHAPED_ITEM.addOreDictItem(5, "graded_coffee_large", 0x635c55, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_HULLED_COFFEE = SHAPED_ITEM.addOreDictItem(6, "hulled_coffee_small", 0x7d4b16, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_HULLED_COFFEE = SHAPED_ITEM.addOreDictItem(7, "hulled_coffee_large", 0x7d4b16, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_DRIED_COFFEE = SHAPED_ITEM.addOreDictItem(8, "dried_coffee_small", 0x8c6842, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_DRIED_COFFEE = SHAPED_ITEM.addOreDictItem(9, "dried_coffee_large", 0x8c6842, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_WET_COFFEE = SHAPED_ITEM.addOreDictItem(10, "wet_coffee_small", 0x756452, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_WET_COFFEE = SHAPED_ITEM.addOreDictItem(11, "wet_coffee_large", 0x756452, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_BASIC_COFFEE = SHAPED_ITEM.addOreDictItem(12, "basic_coffee_small", 0x3b220d, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_BASIC_COFFEE = SHAPED_ITEM.addOreDictItem(13, "basic_coffee_large", 0x3b220d, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem UNSORTED_BASIC_COFFEE = SHAPED_ITEM.addOreDictItem(14, "basic_coffee_unsorted", 0x422003, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem COCOA_HULL = SHAPED_ITEM.addOreDictItem(15, "cocoa_hull", 0x362c25, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem COCOA_NIB = SHAPED_ITEM.addOreDictItem(16, "cocoa_nib", 0x635943, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR = SHAPED_ITEM.addOreDictItem(17, "chocolate_liquor", 0x635943, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_REFINED = SHAPED_ITEM.addOreDictItem(18, "chocolate_liquor_refined", 0x605528, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_DUTCHED = SHAPED_ITEM.addOreDictItem(19, "chocolate_liquor_dutched", 0x695143, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE_DUTCHED = SHAPED_ITEM.addOreDictItem(20, "press_cake_dutched", 0x997e6e, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE = SHAPED_ITEM.addOreDictItem(21, "press_cake", 0x948881, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE_GRADED = SHAPED_ITEM.addOreDictItem(22, "press_cake_graded", 0xa67f68, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem HOT_MILK_CHOCOLATE = SHAPED_ITEM.addOreDictItem(23, "milk_chocolate", 0x636c61, MaterialIconSet.DULL, OrePrefix.ingotHot);
    public static final GTFOOredictItem.OreDictValueItem MATTER_MARSHMALLOW = SHAPED_ITEM.addOreDictItem(24, "matter_marshmallow", 0xe6e0dc, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem MATTER_GRAHAM = SHAPED_ITEM.addOreDictItem(25, "matter_graham", 0xf0c25d, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem MATTER_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(26, "matter_graham_hot", 0xffe1a1, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHUNK_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(27, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem WAFER_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(28, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem CRACKER_GRAHAM_UNGRADED = SHAPED_ITEM.addOreDictItem(29, "cracker_graham_ungraded", 0xf0c25d, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_PRESSED = SHAPED_ITEM.addOreDictItem(30, "chocolate_liquor_pressed", 0xa6795a, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_DUTCHED_PRESSED = SHAPED_ITEM.addOreDictItem(31, "chocolate_liquor_dutched_pressed", 0xab7550, GTFOValues.Organic, OrePrefix.crushed);
    public static final MetaOreDictItem.OreDictValueItem HotAppleHardCandy = SHAPED_ITEM.addFoodOreDictItem(32, "hot_apple_hard_candy", 0x78e32b, MaterialIconSet.DULL, OrePrefix.plate);
    /*
        public static final GTFOOredictItem.OreDictItem BacillusSubtilis = new GTFOOredictItem.OreDictItem(33, "bacillus_subtilis", 0xe0386b, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");
        public static final GTFOOredictItem.OreDictItem LactobacillusPentosis = new GTFOOredictItem.OreDictItem(34, "lactobacillus_pentosis", 0x87316f, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");
        public static final GTFOOredictItem.OreDictItem FructoseConversionPlate = new GTFOOredictItem.OreDictItem(35, "fructose_conversion_plate", 0xe0e0dc, MaterialIconSet.SHINY, OrePrefix.plate);
        public static final GTFOOredictItem.OreDictItem XyloseIsomerase = new GTFOOredictItem.OreDictItem(36, "xylose_isomerase", 0x9718ea, MaterialIconSet.SAND, OrePrefix.dust);
        public static final GTFOOredictItem.OreDictItem AlphaAmylase = new GTFOOredictItem.OreDictItem(37, "alpha_amylase", 0x69D992, MaterialIconSet.SAND, OrePrefix.dust);
        public static final GTFOOredictItem.OreDictItem GammaAmylase = new GTFOOredictItem.OreDictItem(38, "gamma_amylase", 0x4FCE67, MaterialIconSet.SAND, OrePrefix.dust);
        public static final GTFOOredictItem.OreDictItem CornStarch = new GTFOOredictItem.OreDictItem(39, "corn_starch", 0xfff5f5, MaterialIconSet.ROUGH, OrePrefix.dust);
    */
    public static final GTFOOredictItem.OreDictValueItem CrushedHardCandy = SHAPED_ITEM.addFoodOreDictItem(40, "crushed_hard_candy", 0x52a302, MaterialIconSet.DULL, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CrushedPoppy = SHAPED_ITEM.addOreDictItem(41, "crushed_poppy", 0x940801, MaterialIconSet.ROUGH, OrePrefix.dust, "You monster.");
    public static final GTFOOredictItem.OreDictValueItem HardCandyPlate = SHAPED_ITEM.addFoodOreDictItem(42, "hard_candy", 0x78e32b, MaterialIconSet.ROUGH, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem HardCandyResin = SHAPED_ITEM.addFoodOreDictItem(43, "hard_candy", 0x78e32b, MaterialIconSet.ROUGH, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem Zest = SHAPED_ITEM.addFoodOreDictItem(44, "zest", 0xd8ff4a, MaterialIconSet.SAND, dust);
    public static final GTFOOredictItem.OreDictValueItem PotatoStarch = SHAPED_ITEM.addFoodOreDictItem(45, "potato_starch", 0xdedcb1, MaterialIconSet.ROUGH, dust);
    public static final GTFOOredictItem.OreDictValueItem LargeMozzarellaCurd = SHAPED_ITEM.addOreDictItem(46, "large_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem SmallMozzarellaCurd = SHAPED_ITEM.addOreDictItem(47, "small_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem DriedMozzarellaCurd = SHAPED_ITEM.addOreDictItem(48, "dried_mozzarella_curd", 0xf5f4e4, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem SolidifiedMozzarellaCurd = SHAPED_ITEM.addOreDictItem(49, "solidified_mozzarella_curd", 0xedebca, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem CoagulatedMilkCurd = SHAPED_ITEM.addOreDictItem(50, "coagulated_milk_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem CutCurd = SHAPED_ITEM.addOreDictItem(51, "cut_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem CookedCurd = SHAPED_ITEM.addOreDictItem(52, "cooked_curd", 0xffe8b3, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem SaltedCurd = SHAPED_ITEM.addOreDictItem(53, "salted_curd", 0xf7d68b, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem GorgonzolaCurd = SHAPED_ITEM.addOreDictItem(54, "gorgonzola_curd", 0xe5e5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem Fat = SHAPED_ITEM.addFoodOreDictItem(55, "fat", 0xFFF200, Organic, ingot, "C57H110O6"); // yea Fat is much more complicated, but I just stick to this formula...
    public static final GTFOOredictItem.OreDictValueItem MeatIngot = SHAPED_ITEM.addFoodOreDictItem(56, "cooked_meat", 0xa63028, MaterialIconSet.ROUGH, ingot);
    public static final GTFOOredictItem.OreDictValueItem CupricHydrogenArsenite = SHAPED_ITEM.addOreDictItem(57, "cupric_hydrogen_arsenite", 0x0fff00, MaterialIconSet.SHINY, OrePrefix.dust, "CuHAsO3");
    public static final GTFOOredictItem.OreDictValueItem LaminatedDough = SHAPED_ITEM.addFoodOreDictItem(58, "laminated_dough", 0xc6b4bb, MaterialIconSet.DULL, plate);
    public static final GTFOOredictItem.OreDictValueItem BareCornKernel = SHAPED_ITEM.addFoodOreDictItem(69, "corn_kernel_bare", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem CornKernel = SHAPED_ITEM.addFoodOreDictItem(60, "corn_kernel", 0xffea70, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem BoneAsh = SHAPED_ITEM.addOreDictItem(61, "bone_ash", 0xFEFDFE, MaterialIconSet.DULL, OrePrefix.dust);
    public static final MetaOreDictItem.OreDictValueItem UnfiredPorcelainTile = SHAPED_ITEM.addOreDictItem(62, "unfired_porcelain_tile", 0xC2C2C4, MaterialIconSet.ROUGH, plate);
    public static final MetaOreDictItem.OreDictValueItem BiscuitPorcelainTile = SHAPED_ITEM.addOreDictItem(63, "bisque_porcelain_tile", 0xFEFEE8, MaterialIconSet.ROUGH, plate);
    public static final MetaOreDictItem.OreDictValueItem GlazedPorcelainTile = SHAPED_ITEM.addOreDictItem(64, "glazed_porcelain_tile", 0xEEEEEE, MaterialIconSet.SHINY, plate);
    public static final MetaOreDictItem.OreDictValueItem BlackGlazedPorcelainTile = SHAPED_ITEM.addOreDictItem(65, "black_glazed_porcelain_tile", 0x111111, MaterialIconSet.SHINY, plate);

    public static void onMaterialsInit() {
        Materials.Iron.addFlags(GENERATE_FRAME);
        Materials.BismuthBronze.addFlags(GENERATE_FRAME);
        Materials.Aluminium.addFlags(GENERATE_DENSE);
        Materials.StainlessSteel.addFlags(GENERATE_DENSE, GENERATE_SPRING_SMALL);
        Materials.Titanium.addFlags(GENERATE_DENSE);
        Materials.Aluminium.addFlags(GENERATE_DENSE);

        Water.setProperty(FERTILIZER, new FertilizerProperty(5));
        Blood.setProperty(FERTILIZER, new FertilizerProperty(30));
        FertilizerSolution.setProperty(FERTILIZER, new FertilizerProperty(15));

        DistilledWater.setProperty(CLEANER, new CleanerProperty(2));
        SodiumStearate.setProperty(CLEANER, new CleanerProperty(16));

        ChorusJuice.setFormula("(C6H12O6)?", true);
    }

    public static Material.Builder fluidBuilder(int id, String name) {
        return new Material.Builder(id, gtfoId(name)).liquid();
    }

    public static Material.Builder fluidBuilderFood(int id, String name) {
        FOOD_RELATED_MATERIALS.put(id, true);
        return new Material.Builder(id, gtfoId(name)).liquid();
    }

    public static Material.Builder fluidBuilderFood(int id, String name, int temp) {
        FOOD_RELATED_MATERIALS.put(id, true);
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().temperature(temp));
    }

    public static Material.Builder fluidBuilderCustom(int id, String name) {
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().customStill());
    }

    public static Material.Builder gasBuilder(int id, String name, int temp) {
        return new Material.Builder(id, gtfoId(name)).gas(new FluidBuilder().temperature(temp));
    }

    public static Material.Builder fluidBuilder(int id, String name, FluidAttribute attribute) {
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().attribute(attribute));
    }

    public static Material.Builder fluidBuilder(int id, String name, int temp) {
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().temperature(temp));
    }

    public static void registerPropertyTooltips() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            FertilizerProperty fertilizerProperty = material.getProperty(FERTILIZER);
            if (fertilizerProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(LocalizationUtils.format("gregtechfoodoption.fluid.fertilizer", fertilizerProperty.getBoostPercentage())));
            LacingProperty lacingProperty = material.getProperty(LACING);
            if (lacingProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(LocalizationUtils.format("gregtechfoodoption.fluid.lacing")));
            CleanerProperty cleanerProperty = material.getProperty(CLEANER);
            if (cleanerProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(LocalizationUtils.format("gregtechfoodoption.fluid.cleaning", cleanerProperty.getCleaningPower())));
        }
    }

    public static ResourceLocation gtfoId(String path) {
        return new ResourceLocation(GregTechFoodOption.MODID, path);
    }

    public static boolean isFoodRelated(Material material) {
        return FOOD_RELATED_MATERIALS.containsKey(material.getId());
    }
}
