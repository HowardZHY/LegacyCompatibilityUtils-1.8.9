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

    @ShadowConstructor
    public void State(WorldRenderer outer, int[] buffer, VertexFormat format) {}

    @NewConstructor
    public void State(WorldRenderer outer, int[] buffer, int index, VertexFormat format) {
        State(outer, buffer, format);
        this.field_179020_c = index;
        this.field_179017_d = this.getVertexCount();
    }

    @NewConstructor
    public void State(WorldRenderer outer, int[] buffer, int index, int count, VertexFormat format) {
        State(outer, buffer, format);
        this.field_179020_c = index;
        this.field_179017_d = count;
    }

    @MappedName("stateRawBufferIndex")
    public int field_179020_c;

    @MappedName("stateVertexCount")
    public int field_179017_d;

    @MappedName("getRawBufferIndex")
    @Override
    public int func_179015_b() {
        return this.field_179020_c;
    }

    @Override
    public void setRawBufferIndex(int index) {
        this.field_179020_c = index;
        this.field_179017_d = this.getVertexCount();
    }

}
