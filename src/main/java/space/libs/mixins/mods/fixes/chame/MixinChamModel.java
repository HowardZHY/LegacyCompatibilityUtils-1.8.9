/*
 * The MIT License (MIT)
 * Copyright (c) 2014 Justin Aquadro
 */
package space.libs.mixins.mods.fixes.chame;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.spongepowered.asm.mixin.*;

import java.util.List;

@Pseudo
@Mixin(targets = "com.jaquadro.minecraft.chameleon.model.ChamModel", remap = false)
public class MixinChamModel {

    @Shadow
    private static @Final List<BakedQuad> EMPTY;

    @Shadow
    private List<BakedQuad>[] solidCache;

    @Shadow
    private List<BakedQuad>[] cutoutCache;

    @Shadow
    private List<BakedQuad>[] mippedCache;

    @Shadow
    private List<BakedQuad>[] transCache;

    /**
     * @author HowardZHY
     * @reason Fix NPE
     */
    @Overwrite
    public List<BakedQuad> func_177551_a(EnumFacing facing) {
        int index = (facing != null) ? facing.getIndex() : 6;
        EnumWorldBlockLayer renderLayer = MinecraftForgeClient.getRenderLayer();
        if (renderLayer == null) {
            return (solidCache != null) ? solidCache[index] : EMPTY;
        } else {
            switch (renderLayer) {
                case SOLID:
                    return (solidCache != null) ? solidCache[index] : EMPTY;
                case CUTOUT:
                    return (cutoutCache != null) ? cutoutCache[index] : EMPTY;
                case CUTOUT_MIPPED:
                    return (mippedCache != null) ? mippedCache[index] : EMPTY;
                case TRANSLUCENT:
                    return (transCache != null) ? transCache[index] : EMPTY;
                default:
                    return EMPTY;
            }
        }
    }

    /**
     * @author HowardZHY
     * @reason Fix NPE
     */
    @Overwrite
    public List<BakedQuad> func_177550_a() {
        EnumWorldBlockLayer renderLayer = MinecraftForgeClient.getRenderLayer();
        if (renderLayer == null) {
            return (solidCache != null) ? solidCache[6] : EMPTY;
        } else {
            switch (renderLayer) {
                case SOLID:
                    return (solidCache != null) ? solidCache[6] : EMPTY;
                case CUTOUT:
                    return (cutoutCache != null) ? cutoutCache[6] : EMPTY;
                case CUTOUT_MIPPED:
                    return (mippedCache != null) ? mippedCache[6] : EMPTY;
                case TRANSLUCENT:
                    return (transCache != null) ? transCache[6] : EMPTY;
                default:
                    return EMPTY;
            }
        }
    }
}
