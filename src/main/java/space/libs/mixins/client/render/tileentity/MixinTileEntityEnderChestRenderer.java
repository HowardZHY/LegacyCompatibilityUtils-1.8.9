package space.libs.mixins.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;
import net.minecraft.tileentity.TileEntityEnderChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(TileEntityEnderChestRenderer.class)
public class MixinTileEntityEnderChestRenderer {

    @Shadow
    public void renderTileEntityAt(TileEntityEnderChest te, double x, double y, double z, float partialTicks, int destroyStage) {}

    public void func_180540_a(TileEntityEnderChest te, double x, double y, double z, float partialTicks, int destroyStage) {
        this.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }
}
