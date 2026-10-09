/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.interfaces;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.RegistryDelegate;
import org.spongepowered.asm.mixin.*;

@Mixin(value = RegistryDelegate.class, remap = false)
public interface MixinIRegistryDelegate {

    @Shadow
    String name();

    /**
     * @author HowardZHY
     * @reason Default Abstract Method
     */
    @SuppressWarnings("OverwriteModifiers")
    @Overwrite
    default ResourceLocation getResourceName() {
        return new ResourceLocation(name());
    }
}
