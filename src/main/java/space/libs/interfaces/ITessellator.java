package space.libs.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.BufferBuilder;

@SideOnly(Side.CLIENT)
public interface ITessellator {

    int func_78381_a();

    BufferBuilder func_178180_c();

}
