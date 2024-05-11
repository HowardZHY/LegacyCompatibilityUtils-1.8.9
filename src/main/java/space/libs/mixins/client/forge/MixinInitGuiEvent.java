/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@SuppressWarnings("unused")
@Mixin(value = GuiScreenEvent.InitGuiEvent.class, remap = false)
public class MixinInitGuiEvent {

    @Shadow
    public List<GuiButton> buttonList;

    public List<GuiButton> getButtonList()
    {
        return buttonList;
    }

    public void setButtonList(List<GuiButton> buttonList)
    {
        this.buttonList = buttonList;
    }
}
