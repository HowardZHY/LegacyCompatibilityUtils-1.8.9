package space.libs.mixins;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(BlockPos.class)
public abstract class MixinBlockPos extends Vec3i {

    @Shadow
    public abstract BlockPos crossProduct(Vec3i vec);

    public MixinBlockPos(int xIn, int yIn, int zIn) {
        super(xIn, yIn, zIn);
    }

    /** multiply */
    public BlockPos func_177966_a(int i) {
        return new BlockPos(this.getX() * i, this.getY() * i, this.getZ() * i);
    }

    /** crossProductBP */
    public BlockPos func_177983_c(Vec3i vec) {
        return this.crossProduct(vec);
    }

}
