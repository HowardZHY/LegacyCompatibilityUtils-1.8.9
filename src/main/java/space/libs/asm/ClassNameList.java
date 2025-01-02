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
        "ch.qos.logback",
        "club.sk1er.patcher",
        "co.skyclient",
        "com.jcraft",
        "com.llamalad7",
        "com.mojang",
        "com.mumfrey.liteloader",
        "com.google",
        "com.gtnewhorizon",
        "com.replaymod",
        "com.shadowhawk",
        "com.sun",
        "com.thevoxelbox.voxelmap",
        "com.viaversion",
        "com.unascribed.ears",
        "com.xuggle",
        "customskinloader",
        "de.florianmichael",
        "eu.the5zig",
        "gg.essential",
        "gg.skytils",
        "gnu.trove",
        "groovy",
        "io.github.legacymoddingmc",
        "io.github.moulberry",
        "io.netty",
        "it.unimi",
        "java",
        "javax",
        "javaassist",
        "joptsimple",
        "junit",
        "me.jellysquid.mods.phosphor",
        "moe.nea",
        "net.md_5.specialsource",
        "net.minecraft",
        "net.minecraftforge.fml",
        "net.raphimc",
        "oshi",
        "org.apache",
        "org.joml",
        "org.junit",
        "org.lwjgl",
        "org.objectweb",
        "org.polyfrost",
        "org.slf4j",
        "org.spongepowered",
        "org.yaml",
        "paulscode.sound",
        "scala",
        "space.libs",
        "sun.misc",
        "tv.twitch",
        "watson.cli",
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
        "com.mamiyaotaru.chatbubbles",
        "com.mamiyaotaru.jukebox",
        "com.mumfrey.worldeditcui",
        "io.totemo.watson",
        "maya.biomeborders",
        "mnm.mods.linkinfo",
        "net.easymfne.potioncolorizer",
        "net.eq2online.macros",
        "sushen",
        "troy.autofish",
        "troy.chunkborders",
        "watson",
        "xaero.mods",
        "modForge_CameraStudio"
    };

    /** Mod that requires additional transforms */
    public static String[] CONTAINS_BLACKLIST = {
        "TMI"
    };

}
