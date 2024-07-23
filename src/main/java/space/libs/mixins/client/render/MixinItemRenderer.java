package space.libs.mixins.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumWorldBlockLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused, deprecation")
@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Shadow
    private boolean isBlockTranslucent(Block blockIn) {
        return blockIn != null && blockIn.getBlockLayer() == EnumWorldBlockLayer.TRANSLUCENT;
    }

    /** itemRenderer */
    private RenderItem field_178112_h;

    public void func_187462_a(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType type, boolean b) {
        if (stack != null) {
            Item item = stack.getItem();
            Block block = Block.getBlockFromItem(item);
            GlStateManager.pushMatrix();
            boolean flag = (this.field_178112_h.shouldRenderItemIn3D(stack) && isBlockTranslucent(block));
            if (flag) {
                GlStateManager.depthMask(false);
            }
            this.field_178112_h.renderItem(stack, type);
            if (flag) {
                GlStateManager.depthMask(true);
            }
            GlStateManager.popMatrix();
        }
    }
}
