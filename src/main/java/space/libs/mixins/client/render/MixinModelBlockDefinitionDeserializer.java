package space.libs.mixins.client.render;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Type;

@Mixin(ModelBlockDefinition.Deserializer.class)
public abstract class MixinModelBlockDefinitionDeserializer {

    @Shadow
    public abstract ModelBlockDefinition deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException;

    public ModelBlockDefinition func_178336_a(JsonElement json, Type type, JsonDeserializationContext context) {
        return this.deserialize(json, type, context);
    }
}
