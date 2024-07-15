/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@SuppressWarnings("all")
@Mixin(targets = "net.minecraftforge.client.model.ModelLoader$VanillaModelWrapper", remap = false)
public abstract class MixinModelLoaderVanillaModelWrapper implements IModelPart {

    @Shadow
    private IFlexibleBakedModel bakeNormal(
        ModelBlock model,
        IModelState perState,
        final TRSRTransformation modelState,
        List<TRSRTransformation> newTransforms,
        VertexFormat format,
        final Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter,
        boolean uvLocked)
    {
        throw new AbstractMethodError();
    }

    public IFlexibleBakedModel bakeNormal(
        ModelBlock model,
        IPerspectiveState perspectiveState,
        TRSRTransformation trans,
        VertexFormat format,
        Function<ResourceLocation, TextureAtlasSprite> function,
        boolean uvLocked)
    {
        List<TRSRTransformation> newTransforms = Lists.newArrayList();
        for (int i = 0; i < model.getElements().size(); i++) {
            newTransforms.add(trans);
        }
        return this.bakeNormal(
            model,
            perspectiveState,
            trans,
            newTransforms,
            format,
            function,
            uvLocked
        );
    }
}
