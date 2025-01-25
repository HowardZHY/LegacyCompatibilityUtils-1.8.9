/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = RenderWorldLastEvent.class, remap = false)
public class MixinRenderWorldLastEvent {


    @Shadow
    public @Final RenderGlobal context;

    @Shadow
    public @Final float partialTicks;

    public RenderGlobal getContext() {
        return context;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
