package space.libs.util;

import space.libs.core.CompatLibCore;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class ModDetector {

    public static boolean initialized = false;

    public static boolean hasSpACore = false;

    public static boolean hasAlexIILLib = false;

    public static boolean hasCivCraft = false;

    public static int hasMobends = 0;

    public static String mobends = "";

    public static ModDetector INSTANCE;

    public ModDetector() {
        if (initialized) {
            return;
        }
        INSTANCE = this;
        hasSpACore = getCoreMod("net.specialattack.forge.core.asm.SpACorePlugin", true);
        hasAlexIILLib = getCoreMod("alexiil.mods.lib.coremod.LoadPlugin", true);
        hasCivCraft = getCoreMod("alexiil.mods.civ.coremod.LoadPlugin", true);
        initialized = true;
    }

    public static boolean getCoreMod(String name, boolean init) {
        try {
            Class<?> c = Class.forName(name, init, INSTANCE.getClass().getClassLoader());
        } catch (Exception ignored) {
            CompatLibCore.LOGGER.info("Coremod " + name + " Not Found.");
            return false;
        }
        return true;
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
