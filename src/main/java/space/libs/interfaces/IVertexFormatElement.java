package space.libs.interfaces;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import space.libs.util.MappedName;

@SideOnly(Side.CLIENT)
public interface IVertexFormatElement {

    @MappedName("setOffset")
    void func_177371_a(int p_177371_1_);

    @MappedName("getOffset")
    int func_177373_a();

}
