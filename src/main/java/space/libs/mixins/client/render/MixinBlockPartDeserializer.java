package space.libs.mixins.client.render;

import com.google.gson.*;
import net.minecraft.client.renderer.block.model.BlockPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.client.IMathUtils;

import java.lang.reflect.Type;

import static space.libs.util.client.IMathUtils.*;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraft.client.renderer.block.model.BlockPart$Deserializer")
public abstract class MixinBlockPartDeserializer implements IMathUtils {

    @Shadow
    public abstract BlockPart deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException;

    @Shadow
    private org.lwjgl.util.vector.Vector3f parsePositionTo(JsonObject p_178247_1_) {
        throw new AbstractMethodError();
    }

    @Shadow
    private org.lwjgl.util.vector.Vector3f parsePositionFrom(JsonObject p_178249_1_) {
        throw new AbstractMethodError();
    }

    @Shadow
    private org.lwjgl.util.vector.Vector3f parsePosition(JsonObject json, String p_178251_2_) {
        throw new AbstractMethodError();
    }

    public BlockPart func_178254_a(JsonElement element, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return this.deserialize(element, type, jsonDeserializationContext);
    }

    public javax.vecmath.Vector3f func_178247_d(JsonObject jo) {
        return TransformVecMath3f(this.parsePositionTo(jo));
    }

    public javax.vecmath.Vector3f func_178249_e(JsonObject jo) {
        return TransformVecMath3f(this.parsePositionFrom(jo));
    }

    public javax.vecmath.Vector3f func_178251_a(JsonObject jo, String s) {
        return TransformVecMath3f(this.parsePosition(jo, s));
    }
}
