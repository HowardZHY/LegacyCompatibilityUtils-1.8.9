package space.libs.mixins.client.render;

import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.client.renderer.chunk.RenderChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
@Mixin(RenderChunk.class)
public class MixinRenderChunk {

    @Final
    @Shadow
    private ReentrantLock lockCompileTask;

    @Shadow
    private ChunkCompileTaskGenerator compileTask;

    /** isCompileTaskPending */
    public boolean func_178583_l() {
        boolean flag;
        this.lockCompileTask.lock();
        try {
            flag = (this.compileTask == null || this.compileTask.getStatus() == ChunkCompileTaskGenerator.Status.PENDING);
        } finally {
            this.lockCompileTask.unlock();
        }
        return flag;
    }
}
