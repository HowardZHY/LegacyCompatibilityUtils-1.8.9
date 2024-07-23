package net.minecraft.client.renderer;

public class VertexBuffer extends WorldRenderer {

    public VertexBuffer(int bufferSize) {
        super(bufferSize);
    }

    /** pos */
    public net.minecraft.client.renderer.VertexBuffer func_181662_b(double x, double y, double z) {
        return (VertexBuffer) this.pos(x, y, z);
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

    /** tex */
    public net.minecraft.client.renderer.VertexBuffer func_187315_a(double u, double v){
        return (VertexBuffer) this.tex(u, v);
    }

}
