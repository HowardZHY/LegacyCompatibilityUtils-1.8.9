package space.libs.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;

@SideOnly(Side.CLIENT)
public interface ITessellator {

    @SuppressWarnings("unused")
    ITessellator INSTANCE = (ITessellator) Tessellator.getInstance();

    int func_78381_a();

    BufferBuilder func_178180_c();

}
