package space.libs.mixins;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.AnvilChunkLoader$PendingChunk;
import net.minecraft.world.chunk.storage.RegionFileCache;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
@Mixin(AnvilChunkLoader.class)
public class MixinAnvilChunkLoader {

    @Final
    @Shadow
    public File chunkSaveLocation;

    /** syncLockObject */
    public Object field_75827_c = new Object();

    /** writeChunkNBTTags */
    public void func_75821_a(AnvilChunkLoader$PendingChunk p_75821_1_) throws IOException {
        DataOutputStream dataoutputstream = RegionFileCache.getChunkOutputStream(this.chunkSaveLocation, p_75821_1_.field_76548_a.chunkXPos, p_75821_1_.field_76548_a.chunkZPos);
        CompressedStreamTools.write(p_75821_1_.field_76547_b, dataoutputstream);
        dataoutputstream.close();
    }
}
