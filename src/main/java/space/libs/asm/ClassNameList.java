package space.libs.asm;

import java.util.Arrays;

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

    public static boolean ContainsBlacklist(String name) {
        return Arrays.stream(ClassNameList.CONTAINS_BLACKLIST).anyMatch(name::startsWith);
    }

    /** Things shouldn't be transformed */
    public static String[] WHITELIST = {
        "cc.polyfrost",
        "club.sk1er.patcher",
        "com.llamalad7",
        "com.mojang",
        "com.mumfrey.liteloader",
        "com.google",
        "com.gtnewhorizon",
        "com.shadowhawk",
        "com.sun",
        "customskinloader",
        "gg.essential",
        "gnu.trove",
        "groovy",
        "io.github.legacymoddingmc",
        "io.netty",
        "it.unimi",
        "java",
        "javax",
        "joptsimple",
        "oshi",
        "org.apache",
        "org.objectweb",
        "org.polyfrost",
        "org.slf4j",
        "org.spongepowered",
        "org.yaml",
        "net.md_5.specialsource",
        "net.minecraft",
        "net.minecraftforge.fml",
        "scala",
        "space.libs",
        "sun.misc",
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
        "shadersmod",
        "twitch"
    };

    /** Mod packages that requires additional transforms */
    public static String[] BLACKLIST = {
        "com.mamiyaotaru.chatbubbles",
        "com.mamiyaotaru.jukebox",
        "com.mumfrey.worldeditcui",
        "maya.biomeborders",
        "mnm.mods.linkinfo",
        "net.easymfne.omniscience",
        "net.easymfne.potioncolorizer",
        "net.eq2online.macros",
        "troy.autofish",
        "troy.chunkborders",
        "xaero.mods"
    };

    /** Mod that requires additional transforms */
    public static String[] CONTAINS_BLACKLIST = {
        "TMI"
    };

}
