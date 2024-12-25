package space.libs.mixins;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(World.class)
public abstract class MixinWorld {

    @Shadow
    public abstract Chunk getChunkFromBlockCoords(net.minecraft.util.BlockPos pos);

    @Shadow
    public abstract Chunk getChunkFromChunkCoords(int chunkX, int chunkZ);

    @Shadow
    public abstract IBlockState getBlockState(net.minecraft.util.BlockPos pos);

    @Shadow
    public abstract DifficultyInstance getDifficultyForLocation(net.minecraft.util.BlockPos pos);

    public Vec3Pool field_82741_K = new Vec3Pool(300, 2000);

    public Vec3Pool func_82732_R() {
        return this.field_82741_K;
    }

    public Chunk func_175726_f(net.minecraft.util.math.BlockPos pos) {
        return this.getChunkFromChunkCoords(pos.getX() >> 4, pos.getZ() >> 4);
    }

    public DifficultyInstance func_175649_E(net.minecraft.util.math.BlockPos pos) {
        return this.getDifficultyForLocation(pos);
    }

    public IBlockState func_180495_p(net.minecraft.util.math.BlockPos pos) {
        return this.getBlockState(pos);
    }
}
