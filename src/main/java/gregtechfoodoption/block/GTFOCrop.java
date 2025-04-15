package gregtechfoodoption.block;

import gregtechfoodoption.GregTechFoodOption;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GTFOCrop extends BlockCrops {

    public static final PropertyInteger DEFAULT_AGE = PropertyInteger.create("age", 0, 5);
    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
    public static List<GTFOCrop> CROP_BLOCKS = new ArrayList<>();
    private final String name;
    protected ItemStack seed;
    protected ItemStack crop;

    protected GTFOCrop(String name) {
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), 0));
        this.setRegistryName(GregTechFoodOption.MODID, "crop_" + name);
        CROP_BLOCKS.add(this);
        this.name = name;
        this.setTranslationKey("gtfo_crop_" + name);
    }

    public static GTFOCrop create(String name) {
        return new GTFOCrop(name);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROPS_AABB;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    public int getMaxAge() {
        return 5;
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = this.getAge(state);
        Random rand = world instanceof World ? ((World) world).rand : new Random();

        if (age >= this.getMaxAge()) {
            if (!seed.isEmpty()) {
                ItemStack seedStack = seed.copy();
                if (rand.nextInt(9) == 0) seedStack.setCount(seedStack.getCount() + 1);
                drops.add(seedStack);
            }

            int cropCount = 0;

            for (int i = 0; i < 3 + fortune; ++i) {
                if (rand.nextInt(2 * this.getMaxAge()) <= age) {
                    cropCount++;
                }
            }

            if (cropCount > 0) {
                ItemStack crop = this.crop.copy();
                crop.setCount(cropCount);
                drops.add(crop);
            }
        }

    }

    public Item getSeed() {
        return seed.getItem();
    }

    public void setSeed(ItemStack seed) {
        this.seed = seed;
    }

    public Item getCrop() {
        return crop.getItem();
    }

    public void setCrop(ItemStack crop) {
        this.crop = crop;
    }

    public ItemStack getCropStack() {
        return crop;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return seed.getItemDamage();
    }

    // The One Probe needs this!
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return this.seed;
    }

    public ItemStack getSeedStack() {
        return this.seed;
    }

    @Override
    public PropertyInteger getAgeProperty() {
        return DEFAULT_AGE;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getAgeProperty());
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Random rand = world instanceof World ? world.rand : new Random();
        int age = this.getAge(state);

        if (this.isMaxAge(state)) {
            List<ItemStack> drops = new ArrayList<>();
            if (!seed.isEmpty() && rand.nextInt(9) == 0) {
                ItemStack seedStack = seed.copy();
                drops.add(seedStack);
            }

            int cropCount = 0;

            for (int i = 0; i < 3; ++i) {
                if (rand.nextInt(2 * this.getMaxAge()) <= age) {
                    cropCount++;
                }
            }

            if (cropCount > 0) {
                ItemStack crop = this.crop.copy();
                crop.setCount(cropCount);
                drops.add(crop);
            }
            for (ItemStack drop : drops) {
                if (!playerIn.addItemStackToInventory(drop)) {
                    playerIn.dropItem(drop, false);
                }
            }
            world.setBlockState(pos, state.withProperty(getAgeProperty(), 0), 3);

            return true;
        }
        return false;
    }
}
