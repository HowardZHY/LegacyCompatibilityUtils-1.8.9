package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.boss.EntityDragon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderDragon.class)
public class MixinRenderDragon {

    @Shadow
    protected void rotateCorpse(EntityDragon e, float p_77043_2_, float p_77043_3_, float partialTicks) {}

    public void func_180575_a(EntityDragon e, float p_77043_2_, float p_77043_3_, float partialTicks) {
        this.rotateCorpse(e, p_77043_2_, p_77043_3_, partialTicks);
    }
}
