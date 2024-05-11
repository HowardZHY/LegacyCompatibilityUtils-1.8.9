package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.ItemTransformVec3f;

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
public class MixinItemTransformVec3f {

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
        org.lwjgl.util.vector.Vector3f newrotation = TransformVec3f(rotation);
        org.lwjgl.util.vector.Vector3f newtranslation = TransformVec3f(translation);
        org.lwjgl.util.vector.Vector3f newscale = TransformVec3f(scale);
        ItemTransformVec3f(newrotation, newtranslation, newscale);
    }

    @Public
    private static org.lwjgl.util.vector.Vector3f TransformVec3f(javax.vecmath.Vector3f vec) {
        return new org.lwjgl.util.vector.Vector3f(vec.x, vec.y, vec.z);
    }
}
