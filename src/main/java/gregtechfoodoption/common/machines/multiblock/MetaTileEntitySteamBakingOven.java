package gregtechfoodoption.common.machines.multiblock;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.SteamMultiWorkable;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.metatileentity.multiblock.RecipeMapSteamMultiblockController;
import gregtech.api.pattern.BlockPatternTemplate;
import gregtech.api.pattern.SoftTemplate;
import gregtech.api.pattern.TemplatePool;
import gregtech.api.pattern.casing.CasingDefinition;
import gregtech.api.pattern.casing.DeclarativePatternBuilder;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.logic.OCParams;
import gregtech.api.recipes.logic.OCResult;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.common.block.GTFOBlockCasing;
import gregtechfoodoption.common.block.GTFOMetaBlocks;
import gregtechfoodoption.loader.recipe.GTFORecipeMaps;
import gregtechfoodoption.loader.recipe.builder.ElectricBakingOvenRecipeBuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntitySteamBakingOven extends RecipeMapSteamMultiblockController {
    private static final SoftTemplate TEMPLATE = TemplatePool.getInstance().register("gregtechfoodoption:steam_baking_oven", () ->
            DeclarativePatternBuilder.start()
                    .aisle("XXXX", "XGGX", "XXXX")
                    .aisle("XXXX", "GFFG", "XFFX")
                    .aisle("XXXX", "GFFG", "XFFX")
                    .aisle("XXXX", "YGGX", "XXXX")
                    .casing('X', CasingDefinition.simple(getCasingState()))
                    .hatch(MultiblockAbility.STEAM, 1)
                    .hatch(MultiblockAbility.STEAM_IMPORT_ITEMS, 1, 4)
                    .hatch(MultiblockAbility.STEAM_EXPORT_ITEMS, 1, 4)
                    .where('F', states(getFrameState()))
                    .where('#', air())
                    .where(' ', any())
                    .where('Y', selfPredicate(MetaTileEntitySteamBakingOven.class))
                    .where('G', states(getCasingState())
                            .or(states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS))))
                    .buildTemplate()
    );

    public MetaTileEntitySteamBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES, 10, ParallelLogicType.MULTIPLY);
        this.recipeMapWorkable.setParallelLimit(1);
    }

    protected static IBlockState getCasingState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.REINFORCED_ADOBE_BRICKS);
    }

    protected static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Steel).getBlock(Steel);
    }

    @Override
    protected @NotNull BlockPatternTemplate createStructureTemplate() {
        return TEMPLATE.get();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTFOClientHandler.REINFORCED_ADOBE_BRICKS;
    }

    @Nonnull
    protected ICubeRenderer getFrontOverlay() {
        return GTFOClientHandler.MICROWAVE_OVERLAY;
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySteamBakingOven(metaTileEntityId);
    }

    @Override
    protected @NotNull TextureArea getLogo() {
        return GTFOGuiTextures.GTFO_LOGO_WORKING;
    }

    @Override
    protected @NotNull TextureArea getWarningLogo() {
        return GTFOGuiTextures.GTFO_LOGO_WARNING;
    }

    @Override
    protected @NotNull TextureArea getErrorLogo() {
        return GTFOGuiTextures.GTFO_LOGO_ERROR;
    }

    public static class SteamBakingOvenWorkable extends SteamMultiWorkable {

        protected int recipeSteamT;

        public SteamBakingOvenWorkable(RecipeMapSteamMultiblockController tileEntity, double conversionRate) {
            super(tileEntity, conversionRate, ParallelLogicType.MULTIPLY);
        }

        @Override
        protected void updateRecipeProgress() {
            if (this.canRecipeProgress && this.drawEnergy(recipeSteamT, true)) {
                this.drawEnergy(recipeSteamT, false);
                if (++this.progressTime > this.maxProgressTime) {
                    this.completeRecipe();
                }

                if (this.hasNotEnoughEnergy && this.getEnergyInputPerSecond() > 19L * (long) recipeSteamT) {
                    this.hasNotEnoughEnergy = false;
                }
            } else if (recipeSteamT > 0) {
                this.hasNotEnoughEnergy = true;
                if (this.progressTime >= 2) {
                    if (ConfigHolder.machines.recipeProgressLowEnergy) {
                        this.progressTime = 1;
                    } else {
                        this.progressTime = Math.max(1, this.progressTime - 2);
                    }
                }
            }
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            super.setupRecipe(recipe);
            recipeSteamT = previousRecipe == null ? 0 : this.previousRecipe.getProperty(ElectricBakingOvenRecipeBuilder.TemperatureProperty.getInstance(), 0) / 100;
        }

        @Override
        protected void performOverclocking(@NotNull Recipe recipe, @NotNull OCParams ocParams, @NotNull OCResult ocResult) {
            super.performOverclocking(recipe, ocParams, ocResult);
            ocResult.setDuration(ocResult.duration() * 4);
        }

        protected boolean drawEnergy(int recipeEUt, boolean simulate) {
            return recipeEUt == 0 || super.drawEnergy(recipeEUt, simulate);
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = super.serializeNBT();
            compound.setInteger("RecipeSteamT", recipeSteamT);
            return compound;
        }

        @Override
        public void deserializeNBT(@Nonnull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            recipeSteamT = compound.getInteger("RecipeSteamT");
        }

        @Override
        public void writeInitialSyncData(@Nonnull PacketBuffer buf) {
            super.writeInitialSyncData(buf);
            buf.writeInt(recipeSteamT);
        }

        @Override
        public void receiveInitialSyncData(@Nonnull PacketBuffer buf) {
            super.receiveInitialSyncData(buf);
            recipeSteamT = buf.readInt();
        }


        public long getMaxVoltage() {
            return GTValues.V[4];
        }

    }
}