/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(GuiIngameForge.class)
public class MixinGuiIngameForge {

    @Public
    private static ResourceLocation WIDGITS = new ResourceLocation("textures/gui/widgets.png");

    @Public
    private static String MC_VERSION = MinecraftForge.MC_VERSION;
}
