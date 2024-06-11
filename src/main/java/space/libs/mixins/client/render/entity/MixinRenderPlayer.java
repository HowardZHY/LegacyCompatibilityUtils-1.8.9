package space.libs.mixins.client.render.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer extends RendererLivingEntity<AbstractClientPlayer> {

    public MixinRenderPlayer(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    @Shadow
    public ModelPlayer getMainModel() {
        throw new AbstractMethodError();
    }

    @Shadow
    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks) {}

    @Shadow(aliases = "func_177069_a")
    protected void renderOffsetLivingLabel(AbstractClientPlayer entityIn, double x, double y, double z, String str, float p_177069_9_, double p_177069_10_) {}

    @Shadow
    protected void rotateCorpse(AbstractClientPlayer player, float x, float y, float z) {}

    /** getMainModel */
    public ModelPlayer func_177136_g() {
        return this.getMainModel();
    }

    public ModelBase func_177087_b() {
        return this.func_177136_g();
    }

    /** doRender */
    public void func_180596_a(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void func_76986_a(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.func_180596_a((AbstractClientPlayer)entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        super.doRender((AbstractClientPlayer) entity, x, y, z, p_76986_8_, partialTicks);
    }

    /** getEntityTexture */
    public ResourceLocation func_180594_a(AbstractClientPlayer entity) {
        return entity.getLocationSkin();
    }

    /** renderOffsetLivingLabel */
    public void func_96449_a(AbstractClientPlayer entityIn, double x, double y, double z, String str, float p_177069_9_, double p_177069_10_) {
        this.renderOffsetLivingLabel(entityIn, x, y, z, str, p_177069_9_, p_177069_10_);
    }

    /** rotateCorpse */
    public void func_180595_a(AbstractClientPlayer player, float x, float y, float z) {
        this.rotateCorpse(player, x, y, z);
    }

}
