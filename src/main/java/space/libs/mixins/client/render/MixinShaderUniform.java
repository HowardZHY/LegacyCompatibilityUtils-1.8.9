package space.libs.mixins.client.render;

import net.minecraft.client.shader.ShaderUniform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.client.IMathUtils;

import static space.libs.util.client.IMathUtils.*;

@SuppressWarnings("unused")
@Mixin(ShaderUniform.class)
public abstract class MixinShaderUniform implements IMathUtils {

    @Shadow
    public abstract void set(org.lwjgl.util.vector.Matrix4f p_148088_1_);

    public void func_148088_a(javax.vecmath.Matrix4f matrix4f) {
        this.set(TransformMat4f(matrix4f));
    }
}
