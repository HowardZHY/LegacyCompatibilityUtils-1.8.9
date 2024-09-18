package space.libs.mixins.client.render;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Type;

@Mixin(targets = "net.minecraft.client.renderer.block.model.BlockFaceUV$Deserializer")
public abstract class MixinBlockFaceUVDeserializer {

    @Shadow
    public abstract BlockFaceUV deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_);

    public BlockFaceUV func_178293_a(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
        return this.deserialize(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
    }
}
