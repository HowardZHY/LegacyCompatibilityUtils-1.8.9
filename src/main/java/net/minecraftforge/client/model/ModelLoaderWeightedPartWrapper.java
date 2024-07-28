/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.client.model;

import com.google.common.base.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;

@SuppressWarnings("all")
public class ModelLoaderWeightedPartWrapper implements IModel {

    public IModel model;

    public ModelLoaderWeightedPartWrapper(IModel model) {
        this.model = model;
    }

    public Collection<ResourceLocation> getDependencies() {
        return this.model.getDependencies();
    }

    public Collection<ResourceLocation> getTextures() {
        return this.model.getTextures();
    }

    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return this.model.bake(state, format, bakedTextureGetter);
    }

    public IModelState getDefaultState() {
        return this.model.getDefaultState();
    }

}
