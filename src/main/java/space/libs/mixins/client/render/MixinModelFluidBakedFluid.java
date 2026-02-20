package space.libs.mixins.client.render;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "deprecation", "unused"})
@Mixin(value = ModelFluid.BakedFluid.class, remap = false)
public class MixinModelFluidBakedFluid {

    @ShadowConstructor
    public void BakedFluid(
        Optional<TRSRTransformation> transformation, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms,
        VertexFormat format, int color,
        TextureAtlasSprite still, TextureAtlasSprite flowing, boolean gas,
        boolean statePresent, int[] cornerRound, int flowRound) {}

    @NewConstructor
    public void BakedFluid(
        Optional<TRSRTransformation> transformation, VertexFormat format, int color,
        TextureAtlasSprite still, TextureAtlasSprite flowing, boolean gas,
        boolean statePresent, int[] cornerRound, int flowRound)
    {
        BakedFluid(
            transformation, ImmutableMap.of(),
            format, color, still, flowing, gas, statePresent, cornerRound, flowRound);
    }
}
