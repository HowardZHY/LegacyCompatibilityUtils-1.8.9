package space.libs.mixins.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = Tessellator.class, priority = 110)
public abstract class MixinTessellatorVertexBuffer {

    @Shadow
    private WorldRenderer worldRenderer;

    /** getBuffer */
    public VertexBuffer func_178180_c() {
        return (VertexBuffer) this.worldRenderer;
    }
}
