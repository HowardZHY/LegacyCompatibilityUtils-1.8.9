package space.libs.interfaces;

import net.minecraft.client.renderer.block.model.ItemTransformVec3f;

@SuppressWarnings("all")
public interface IModelBlockItemTransform {
    /** getThirdPersonTransform */
    ItemTransformVec3f func_178296_g();
    /** getFirstPersonTransform */
    ItemTransformVec3f func_178306_h();
    /** getHeadTransform */
    ItemTransformVec3f func_178301_i();
    /** getInGuiTransform */
    ItemTransformVec3f func_178297_j();
}
