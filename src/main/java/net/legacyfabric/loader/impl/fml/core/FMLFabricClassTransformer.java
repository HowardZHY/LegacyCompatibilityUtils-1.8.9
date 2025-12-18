package net.legacyfabric.loader.impl.fml.core;

import net.fabricmc.loader.impl.game.minecraft.launchwrapper.FabricClassTransformer;
import net.minecraft.launchwrapper.IClassTransformer;

public class FMLFabricClassTransformer extends FabricClassTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name == null || bytes == null) {
            return bytes;
        } else {
            if (name.startsWith("$w")) {
                return bytes; // Ignore $wrappers
            }
            return super.transform(name, transformedName, bytes);
        }
    }
}
