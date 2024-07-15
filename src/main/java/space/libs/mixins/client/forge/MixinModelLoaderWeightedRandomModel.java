/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.MapModelState;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraftforge.client.model.ModelLoader$WeightedRandomModel")
public class MixinModelLoaderWeightedRandomModel implements IModelPart {

    public IModelState getState(IModelState state, IModelPart part) {
        if (state instanceof MapModelState) {
            return ((MapModelState)state).getState(part);
        }
        return state;
    }
}
