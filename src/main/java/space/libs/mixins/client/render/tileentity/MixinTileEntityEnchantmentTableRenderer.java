package space.libs.mixins.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(TileEntityEnchantmentTableRenderer.class)
public class MixinTileEntityEnchantmentTableRenderer {

    @Shadow
    public void renderTileEntityAt(TileEntityEnchantmentTable te, double x, double y, double z, float partialTicks, int destroyStage) {}

    public void func_180537_a(TileEntityEnchantmentTable te, double x, double y, double z, float partialTicks, int destroyStage) {
        this.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }
}
