/*
 * Copyright (c) LoliASM
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.util;

import space.libs.core.CompatLibCore;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@SuppressWarnings("all")
public class UnsafeUtils {

    public static Class<?> TYPE;

    public static Object UNSAFE;

    public static void init() {
        Object instance = null;
        try {
            TYPE = Class.forName("sun.misc.Unsafe");
            final Field field = TYPE.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            instance = field.get(null);
        } catch (Exception ignored) {
            try {
                Constructor<?> ctor = TYPE.getDeclaredConstructor();
                ctor.setAccessible(true);
                instance = ctor.newInstance();
            } catch (Exception e) {
                CompatLibCore.LOGGER.warn("Failed to get Unsafe.");
            }
        }
        if (instance != null) {
            UNSAFE = instance;
        }
    }

    public static void removeFMLSecurityManager() {
        init();
        if (TYPE == null || UNSAFE == null) {
            return;
        }
        CompatLibCore.LOGGER.warn("Detaching FMLSecurityManager.");
        try {
            Field out = System.class.getDeclaredField("out");
            Field err = System.class.getDeclaredField("err");
            out.setAccessible(true);
            err.setAccessible(true);
            long errOffset = (long) TYPE.getDeclaredMethod("staticFieldOffset", Field.class).invoke(UNSAFE, err);
            long offset = errOffset + (errOffset - (long) TYPE.getDeclaredMethod("staticFieldOffset", Field.class).invoke(UNSAFE, out));
            TYPE.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class)
                .invoke(UNSAFE, TYPE.getDeclaredMethod("staticFieldBase", Field.class).invoke(UNSAFE, err), offset, null);
        } catch (Exception e) {
            CompatLibCore.LOGGER.error("Failed to access for FMLSecurityManager removal: " + e);
        }
        if (System.getSecurityManager() != null) {
            CompatLibCore.LOGGER.warn("Failed to detach FMLSecurityManager.");
        }
    }
}
