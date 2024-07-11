package space.libs.mixins.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.tileentity.TileEntityChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(TileEntityChestRenderer.class)
public class MixinTileEntityChestRenderer {

    @Shadow
    public void renderTileEntityAt(TileEntityChest te, double x, double y, double z, float partialTicks, int destroyStage) {}

    public void func_180538_a(TileEntityChest te, double x, double y, double z, float partialTicks, int destroyStage) {
        this.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }
}
