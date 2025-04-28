/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@SuppressWarnings("all")
@Mixin(value = GuiModList.class, remap = false)
public class MixinGuiModList {

    private ResourceLocation cachedLogo;

    private Dimension cachedLogoDimensions;

    /**
     * @reason Dummy
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        this.cachedLogo = new ResourceLocation("pack.png");
        this.cachedLogoDimensions = new Dimension(0, 0);
    }

}
