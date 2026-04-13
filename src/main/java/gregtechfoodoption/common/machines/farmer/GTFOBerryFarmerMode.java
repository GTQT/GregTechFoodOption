package gregtechfoodoption.common.machines.farmer;

import gregtechfoodoption.common.block.GTFOBerryBush;
import gregtechfoodoption.common.block.GTFOCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GTFOBerryFarmerMode implements FarmerMode {
    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return state.getBlock() instanceof GTFOBerryBush && ((GTFOBerryBush) state.getBlock()).isMaxAge(state);
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return false;
    }

    @Override
    public void harvest(IBlockState state, World world, BlockPos.MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        GTFOCrop crop = (GTFOCrop) state.getBlock();
        world.setBlockState(pos, state.withProperty(crop.getAgeProperty(), Integer.valueOf(crop.getMaxAge() - 1)), 3);
    }
}
