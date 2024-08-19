/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IFluid;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.Map;

@SuppressWarnings("all")
@Mixin(FluidRegistry.class)
public abstract class MixinFluidRegistry {

    @Shadow(remap = false)
    static BiMap<String, Fluid> fluids;

    @Shadow(remap = false)
    static BiMap<Fluid, Integer> fluidIDs;

    @Shadow(remap = false)
    static BiMap<Integer, String> fluidNames;

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

    @Public
    private static void onTextureStitchedPre(TextureMap map) {
        for (Fluid fluid : fluids.values()) {
            IFluid accessor = (IFluid) fluid;
            if (fluid.getStill() != null) {
                TextureAtlasSprite still = map.registerSprite(fluid.getStill());
                accessor.setStillIcon(still);
            }
            if (fluid.getFlowing() != null) {
                TextureAtlasSprite flowing = map.registerSprite(fluid.getFlowing());
                accessor.setStillIcon(flowing);
            }
        }
    }
}
