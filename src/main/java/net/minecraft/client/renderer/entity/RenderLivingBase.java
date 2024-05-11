package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;

@SuppressWarnings("unused")
public abstract class RenderLivingBase<T extends EntityLivingBase> extends RendererLivingEntity<T> {

    public RenderLivingBase(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

}
