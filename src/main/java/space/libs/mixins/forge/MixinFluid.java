/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = Fluid.class, remap = false)
public abstract class MixinFluid {

    @ShadowConstructor
    public void Fluid(String fluidName, ResourceLocation still, ResourceLocation flowing) {}

    @NewConstructor
    public void Fluid(String fluidName) {
        this.Fluid(fluidName, null, null);
    }

    @Shadow
    public abstract String getUnlocalizedName();

    public String getLocalizedName() {
        String s = this.getUnlocalizedName();
        return s == null ? "" : StatCollector.translateToLocal(s);
    }

    public int getSpriteNumber() {
        return 0;
    }

}
