package space.libs.mixins.client.render;

import net.minecraft.client.shader.Shader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.client.IMathUtils;

import static space.libs.util.client.IMathUtils.*;

@SuppressWarnings("unused")
@Mixin(Shader.class)
public abstract class MixinShader implements IMathUtils {

    @Shadow
    public void setProjectionMatrix(org.lwjgl.util.vector.Matrix4f p_148045_1_) {}

    public void func_148045_a(javax.vecmath.Matrix4f matrix4f) {
        this.setProjectionMatrix(TransformMat4f(matrix4f));
    }
}
