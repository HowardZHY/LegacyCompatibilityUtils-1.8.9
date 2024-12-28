package space.libs.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IWorldRendererState {

    /** getRawBufferIndex */
    int func_179015_b();

    void setRawBufferIndex(int index);
}
