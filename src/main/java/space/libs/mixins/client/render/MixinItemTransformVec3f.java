package space.libs.mixins.client.render;

import com.google.common.base.Function;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import org.lwjgl.util.vector.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.Public;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(ItemTransformVec3f.class)
public class MixinItemTransformVec3f implements Function<IModelPart, TRSRTransformation> {

    @Shadow
    @Final
    @Mutable
    public Vector3f rotation;

    @Shadow
    @Final
    @Mutable
    public Vector3f translation;

    @Shadow
    @Final
    @Mutable
    public Vector3f scale;

    @ShadowConstructor
    public void ItemTransformVec3f(org.lwjgl.util.vector.Vector3f rotation, org.lwjgl.util.vector.Vector3f translation, org.lwjgl.util.vector.Vector3f scale) {
        this.rotation = new Vector3f(rotation);
        this.translation = new Vector3f(translation);
        this.scale = new Vector3f(scale);
    }

    @NewConstructor
    public void ItemTransformVec3f(javax.vecmath.Vector3f rotation, javax.vecmath.Vector3f translation, javax.vecmath.Vector3f scale) {
        this.ItemTransformVec3f(TransformVec3f(rotation), TransformVec3f(translation), TransformVec3f(scale));
    }

    @Public
    private static org.lwjgl.util.vector.Vector3f TransformVec3f(javax.vecmath.Vector3f vec) {
        return new org.lwjgl.util.vector.Vector3f(vec.x, vec.y, vec.z);
    }

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return new net.minecraftforge.client.model.TRSRTransformation((ItemTransformVec3f) (Object) this);
    }

}
