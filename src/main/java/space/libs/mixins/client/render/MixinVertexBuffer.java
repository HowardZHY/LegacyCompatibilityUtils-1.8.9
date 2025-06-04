package space.libs.mixins.client.render;

import net.minecraft.client.renderer.vertex.VertexBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.MappedName;

import java.nio.ByteBuffer;

@SuppressWarnings("unused")
@Mixin(VertexBuffer.class)
public abstract class MixinVertexBuffer {

    @Shadow
    public void bufferData(ByteBuffer buffer) {}

    @MappedName("bufferData")
    public void func_177360_a(ByteBuffer buffer, int i) {
        this.bufferData(buffer);
    }

}
