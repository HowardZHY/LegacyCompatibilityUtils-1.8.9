package space.libs.mixins.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Constructor;

@SuppressWarnings("unused")
@Mixin(Tessellator.class)
public class MixinTessellator {

    @Shadow
    private WorldRenderer worldRenderer;

    @Shadow
    private WorldVertexBufferUploader vboUploader;

    @Shadow
    public void draw() {}

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(int bufferSize, CallbackInfo ci) {
        //this.field_178183_a = new VertexBuffer(bufferSize);
        try {
            Class<?> clazz = Class.forName("net.minecraft.client.renderer.vertex.VertexBuffer");
            Constructor<?> constructor = clazz.getConstructor(int.class);
            this.field_178183_a = (VertexBuffer) constructor.newInstance(bufferSize);
        } catch (Exception e) {
            System.out.println("Failed to create VertexBuffer instance");
        }
    }

    /** worldRenderer */
    public VertexBuffer field_178183_a;

    public int func_78381_a() {
        this.draw();
        return 0;
    }

    /** getBuffer */
    public VertexBuffer func_178180_c() {
        return this.field_178183_a;
    }

}
