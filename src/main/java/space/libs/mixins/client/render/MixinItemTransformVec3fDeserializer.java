package space.libs.mixins.client.render;

import com.google.gson.JsonObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.client.IMathUtils;

import static space.libs.util.client.IMathUtils.*;

@Mixin(targets = "net.minecraft.client.renderer.block.model.ItemTransformVec3f$Deserializer")
public abstract class MixinItemTransformVec3fDeserializer implements IMathUtils {

    @Shadow
    private org.lwjgl.util.vector.Vector3f parseVector3f(JsonObject jsonObject, String key, org.lwjgl.util.vector.Vector3f defaultValue) {
        throw new AbstractMethodError();
    }

    public javax.vecmath.Vector3f func_178358_a(JsonObject jsonObject, String key, javax.vecmath.Vector3f defaultValue) {
        return TransformVecMath3f(this.parseVector3f(jsonObject, key, TransformVector3f(defaultValue)));
    }
}
