/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.collect.Maps;
import net.minecraft.item.*;
import space.libs.interfaces.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.IdentityHashMap;

@SuppressWarnings("all")
@Mixin(value = MinecraftForgeClient.class, remap = false)
public class MixinMinecraftForgeClient {

    @Public
    private static IdentityHashMap<Item, IItemRenderer> customItemRenderers = Maps.newIdentityHashMap();

    @Public
    private static void registerItemRenderer(Item item, IItemRenderer renderer) {
        customItemRenderers.put(item, renderer);
    }

    @Public
    private static IItemRenderer getItemRenderer(ItemStack item, IItemRenderer.ItemRenderType type) {
        IItemRenderer renderer = customItemRenderers.get(item.getItem());
        if (renderer != null && renderer.handleRenderType(item, type)) {
            return renderer;
        }
        return null;
    }
}
