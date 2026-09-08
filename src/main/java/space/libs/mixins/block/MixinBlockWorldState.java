package space.libs.mixins.block;

import net.minecraft.block.state.BlockWorldState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(BlockWorldState.class)
public class MixinBlockWorldState {

    @ShadowConstructor
    public void BlockWorldState(World worldIn, BlockPos posIn, boolean p_i46451_3_) {}

    @NewConstructor
    public void BlockWorldState(World worldIn, BlockPos posIn) {
        this.BlockWorldState(worldIn, posIn, false);
    }
}
