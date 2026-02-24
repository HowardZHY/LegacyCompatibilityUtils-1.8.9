package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(RenderItem.class)
public abstract class MixinRenderItem {

    @Shadow
    private @Final ItemModelMesher itemModelMesher;

    @Shadow
    private void draw(WorldRenderer renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha) {}

    @Shadow
    protected void renderItemModelTransform(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType) {}

    /** debugItemOffsetX */
    @Public
    private static float field_175055_b = 0.0F;

    /** debugItemOffsetY */
    @Public
    private static float field_175056_c = 0.0F;

    /** debugItemOffsetZ */
    @Public
    private static float field_175053_d = 0.0F;

    /** debugItemRotationOffsetX */
    @Public
    private static float field_175054_e = 0.0F;

    /** debugItemRotationOffsetY */
    @Public
    private static float field_175051_f = 0.0F;

    /** debugItemRotationOffsetZ */
    @Public
    private static float field_175052_g = 0.0F;

    /** debugItemScaleX */
    @Public
    private static float field_175061_h = 0.0F;

    /** debugItemScaleY */
    @Public
    private static float field_175062_i = 0.0F;

    /** debugItemScaleZ */
    @Public
    private static float field_175060_j = 0.0F;

    /** applyTransform */
    public void func_175034_a(ItemTransformVec3f vec3f) {
        this.applyVanillaTransform(vec3f);
    }

    /** renderItemModel */
    public void func_175043_b(ItemStack stack) {
        if (stack != null) {
            IBakedModel ibakedmodel = this.itemModelMesher.getItemModel(stack);
            this.renderItemModelTransform(stack, ibakedmodel, ItemCameraTransforms.TransformType.NONE);
        }
    }

    /** drawRect */
    public void func_175044_a(WorldRenderer renderer, int x, int y, int width, int height, int color) {
        int r = color >> 16 & 255;
        int g = color >> 8 & 255;
        int b = color & 255;
        this.draw(renderer, x, y, width, height, r, g, b, 255);
    }

    @Public
    private static void applyVanillaTransform(ItemTransformVec3f vec3f) {
        if (vec3f != ItemTransformVec3f.DEFAULT) {
            GlStateManager.translate(vec3f.translation.x + field_175055_b, vec3f.translation.y + field_175056_c, vec3f.translation.z + field_175053_d);
            GlStateManager.rotate(vec3f.rotation.y + field_175051_f, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(vec3f.rotation.x + field_175054_e, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(vec3f.rotation.z + field_175052_g, 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(vec3f.scale.x + field_175061_h, vec3f.scale.y + field_175062_i, vec3f.scale.z + field_175060_j);
        }
    }

}
