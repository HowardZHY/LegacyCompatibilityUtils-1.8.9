/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@Mixin(value = RenderingRegistry.class, remap = false)
public class MixinRenderingRegistry {

    @Shadow
    private static @Final RenderingRegistry INSTANCE;

    @Public
    private static RenderingRegistry instance() {
        return INSTANCE;
    }

}
