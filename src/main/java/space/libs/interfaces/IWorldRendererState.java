package space.libs.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import space.libs.util.MappedName;

@SideOnly(Side.CLIENT)
public interface IWorldRendererState {

    @MappedName("getRawBufferIndex")
    int func_179015_b();

    void setRawBufferIndex(int index);

}
