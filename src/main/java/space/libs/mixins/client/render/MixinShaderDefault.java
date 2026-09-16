package space.libs.mixins.client.render;

import net.minecraft.client.shader.ShaderDefault;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.client.IMathUtils;

@SuppressWarnings("all")
@Mixin(ShaderDefault.class)
public abstract class MixinShaderDefault extends MixinShaderUniform implements IMathUtils {

    @Shadow
    public void set(org.lwjgl.util.vector.Matrix4f p_148088_1_) {}

    @Override
    public void func_148088_a(javax.vecmath.Matrix4f matrix4f) {
        this.set(TransformMat4f(matrix4f));
    }
}
