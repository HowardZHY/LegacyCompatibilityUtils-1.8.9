/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.forge.client;

import com.google.common.base.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;

import java.util.Collection;

@SuppressWarnings("all")
public class WeightedPartWrapper implements IModel {

    public IModel model;

    public WeightedPartWrapper(IModel model) {
        this.model = model;
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return this.model.getDependencies();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return this.model.getTextures();
    }

    @Override
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return this.model.bake(state, format, bakedTextureGetter);
    }

    @Override
    public IModelState getDefaultState() {
        return this.model.getDefaultState();
    }

}
