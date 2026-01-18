/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge.event;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = RenderGameOverlayEvent.class, remap = false)
public abstract class MixinRenderGameOverlayEvent {

    @Shadow
    public @Final float partialTicks;

    @Shadow
    public @Final ScaledResolution resolution;

    @Shadow
    public @Final RenderGameOverlayEvent.ElementType type;

    public float getPartialTicks() {
        return partialTicks;
    }

    public ScaledResolution getResolution() {
        return resolution;
    }

    public RenderGameOverlayEvent.ElementType getType() {
        return type;
    }
}
