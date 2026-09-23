package space.libs.mixins.client.render;

import com.google.common.base.Function;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.client.IMathUtils;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import static space.libs.util.client.IMathUtils.*;

@SuppressWarnings("all")
@Mixin(value = ItemTransformVec3f.class, priority = 100)
public abstract class MixinItemTransformVec3f implements Function<IModelPart, TRSRTransformation>, IMathUtils {

    @Shadow
    @Final
    @Mutable
    public org.lwjgl.util.vector.Vector3f rotation;

    @Shadow
    @Final
    @Mutable
    public org.lwjgl.util.vector.Vector3f translation;

    @Shadow
    @Final
    @Mutable
    public org.lwjgl.util.vector.Vector3f scale;

    public javax.vecmath.Vector3f field_178364_b;

    public javax.vecmath.Vector3f field_178365_c;

    public javax.vecmath.Vector3f field_178363_d;

    @ShadowConstructor
    public void ItemTransformVec3f(org.lwjgl.util.vector.Vector3f rotation, org.lwjgl.util.vector.Vector3f translation, org.lwjgl.util.vector.Vector3f scale) {}

    @NewConstructor
    public void ItemTransformVec3f(javax.vecmath.Vector3f rotation, javax.vecmath.Vector3f translation, javax.vecmath.Vector3f scale) {
        this.ItemTransformVec3f(TransformVector3f(rotation), TransformVector3f(translation), TransformVector3f(scale));
    }

    @Inject(method = "<init>(Lorg/lwjgl/util/vector/Vector3f;Lorg/lwjgl/util/vector/Vector3f;Lorg/lwjgl/util/vector/Vector3f;)V", at = @At("RETURN"))
    public void init(org.lwjgl.util.vector.Vector3f rotation, org.lwjgl.util.vector.Vector3f translation, org.lwjgl.util.vector.Vector3f scale, CallbackInfo ci) {
        this.field_178364_b = TransformVecMath3f(rotation);
        this.field_178365_c = TransformVecMath3f(translation);
        this.field_178363_d = TransformVecMath3f(scale);
    }

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return new TRSRTransformation((ItemTransformVec3f) (Object) this);
    }

}
