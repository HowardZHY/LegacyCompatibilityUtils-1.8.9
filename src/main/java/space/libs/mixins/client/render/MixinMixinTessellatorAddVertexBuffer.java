package space.libs.mixins.client.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = Tessellator.class, priority = 110)
public abstract class MixinMixinTessellatorAddVertexBuffer {

    @Shadow
    private WorldRenderer worldRenderer;

    @Dynamic
    @Shadow(remap = false)
    public BufferBuilder field_178183_a;

    /** getBuffer */
    public VertexBuffer func_178180_c() {
        return this.field_178183_a;
    }
}
