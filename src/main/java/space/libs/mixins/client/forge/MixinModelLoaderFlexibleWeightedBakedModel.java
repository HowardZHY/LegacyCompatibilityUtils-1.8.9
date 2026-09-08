package space.libs.mixins.client.forge;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.WeightedBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraftforge.client.model.ModelLoader$FlexibleWeightedBakedModel", remap = false)
public abstract class MixinModelLoaderFlexibleWeightedBakedModel {

    public WeightedBakedModel parent;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(WeightedBakedModel parent, VertexFormat format, CallbackInfo ci) {
        this.parent = parent;
    }
}
