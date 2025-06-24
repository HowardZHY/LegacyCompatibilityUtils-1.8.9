package space.libs.interfaces;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public interface IWorldRenderer {

    static IWorldRenderer get(WorldRenderer instance) {
        return (IWorldRenderer) instance;
    }

    @MappedName("setVertexFormat")
    void func_178967_a(VertexFormat format);

    @MappedName("finishDrawing")
    int func_178977_d();

    boolean hasDrawing();

    int getVertexFormatIndex();

    VertexFormatElement getVertexFormatElement();

    void setVertexFormatElement(VertexFormatElement vertexFormatElement);

    /**
     * @apiNote Do not use methods below.
     */
    void endVertex();

}
