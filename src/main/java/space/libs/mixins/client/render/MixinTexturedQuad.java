package space.libs.mixins.client.render;

import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(TexturedQuad.class)
public abstract class MixinTexturedQuad {

    @Shadow
    public void draw(WorldRenderer renderer, float scale) {}

    public void func_178765_a(VertexBuffer buffer, float scale) {
        this.draw(buffer, scale);
    }

}
