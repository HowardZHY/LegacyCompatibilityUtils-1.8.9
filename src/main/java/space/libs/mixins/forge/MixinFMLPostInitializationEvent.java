/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import com.google.common.base.Throwables;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(FMLPostInitializationEvent.class)
public class MixinFMLPostInitializationEvent {

    public Object buildSoftDependProxy(String modId, String className) {
        if (Loader.isModLoaded(modId))
            try {
                Class<?> clz = Class.forName(className, true, Loader.instance().getModClassLoader());
                return clz.newInstance();
            } catch (Exception e) {
                Throwables.propagateIfPossible(e);
                return null;
            }
        return null;
    }
}
