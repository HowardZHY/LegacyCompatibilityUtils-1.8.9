package space.libs.mixins.client.render.entity;

import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ArmorStandRenderer.class)
public abstract class MixinArmorStandRenderer {

    @Shadow
    protected abstract boolean canRenderName(EntityArmorStand entity);

    @Shadow
    public abstract ModelArmorStand getMainModel();

    @Shadow
    protected void rotateCorpse(EntityArmorStand e, float p_77043_2_, float p_77043_3_, float partialTicks) {}

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityArmorStand entity);

    public boolean func_177099_b(EntityArmorStand e) {
        return this.canRenderName(e);
    }

    public ModelArmorStand func_177100_a() {
        return this.getMainModel();
    }

    public void func_177101_a(EntityArmorStand e, float p_77043_2_, float p_77043_3_, float partialTicks) {
        this.rotateCorpse(e, p_77043_2_, p_77043_3_, partialTicks);
    }

    public ResourceLocation func_177102_a(EntityArmorStand entity) {
        return this.getEntityTexture(entity);
    }
}
