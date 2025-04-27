package space.libs.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(BlockSourceImpl.class)
public abstract class MixinBlockSourceImpl {

    @Final
    @Shadow
    private World worldObj;

    @Final
    @Shadow
    private BlockPos pos;

    /** getBlock */
    public Block func_179316_e() {
        return worldObj.getBlockState(pos).getBlock();
    }

}
