/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(ItemLayerModel.BakedModel.class)
public abstract class MixinItemLayerModelBakedModel implements IPerspectiveAwareModel {

    public ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms;

    @ShadowConstructor
    public void BakedModel(ImmutableList<BakedQuad> quads, TextureAtlasSprite particle, VertexFormat format) {}

    @NewConstructor
    public void BakedModel(ImmutableList<BakedQuad> quads, TextureAtlasSprite particle, VertexFormat format, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms) {
        this.BakedModel(quads, particle, format);
        this.transforms = transforms;
    }

    @Override
    public Pair<? extends IFlexibleBakedModel, javax.vecmath.Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
    {
        TRSRTransformation tr = transforms.get(cameraTransformType);
        javax.vecmath.Matrix4f mat = null;
        if (tr != null && tr != TRSRTransformation.identity()) mat = TRSRTransformation.blockCornerToCenter(tr).getMatrix();
        return Pair.of(this, mat);
    }
}
