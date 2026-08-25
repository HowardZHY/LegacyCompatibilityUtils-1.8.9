package space.libs.interfaces;

import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import space.libs.util.MappedName;

@SuppressWarnings("all")
@SideOnly(Side.CLIENT)
public interface IModelBlockItemTransform {

    @MappedName("getThirdPersonTransform")
    ItemTransformVec3f func_178296_g();

    @MappedName("getFirstPersonTransform")
    ItemTransformVec3f func_178306_h();

    @MappedName("getHeadTransform")
    ItemTransformVec3f func_178301_i();

    @MappedName("getInGuiTransform")
    ItemTransformVec3f func_178297_j();

}
