/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.MapModelState;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(value = MapModelState.class, remap = false)
public abstract class MixinMapModelState implements Function<IModelPart, TRSRTransformation> {

    @Shadow(prefix = "obj$")
    public abstract IModelState obj$getState(Object obj);

    @Shadow
    public abstract Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part);

    public IModelState getState(IModelState state) {
        return this.obj$getState(state);
    }

    @Override
    public TRSRTransformation apply(IModelPart input) {
        return this.apply(Optional.fromNullable(input)).orNull();
    }
}
