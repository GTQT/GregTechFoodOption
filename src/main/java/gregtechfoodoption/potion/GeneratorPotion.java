package gregtechfoodoption.potion;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtechfoodoption.GTFOConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static gregtech.api.GTValues.V;

public class GeneratorPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - generator";
    public static GeneratorPotion INSTANCE = null;

    public GeneratorPotion() {
        super("generator", false, 0x51f5d1, 0);
        INSTANCE = this;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            World world = player.world;

            // 只在服务器端执行，避免客户端重复执行
            if (world.isRemote) {
                return;
            }

            // 获取玩家位置
            BlockPos playerPos = player.getPosition();
            int range = 5; // 5×5范围
            int energyToAdd = Math.toIntExact(V[amplifier]);

            // 遍历玩家周围5×5×5的区域
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    for (int z = -range; z <= range; z++) {
                        BlockPos checkPos = playerPos.add(x, y, z);

                        // 获取该位置的MetaTileEntity
                        MetaTileEntity mte = GTUtility.getMetaTileEntity(world, checkPos);
                        if (mte == null) {
                            continue;
                        }

                        // 获取能量容器能力
                        IEnergyContainer container = mte.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null);
                        if (container != null) {
                            // 检查机器是否能够接收能量
                            if (container.getEnergyCanBeInserted() > 0) {
                                // 给机器充能
                                long added = container.addEnergy(energyToAdd);

                                // 可选：添加粒子效果或声音反馈
                                if (added > 0 && world.rand.nextInt(20) == 0) {
                                    spawnChargingParticles(world, checkPos);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // 生成充能粒子效果
    private void spawnChargingParticles(World world, BlockPos pos) {
        if (world.isRemote) {
            for (int i = 0; i < 5; i++) {
                double x = pos.getX() + 0.5 + (world.rand.nextDouble() - 0.5);
                double y = pos.getY() + 1.0 + world.rand.nextDouble();
                double z = pos.getZ() + 0.5 + (world.rand.nextDouble() - 0.5);

                world.spawnParticle(EnumParticleTypes.REDSTONE,
                        x, y, z,
                        0.0, 0.5, 1.0); // 蓝色粒子
            }
        }
    }


    @Override
    protected boolean canRender() {
        return GTFOConfig.gtfoPotionConfig.creativity;
    }
}
