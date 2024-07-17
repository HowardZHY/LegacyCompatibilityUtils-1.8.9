package space.libs.mixins.interfaces;

import net.minecraft.block.Block;
import net.minecraft.dispenser.IBlockSource;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(IBlockSource.class)
public interface MixinIBlockSource {

    /** getBlock */
    Block func_179316_e();

}
