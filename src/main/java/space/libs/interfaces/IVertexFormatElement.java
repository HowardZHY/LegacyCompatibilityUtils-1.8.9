package space.libs.interfaces;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("all")
@SideOnly(Side.CLIENT)
public interface IVertexFormatElement {

    void func_177371_a(int p_177371_1_); // setOffset

    int func_177373_a(); // getOffset

}
