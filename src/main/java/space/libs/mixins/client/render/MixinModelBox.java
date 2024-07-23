package space.libs.mixins.client.render;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused ")
@Mixin(ModelBox.class)
public abstract class MixinModelBox {

    @Shadow
    public abstract void render(WorldRenderer renderer, float scale);

    public void func_178780_a(VertexBuffer buffer, float scale) {
        this.render(buffer, scale);
    }

}
