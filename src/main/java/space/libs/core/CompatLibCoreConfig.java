package space.libs.core;

import space.libs.util.UnsafeUtils;

public class CompatLibCoreConfig {

    public static void init() {
        if (Boolean.parseBoolean(System.getProperty("compatlib.NSM"))) {
            UnsafeUtils.removeFMLSecurityManager();
        }
    }
}
