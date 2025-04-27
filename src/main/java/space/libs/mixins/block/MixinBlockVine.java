package space.libs.mixins.block;

import net.minecraft.block.BlockVine;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(BlockVine.class)
public class MixinBlockVine {

    @Public
    private static int field_176272_Q = func_176270_b(EnumFacing.SOUTH);

    @Public
    private static int field_176276_R = func_176270_b(EnumFacing.NORTH);

    @Public
    private static int field_176275_S = func_176270_b(EnumFacing.EAST);

    @Public
    private static int field_176271_T = func_176270_b(EnumFacing.WEST);

    @Public
    private static int func_176270_b(EnumFacing face) {
        return 1 << face.getHorizontalIndex();
    }
}
