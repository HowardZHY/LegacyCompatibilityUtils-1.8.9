package space.libs.mixins.client.render;

import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.core.CompatLibCore;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@SuppressWarnings("all")
@Mixin(value = VertexFormatElement.EnumUsage.class, remap = false)
public abstract class MixinVertexFormatElementEnumUsage {

    @Public
    private static Method PreDraw;

    @Public
    private static Method PostDraw;

    public void preDraw(VertexFormatElement element, int stride, ByteBuffer buffer) {
        try {
            PreDraw.invoke(null, (VertexFormatElement.EnumUsage) (Object) this, element, stride, buffer);
        } catch (Exception e) {
            CompatLibCore.LOGGER.error("Failed to invoke old preDraw: " + e);
        }
    }

    public void postDraw(VertexFormatElement element, int stride, ByteBuffer buffer) {
        try {
            PostDraw.invoke(null, (VertexFormatElement.EnumUsage) (Object) this, element, stride, buffer);
        } catch (Exception e) {
            CompatLibCore.LOGGER.error("Failed to invoke old postDraw: " + e);
        }
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinit(CallbackInfo ci) {
        try {
            Class<?> clazz = ForgeHooksClient.class;
            PreDraw = clazz.getDeclaredMethod("preDraw", VertexFormatElement.EnumUsage.class, VertexFormatElement.class, int.class, ByteBuffer.class);
            PostDraw = clazz.getDeclaredMethod("postDraw", VertexFormatElement.EnumUsage.class, VertexFormatElement.class, int.class, ByteBuffer.class);
        } catch (Exception e) {
            CompatLibCore.LOGGER.error("Failed to get old preDraw/postDraw from ForgeHooksClient: " + e);
        }
    }
}
