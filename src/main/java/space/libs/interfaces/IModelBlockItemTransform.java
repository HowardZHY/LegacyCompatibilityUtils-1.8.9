package space.libs.interfaces;

import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("all")
@SideOnly(Side.CLIENT)
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
