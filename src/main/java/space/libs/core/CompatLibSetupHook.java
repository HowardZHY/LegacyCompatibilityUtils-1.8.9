package space.libs.core;

import net.minecraftforge.fml.relauncher.IFMLCallHook;

import java.util.Map;

@SuppressWarnings("unused")
public class CompatLibSetupHook implements IFMLCallHook {

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public Void call() throws Exception {
        CompatLibCore.LOGGER.info("Mods calling setup hooks...");
        return null;
    }
}
