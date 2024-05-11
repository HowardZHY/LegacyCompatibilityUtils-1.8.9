package space.libs.mixins.client.render;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal {

    @Shadow
    public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {}

    /** drawOutlinedBoundingBox */
    @Public
    private static void func_147590_a(AxisAlignedBB boundingBox, int i) {
        drawSelectionBoundingBox(boundingBox);
    }

}
