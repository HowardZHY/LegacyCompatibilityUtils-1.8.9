package space.libs.mixins.client.render;

import com.google.common.base.Function;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.client.IMathUtils;

@SuppressWarnings("unused")
@Mixin(ModelRotation.class)
public abstract class MixinModelRotation implements Function<IModelPart, TRSRTransformation>, IMathUtils {

    @Shadow
    public abstract org.lwjgl.util.vector.Matrix4f getMatrix4d();

    @Shadow(remap = false)
    public abstract javax.vecmath.Matrix4f getMatrix();

    public javax.vecmath.Matrix4d func_177525_a() {
        return TransformMat4fTo4d(this.getMatrix4d());
    }

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return new net.minecraftforge.client.model.TRSRTransformation(getMatrix());
    }

}
