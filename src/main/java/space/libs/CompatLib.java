package space.libs;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("all")
@Mod(
    modid = "compatlib",
    version = "1.8.9",
    name = "CompatLib",
    acceptedMinecraftVersions = "*",
    acceptableRemoteVersions = "*"
)
public class CompatLib {

    public static final String MODID = "compatlib";

    public static Logger LOGGER = FMLLog.getLogger();

    public CompatLib() {
        LOGGER.info("[CompatLib] Loading...");
    }

    public static String getMCVersion() {
        return Loader.instance().getMCVersionString();
    }

    public static String getMoBendsVersion() {
        try {
            
        }
    }

}
