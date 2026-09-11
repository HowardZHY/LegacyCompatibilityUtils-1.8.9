package space.libs.mixins.world;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.PendingChunk;
import net.minecraft.world.chunk.storage.RegionFileCache;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.MappedName;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
@Mixin(AnvilChunkLoader.class)
public class MixinAnvilChunkLoader {

    @Shadow
    public @Final File chunkSaveLocation;

    @MappedName("syncLockObject")
    public Object field_75827_c = new Object();

    @MappedName("writeChunkNBTTags")
    public void func_75821_a(PendingChunk chunk) throws IOException {
        DataOutputStream dataoutputstream = RegionFileCache.getChunkOutputStream(this.chunkSaveLocation, chunk.field_76548_a.chunkXPos, chunk.field_76548_a.chunkZPos);
        CompressedStreamTools.write(chunk.field_76547_b, dataoutputstream);
        dataoutputstream.close();
    }
}
