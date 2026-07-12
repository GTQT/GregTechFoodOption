package gregtechfoodoption.common.machines.multiblock.kitchen;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.FormedStructureView;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PieceTemplate;
import gregtech.api.pattern.StructureContributionKey;
import gregtech.api.pattern.StructureDependency;
import gregtech.api.pattern.StructureElementPreviewEntry;
import gregtech.api.pattern.StructureEvaluationContext;
import gregtech.api.pattern.StructureHintResult;
import gregtech.api.pattern.StructureIncrementalSupport;
import gregtech.api.pattern.StructureOperationRequest;
import gregtech.api.pattern.StructurePreviewResult;
import gregtech.api.pattern.StructureRuntime;
import gregtech.api.pattern.StructureRuntimeDetectionContext;
import gregtech.api.pattern.element.Elements;
import gregtech.api.pattern.element.IStructureElement;
import gregtech.api.pattern.element.ITypedStructureElement;
import gregtech.api.pattern.element.StructureDefinition;
import gregtech.api.pattern.element.impl.ChainElement;
import gregtech.api.pattern.element.impl.HatchElement;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.GTUtility;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.api.utils.GTFOValues;
import gregtechfoodoption.common.block.GTFOBlockCasing;
import gregtechfoodoption.common.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOGuiTextures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MetaTileEntityKitchen extends MultiblockWithDisplayBase {
    @Override
    public boolean usesMui2() {
        return false;
    }
    public static final int MIN_RADIUS = 2;
    private static final int MIN_BODY_LENGTH = MIN_RADIUS * 2;
    private static final int MAX_RADIUS = 8;
    private static final int MAX_BODY_LENGTH = 14;
    private static final String RUNTIME_PIECE = "runtime";
    private static final StructureContributionKey<KitchenDimensions, KitchenDimensions> KITCHEN_DIMENSIONS_KEY =
            StructureContributionKey.uniform("gregtechfoodoption:kitchen/dimensions");
    private static final StructureContributionKey<WorkableTieredMetaTileEntity, Set<WorkableTieredMetaTileEntity>>
            KITCHEN_MACHINES_KEY = StructureContributionKey.setUnion("gregtechfoodoption:kitchen/machines");
    private static final StructureDefinition<MetaTileEntityKitchen> STRUCTURE_DEFINITION =
            StructureDefinition.getOrBuild("gregtechfoodoption:kitchen", () ->
                    StructureDefinition.<MetaTileEntityKitchen>builder(
                                    RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .piece(RUNTIME_PIECE, "S")
                            .where('S', Elements.self(MetaTileEntityKitchen.class))
                            .end()
                            .globalAbilityLimit(MultiblockAbility.INPUT_ENERGY, 1, 3)
                            .globalAbilityLimit(MultiblockAbility.MAINTENANCE_HATCH,
                                    ConfigHolder.machines.enableMaintenance ? 1 : 0, 1)
                            .runtimeDetector(MetaTileEntityKitchen::detectRuntimeStructure)
                            .build());
    private final KitchenLogic kitchenLogic;
    protected IItemHandlerModifiable inputInventory;
    protected IItemHandlerModifiable outputInventory;
    protected IMultipleTankHandler inputFluidInventory;
    protected IMultipleTankHandler outputFluidInventory;
    protected IEnergyContainer energyContainer;
    protected IItemHandlerModifiable recipeHolder = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            kitchenLogic.reset();
        }
    };
    protected IItemHandlerModifiable allCircuits = new ItemStackHandler(32) {
        @NotNull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return IntCircuitIngredient.getIntegratedCircuit(slot);
        }
    };

    protected int orderSize = 64;

    private int sDist = 0;
    private int bDist = 0;

    public MetaTileEntityKitchen(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        kitchenLogic = new KitchenLogic(this);
        resetTileAbilities();
    }

    protected void initializeAbilities() {
        List<IItemHandlerModifiable> inputs = new ArrayList<>(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        inputs.add(allCircuits);
        this.inputInventory = new ItemHandlerList(inputs);
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.inputInventory = new ItemStackHandler(0);
        this.inputFluidInventory = new FluidTankList(true);
        this.outputInventory = new ItemStackHandler(0);
        this.outputFluidInventory = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    @Override
    protected void formStructure(@NotNull FormedStructureView formed) {
        super.formStructure(formed);
        KitchenDimensions dimensions = formed.getAggregate(KITCHEN_DIMENSIONS_KEY);
        if (dimensions == null) {
            invalidateStructure();
            return;
        }
        applyStructureDimensions(dimensions);
        initializeAbilities();
        Set<WorkableTieredMetaTileEntity> machines = formed.getAggregate(KITCHEN_MACHINES_KEY);
        if (machines != null && !machines.isEmpty()) {
            machines.forEach(kitchenLogic::giveMetaTileEntity);
            kitchenLogic.wasNotified = true;
        }
    }

    @Override
    protected void updateFormedValid() {

    }

    @Override
    public void clearMachineInventory(List<ItemStack> itemBuffer) {
        super.clearMachineInventory(itemBuffer);
        itemBuffer.add(recipeHolder.getStackInSlot(0));
    }

    public boolean drainEnergy(boolean simulate) {
        long energyToDrain = GTValues.VA[getEnergyTier()] / 2;
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-energyToDrain);
            return true;
        }
        return false;
    }

    public int getEnergyTier() {
        if (energyContainer == null) return GTValues.LV;
        return Math.max(GTValues.LV, GTUtility.getFloorTierByVoltage(energyContainer.getInputVoltage()));
    }

    @NotNull
    @Override
    protected StructureDefinition<?> createStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }

    private static boolean detectRuntimeStructure(
            @NotNull StructureRuntimeDetectionContext<MetaTileEntityKitchen> context) {
        MetaTileEntityKitchen controller = context.getController();
        KitchenDimensions dimensions = controller.scanStructureDimensions();
        if (dimensions == null) {
            return context.fail(context.getControllerPos(), "valid kitchen shell", "kitchen boundary not found");
        }

        context.emit(KITCHEN_DIMENSIONS_KEY, dimensions);
        KitchenRuntimeElements elements = controller.createRuntimeCellElements();
        int totalLength = dimensions.getTotalLength();
        for (int aisle = 0; aisle < totalLength; aisle++) {
            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < dimensions.getWidth(); column++) {
                    KitchenCellType cellType = classifyCell(column, row, aisle, dimensions);
                    BlockPos pos = context.localPos(
                            column - dimensions.radius,
                            row,
                            aisle - (totalLength - 1),
                            RelativeDirection.LEFT,
                            RelativeDirection.UP,
                            RelativeDirection.FRONT);
                    if (!context.match(pos, elements.get(cellType))) {
                        return context.fail(
                                pos,
                                cellType.expected,
                                String.valueOf(context.getWorld().getBlockState(pos)));
                    }
                }
            }
        }
        return true;
    }

    @NotNull
    private KitchenRuntimeElements createRuntimeCellElements() {
        List<IStructureElement<?>> baseAlternatives = new ArrayList<>();
        baseAlternatives.add(Elements.block(getCasingState()));
        if (ConfigHolder.machines.enableMaintenance) {
            baseAlternatives.add(new HatchElement(MultiblockAbility.MAINTENANCE_HATCH, 1, 1));
        }
        baseAlternatives.add(new HatchElement(MultiblockAbility.INPUT_ENERGY, 1, 3));
        baseAlternatives.add(new HatchElement(MultiblockAbility.IMPORT_ITEMS));
        baseAlternatives.add(new HatchElement(MultiblockAbility.EXPORT_ITEMS));
        baseAlternatives.add(new HatchElement(MultiblockAbility.IMPORT_FLUIDS));
        baseAlternatives.add(new HatchElement(MultiblockAbility.EXPORT_FLUIDS));

        return new KitchenRuntimeElements(
                Elements.self(MetaTileEntityKitchen.class),
                new ChainElement(baseAlternatives.toArray(new IStructureElement[0])),
                Elements.blocks(getFloorState(), getFloorState2()),
                new KitchenInnerElement(),
                Elements.any());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);

        for (int i = 1; i < 6; i++) {
            tooltip.add(I18n.format("gregtechfoodoption.machine.kitchen.tooltip." + i));
        }
    }


    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @Override
    public boolean allowsFlip() {
        return false;
    }

    @Nullable
    private KitchenDimensions scanStructureDimensions() {
        World world = getWorld();
        if (world == null) {
            return null;
        }
        EnumFacing front = getFrontFacing();
        EnumFacing back = front.getOpposite();
        EnumFacing left = front.rotateYCCW();
        EnumFacing right = left.getOpposite();

        BlockPos.MutableBlockPos lPos = new BlockPos.MutableBlockPos(getPos().offset(back));
        BlockPos.MutableBlockPos rPos = new BlockPos.MutableBlockPos(getPos().offset(back));
        BlockPos.MutableBlockPos bPos = new BlockPos.MutableBlockPos(getPos());

        int detectedRadius = 0;
        int detectedBodyLength = 0;
        for (int i = 0; i <= MAX_BODY_LENGTH; i++) {
            if (isBlockEdge(world, bPos, back)) {
                detectedBodyLength = i;
                break;
            }
        }
        for (int i = 1; i <= MAX_RADIUS; i++) {
            // Both mutable probes must advance even when the first side is not a boundary.
            if (isBlockEdge(world, lPos, left) & isBlockEdge(world, rPos, right)) {
                detectedRadius = i;
                break;
            }
        }
        if (detectedRadius < MIN_RADIUS || detectedBodyLength < MIN_BODY_LENGTH) {
            return null;
        }
        return new KitchenDimensions(detectedRadius, detectedBodyLength);
    }

    private void applyStructureDimensions(@NotNull KitchenDimensions dimensions) {
        this.sDist = dimensions.radius;
        this.bDist = dimensions.bodyLength;
        World world = getWorld();
        if (world == null || world.isRemote) {
            return;
        }
        writeCustomData(GregtechDataCodes.UPDATE_STRUCTURE_SIZE, buf -> {
            buf.writeInt(this.sDist);
            buf.writeInt(this.bDist);
        });
    }

    @NotNull
    private static KitchenCellType classifyCell(int column, int row, int aisle,
                                                @NotNull KitchenDimensions dimensions) {
        int totalLength = dimensions.getTotalLength();
        if (row == 0 && aisle == totalLength - 1 && column == dimensions.radius) {
            return KitchenCellType.CONTROLLER;
        }
        if (row == 0) {
            if (aisle == 0 || aisle == totalLength - 1 ||
                    column == 0 || column == dimensions.getWidth() - 1) {
                return KitchenCellType.BASE;
            }
            return KitchenCellType.FLOOR;
        }
        if (aisle > 0 && aisle < totalLength - 1 &&
                column > 0 && column < dimensions.getWidth() - 1) {
            return KitchenCellType.INTERIOR;
        }
        return KitchenCellType.ANY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return getMatchingShapes(null);
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes(@Nullable Map<String, Integer> channelValues) {
        KitchenDimensions dimensions = getToolingDimensions();
        StructureRuntime runtime = createToolingRuntime(dimensions);
        return Collections.singletonList(new MultiblockShapeInfo(runtime.previewSingle(
                StructureOperationRequest.preview(getToolingRepetitions(dimensions), channelValues))));
    }

    @Override
    @NotNull
    public Map<BlockPos, StructureElementPreviewEntry> buildStructurePreviewEntries(
            @Nullable Map<String, Integer> channelValues) {
        KitchenDimensions dimensions = getToolingDimensions();
        StructureRuntime runtime = createToolingRuntime(dimensions);
        StructurePreviewResult result = runtime.previewSingleResult(
                StructureOperationRequest.preview(getToolingRepetitions(dimensions), channelValues));
        gregtech.api.pattern.PieceRuntimeState.PreviewCells cells = result.getSinglePieceCells();
        if (cells == null || cells.getPreviewEntries().isEmpty()) {
            return Collections.emptyMap();
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        for (BlockPos pos : cells.getBlocks().keySet()) {
            minX = Math.min(minX, pos.getX());
            minY = Math.min(minY, pos.getY());
            minZ = Math.min(minZ, pos.getZ());
        }
        Map<BlockPos, StructureElementPreviewEntry> normalized = new java.util.HashMap<>();
        for (Map.Entry<BlockPos, StructureElementPreviewEntry> entry : cells.getPreviewEntries().entrySet()) {
            BlockPos pos = entry.getKey();
            normalized.put(new BlockPos(pos.getX() - minX, pos.getY() - minY, pos.getZ() - minZ), entry.getValue());
        }
        return normalized;
    }

    @Override
    public boolean autoBuildStructure(@NotNull StructureOperationRequest request) {
        request.requireBuildKind();
        createToolingRuntime(getToolingDimensions()).buildAllPieces(request);
        return true;
    }

    @Override
    public void spawnStructureHints(@NotNull StructureOperationRequest request) {
        hintStructure(request);
    }

    @Override
    @NotNull
    public StructureHintResult hintStructure(@NotNull StructureOperationRequest request) {
        request.requireKind(StructureOperationRequest.Kind.HINT);
        return createToolingRuntime(getToolingDimensions()).hintAllPieces(request);
    }

    @NotNull
    private StructureRuntime createToolingRuntime(@NotNull KitchenDimensions dimensions) {
        return createDynamicStructureRuntime(buildToolingDefinition(dimensions));
    }

    @NotNull
    private StructureDefinition<MetaTileEntityKitchen> buildToolingDefinition(
            @NotNull KitchenDimensions dimensions) {
        return StructureDefinition.<MetaTileEntityKitchen>builder(
                        RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.FRONT)
                .pieceFromTemplate(RUNTIME_PIECE, buildToolingTemplate(dimensions))
                .end()
                .globalAbilityLimit(MultiblockAbility.INPUT_ENERGY, 1, 3)
                .globalAbilityLimit(MultiblockAbility.MAINTENANCE_HATCH,
                        ConfigHolder.machines.enableMaintenance ? 1 : 0, 1)
                .build();
    }

    @NotNull
    private PieceTemplate buildToolingTemplate(@NotNull KitchenDimensions dimensions) {
        int totalLength = dimensions.getTotalLength();
        IStructureElement<?>[][][] template = new IStructureElement<?>[totalLength][2][dimensions.getWidth()];
        KitchenRuntimeElements elements = createRuntimeCellElements();
        for (int aisle = 0; aisle < totalLength; aisle++) {
            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < dimensions.getWidth(); column++) {
                    template[aisle][row][column] = elements.get(classifyCell(column, row, aisle, dimensions));
                }
            }
        }
        int[][] repetitions = new int[totalLength][2];
        for (int i = 0; i < totalLength; i++) {
            repetitions[i][0] = 1;
            repetitions[i][1] = 1;
        }
        return new PieceTemplate(
                template,
                new RelativeDirection[]{RelativeDirection.LEFT, RelativeDirection.UP, RelativeDirection.FRONT},
                repetitions,
                new String[totalLength],
                new int[]{dimensions.radius, 0, totalLength - 1, totalLength - 1, totalLength - 1},
                null);
    }

    @NotNull
    private KitchenDimensions getToolingDimensions() {
        return new KitchenDimensions(
                Math.max(MIN_RADIUS, Math.min(MAX_RADIUS, sDist)),
                Math.max(MIN_BODY_LENGTH, Math.min(MAX_BODY_LENGTH, bDist)));
    }

    @NotNull
    private static int[] getToolingRepetitions(@NotNull KitchenDimensions dimensions) {
        int[] repetitions = new int[dimensions.getTotalLength()];
        for (int i = 0; i < repetitions.length; i++) {
            repetitions[i] = 1;
        }
        return repetitions;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setInteger("sDist", this.sDist);
        data.setInteger("bDist", this.bDist);
        data.setInteger("orderSize", this.orderSize);
        writeRecipeItemToNBT(data);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.sDist = data.getInteger("sDist");
        this.bDist = data.getInteger("bDist");
        this.orderSize = data.getInteger("orderSize");
        if (data.hasKey("recipe")) {
            this.recipeHolder.setStackInSlot(0, new ItemStack(data.getCompoundTag("recipe")));
        }
        reinitializeStructurePattern();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.sDist);
        buf.writeInt(this.bDist);
        buf.writeInt(this.orderSize);
        buf.writeItemStack(this.recipeHolder.getStackInSlot(0));
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.sDist = buf.readInt();
        this.bDist = buf.readInt();
        this.orderSize = buf.readInt();
        try {
            this.recipeHolder.setStackInSlot(0, buf.readItemStack());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reinitializeStructurePattern();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityKitchen(metaTileEntityId);
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = super.createUITemplate(entityPlayer);
        builder.slot(recipeHolder, 0, 150, 100, GuiTextures.SLOT);

        return builder;
    }

    public boolean isBlockEdge(@Nonnull World world, @Nonnull BlockPos.MutableBlockPos pos, @Nonnull EnumFacing direction) {
        return world.getBlockState(pos.move(direction)) == getCasingState() || world.getTileEntity(pos) instanceof MetaTileEntityHolder;
    }

    @Nonnull
    protected IBlockState getCasingState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Nonnull
    protected IBlockState getFloorState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.PORCELAIN_TILE);
    }

    @Nonnull
    protected IBlockState getFloorState2() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.DARK_PORCELAIN_TILE);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.UPDATE_STRUCTURE_SIZE) {
            this.sDist = buf.readInt();
            this.bDist = buf.readInt();
        } else if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            scheduleRenderUpdate();
        } else if (dataId == GTFOValues.UPDATE_KITCHEN_ORDER) {
            this.orderSize = buf.readInt();
            kitchenLogic.recheckOutputs = true;
        }
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.BLAST_FURNACE_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), isActive(), kitchenLogic.isWorkingEnabled());
    }

    public boolean isActive() {
        return this.isStructureFormed() && kitchenLogic.isWorkingEnabled();
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(this.kitchenLogic.isWorkingEnabled(), this.isActive())
                .addWorkingStatusLine()
                .addEnergyUsageLine(this.energyContainer)
                .addEnergyTierLine(this.getEnergyTier())
                .addCustom((list) -> {
                    if (!this.isActive())
                        return;
                    ITextComponent comp = null;
                    switch (this.kitchenLogic.state) {
                        case PROBABLY_FINE ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.probably_fine");
                        case ORDER_COMPLETE ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.order_complete");
                    }
                    if (comp != null) {
                        list.add(comp.setStyle((new Style()).setColor(TextFormatting.AQUA)));
                    }
                })
                .addCustom((list) -> {
                    if (!this.isActive())
                        return;
                    list.add(new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.order_size", this.orderSize).setStyle((new Style()).setColor(TextFormatting.GOLD)));
                })
                .addCustom((list) -> {
                    TextFormatting color = TextFormatting.GRAY;
                    if (kitchenLogic.dirtiness > 100) {
                        color = TextFormatting.RED;
                    } else if (kitchenLogic.dirtiness > 50) {
                        color = TextFormatting.YELLOW;
                    } else if (kitchenLogic.dirtiness > 10) {
                        color = TextFormatting.WHITE;
                    }

                    list.add(new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.dirtiness", this.kitchenLogic.dirtiness).setStyle(
                            (new Style()).setColor(color)));
                });
    }

    @Override
    protected void addErrorText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, this.isStructureFormed(), true)
                .addCustom((list) -> {
                    if (!this.isStructureFormed() || !this.kitchenLogic.isWorkingEnabled())
                        return;
                    ITextComponent comp = null;
                    switch (this.kitchenLogic.state) {
                        case NO_RECIPE ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.no_recipe");
                        case BAD_MACHINES ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.bad_machines");
                        case MACHINES_NOT_WORKING ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.machines_not_working");
                        case NO_INGREDIENTS ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.no_ingredients");
                    }
                    if (comp != null) {
                        list.add(comp.setStyle((new Style()).setColor(TextFormatting.AQUA)));
                    }
                });
    }

    protected void addWarningText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, this.isStructureFormed(), false)
                .addMaintenanceProblemLines(this.getMaintenanceProblems())
                .addLowPowerLine(!drainEnergy(true))
                .addCustom((list) -> {
                    if (!this.isStructureFormed() || !this.kitchenLogic.isWorkingEnabled())
                        return;
                    ITextComponent comp = null;
                    switch (this.kitchenLogic.state) {
                        case BUSES_FULL ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.buses_full");
                        case HATCHES_FULL ->
                                comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.hatches_full");
                    }
                    if (comp != null) {
                        list.add(comp.setStyle((new Style()).setColor(TextFormatting.AQUA)));
                    }
                    if (this.kitchenLogic.dirtiness > 100) {
                        list.add(new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.very_dirty").setStyle(new Style().setColor(TextFormatting.RED)));
                    }
                });
    }


    @Override
    protected @NotNull Widget getFlexButton(int x, int y, int width, int height) {
        WidgetGroup group = new WidgetGroup(x, y, width, height);
        group.addWidget((new ClickButtonWidget(0, 0, 9, 18, "", this::decrementOrderSize)).setTooltipText("gregtechfoodoption.multiblock.kitchen.decrement_order").setButtonTexture(GuiTextures.BUTTON_THROTTLE_MINUS));
        group.addWidget((new ClickButtonWidget(9, 0, 9, 18, "", this::incrementOrderSize)).setTooltipText("gregtechfoodoption.multiblock.kitchen.increment_order").setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS));
        return group;
    }

    private void incrementOrderSize(Widget.ClickData clickData) {
        this.orderSize++;
        this.orderSize = Math.min(this.orderSize, 64);
        this.writeCustomData(GTFOValues.UPDATE_KITCHEN_ORDER, buf -> buf.writeInt(this.orderSize));
        this.markDirty();
    }


    private void decrementOrderSize(Widget.ClickData clickData) {
        this.orderSize--;
        this.orderSize = Math.max(this.orderSize, 1);
        this.writeCustomData(GTFOValues.UPDATE_KITCHEN_ORDER, buf -> buf.writeInt(this.orderSize));
        this.markDirty();
    }

    public IItemHandlerModifiable getInputInventory() {
        return inputInventory;
    }

    public IItemHandlerModifiable getOutputInventory() {
        return outputInventory;
    }

    public IMultipleTankHandler getInputFluidInventory() {
        return inputFluidInventory;
    }

    public IMultipleTankHandler getOutputFluidInventory() {
        return outputFluidInventory;
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

    public NBTTagCompound getRecipeNBT() {
        if (!recipeHolder.getStackInSlot(0).isEmpty()) {
            return recipeHolder.getStackInSlot(0).getTagCompound();
        }
        return null;
    }

    public void writeRecipeItemToNBT(NBTTagCompound tag) {
        if (!recipeHolder.getStackInSlot(0).isEmpty()) {
            NBTTagCompound item = new NBTTagCompound();
            recipeHolder.getStackInSlot(0).writeToNBT(item);
            tag.setTag("recipe", item);
        }
    }

    public int getOrderSize() {
        return orderSize;
    }

    private enum KitchenCellType {

        CONTROLLER("kitchen controller"),
        BASE("steel pipe casing or permitted kitchen hatch"),
        FLOOR("porcelain tile"),
        INTERIOR("valid kitchen interior"),
        ANY("any block");

        private final String expected;

        KitchenCellType(@NotNull String expected) {
            this.expected = expected;
        }
    }

    private static final class KitchenDimensions {

        private final int radius;
        private final int bodyLength;

        private KitchenDimensions(int radius, int bodyLength) {
            this.radius = radius;
            this.bodyLength = bodyLength;
        }

        private int getWidth() {
            return radius * 2 + 1;
        }

        private int getTotalLength() {
            return bodyLength + 2;
        }
    }

    private static final class KitchenRuntimeElements {

        private final IStructureElement<?> controller;
        private final IStructureElement<?> base;
        private final IStructureElement<?> floor;
        private final IStructureElement<?> interior;
        private final IStructureElement<?> any;

        private KitchenRuntimeElements(@NotNull IStructureElement<?> controller,
                                       @NotNull IStructureElement<?> base,
                                       @NotNull IStructureElement<?> floor,
                                       @NotNull IStructureElement<?> interior,
                                       @NotNull IStructureElement<?> any) {
            this.controller = controller.compile();
            this.base = base.compile();
            this.floor = floor.compile();
            this.interior = interior.compile();
            this.any = any.compile();
        }

        @NotNull
        private IStructureElement<?> get(@NotNull KitchenCellType cellType) {
            switch (cellType) {
                case CONTROLLER:
                    return controller;
                case BASE:
                    return base;
                case FLOOR:
                    return floor;
                case INTERIOR:
                    return interior;
                case ANY:
                    return any;
                default:
                    throw new IllegalStateException("Unknown kitchen cell type: " + cellType);
            }
        }
    }

    private static final class KitchenInnerElement implements ITypedStructureElement<Object> {

        @Override
        public boolean check(@NotNull StructureEvaluationContext<Object> context) {
            TileEntity tileEntity = context.getTileEntity();
            if (!(tileEntity instanceof IGregTechTileEntity)) {
                return true;
            }

            MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
            if (metaTileEntity instanceof WorkableTieredMetaTileEntity &&
                    !(metaTileEntity instanceof SimpleGeneratorMetaTileEntity)) {
                context.getCollector().emit(
                        KITCHEN_MACHINES_KEY,
                        (WorkableTieredMetaTileEntity) metaTileEntity);
            }
            return true;
        }

        @Override
        public BlockInfo[] getCandidates() {
            return new BlockInfo[0];
        }

        @NotNull
        @Override
        public StructureIncrementalSupport getIncrementalSupport() {
            return StructureIncrementalSupport.TYPED_CONTRIBUTION;
        }

        @NotNull
        @Override
        public Set<StructureDependency> getDependencies() {
            return Collections.emptySet();
        }
    }
}
