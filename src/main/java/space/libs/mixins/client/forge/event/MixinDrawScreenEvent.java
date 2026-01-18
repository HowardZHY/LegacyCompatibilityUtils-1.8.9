/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge.event;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.*;

@SuppressWarnings("unused")
@Pseudo
@Mixin(value = GuiScreenEvent.DrawScreenEvent.class, remap = false)
public class MixinDrawScreenEvent extends GuiScreenEvent {

    @Final
    @Mutable
    @Shadow
    public int mouseX;

    @Final
    @Mutable
    @Shadow
    public int mouseY;

    public float renderPartialTicks;

    public MixinDrawScreenEvent(GuiScreen gui) {
        super(gui);
    }

    public int getMouseX()
    {
        return mouseX;
    }

    /**
     * The y coordinate of the mouse pointer on the screen.
     */
    public int getMouseY()
    {
        return mouseY;
    }

    /**
     * Partial render ticks elapsed.
     */
    public float getRenderPartialTicks()
    {
        return renderPartialTicks;
    }
}
