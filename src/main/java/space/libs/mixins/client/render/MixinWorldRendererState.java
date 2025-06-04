package space.libs.mixins.client.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.*;
import space.libs.interfaces.IWorldRendererState;
import space.libs.util.MappedName;
import space.libs.util.cursedmixinextensions.annotations.*;

@SuppressWarnings("all")
@Mixin(value = WorldRenderer.State.class, priority = 2000)
public abstract class MixinWorldRendererState implements IWorldRendererState {

    @Shadow
    public abstract int getVertexCount();

    @ShadowSuperConstructor
    public void Object() {}

    @ShadowConstructor
    public void State(int[] buffer, VertexFormat format) {}

    /**
     * @param dummy
     * @reason unknown cause first int[] disappear
     */
    @NewConstructor
    public void State(int[] dummy, int[] buffer, int p_i46274_3_, VertexFormat format) {
        State(buffer, format);
        this.field_179020_c = p_i46274_3_;
        this.field_179017_d = this.getVertexCount();
    }

    @NewConstructor
    public void State(int[] dummy, int[] buffer, int p_i46274_3_, int p_i46274_4_, VertexFormat format) {
        State(buffer, format);
        this.field_179020_c = p_i46274_3_;
        this.field_179017_d = p_i46274_4_;
    }

    @MappedName("stateRawBufferIndex")
    public int field_179020_c;

    @MappedName("stateVertexCount")
    public int field_179017_d;

    @MappedName("getRawBufferIndex")
    public int func_179015_b() {
        return this.field_179020_c;
    }

    public void setRawBufferIndex(int index) {
        this.field_179020_c = index;
        this.field_179017_d = this.getVertexCount();
    }

}
