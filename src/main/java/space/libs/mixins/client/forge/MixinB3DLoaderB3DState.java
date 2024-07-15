package space.libs.mixins.client.forge;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.client.model.b3d.B3DLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(value = B3DLoader.B3DState.class, remap = false)
public abstract class MixinB3DLoaderB3DState implements Function<IModelPart, TRSRTransformation> {

    @Shadow
    public abstract Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part);

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return this.apply(Optional.fromNullable(input)).orNull();
    }
}
