package net.minecraft.client.renderer;

import net.minecraft.client.renderer.vertex.VertexFormat;

public class VertexBuffer {

    public VertexBuffer(int bufferSize) {
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
    public net.minecraft.client.renderer.VertexBuffer func_181662_b(double x, double y, double z) {
        renderer.pos(x, y, z);
        return this;
    }

    /** color */
    public net.minecraft.client.renderer.VertexBuffer func_181666_a(float r, float g, float b, float a) {
        renderer.color(r, g, b, a);
        return this;
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
    public net.minecraft.client.renderer.VertexBuffer func_181669_b(int r, int g, int b, int a) {
        renderer.color(r, g, b, a);
        return this;
    }

    /** endVertex */
    public void func_181675_d() {
        renderer.endVertex();
    }

    /** tex */
    public net.minecraft.client.renderer.VertexBuffer func_187315_a(double u, double v){
        renderer.tex(u, v);
        return this;
    }

}
