/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.client.model;

import java.util.Map;

import com.google.common.base.Optional;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * IModelState that can change depending on the perspective.
 */
@SuppressWarnings("all")
public interface IPerspectiveState extends IModelState {

    /**
     * @return the additional state that needs to be applied for each part when in given perspective type.
     */
    public IModelState forPerspective(TransformType type);

    public static class Impl implements IPerspectiveState {

        private final IModelState parent;
        private final ImmutableMap<TransformType, IModelState> states;

        public Impl(IModelState parent, ImmutableMap<TransformType, IModelState> states) {
            this.parent = parent;
            this.states = states;
        }

        public Impl(IModelState parent, ItemCameraTransforms transforms) {
            this(parent, getMap(transforms));
        }

        private static ImmutableMap<TransformType, IModelState> getMap(ItemCameraTransforms transforms) {
            Map<TransformType, IModelState> map = Maps.newHashMap();
            for(TransformType type : TransformType.values()) {
                map.put(type, transforms.getTransform(type));
            }
            return Maps.immutableEnumMap(map);
        }

        public TRSRTransformation apply(IModelPart part) {
            Optional<? extends IModelPart> optionalPart = Optional.fromNullable(part);
            if (this.apply(optionalPart).isPresent()) {
                return this.apply(optionalPart).get();
            }
            System.out.println("[WARN] IModelState apply not Present ?");
            return null;
        }

        public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part) {
            return parent.apply(part);
        }

        public IModelState forPerspective(TransformType type) {
            IModelState state = states.get(type);
            if(state == null) state = TRSRTransformation.identity();
            return state;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this.getClass()).add("parent", parent).add("states", states).toString();
        }
    }
}
