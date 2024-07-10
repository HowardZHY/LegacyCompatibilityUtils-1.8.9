package space.libs.mixins.client.render;

import net.minecraft.client.renderer.tileentity.TileEntityPistonRenderer;
import net.minecraft.tileentity.TileEntityPiston;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(TileEntityPistonRenderer.class)
public class MixinTileEntityPistonRenderer {

    @Shadow
    public void renderTileEntityAt(TileEntityPiston te, double x, double y, double z, float partialTicks, int destroyStage) {}

    public void func_178461_a(TileEntityPiston te, double x, double y, double z, float partialTicks, int destroyStage) {
        this.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }
}
