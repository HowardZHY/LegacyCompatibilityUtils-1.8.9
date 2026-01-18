package net.minecraft.util.math;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;

public class RayTraceResult extends MovingObjectPosition {

    public RayTraceResult(Vec3d hitVecIn, EnumFacing facing, BlockPos blockPosIn) {
        super(hitVecIn, facing, blockPosIn);
    }

    public RayTraceResult(Vec3d p_i45552_1_, EnumFacing facing) {
        super(p_i45552_1_, facing);
    }

    public RayTraceResult(Entity entityIn) {
        super(entityIn);
    }

    public RayTraceResult(MovingObjectType typeOfHitIn, Vec3d hitVecIn, EnumFacing sideHitIn, BlockPos blockPosIn) {
        super(typeOfHitIn, hitVecIn, sideHitIn, blockPosIn);
    }

    public RayTraceResult(Entity entityHitIn, Vec3d hitVecIn) {
        super(entityHitIn, hitVecIn);
    }
}
