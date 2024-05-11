package space.libs.mixins.client.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(WorldVertexBufferUploader.class)
public class MixinWorldVertexBufferUploader {

    @Shadow
    public void draw(WorldRenderer p_181679_1_) {}

    public int func_178177_a(WorldRenderer renderer, int i) {
        draw(renderer);
        return i;
    }

}
