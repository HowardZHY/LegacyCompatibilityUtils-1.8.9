package space.libs.asm;

import java.util.Arrays;

public class ClassNameList {

    public static boolean Startswith(String name) {
        return Arrays.stream(ClassNameList.WHITELIST).anyMatch(name::startsWith);
    }

    public static boolean Contains(String name) {
        return Arrays.stream(ClassNameList.CONTAINS_WHITELIST).anyMatch(name::contains);
    }

    /** Things shouldn't be transformed */
    public static String[] WHITELIST = {
        "club.sk1er.patcher",
        "com.mojang",
        "com.mumfrey.liteloader",
        "com.google",
        "com.gtnewhorizon",
        "com.sun",
        "customskinloader",
        "gg.essential",
        "io.github.legacymoddingmc",
        "io.netty",
        "it.unimi",
        "joptsimple",
        "oshi",
        "org.apache.commons",
        "org.slf4j",
        "org.spongepowered",
        "org.yaml",
        "net.minecraft",
        "net.minecraftforge.fml",
        "space.libs",
        "zone.rong",
        "kotlin",
        "kotlinx"
    };

    /** Things totally shouldn't be transformed */
    public static String[] CONTAINS_WHITELIST = {
        "EarlyMixin",
        "LateMixin",
        "betterfps",
        "makamys",
        "mixinbooter",
        "mixinextras",
        "optifine",
    };

    /** Mod packages that requires addtional transforms */
    public static String[] BLACKLIST = {
        ""
    };

    /** Mod that requires addtional transforms */
    public static String[] CONTAINS_BLACKLIST = {
        "TMI"
    };

}
