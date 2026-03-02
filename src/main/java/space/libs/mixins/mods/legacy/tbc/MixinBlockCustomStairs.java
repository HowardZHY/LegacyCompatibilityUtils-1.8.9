package space.libs.mixins.mods.legacy.tbc;

import com.superdextor.thinkbigcore.blocks.BlockCustomStairs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowSuperConstructor;

@Mixin(BlockCustomStairs.class)
public class MixinBlockCustomStairs extends BlockStairs {

    protected MixinBlockCustomStairs(IBlockState modelState) {
        super(modelState);
    }

    @ShadowSuperConstructor
    protected void BlockStairs(IBlockState modelState) {}

    @NewConstructor
    public void BlockCustomStairs(IBlockState modelState) {
        BlockStairs(modelState);
        this.setLightOpacity(0);
    }
}
