package net.minecraft.client.renderer;

public class BufferBuilder extends VertexBuffer {

    public BufferBuilder(int bufferSize) {
        super(bufferSize);
    }

    /** pos */
    public BufferBuilder func_181662_b(double x, double y, double z) {
        return (BufferBuilder) super.pos(x, y, z);
    }

    /** normal */
    public BufferBuilder func_181663_c(float x, float y, float z) {
        return (BufferBuilder) super.normal(x, y, z);
    }

    /** color */
    public BufferBuilder func_181666_a(float r, float g, float b, float a) {
        return (BufferBuilder) super.color(r, g, b, a);
    }

    /** color */
    public BufferBuilder func_181669_b(int r, int g, int b, int a) {
        return (BufferBuilder) super.color(r, g, b, a);
    }

    /** lightmap */
    public BufferBuilder func_187314_a(int u, int v) {
        return (BufferBuilder) super.lightmap(u, v);
    }

    /** tex */
    public BufferBuilder func_187315_a(double u, double v) {
        return (BufferBuilder) super.tex(u, v);
    }
}
