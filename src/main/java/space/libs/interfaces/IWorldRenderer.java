package space.libs.interfaces;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IWorldRenderer {

    static IWorldRenderer get(WorldRenderer instance) {
        return (IWorldRenderer) instance;
    }

    /** setVertexFormat */
    void func_178967_a(VertexFormat format);

    /** finishDrawing */
    int func_178977_d();

    int getVertexFormatIndex();

    VertexFormatElement getVertexFormatElement();

    void setVertexFormatElement(VertexFormatElement vertexFormatElement);

}
