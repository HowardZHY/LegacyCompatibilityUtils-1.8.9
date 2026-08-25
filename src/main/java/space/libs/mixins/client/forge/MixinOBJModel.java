/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.client.model.obj.OBJModel;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(value = OBJModel.class, remap = false)
public abstract class MixinOBJModel implements IModelPart {

    public Gson GSON = (new GsonBuilder()).create();

    public TRSRTransformation getDefaultState() {
        return TRSRTransformation.identity();
    }
}
