package net.minecraft.util.math;

import net.minecraft.world.ChunkCoordIntPair;

public class ChunkPos extends ChunkCoordIntPair {

    public ChunkPos(int x, int z) {
        super(x, z);
    }

    public ChunkPos(BlockPos pos) {
        this(pos.getX(), pos.getZ());
    }
}
