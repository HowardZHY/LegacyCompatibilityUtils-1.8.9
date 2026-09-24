/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.MapModelState;
import net.minecraftforge.client.model.ModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraftforge.client.model.ModelLoader$WeightedRandomModel", remap = false)
public class MixinModelLoaderWeightedRandomModel implements IModelPart {

    @ShadowConstructor
    public void WeightedRandomModel(ModelLoader outer, ModelResourceLocation parent, ModelBlockDefinition.Variants variants) {}

    @NewConstructor
    public void WeightedRandomModel(ModelLoader outer, ModelBlockDefinition.Variants variants) {
        WeightedRandomModel(outer, null, variants);
    }

    public IModelState getState(IModelState state, IModelPart part) {
        if (state instanceof MapModelState) {
            return ((MapModelState)state).getState(part);
        }
        return state;
    }
}
