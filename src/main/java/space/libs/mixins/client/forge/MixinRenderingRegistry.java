/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.collect.Lists;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.forge.client.EntityRendererInfo;
import space.libs.interfaces.IRenderingRegistry;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.List;

@Mixin(value = RenderingRegistry.class, remap = false)
public abstract class MixinRenderingRegistry implements IRenderingRegistry {

    @Shadow
    private static @Final RenderingRegistry INSTANCE;

    @Public
    private static RenderingRegistry instance() {
        return INSTANCE;
    }

    public List<EntityRendererInfo> entityRenderers = Lists.newArrayList();

    @Override
    public List<EntityRendererInfo> GetEntityRenderers() {
        return entityRenderers;
    }

    @Inject(method = "registerEntityRenderingHandler(Ljava/lang/Class;Lnet/minecraft/client/renderer/entity/Render;)V", at = @At("RETURN"))
    private static void registerEntityRenderingHandler(Class<? extends Entity> entityClass, Render<? extends Entity> renderer, CallbackInfo ci) {
        ((IRenderingRegistry) INSTANCE).GetEntityRenderers().add(new EntityRendererInfo(entityClass, renderer));
    }
}
