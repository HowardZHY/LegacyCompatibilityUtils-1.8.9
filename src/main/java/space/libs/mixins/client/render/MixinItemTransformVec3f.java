package space.libs.mixins.client.render;

import com.google.common.base.Function;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.Public;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(ItemTransformVec3f.class)
public class MixinItemTransformVec3f implements Function<IModelPart, TRSRTransformation> {

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
    public void ItemTransformVec3f(org.lwjgl.util.vector.Vector3f rotation, org.lwjgl.util.vector.Vector3f translation, org.lwjgl.util.vector.Vector3f scale) {
        this.rotation = new org.lwjgl.util.vector.Vector3f(rotation);
        this.translation = new org.lwjgl.util.vector.Vector3f(translation);
        this.scale = new org.lwjgl.util.vector.Vector3f(scale);
    }

    @NewConstructor
    public void ItemTransformVec3f(javax.vecmath.Vector3f rotation, javax.vecmath.Vector3f translation, javax.vecmath.Vector3f scale) {
        this.ItemTransformVec3f(TransformVec3f(rotation), TransformVec3f(translation), TransformVec3f(scale));
    }

    @Inject(method = "<init>(Lorg/lwjgl/util/vector/Vector3f;Lorg/lwjgl/util/vector/Vector3f;Lorg/lwjgl/util/vector/Vector3f;)V", at = @At("RETURN"))
    public void init(org.lwjgl.util.vector.Vector3f rotation, org.lwjgl.util.vector.Vector3f translation, org.lwjgl.util.vector.Vector3f scale, CallbackInfo ci) {
        this.field_178364_b = ReTransformVec3f(rotation);
        this.field_178365_c = ReTransformVec3f(translation);
        this.field_178363_d = ReTransformVec3f(scale);
    }

    @Public
    private static org.lwjgl.util.vector.Vector3f TransformVec3f(javax.vecmath.Vector3f vec) {
        return new org.lwjgl.util.vector.Vector3f(vec.x, vec.y, vec.z);
    }

    @Public
    private static javax.vecmath.Vector3f ReTransformVec3f(org.lwjgl.util.vector.Vector3f vec) {
        return new javax.vecmath.Vector3f(vec.x, vec.y, vec.z);
    }

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return new net.minecraftforge.client.model.TRSRTransformation((ItemTransformVec3f) (Object) this);
    }

}
