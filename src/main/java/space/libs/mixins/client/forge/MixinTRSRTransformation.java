/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(value = TRSRTransformation.class, remap = false)
public abstract class MixinTRSRTransformation implements Function<IModelPart, TRSRTransformation> {

    @Shadow
    public abstract Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part);

    @Override
    public TRSRTransformation apply(IModelPart part) {
        return this.apply(Optional.fromNullable(part)).orNull();
    }

}
