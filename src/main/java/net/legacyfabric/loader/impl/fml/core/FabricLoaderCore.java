package net.legacyfabric.loader.impl.fml.core;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class FabricLoaderCore implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        //FabricLoaderTweaker.instance.injectIntoClassLoader(Launch.classLoader);
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
