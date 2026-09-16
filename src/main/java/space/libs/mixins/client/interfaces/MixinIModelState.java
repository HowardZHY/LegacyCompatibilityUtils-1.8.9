package space.libs.mixins.client.interfaces;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import net.minecraftforge.client.model.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import space.libs.CompatLib;

@Mixin(value = IModelState.class, remap = false)
public interface MixinIModelState extends Function<IModelPart, TRSRTransformation> {

    @Override
    default TRSRTransformation apply(IModelPart part) {
        try {
            Optional<TRSRTransformation> transformation = this.apply(Optional.fromNullable(part));
            return transformation.or(TRSRTransformation.identity());
        } catch (StackOverflowError e) {
            CompatLib.LOGGER.error(e);
            return TRSRTransformation.identity();
        }
    }

    /**
     * @author HowardZHY
     * @reason Call Legacy
     */
    @SuppressWarnings("OverwriteModifiers")
    @Overwrite
    default Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part) {
        try {
            return Optional.fromNullable(this.apply(part.orNull()));
        } catch (NullPointerException e) {
            CompatLib.LOGGER.error(e);
        }
        return Optional.absent();
    }
}
