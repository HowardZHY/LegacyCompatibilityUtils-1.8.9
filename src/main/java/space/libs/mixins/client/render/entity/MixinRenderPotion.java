package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderPotion;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderPotion.class)
public abstract class MixinRenderPotion {

    @Shadow
    public abstract ItemStack func_177082_d(EntityPotion entityIn);

    public ItemStack func_177085_a(EntityPotion entityIn) {
        return this.func_177082_d(entityIn);
    }
}
