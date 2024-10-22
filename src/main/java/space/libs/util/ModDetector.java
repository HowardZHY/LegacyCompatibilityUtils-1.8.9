package space.libs.util;

import com.google.common.base.Strings;
import space.libs.core.CompatLibCore;

import java.lang.reflect.Field;
import java.util.Objects;

@SuppressWarnings("all")
public class ModDetector {

    public static boolean hasSpACore = false;

    public static int hasMobends = 0;

    public static String mobends = "";

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
        if (hasMobends == 0) {
            try {
                Class<?> c = Class.forName("net.gobbob.mobends.MoBends");
                Field versionField = c.getField("VERSION");
                mobends = (String) versionField.get(null);
            } catch (Exception ignored) {
                CompatLibCore.LOGGER.info("MoBends Version not found.");
                hasMobends = 1;
                return "";
            }
            hasMobends = 2;
            CompatLibCore.LOGGER.info("MoBends Version: " + mobends);
        }
        return mobends;
    }

}
