package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.entity.RenderItem;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(RenderItem.class)
public class MixinRenderItem {

    @Public
    private static float field_175055_b = 0.0F;

    @Public
    private static float field_175056_c = 0.0F;

    @Public
    private static float field_175053_d = 0.0F;

    @Public
    private static float field_175054_e = 0.0F;

    @Public
    private static float field_175051_f = 0.0F;

    @Public
    private static float field_175052_g = 0.0F;

    @Public
    private static float field_175061_h = 0.0F;

    @Public
    private static float field_175062_i = 0.0F;

    @Public
    private static float field_175060_j = 0.0F;

    public void func_175034_a(ItemTransformVec3f vec3f) {
        this.applyVanillaTransform(vec3f);
    }

    @Public
    private static void applyVanillaTransform(ItemTransformVec3f p_175034_1_) {

        if (p_175034_1_ != ItemTransformVec3f.DEFAULT)
        {
            GlStateManager.translate(p_175034_1_.translation.x + field_175055_b, p_175034_1_.translation.y + field_175056_c, p_175034_1_.translation.z + field_175053_d);
            GlStateManager.rotate(p_175034_1_.rotation.y + field_175051_f, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(p_175034_1_.rotation.x + field_175054_e, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(p_175034_1_.rotation.z + field_175052_g, 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(p_175034_1_.scale.x + field_175061_h, p_175034_1_.scale.y + field_175062_i, p_175034_1_.scale.z + field_175060_j);
        }

    }

}
