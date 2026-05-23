package gregtechfoodoption.common.machines.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.value.sync.DoubleSyncValue;
import com.cleanroommc.modularui.widgets.ProgressWidget;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.cleanroommc.modularui.widgets.slot.SlotGroup;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.mui.GTGuiTheme;
import gregtech.api.mui.widget.RecipeProgressWidget;
import gregtech.api.pattern.BlockPatternTemplate;
import gregtech.api.pattern.SoftTemplate;
import gregtech.api.pattern.TemplatePool;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.pattern.casing.DeclarativePatternBuilder;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.common.block.GTFOBlockCasing;
import gregtechfoodoption.common.block.GTFOMetaBlocks;
import gregtechfoodoption.loader.recipe.GTFORecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.Bronze;
import static gregtech.api.unification.material.Materials.Iron;

public class MetaTileEntityBakingOven extends RecipeMapPrimitiveMultiblockController {

    private static final SoftTemplate TEMPLATE = TemplatePool.getInstance().register("gregtechfoodoption:large_baking_oven", () ->
            DeclarativePatternBuilder.start()
                    .aisle("XXX", "XXX")
                    .aisle("XFX", "X#X")
                    .aisle("XYX", "XXX")
                    .where('X', states(getCasingState()))
                    .where('F', getFrameState())
                    .where('#', air())
                    .where(' ', any())
                    .where('Y', selfPredicate(MetaTileEntityBakingOven.class))
                    .buildTemplate()
    );

    public MetaTileEntityBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.BAKING_OVEN_RECIPES);
    }

    protected static IBlockState getCasingState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.ADOBE_BRICKS);
    }

    protected static TraceabilityPredicate getFrameState() {
        return states(MetaBlocks.FRAMES.get(Iron).getBlock(Iron),
                MetaBlocks.FRAMES.get(Bronze).getBlock(Bronze));
    }

    @Override
    public int getActualLightValue() {
        return recipeMapWorkable.isActive() ? 15 : 0;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTFOClientHandler.ADOBE_BRICKS;
    }

    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GTFOClientHandler.BAKING_OVEN_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, this.getFrontFacing(), this.recipeMapWorkable.isActive(), this.recipeMapWorkable.isWorkingEnabled());
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        ItemStackHandler emptyHandler = new ItemStackHandler(0);
        this.itemInventory = new ItemHandlerProxy(emptyHandler, emptyHandler);
    }

    @Override
    protected @NotNull BlockPatternTemplate createStructureTemplate() {
        return TEMPLATE.get();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityBakingOven(metaTileEntityId);
    }

    @Override
    protected MultiblockUIFactory createUIFactory() {
        return new MultiblockUIFactory(this)
                .setSize(176, 166)
                .disableDisplay()
                .disableButtons()
                .addScreenChildren((parent, syncManager) -> {

                    SlotGroup importGroup = new SlotGroup("import", 1, true);

                    parent.child(IKey.lang(getMetaFullName()).asWidget().pos(5, 5))
                            .child(new ItemSlot()
                                    .background(GTGuiTextures.SLOT_PRIMITIVE)
                                    .slot(new ModularSlot(importItems, 0)
                                            .slotGroup(importGroup)
                                            .accessibility(true, true))
                                    .pos(22, 30))
                            .child(new ItemSlot()
                                    .background(GTGuiTextures.SLOT_PRIMITIVE)
                                    .slot(new ModularSlot(importItems, 1)
                                            .slotGroup(importGroup)
                                            .accessibility(true, true))
                                    .pos(40, 30))
                            .child(new RecipeProgressWidget()
                                    .recipeMap(this.recipeMapWorkable.recipeMap)
                                    .size(20, 15)
                                    .pos(61, 32)
                                    .value(new DoubleSyncValue(recipeMapWorkable::getProgressPercent))
                                    .texture(GTGuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR, -1)
                                    .direction(ProgressWidget.Direction.RIGHT))
                            .child(new ItemSlot()
                                    .background(GTGuiTextures.SLOT_PRIMITIVE)
                                    .slot(new ModularSlot(exportItems, 0)
                                            .accessibility(false, true))
                                    .pos(86, 30));
                });
    }

    @Override
    public GTGuiTheme getUITheme() {
        return GTGuiTheme.PRIMITIVE;
    }

    public boolean hasMaintenanceMechanics() {
        return false;
    }
}
