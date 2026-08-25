package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IModelBlockItemTransform;
import space.libs.util.MappedName;

@SuppressWarnings("all")
@Mixin(ModelBlock.class)
public class MixinModelBlock implements IModelBlockItemTransform {

    @Shadow
    public ModelBlock parent;

    @MappedName("cameraTransforms")
    public ItemCameraTransforms field_178320_j;

    @MappedName("getThirdPersonTransform")
    public ItemTransformVec3f func_178296_g() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.field_178320_j.thirdPerson == ItemTransformVec3f.DEFAULT ? accessor.func_178296_g() : this.field_178320_j.thirdPerson;
    }

    @MappedName("getFirstPersonTransform")
    public ItemTransformVec3f func_178306_h() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.field_178320_j.firstPerson == ItemTransformVec3f.DEFAULT ? accessor.func_178306_h() : this.field_178320_j.firstPerson;
    }

    @MappedName("getHeadTransform")
    public ItemTransformVec3f func_178301_i() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.field_178320_j.head == ItemTransformVec3f.DEFAULT ? accessor.func_178301_i() : this.field_178320_j.head;
    }

    @MappedName("getInGuiTransform")
    public ItemTransformVec3f func_178297_j() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.field_178320_j.gui == ItemTransformVec3f.DEFAULT ? accessor.func_178297_j() : this.field_178320_j.gui;
    }

}
