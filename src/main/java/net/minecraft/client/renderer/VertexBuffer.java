package net.minecraft.client.renderer;

public class VertexBuffer extends WorldRenderer {

    public VertexBuffer(int bufferSize) {
        super(bufferSize);
    }

    /** pos */
    public net.minecraft.client.renderer.VertexBuffer func_181662_b(double x, double y, double z) {
        return (VertexBuffer) super.pos(x, y, z);
    }

    /** normal */
    public net.minecraft.client.renderer.VertexBuffer func_181663_c(float x, float y, float z) {
        return (VertexBuffer) super.normal(x, y, z);
    }

    /** color */
    public net.minecraft.client.renderer.VertexBuffer func_181666_a(float r, float g, float b, float a) {
        return (VertexBuffer) super.color(r, g, b, a);
    }

    /** color */
    public net.minecraft.client.renderer.VertexBuffer func_181669_b(int r, int g, int b, int a) {
        return (VertexBuffer) super.color(r, g, b, a);
    }

    /** lightmap */
    public net.minecraft.client.renderer.VertexBuffer func_181671_a(int u, int v) {
        return (VertexBuffer) super.lightmap(u, v);
    }

    public net.minecraft.client.renderer.VertexBuffer func_187314_a(int u, int v) {
        return (VertexBuffer) super.lightmap(u, v);
    }

    /** tex */
    public net.minecraft.client.renderer.VertexBuffer func_181673_a(double u, double v) {
        return (VertexBuffer) super.tex(u, v);
    }

    public net.minecraft.client.renderer.VertexBuffer func_187315_a(double u, double v) {
        return (VertexBuffer) super.tex(u, v);
    }

}
