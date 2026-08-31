/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.common.property.IExtendedBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings({"unused", "deprecation"})
@Mixin(value = ModelFluid.BakedFluid.class, remap = false)
public class MixinModelFluidBakedFluid {

    public Optional<IExtendedBlockState> state;

    @ShadowConstructor
    public void BakedFluid(Optional<TRSRTransformation> transformation, VertexFormat format, int color, TextureAtlasSprite still, TextureAtlasSprite flowing, boolean gas, Optional<IExtendedBlockState> stateOption) {}

    @NewConstructor
    public void BakedFluid(TRSRTransformation transformation, VertexFormat format, int color, TextureAtlasSprite still, TextureAtlasSprite flowing, boolean gas) {
        BakedFluid(transformation, format, color, still, flowing, gas, Optional.absent());
    }

    @NewConstructor
    public void BakedFluid(TRSRTransformation transformation, VertexFormat format, int color, TextureAtlasSprite still, TextureAtlasSprite flowing, boolean gas, Optional<IExtendedBlockState> stateOption) {
        BakedFluid(Optional.fromNullable(transformation), format, color, still, flowing, gas, stateOption);
    }

    @Inject(
        method = "<init>(Lcom/google/common/base/Optional;Lcom/google/common/collect/ImmutableMap;Lnet/minecraft/client/renderer/vertex/VertexFormat;ILnet/minecraft/client/renderer/texture/TextureAtlasSprite;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;ZLcom/google/common/base/Optional;)V",
        at = @At("RETURN")
    )
    public void init(
        Optional<TRSRTransformation> transformation, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms, VertexFormat format,
        int color, TextureAtlasSprite still, TextureAtlasSprite flowing, boolean gas, Optional<IExtendedBlockState> stateOption, CallbackInfo ci
    ) {
        this.state = stateOption;
    }

}
