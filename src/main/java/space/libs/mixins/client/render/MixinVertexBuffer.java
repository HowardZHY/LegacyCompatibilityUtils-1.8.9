package space.libs.mixins.client.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowSuperConstructor;

import java.nio.ByteBuffer;

@SuppressWarnings("unused")
@Mixin(VertexBuffer.class)
public abstract class MixinVertexBuffer {

    @Shadow
    public void bufferData(ByteBuffer buffer) {}

    /** 1.8 bufferData */
    public void func_177360_a(ByteBuffer buffer, int i) {
        this.bufferData(buffer);
    }

    @ShadowSuperConstructor
    public void Object() {}

    @NewConstructor
    public void VertexBuffer(int bufferSize) {
        Object();
        this.renderer = new WorldRenderer(bufferSize);
    }

    /** 1.9+ */
    public WorldRenderer renderer;

    /** noColor*/
    public void func_78914_f() {
        renderer.noColor();
    }

    /** reset */
    public void func_178965_a() {
        renderer.reset();
    }

    /** finishDrawing */
    public void func_178977_d() {
        renderer.finishDrawing();
    }

    /** addVertexData */
    public void func_178981_a(int[] vertexData) {
        renderer.addVertexData(vertexData);
    }

    /** pos */
    public VertexBuffer func_181662_b(double x, double y, double z) {
        renderer.pos(x, y, z);
        return (VertexBuffer) (Object) this;

    }
    /** color */
    public VertexBuffer func_181666_a(float r, float g, float b, float a) {
        renderer.color(r, g, b, a);
        return (VertexBuffer) (Object) this;
    }

    /** nextVertexFormatIndex */
    public void func_181667_k() {
        renderer.nextVertexFormatIndex();
    }

    /** begin */
    public void func_181668_a(int mode, VertexFormat format) {
        renderer.begin(mode, format);
    }

    /** color */
    public VertexBuffer func_181669_b(int r, int g, int b, int a) {
        renderer.color(r, g, b, a);
        return (VertexBuffer) (Object) this;
    }

    /** endVertex */
    public void func_181675_d() {
        renderer.endVertex();
    }

    /** tex */
    public VertexBuffer func_187315_a(double u, double v){
        renderer.tex(u, v);
        return (VertexBuffer) (Object) this;
    }
}
