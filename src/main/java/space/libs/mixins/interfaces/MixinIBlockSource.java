package space.libs.mixins.interfaces;

import net.minecraft.block.Block;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.ILocation;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@Mixin(IBlockSource.class)
public interface MixinIBlockSource {

    @Shadow
    BlockPos getBlockPos();

    @MappedName("getBlock")
    default Block func_179316_e() {
        return ((ILocation) this).getWorld().getBlockState(this.getBlockPos()).getBlock();
    }

}
