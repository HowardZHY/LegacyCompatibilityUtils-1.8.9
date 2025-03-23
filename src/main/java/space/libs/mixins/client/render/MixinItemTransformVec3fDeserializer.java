package space.libs.mixins.client.render;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Type;

@SuppressWarnings("deprecation")
@Mixin(targets = "net.minecraft.client.renderer.block.model.ItemTransformVec3f$Deserializer")
public abstract class MixinItemTransformVec3fDeserializer {

    @Shadow
    public abstract ItemTransformVec3f deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException;

    @Shadow
    private org.lwjgl.util.vector.Vector3f parseVector3f(JsonObject jsonObject, String key, org.lwjgl.util.vector.Vector3f defaultValue) {
        throw new AbstractMethodError();
    }

    public ItemTransformVec3f func_178359_a(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
        return this.deserialize(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
    }

    public javax.vecmath.Vector3f func_178358_a(JsonObject jsonObject, String key, javax.vecmath.Vector3f defaultValue) {
        org.lwjgl.util.vector.Vector3f v = this.parseVector3f(jsonObject, key, new org.lwjgl.util.vector.Vector3f(defaultValue.x, defaultValue.y, defaultValue.z));
        return new javax.vecmath.Vector3f(v.x, v.y, v.z);
    }
}
