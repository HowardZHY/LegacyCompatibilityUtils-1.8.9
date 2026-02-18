package space.libs.core;

import space.libs.util.UnsafeUtils;

public class CompatLibCoreConfig {

    public static boolean NSM = false;

    public static boolean DummyModID = false;

    public static void init() {
        NSM = Boolean.parseBoolean(System.getProperty("compatlib.NSM"));
        DummyModID = Boolean.parseBoolean(System.getProperty("compatlib.DummyModID"));
        if (NSM) {
            UnsafeUtils.removeFMLSecurityManager();
        }
    }
}
