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
    public abstract ModelBlock deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException;

    public ModelBlock func_178327_a(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
        return this.deserialize(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
    }
}
