package space.libs.mixins.client.forge;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraftforge.client.model.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.Public;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(value = IPerspectiveAwareModel.MapWrapper.class, remap = false)
public abstract class MixinIPerspectiveAwareModelMapWrapper {

    @ShadowConstructor
    public void MapWrapper(IFlexibleBakedModel parent, IModelState state) {}

    @NewConstructor
    public void MapWrapper(IFlexibleBakedModel parent, IPerspectiveState state, IModelPart part) {
        this.MapWrapper(parent, state);
    }

    @Shadow
    public static ImmutableMap<TransformType, TRSRTransformation> getTransforms(IModelState state) {
        throw new AbstractMethodError();
    }

    @Public
    private static ImmutableMap<TransformType, TRSRTransformation> getTransforms(IPerspectiveState state, IModelPart part) {
        return getTransforms(state);
    }

}
