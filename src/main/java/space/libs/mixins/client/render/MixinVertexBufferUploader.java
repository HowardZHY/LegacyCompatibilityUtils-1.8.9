package space.libs.mixins.client.render;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(VertexBufferUploader.class)
public class MixinVertexBufferUploader {

    @Shadow
    public void draw(WorldRenderer renderer) {}

    public int func_178177_a(WorldRenderer renderer, int i) {
        this.draw(renderer);
        return i;
    }

    public void func_181679_a(VertexBuffer buffer) {
        this.draw(buffer.renderer);
    }

}
