package space.libs.mixins.client.render;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.client.renderer.block.model.BlockPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.lang.reflect.Type;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraft.client.renderer.block.model.BlockPart$Deserializer")
public abstract class MixinBlockPartDeserializer {

    @Shadow
    public abstract BlockPart deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException;

    @Shadow
    private org.lwjgl.util.vector.Vector3f parsePositionTo(JsonObject p_178247_1_) {
        throw new AbstractMethodError();
    }

    @Shadow
    private org.lwjgl.util.vector.Vector3f parsePositionFrom(JsonObject p_178249_1_) {
        throw new AbstractMethodError();
    }

    @Shadow
    private org.lwjgl.util.vector.Vector3f parsePosition(JsonObject p_178251_1_, String p_178251_2_) {
        throw new AbstractMethodError();
    }

    public BlockPart func_178254_a(JsonElement element, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return this.deserialize(element, type, jsonDeserializationContext);
    }

    public javax.vecmath.Vector3f func_178247_d(JsonObject jo) {
        return TransformVec3f(this.parsePositionTo(jo));
    }

    public javax.vecmath.Vector3f func_178249_e(JsonObject jo) {
        return TransformVec3f(this.parsePositionFrom(jo));
    }

    public javax.vecmath.Vector3f func_178251_a(JsonObject jo, String s) {
        return TransformVec3f(this.parsePosition(jo, s));
    }

    @Public
    private static javax.vecmath.Vector3f TransformVec3f(org.lwjgl.util.vector.Vector3f vec) {
        return new javax.vecmath.Vector3f(vec.x, vec.y, vec.z);
    }
}
