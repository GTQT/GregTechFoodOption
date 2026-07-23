package gregtechfoodoption.api.unification.materials;

import gregtech.api.GregTechAPI;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttribute;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.LocalizationUtils;
import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.api.unification.materials.materials.*;
import it.unimi.dsi.fastutil.ints.Int2BooleanArrayMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

/**
 * Hub class for GregTech Food Option material definitions.
 * Follows the GregTech CEu pattern: field declarations + register() orchestration.
 *
 * <p>Registration order (mirrors GregTech's Materials.java):
 * <ol>
 *   <li>{@link GTFOFirstDegreeMaterials#register()} (ID 0-199) — Simple compounds</li>
 *   <li>{@link GTFOOrganicChemistryMaterials#register()} (ID 200-499) — Organic chemistry</li>
 *   <li>{@link GTFOUnknownCompositionMaterials#register()} (ID 500-699) — Unknown composition</li>
 *   <li>{@link GTFOFoodMaterials#register()} (ID 700-1299) — Food / drink fluids</li>
 *   <li>{@link GTFOMaterialFlagAddition#register()} — Post-processing flags & properties</li>
 * </ol>
 */
public class GTFOMaterials {

    // ==================== Custom Property Keys ====================

    public static final PropertyKey<FertilizerProperty> FERTILIZER = new PropertyKey<>("gtfo_fertilizer", FertilizerProperty.class);
    public static final PropertyKey<LacingProperty> LACING = new PropertyKey<>("gtfo_lacing", LacingProperty.class);
    public static final PropertyKey<CleanerProperty> CLEANER = new PropertyKey<>("gtfo_cleaner", CleanerProperty.class);

    // ==================== First Degree Materials (ID 0-199) ====================

    public static Material SodiumSulfate;
    public static Material PerchloricAcid;
    public static Material ChloroauricAcid;
    public static Material HydrogenCyanide;
    public static Material SodiumArseniteSolution;
    public static Material BlueVitriol;
    public static Material BakingSodaSolution;
    public static Material SodiumPerchlorate;
    public static Material SodiumChlorate;
    public static Material SodiumCyanide;
    public static Material LithiumOxide;
    public static Material LithiumCarbonate;
    public static Material AmmoniumPerchlorate;
    public static Material PotassiumPerchlorate;
    public static Material SodiumCarbonateSolution;
    public static Material MoistAir;
    public static Material ColdMoistAir;
    public static Material EnderPearlSolution;

    // ==================== Organic Chemistry Materials (ID 200-499) ====================

    public static Material IsopropylChloride;
    public static Material Stearin;
    public static Material SodiumStearate;
    public static Material CitricAcid;
    public static Material Guaiacol;
    public static Material Acetaldehyde;
    public static Material Glyoxal;
    public static Material GlyoxylicAcid;
    public static Material Aniline;
    public static Material AceticAnhydride;
    public static Material Nitrophenols;
    public static Material XPhenothiazineIiPropylChloride;
    public static Material CoughSyrup;
    public static Material Promethazine;
    public static Material Codeine;
    public static Material Phenothiazine;
    public static Material Diphenylamine;
    public static Material VanillylmandelicAcid;
    public static Material VanilglycolicAcid;
    public static Material Vanillin;
    public static Material Aminophenol;
    public static Material IVNitrophenol;
    public static Material IINitrophenol;
    public static Material Paracetamol;
    public static Material EnderSugarSolution;

    // ==================== Unknown Composition Materials (ID 500-699) ====================

    public static Material CookedMinceMeat;
    public static Material ShreddedParmesan;
    public static Material BlackPepper;
    public static Material Nutmeg;
    public static Material GratedHorseradishRoot;
    public static Material BoneChinaClay;
    public static Material PenicilliumRoqueforti;
    public static Material BurntBananaPeel;
    public static Material MashedPotato;
    public static Material ToughMeat;
    public static Material KubideMeat;
    public static Material BargMeat;

    // ==================== Food & Drink Materials (ID 700-1299) ====================

    // Extracts
    public static Material LemonExtract;
    public static Material LimeExtract;
    public static Material OrangeExtract;
    public static Material AppleExtract;
    public static Material MelonExtract;
    public static Material ApricotExtract;
    public static Material GrapeExtract;
    public static Material CranberryExtract;
    public static Material VibrantExtract;
    public static Material SoyLecithin;

    // Oils
    public static Material RawSoybeanOil;
    public static Material HydratedSoybeanOil;
    public static Material SoybeanOil;
    public static Material OliveOil;
    public static Material FryingOil;
    public static Material HotFryingOil;

    // Dairy
    public static Material ItalianBuffaloMilk;
    public static Material CrudeRennetSolution;
    public static Material Whey;
    public static Material ActivatedBuffaloMilk;
    public static Material WheySaltWaterMix;
    public static Material HeatedRicottaStarter;
    public static Material AcidicMilkSolution;
    public static Material CoagulatingRicottaSolution;
    public static Material SkimmedMilk;
    public static Material PasteurizedMilk;
    public static Material MilkColloid;
    public static Material Cream;
    public static Material Butter;
    public static Material SourCream;
    public static Material UnpasteurizedSkimmedMilk;
    public static Material ParmigianoReggianoStarter;
    public static Material CurdlingParmigianoReggiano;
    public static Material LacticAcidBacteria;
    public static Material FungalRennetSolution;
    public static Material Albumen;
    public static Material Yolk;
    public static Material Egg;

    // Sauces & Condiments
    public static Material TomatoSauce;
    public static Material SoySauce;
    public static Material Vinegar;
    public static Material LightSoySauce;
    public static Material OysterSauce;
    public static Material Agrodolce;
    public static Material Pesto;
    public static Material BechamelSauce;
    public static Material CarbonaraSauce;
    public static Material BologneseSauce;
    public static Material TomatoBologneseSauce;
    public static Material ChickenBroth;
    public static Material VitelloTonnatoSauce;
    public static Material VitelloTonnatoFlavorant;
    public static Material RafanataMixture;
    public static Material BeerBatter;

    // Soy Sauce Process
    public static Material Koji;
    public static Material Mash;
    public static Material CrudeSoySauce;
    public static Material SoySauceResidue;
    public static Material SoyMilk;

    // Beverages
    public static Material AppleCider;
    public static Material PurpleDrink;
    public static Material Leninade;
    public static Material WhiteWine;
    public static Material RedWine;
    public static Material Vodka;
    public static Material Coffee;
    public static Material EnergizedCoffee;
    public static Material CarbonatedWater;
    public static Material PoorQualityBeer;
    public static Material WheatyJuice;
    public static Material Nilk;
    public static Material Etirps;
    public static Material EtirpsCranberry;
    public static Material LemonLimeSodaSyrup;
    public static Material LemonLimeSolution;
    public static Material LemonLimeSludge;
    public static Material CranberrySludge;
    public static Material CranberrySodaSyrup;
    public static Material HeatedWater;
    public static Material PotatoJuice;

    // Wine Process
    public static Material MaceratedWhiteGrapes;
    public static Material PressedWhiteWort;
    public static Material ClarifiedWhiteWort;
    public static Material RedGrapesMust;
    public static Material FermentedRedGrapesMust;
    public static Material AlcoholicRedGrapeJuice;

    // Syrups & Sweets
    public static Material UnheatedCaneSyrup;
    public static Material CaneSyrup;
    public static Material MoltenUnsweetenedChocolate;
    public static Material CocoaButter;
    public static Material MoltenDarkChocolate;
    public static Material MoltenMilkChocolate;
    public static Material AppleSyrup;
    public static Material AppleCandySyrup;
    public static Material GelatinSolution;
    public static Material IceCreamMixture;
    public static Material LingonberryJam;
    public static Material ElderberryJam;

    // Soups & Meals
    public static Material MushroomSoup;
    public static Material BeetrootSoup;
    public static Material RabbitStew;
    public static Material Polenta;
    public static Material PastaEFagioliBase;
    public static Material MixedPastaEFagioli;

    // Special / Industrial Fluids
    public static Material Sludge;
    public static Material AlkalineExtract;
    public static Material Blood;
    public static Material FertilizerSolution;
    public static Material HighFructoseCornSyrupSolution;
    public static Material RubberSap;
    public static Material RainbowSap;
    public static Material ChorusJuice;
    public static Material FermentedChorusJuice;
    public static Material Antaf;
    public static Material SweetenedDilutedCaneSyrupMixture;
    public static Material MarshmallowSyrupMixture;
    public static Material MarshmallowFoam;
    public static Material StarchFilledWater;

    // ==================== Food-Related Tracking ====================

    private static final Int2BooleanMap FOOD_RELATED_MATERIALS = new Int2BooleanArrayMap();

    public static boolean isFoodRelated(Material material) {
        return FOOD_RELATED_MATERIALS.containsKey(material.getId());
    }

    // ==================== Registration ====================

    /**
     * Called during {@link gregtech.api.unification.material.event.MaterialEvent} to register
     * all GTFO materials in the proper order. This mirrors GregTech's Materials.register().
     */
    public static void register() {
        GTFOFirstDegreeMaterials.register();
        GTFOOrganicChemistryMaterials.register();
        GTFOUnknownCompositionMaterials.register();
        GTFOFoodMaterials.register();
        GTFOMaterialFlagAddition.register();
    }

    // ==================== Builder Helper Methods ====================

    public static Material.Builder fluidBuilder(int id, String name) {
        return new Material.Builder(id, gtfoId(name)).liquid();
    }

    public static Material.Builder fluidBuilder(int id, String name, FluidAttribute attribute) {
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().attribute(attribute));
    }

    public static Material.Builder fluidBuilder(int id, String name, int temp) {
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().temperature(temp));
    }

    public static Material.Builder fluidBuilderFood(int id, String name) {
        FOOD_RELATED_MATERIALS.put(id, true);
        return new Material.Builder(id, gtfoId(name)).liquid();
    }

    public static Material.Builder fluidBuilderFood(int id, String name, int temp) {
        FOOD_RELATED_MATERIALS.put(id, true);
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().temperature(temp));
    }

    public static Material.Builder fluidBuilderFoodCustom(int id, String name) {
        FOOD_RELATED_MATERIALS.put(id, true);
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().customStill());
    }

    public static Material.Builder fluidBuilderCustom(int id, String name) {
        return new Material.Builder(id, gtfoId(name)).liquid(new FluidBuilder().customStill());
    }

    public static Material.Builder gasBuilder(int id, String name, int temp) {
        return new Material.Builder(id, gtfoId(name)).gas(new FluidBuilder().temperature(temp));
    }

    // ==================== Tooltip Registration ====================

    /**
     * Called during FMLInitializationEvent to register fluid tooltips
     * for materials with custom properties (fertilizer, lacing, cleaner).
     */
    public static void registerPropertyTooltips() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            FertilizerProperty fertilizerProperty = material.getProperty(FERTILIZER);
            if (fertilizerProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(
                        LocalizationUtils.format("gregtechfoodoption.fluid.fertilizer", fertilizerProperty.getBoostPercentage())));
            LacingProperty lacingProperty = material.getProperty(LACING);
            if (lacingProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(
                        LocalizationUtils.format("gregtechfoodoption.fluid.lacing")));
            CleanerProperty cleanerProperty = material.getProperty(CLEANER);
            if (cleanerProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(
                        LocalizationUtils.format("gregtechfoodoption.fluid.cleaning", cleanerProperty.getCleaningPower())));
        }
    }

    // ==================== Utilities ====================

    public static ResourceLocation gtfoId(String path) {
        return new ResourceLocation(GregTechFoodOption.MODID, path);
    }
}
