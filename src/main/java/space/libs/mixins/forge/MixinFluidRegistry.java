/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.Map;

@Mixin(value = FluidRegistry.class, remap = false)
public abstract class MixinFluidRegistry {

    @Shadow
    static BiMap<Fluid, Integer> fluidIDs;

    @Shadow
    static BiMap<Integer, String> fluidNames;

    @SuppressWarnings("all")
    @Public
    private static int renderIdFluid = -1;

    @Public
    private static String getFluidName(int fluidID) {
        return fluidNames.get(fluidID);
    }

    @Public
    private static Map<Fluid, Integer> getRegisteredFluidIDsByFluid() {
        return ImmutableMap.copyOf(fluidIDs);
    }
}
