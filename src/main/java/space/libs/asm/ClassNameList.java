package space.libs.asm;

import java.util.Arrays;

@SuppressWarnings("unused")
public class ClassNameList {

    public static boolean Startswith(String name) {
        return Arrays.stream(ClassNameList.WHITELIST).anyMatch(name::startsWith);
    }

    public static boolean StartswithBlacklist(String name) {
        return Arrays.stream(ClassNameList.BLACKLIST).anyMatch(name::startsWith);
    }

    public static boolean Contains(String name) {
        return Arrays.stream(ClassNameList.CONTAINS_WHITELIST).anyMatch(name::contains);
    }

    /** Things shouldn't be transformed */
    public static String[] WHITELIST = {
        "cc.polyfrost",
        "club.sk1er.patcher",
        "com.mojang",
        "com.mumfrey.liteloader",
        "com.google",
        "com.gtnewhorizon",
        "com.shadowhawk",
        "com.sun",
        "customskinloader",
        "gg.essential",
        "gnu.trove",
        "io.github.legacymoddingmc",
        "io.netty",
        "it.unimi",
        "joptsimple",
        "oshi",
        "org.apache.commons",
        "org.slf4j",
        "org.spongepowered",
        "org.yaml",
        "net.md_5.specialsource",
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
        "shadersmod"
    };

    /** Mod packages that requires additional transforms */
    public static String[] BLACKLIST = {
        "maya.biomeborders",
        "net.eq2online.macros"
    };

    /** Mod that requires additional transforms */
    public static String[] CONTAINS_BLACKLIST = {
        "TMI"
    };

}
