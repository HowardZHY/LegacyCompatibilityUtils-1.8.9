package space.libs.mixins.client.render;

import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.spongepowered.asm.mixin.Mixin;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@SuppressWarnings("all")
@Mixin(VertexFormatElement.EnumUsage.class)
public abstract class MixinVertexFormatElementEnumUsage {

    public void preDraw(VertexFormatElement element, int stride, ByteBuffer buffer) {
        try {
            Class<?> clazz = Class.forName("net.minecraftforge.client.ForgeHooksClient");
            Method method = clazz.getDeclaredMethod("preDraw", VertexFormatElement.EnumUsage.class, VertexFormatElement.class, int.class, ByteBuffer.class);
            method.invoke(null, (VertexFormatElement.EnumUsage) (Object) this, element, stride, buffer);
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to get old preDraw from ForgeHooksClient.");
        }
    }

    public void postDraw(VertexFormatElement element, int stride, ByteBuffer buffer) {
        try {
            Class<?> clazz = Class.forName("net.minecraftforge.client.ForgeHooksClient");
            Method method = clazz.getDeclaredMethod("postDraw", VertexFormatElement.EnumUsage.class, VertexFormatElement.class, int.class, ByteBuffer.class);
            method.invoke(null, (VertexFormatElement.EnumUsage) (Object) this, element, stride, buffer);
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to get old postDraw from ForgeHooksClient.");
        }
    }

}
