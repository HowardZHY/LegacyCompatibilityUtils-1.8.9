package space.libs.util.mods;

import com.mumfrey.liteloader.core.runtime.Obf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import space.libs.core.CompatLibCore;

@SideOnly(Side.CLIENT)
public class ObfCompat extends Obf {

    public static final ObfCompat GuiTextField = new ObfCompat("net.minecraft.client.gui.GuiTextField", "avw");

    public ObfCompat(String seargeName, String obfName) {
        super(seargeName, obfName);
    }

    public static void init() {
        CompatLibCore.LOGGER.info("Try loading LiteLoader Obf...");
    }
}
