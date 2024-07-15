package space.libs.mixins.client.render;

import net.minecraft.client.shader.ShaderDefault;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ShaderDefault.class)
public class MixinShaderDefault {

    @Shadow
    public void set(org.lwjgl.util.vector.Matrix4f p_148088_1_) {}

    public void func_148088_a(javax.vecmath.Matrix4f matrix4f) {
        org.lwjgl.util.vector.Matrix4f lwjglMatrix4f = new org.lwjgl.util.vector.Matrix4f();
        lwjglMatrix4f.m00 = matrix4f.m00;
        lwjglMatrix4f.m01 = matrix4f.m01;
        lwjglMatrix4f.m02 = matrix4f.m02;
        lwjglMatrix4f.m03 = matrix4f.m03;
        lwjglMatrix4f.m10 = matrix4f.m10;
        lwjglMatrix4f.m11 = matrix4f.m11;
        lwjglMatrix4f.m12 = matrix4f.m12;
        lwjglMatrix4f.m13 = matrix4f.m13;
        lwjglMatrix4f.m20 = matrix4f.m20;
        lwjglMatrix4f.m21 = matrix4f.m21;
        lwjglMatrix4f.m22 = matrix4f.m22;
        lwjglMatrix4f.m23 = matrix4f.m23;
        lwjglMatrix4f.m30 = matrix4f.m30;
        lwjglMatrix4f.m31 = matrix4f.m31;
        lwjglMatrix4f.m32 = matrix4f.m32;
        lwjglMatrix4f.m33 = matrix4f.m33;
        this.set(lwjglMatrix4f);
    }
}
