package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.client.IMathUtils;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(BlockPartRotation.class)
public class MixinBlockPartRotation implements IMathUtils {

    @ShadowConstructor
    public void BlockPartRotation(org.lwjgl.util.vector.Vector3f originIn, EnumFacing.Axis axisIn, float angleIn, boolean rescaleIn) {}

    @NewConstructor
    public void BlockPartRotation(javax.vecmath.Vector3f originIn, EnumFacing.Axis axisIn, float angleIn, boolean rescaleIn) {
        BlockPartRotation(TransformVec3f(originIn), axisIn, angleIn, rescaleIn);
    }
}
