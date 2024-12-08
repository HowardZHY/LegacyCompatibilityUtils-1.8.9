package space.libs.mixins.client.interfaces;

import com.google.common.base.Function;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IModelState.class)
public interface MixinIModelState extends Function<IModelPart, TRSRTransformation> {

    @Override
    TRSRTransformation apply(IModelPart part);
}
