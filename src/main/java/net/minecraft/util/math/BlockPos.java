package net.minecraft.util.math;

import net.minecraft.entity.Entity;

public class BlockPos extends net.minecraft.util.BlockPos {


    public BlockPos(int x, int y, int z) {
        super(x, y, z);
    }

    public BlockPos(double x, double y, double z) {
        super(x, y, z);
    }

    public BlockPos(Entity source) {
        super(source);
    }

    public BlockPos(Vec3d source) {
        super(source);
    }

    public BlockPos(Vec3i source) {
        super(source);
    }

}
