/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = GuiScreenEvent.class, remap = false)
public class MixinGUIScreenEvent {
    @Final
    @Mutable
    @Shadow
    public GuiScreen gui;

    /**
     * The GuiScreen object generating this event.
     */
    public GuiScreen getGui()
    {
        return gui;
    }
}
