package space.libs.util;

import space.libs.core.CompatLibCore;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class ModDetector {

    public static boolean hasSpACore = false;

    public ModDetector() {
        if (hasSpACore) {
            return;
        }
        this.getSpACore();
    }

    public static void getSpACore() {
        try {
            Class<?> c = Class.forName("net.specialattack.forge.core.asm.SpACorePlugin");
        } catch (Exception ignored) {
            CompatLibCore.LOGGER.info("SpACore Not Found.");
            return;
        }
        CompatLibCore.LOGGER.info("Found SpACore, load ASM Transformers of it.");
        hasSpACore = true;
    }

    public static String getMoBendsVersion() {
        String version = "";
        try {
            Class<?> c = Class.forName("net.gobbob.mobends.MoBends");
            Field versionField = c.getField("VERSION");
            version = (String) versionField.get(null);
        } catch (Exception ignored) {
            CompatLibCore.LOGGER.info("MoBends Version not found.");
            return "";
        }
        CompatLibCore.LOGGER.info("MoBends Version: "+ version);
        return version;
    }

}
