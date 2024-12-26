package space.libs.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public abstract class ClientUtils {

    public static boolean TICK100() {
        return String.valueOf(Minecraft.getMinecraft().theWorld.getWorldTime()).endsWith("00");
    }
}
