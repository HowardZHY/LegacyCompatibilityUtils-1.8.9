package space.libs.mixins.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import net.minecraft.world.gen.ChunkProviderSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Type;

@Mixin(ChunkProviderSettings.Serializer.class)
public abstract class MixinChunkProviderSettingsSerializer {

    @Shadow
    public abstract ChunkProviderSettings.Factory deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException;

    @Shadow
    public abstract JsonElement serialize(ChunkProviderSettings.Factory p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_);

    public ChunkProviderSettings.Factory func_177861_a(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
        return this.deserialize(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
    }

    public JsonElement func_177862_a(ChunkProviderSettings.Factory p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
        return this.serialize(p_serialize_1_, p_serialize_2_, p_serialize_3_);
    }
}
