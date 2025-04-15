/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraftforge.common.BiomeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(value = BiomeManager.class, priority = 2020, remap = false)
public class MixinBiomeManager {

    @Public
    private static boolean isModded = false;

    @Inject(method = "addBiome", at = @At("HEAD"))
    private static void addBiome(BiomeManager.BiomeType type, BiomeManager.BiomeEntry entry, CallbackInfo ci) {
        isModded = true;
    }
}
