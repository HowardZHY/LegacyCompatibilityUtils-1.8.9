package net.minecraft.client.renderer;

public class VertexBuffer extends WorldRenderer {

    public VertexBuffer(int bufferSize) {
        super(bufferSize);
    }

    /** pos */
    public net.minecraft.client.renderer.VertexBuffer func_181662_b(double x, double y, double z) {
        return (VertexBuffer) this.pos(x, y, z);
    }

    /** normal */
    public net.minecraft.client.renderer.VertexBuffer func_181663_c(float p_181663_1_, float p_181663_2_, float p_181663_3_) {
        return (VertexBuffer) this.normal(p_181663_1_, p_181663_2_, p_181663_3_);
    }

    /** color */
    public net.minecraft.client.renderer.VertexBuffer func_181666_a(float r, float g, float b, float a) {
        return (VertexBuffer) this.color(r, g, b, a);
    }

    /** color */
    public net.minecraft.client.renderer.VertexBuffer func_181669_b(int r, int g, int b, int a) {
        return (VertexBuffer) this.color(r, g, b, a);
    }

    /** lightmap */
    public net.minecraft.client.renderer.VertexBuffer func_181671_a(int p_181671_1_, int p_181671_2_) {
        return (VertexBuffer) this.lightmap(p_181671_1_, p_181671_2_);
    }

    public net.minecraft.client.renderer.VertexBuffer func_187314_a(int u, int v) {
        return (VertexBuffer) this.lightmap(u, v);
    }

    /** tex */
    public net.minecraft.client.renderer.VertexBuffer func_181673_a(double u, double v) {
        return (VertexBuffer) this.tex(u, v);
    }

    public net.minecraft.client.renderer.VertexBuffer func_187315_a(double u, double v) {
        return (VertexBuffer) this.tex(u, v);
    }

}
