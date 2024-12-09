/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package space.libs.mixins.client.forge;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.TRSRTransformation;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.core.CompatLibCore;
import space.libs.interfaces.IVertexFormatElement;
import space.libs.util.cursedmixinextensions.annotations.Public;

import javax.vecmath.Vector4f;
import java.nio.ByteBuffer;
import java.util.Arrays;

@SuppressWarnings("unused")
@Mixin(Attributes.class)
public class MixinAttributes {

    @Public
    private static void put(ByteBuffer buf, VertexFormatElement e, boolean normalize, Number fill, Number... ns) {
        if (e.getElementCount() > ns.length && fill == null) throw new IllegalArgumentException("not enough elements");
        Number n;
        for (int i = 0; i < e.getElementCount(); i++) {
            if (i < ns.length) {
                n = ns[i];
            } else {
                n = fill;
            }
            final int o = e.getType().ordinal();
            if (o == 0) {
                buf.put(normalize ? (byte) (n.floatValue() / (Byte.MAX_VALUE - 1)) : n.byteValue());
            } else if (o == 1) {
                buf.put(normalize ? (byte) (n.floatValue() / ((byte) -1)) : n.byteValue());
            } else if (o == 2) {
                buf.putShort(normalize ? (short)(n.floatValue() / (Short.MAX_VALUE - 1)) : n.shortValue());
            } else if (o == 3) {
                buf.putShort(normalize ? (short)(n.floatValue() / ((short) -1)) : n.shortValue());
            } else if (o == 4) {
                buf.putInt(normalize ? (int)(n.doubleValue() / (Integer.MAX_VALUE - 1)) : n.intValue());
            } else if (o == 5) {
                buf.putInt(normalize ? (int)(n.doubleValue() / - 1) : n.intValue());
            } else if (o == 6) {
                buf.putFloat(n.floatValue());
            } else {
                CompatLibCore.LOGGER.error("Unknown VertexFormatElement EnumType:" + o);
            }
        }
    }

    @Public
    private static BakedQuad transform(TRSRTransformation transform, BakedQuad quad, VertexFormat format) {
        for (VertexFormatElement e : format.getElements()) {
            if (e.getUsage() == VertexFormatElement.EnumUsage.POSITION) {
                if (e.getType() != VertexFormatElement.EnumType.FLOAT) {
                    throw new IllegalArgumentException("can only transform float position");
                }
                int[] data = Arrays.copyOf(quad.getVertexData(), (quad.getVertexData()).length);
                int shift = data.length / 4;
                for (int v = 0; v < 4; v++) {
                    float[] pos = { 0.0F, 0.0F, 0.0F, 1.0F };
                    IVertexFormatElement accessor = (IVertexFormatElement) e;
                    for (int i = 0; i < Math.min(4, e.getElementCount()); i++) {
                        pos[i] = Float.intBitsToFloat(data[shift * v + accessor.func_177373_a() / 4 + i]);
                    }
                    Vector4f vec = new Vector4f(pos);
                    transform.getMatrix().transform(vec);
                    vec.get(pos);
                    for (int j = 0; j < Math.min(4, e.getElementCount()); j++) {
                        data[shift * v + accessor.func_177373_a() / 4 + j] = Float.floatToRawIntBits(pos[j]);
                    }
                }
                return new BakedQuad(data, quad.getTintIndex(), quad.getFace());
            }
        }
        return quad;
    }
}
