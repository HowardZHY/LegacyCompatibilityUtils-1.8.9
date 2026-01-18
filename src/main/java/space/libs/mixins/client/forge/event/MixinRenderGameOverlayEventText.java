/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge.event;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Mixin(value = RenderGameOverlayEvent.Text.class, remap = false)
public abstract class MixinRenderGameOverlayEventText {

    @Shadow
    public @Final ArrayList<String> left;

    @Shadow
    public @Final ArrayList<String> right;

    public ArrayList<String> getLeft() {
        return left;
    }

    public ArrayList<String> getRight() {
        return right;
    }
}
