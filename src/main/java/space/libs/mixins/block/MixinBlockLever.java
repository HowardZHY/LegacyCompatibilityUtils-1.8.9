package space.libs.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@Mixin(BlockLever.class)
public abstract class MixinBlockLever extends Block {

    public MixinBlockLever(Material materialIn) {
        super(materialIn);
    }

    @Shadow
    public abstract boolean canPlaceBlockAt(World worldIn, BlockPos pos);

    @MappedName("checkForDrop")
    public boolean func_176356_e(World worldIn, BlockPos pos) {
        if (canPlaceBlockAt(worldIn, pos)) {
            return true;
        }
        dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
        worldIn.setBlockToAir(pos);
        return false;
    }

}
