/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.forge;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IFluid;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(Fluid.class)
public abstract class MixinFluid implements IFluid {

    @ShadowConstructor
    public void Fluid(String fluidName, ResourceLocation still, ResourceLocation flowing) {}

    @NewConstructor
    public void Fluid(String fluidName) {
        this.Fluid(fluidName, null, null);
    }

    public TextureAtlasSprite stillIcon;

    public TextureAtlasSprite flowingIcon;

    @Shadow(remap = false)
    public abstract String getUnlocalizedName();

    public String getLocalizedName() {
        String s = this.getUnlocalizedName();
        return s == null ? "" : StatCollector.translateToLocal(s);
    }

    public int getSpriteNumber() {
        return 0;
    }

    public Fluid setStillIcon(TextureAtlasSprite stillIcon) {
        this.stillIcon = stillIcon;
        return (Fluid) (Object) this;
    }

    public Fluid setFlowingIcon(TextureAtlasSprite flowingIcon) {
        this.flowingIcon = flowingIcon;
        return (Fluid) (Object) this;
    }

    public Fluid setIcons(TextureAtlasSprite stillIcon, TextureAtlasSprite flowingIcon) {
        IFluid accessor = (IFluid) this.setStillIcon(stillIcon);
        return accessor.setFlowingIcon(flowingIcon);
    }

    public Fluid setIcons(TextureAtlasSprite commonIcon) {
        IFluid accessor = (IFluid) this.setStillIcon(commonIcon);
        return accessor.setFlowingIcon(commonIcon);
    }

    public TextureAtlasSprite getIcon() {
        return this.getStillIcon();
    }

    public TextureAtlasSprite getStillIcon() {
        return this.stillIcon;
    }

    public TextureAtlasSprite getFlowingIcon() {
        return this.flowingIcon;
    }

    public TextureAtlasSprite getIcon(FluidStack stack) {
        return this.getIcon();
    }

    public TextureAtlasSprite getIcon(World world, BlockPos pos) {
        return this.getIcon();
    }
}
