/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package space.libs.util.client;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiIngameModOptions extends GuiScreen {

    public final GuiScreen parentScreen;

    public String title = "Mod Options";

    public GuiModOptionList optionList;

    public GuiIngameModOptions(GuiScreen parentScreen)
    {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        this.optionList=new GuiModOptionList(this);
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // force a non-transparent background
        this.drawDefaultBackground();
        this.optionList.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public FontRenderer getFontRenderer() {
        return fontRendererObj;
    }

}
