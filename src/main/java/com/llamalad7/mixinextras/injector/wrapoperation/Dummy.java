package com.llamalad7.mixinextras.injector.wrapoperation;

import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import space.libs.core.CompatLibCore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class Dummy {

    public Dummy() {
        //init();
    }

    public static void init() {
        try {
            removeClassFromClasspath(Launch.classLoader, "com.llamalad7.mixinextras.injector.wrapoperation.WrapOperationInjector");
            fixMixinClasspathOrder();
            Launch.classLoader.loadClass("com.llamalad7.mixinextras.injector.wrapoperation.WrapOperationInjector");
            //CompatLibCore.LOGGER.info(WrapOperationInjector.Dummy());
            //Launch.classLoader.addClassLoaderExclusion("com.llamalad7.mixinextras.injector.wrapoperation.WrapOperationInjector");
        } catch (Exception e) {
            CompatLibCore.LOGGER.error(e);
            e.printStackTrace();
        }
    }

    private static void fixMixinClasspathOrder() {
        // Borrowed from VanillaFix -- Move jar up in the classloader's URLs to make sure that the latest version of Mixin is used
        URL url = Dummy.class.getProtectionDomain().getCodeSource().getLocation();
        givePriorityInClasspath(url, Launch.classLoader);
    }

    private static void givePriorityInClasspath(URL url, URLClassLoader classLoader) {
        try {
            Field ucpField = URLClassLoader.class.getDeclaredField("ucp");
            ucpField.setAccessible(true);
            final Method rawUrlsGetter = ucpField.getType().getDeclaredMethod("getURLs");
            rawUrlsGetter.setAccessible(true);
            URL[] originalUrls = ((URLClassLoader) classLoader).getURLs();
            List<URL> urls = new ArrayList<>(Arrays.asList(originalUrls));
            urls.remove(url);
            urls.add(0, url);
            final URLClassLoader dummyLoader = new URLClassLoader(urls.toArray(new URL[0]), classLoader);
            final Object newUcp = ucpField.get(dummyLoader);
            ucpField.set(classLoader, newUcp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeClassFromClasspath(LaunchClassLoader classLoader, String className) {
        try {
            Field field = LaunchClassLoader.class.getDeclaredField("sources");
            field.setAccessible(true);
            List<URL> sources = (List<URL>) field.get(classLoader);
            sources.removeIf(url -> url.getPath().contains(className.replace('.', '/')));
            Field cachedClassesField = LaunchClassLoader.class.getDeclaredField("cachedClasses");
            cachedClassesField.setAccessible(true);
            ((Map<String, Class<?>>) cachedClassesField.get(classLoader)).remove(className);
        } catch (Exception e) {
            CompatLibCore.LOGGER.error(e);
        }
    }
}
