package space.libs.mixins.client.render;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.client.renderer.block.model.ModelBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Type;

@Mixin(ModelBlock.Deserializer.class)
public abstract class MixinModelBlockDeserializer {

    @Shadow
    public abstract ModelBlock deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException;

    public ModelBlock func_178327_a(JsonElement json, Type type, JsonDeserializationContext context) {
        return this.deserialize(json, type, context);
    }
}
