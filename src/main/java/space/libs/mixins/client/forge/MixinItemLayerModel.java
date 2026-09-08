/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.BitSet;

@SuppressWarnings("all")
@Mixin(value = ItemLayerModel.class, remap = false)
public abstract class MixinItemLayerModel implements IModelPart {

    @Shadow
    public abstract ImmutableList<BakedQuad> getQuadsForSprite(int tint, TextureAtlasSprite sprite, VertexFormat format, Optional<TRSRTransformation> transform);

    @Shadow
    private static void addSideQuad(ImmutableList.Builder<BakedQuad> builder, BitSet faces, VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, int tint, TextureAtlasSprite sprite, int uMax, int vMax, int u, int v) {}

    @Shadow
    private static BakedQuad buildSideQuad(VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, int tint, TextureAtlasSprite sprite, int u, int v) {
        throw new AbstractMethodError();
    }

    @Shadow
    private static final BakedQuad buildQuad(
        VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, int tint,
        float x0, float y0, float z0, float u0, float v0,
        float x1, float y1, float z1, float u1, float v1,
        float x2, float y2, float z2, float u2, float v2,
        float x3, float y3, float z3, float u3, float v3) {
        throw new AbstractMethodError();
    }

    @Shadow
    private static void putVertex(UnpackedBakedQuad.Builder builder, VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, float x, float y, float z, float u, float v) {}

    public ImmutableList<BakedQuad> getQuadsForSprite(int tint, TextureAtlasSprite sprite, VertexFormat format, TRSRTransformation transform) {
        return this.getQuadsForSprite(tint, sprite, format, Optional.fromNullable(transform));
    }

    @Public
    private static void addSideQuad(ImmutableList.Builder<BakedQuad> builder, BitSet faces, VertexFormat format, TRSRTransformation transform, EnumFacing side, int tint, TextureAtlasSprite sprite, int uMax, int vMax, int u, int v) {
        addSideQuad(builder, faces, format, Optional.fromNullable(transform), side, tint, sprite, uMax, vMax, u, v);
    }

    @Public
    private static BakedQuad buildSideQuad(VertexFormat format, TRSRTransformation transform, EnumFacing side, int tint, TextureAtlasSprite sprite, int u, int v) {
        return buildSideQuad(format, Optional.fromNullable(transform), side, tint, sprite, u, v);
    }

    @Public
    private static BakedQuad buildQuad(
        VertexFormat format, TRSRTransformation transform, EnumFacing side, int tint,
        float x0, float y0, float z0, float u0, float v0,
        float x1, float y1, float z1, float u1, float v1,
        float x2, float y2, float z2, float u2, float v2,
        float x3, float y3, float z3, float u3, float v3) {
        return buildQuad(format, Optional.fromNullable(transform), side, tint,
            x0, y0, z0, u0, v0, x1, y1, z1, u1, v1,
            x2, y2, z2, u2, v2, x3, y3, z3, u3, v3
        );
    }

    @Public
    private static void putVertex(UnpackedBakedQuad.Builder builder, VertexFormat format, TRSRTransformation transform, EnumFacing side, float x, float y, float z, float u, float v) {
        putVertex(builder, format, Optional.fromNullable(transform), side, x, y, z, u, v);
    }
}
