package space.libs.mixins.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.tileentity.TileEntitySign;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(TileEntitySignRenderer.class)
public class MixinTileEntitySignRenderer {

    @Shadow
    public void renderTileEntityAt(TileEntitySign te, double x, double y, double z, float partialTicks, int destroyStage) {}

    public void func_180541_a(TileEntitySign te, double x, double y, double z, float partialTicks, int destroyStage) {
        this.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }

}
