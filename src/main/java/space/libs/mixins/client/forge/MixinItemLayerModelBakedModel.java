package space.libs.mixins.client.forge;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@Mixin(ItemLayerModel.BakedModel.class)
public abstract class MixinItemLayerModelBakedModel implements IPerspectiveAwareModel {

    @ShadowConstructor
    public void BakedModel(ImmutableList<BakedQuad> quads, TextureAtlasSprite particle, VertexFormat format) {}

    @NewConstructor
    public void BakedModel(ImmutableList<BakedQuad> quads, TextureAtlasSprite particle, VertexFormat format, ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms) {
        this.BakedModel(quads, particle, format);
    }
}
