package space.libs.mixins.client.forge;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.ModelStateComposition;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(value = ModelStateComposition.class, remap = false)
public abstract class MixinModelStateComposition implements Function<IModelPart, TRSRTransformation> {


    @Shadow
    public abstract Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part);

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return this.apply(Optional.fromNullable(input)).orNull();
    }

}
