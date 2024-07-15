package space.libs.mixins.client.render;

import com.google.common.base.Function;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(ModelRotation.class)
public abstract class MixinModelRotation implements Function<IModelPart, TRSRTransformation> {

    @Shadow
    public abstract org.lwjgl.util.vector.Matrix4f getMatrix4d();

    @Shadow(remap = false)
    public abstract javax.vecmath.Matrix4f getMatrix();

    public javax.vecmath.Matrix4d func_177525_a() {
        return TransformMatrix4fToMatrix4d(this.getMatrix4d());
    }

    @Public
    private static javax.vecmath.Matrix4d TransformMatrix4fToMatrix4d(org.lwjgl.util.vector.Matrix4f matrix4f) {

        javax.vecmath.Matrix4d matrix4d = new javax.vecmath.Matrix4d();

        matrix4d.m00 = matrix4f.m00;
        matrix4d.m01 = matrix4f.m01;
        matrix4d.m02 = matrix4f.m02;
        matrix4d.m03 = matrix4f.m03;

        matrix4d.m10 = matrix4f.m10;
        matrix4d.m11 = matrix4f.m11;
        matrix4d.m12 = matrix4f.m12;
        matrix4d.m13 = matrix4f.m13;

        matrix4d.m20 = matrix4f.m20;
        matrix4d.m21 = matrix4f.m21;
        matrix4d.m22 = matrix4f.m22;
        matrix4d.m23 = matrix4f.m23;

        matrix4d.m30 = matrix4f.m30;
        matrix4d.m31 = matrix4f.m31;
        matrix4d.m32 = matrix4f.m32;
        matrix4d.m33 = matrix4f.m33;

        return matrix4d;
    }

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return new net.minecraftforge.client.model.TRSRTransformation(getMatrix());
    }

}
