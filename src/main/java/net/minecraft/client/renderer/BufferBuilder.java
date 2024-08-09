package net.minecraft.client.renderer;

public class BufferBuilder extends VertexBuffer {

    public BufferBuilder(int bufferSize) {
        super(bufferSize);
    }

    /** pos */
    public BufferBuilder func_181662_b(double x, double y, double z) {
        return (BufferBuilder) this.pos(x, y, z);
    }

    /** normal */
    public BufferBuilder func_181663_c(float p_181663_1_, float p_181663_2_, float p_181663_3_) {
        return (BufferBuilder) this.normal(p_181663_1_, p_181663_2_, p_181663_3_);
    }

    /** color */
    public BufferBuilder func_181666_a(float r, float g, float b, float a) {
        return (BufferBuilder) this.color(r, g, b, a);
    }

    /** color */
    public BufferBuilder func_181669_b(int r, int g, int b, int a) {
        return (BufferBuilder) this.color(r, g, b, a);
    }

    /** lightmap */
    public BufferBuilder func_187314_a(int u, int v) {
        return (BufferBuilder) this.lightmap(u, v);
    }

    /** tex */
    public BufferBuilder func_187315_a(double u, double v) {
        return (BufferBuilder) this.tex(u, v);
    }
}
