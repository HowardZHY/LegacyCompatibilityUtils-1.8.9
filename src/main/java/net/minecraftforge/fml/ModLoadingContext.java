/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.fml;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.config.ModConfig;

import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class ModLoadingContext
{
    public static ThreadLocal<ModLoadingContext> context = ThreadLocal.withInitial(ModLoadingContext::new);

    public Map<ExtensionPoint, Supplier<?>> extensionPoints = new IdentityHashMap<>();

    public EnumMap<ModConfig.Type, ModConfig> configs = new EnumMap<>(ModConfig.Type.class);

    private Object languageExtension;

    public static ModLoadingContext get() {
        return context.get();
    }

    private ModContainer activeContainer = Loader.instance().activeModContainer();

    public void setActiveContainer(final ModContainer container, final Object languageExtension) {
        this.activeContainer = container;
        this.languageExtension = languageExtension;
    }

    public ModContainer getActiveContainer() {
        return activeContainer;
    }

    public String getActiveNamespace() {
        return activeContainer == null ? "minecraft" : activeContainer.getModId();
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getCustomExtension(ExtensionPoint<T> point) {
        return Optional.ofNullable((T)extensionPoints.getOrDefault(point,()-> null).get());
    }

    public void addConfig(final ModConfig modConfig) {
        configs.put(modConfig.getType(), modConfig);
    }

    /**
     * Register an ExtensionPoint with the mod container.
     * @param point The extension point to register
     * @param extension An extension operator
     * @param <T> The type signature of the extension operator
     */
    public <T> void registerExtensionPoint(ExtensionPoint<T> point, Supplier<T> extension) {
        extensionPoints.put(point, extension);
    }

    public void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {
        this.addConfig(new ModConfig(type, spec, getActiveContainer()));
    }

    public void registerConfig(ModConfig.Type type, ForgeConfigSpec spec, String fileName) {
        this.addConfig(new ModConfig(type, spec, getActiveContainer(), fileName));
    }


    @SuppressWarnings("unchecked")
    public <T> T extension() {
        return (T)languageExtension;
    }
}
