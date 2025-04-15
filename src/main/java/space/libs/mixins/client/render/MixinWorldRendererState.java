package space.libs.mixins.client.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IWorldRendererState;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(WorldRenderer.State.class)
public abstract class MixinWorldRendererState implements IWorldRendererState {

    @Shadow
    public abstract int getVertexCount();

    @ShadowConstructor
    public void State(int[] buffer, VertexFormat format) {}

    @NewConstructor
    public void State(int[] buffer, int p_i46274_3_, int p_i46274_4_, VertexFormat format) {
        State(buffer, format);
        this.field_179020_c = p_i46274_3_;
        this.field_179017_d = p_i46274_4_;
    }

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
