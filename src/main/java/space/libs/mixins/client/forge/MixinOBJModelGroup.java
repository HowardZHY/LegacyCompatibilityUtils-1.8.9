/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.common.base.Optional;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.client.model.obj.OBJModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.LinkedHashSet;

@Mixin(value = OBJModel.Group.class, remap = false)
public abstract class MixinOBJModelGroup {

    @Shadow
    public abstract LinkedHashSet<OBJModel.Face> applyTransform(Optional<TRSRTransformation> transform);

    public LinkedHashSet<OBJModel.Face> applyTransform(TRSRTransformation transform) {
        return this.applyTransform(Optional.fromNullable(transform));
    }
    
}
