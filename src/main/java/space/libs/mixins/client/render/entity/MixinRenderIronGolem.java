package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderIronGolem.class)
public abstract class MixinRenderIronGolem {

    @Shadow
    protected void rotateCorpse(EntityIronGolem e, float p_77043_2_, float p_77043_3_, float partialTicks) {}

    public void func_180588_a(EntityIronGolem e, float p_77043_2_, float p_77043_3_, float partialTicks) {
        this.rotateCorpse(e, p_77043_2_, p_77043_3_, partialTicks);
    }
}
