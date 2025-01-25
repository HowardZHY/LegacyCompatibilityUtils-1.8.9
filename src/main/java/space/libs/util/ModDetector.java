package space.libs.util;

import space.libs.core.CompatLibCore;

public class ModDetector {

    public static boolean initialized = false;

    public static boolean hasSpACore = false;

    public static boolean hasAlexIILLib = false;

    public static boolean hasCivCraft = false;

    public static boolean hasMobends = false;

    public static boolean hasSkybox = false;

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

    @SuppressWarnings("unused")
    public static boolean getCoreMod(String name, boolean init) {
        try {
            Class<?> c = Class.forName(name, init, INSTANCE.getClass().getClassLoader());
        } catch (Exception ignored) {
            CompatLibCore.LOGGER.info("Coremod " + name + " Not Found.");
            return false;
        }
        return true;
    }

    public static boolean mobends() {
        return hasMobends;
    }

    public static boolean skybox() {
        return hasSkybox;
    }
}
