package space.libs.mixins.client.render;

import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IModelBlockItemTransform;
import space.libs.util.MappedName;

@SuppressWarnings("deprecation")
@Mixin(ModelBlock.class)
public class MixinModelBlock implements IModelBlockItemTransform {

    @Shadow
    public ModelBlock parent;

    @Shadow(aliases = "field_178320_j")
    public ItemCameraTransforms cameraTransforms;

    @MappedName("getThirdPersonTransform")
    public ItemTransformVec3f func_178296_g() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.cameraTransforms.thirdPerson == ItemTransformVec3f.DEFAULT ? accessor.func_178296_g() : this.cameraTransforms.thirdPerson;
    }

    @MappedName("getFirstPersonTransform")
    public ItemTransformVec3f func_178306_h() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.cameraTransforms.firstPerson == ItemTransformVec3f.DEFAULT ? accessor.func_178306_h() : this.cameraTransforms.firstPerson;
    }

    @MappedName("getHeadTransform")
    public ItemTransformVec3f func_178301_i() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.cameraTransforms.head == ItemTransformVec3f.DEFAULT ? accessor.func_178301_i() : this.cameraTransforms.head;
    }

    @MappedName("getInGuiTransform")
    public ItemTransformVec3f func_178297_j() {
        IModelBlockItemTransform accessor = (IModelBlockItemTransform) parent;
        return this.parent != null && this.cameraTransforms.gui == ItemTransformVec3f.DEFAULT ? accessor.func_178297_j() : this.cameraTransforms.gui;
    }

}
