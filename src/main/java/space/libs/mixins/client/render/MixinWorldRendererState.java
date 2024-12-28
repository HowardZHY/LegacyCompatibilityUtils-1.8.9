package space.libs.mixins.client.render;

import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IWorldRendererState;

@SuppressWarnings("unused")
@Mixin(WorldRenderer.State.class)
public abstract class MixinWorldRendererState implements IWorldRendererState {

    @Shadow
    public abstract int getVertexCount();

    /** stateRawBufferIndex */
    public int field_179020_c;

    /** stateVertexCount */
    public int field_179017_d;

    /** getRawBufferIndex */
    public int func_179015_b() {
        return this.field_179020_c;
    }

    public void setRawBufferIndex(int index) {
        this.field_179020_c = index;
        this.field_179017_d = this.getVertexCount();
    }

}
