package space.libs.util;

import space.libs.core.CompatLibCore;

public class ModDetector {

    public static boolean hasSpACore = false;

    public ModDetector() {
        try {
            Class<?> c = Class.forName("net.specialattack.forge.core.asm.SpACorePlugin");
        } catch (Exception e) {
            CompatLibCore.LOGGER.info("SpACore Not Found.");
        }
        CompatLibCore.LOGGER.info("Found SpACore, load ASM Transformers of it.");
        hasSpACore = true;

    }

}
