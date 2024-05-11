package space.libs.mixins.client;

import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.net.InetAddress;

@SuppressWarnings("unused")
@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    @Shadow
    public static NetworkManager createNetworkManagerAndConnect(InetAddress address, int serverPort, boolean useNativeTransport) {
        throw new AbstractMethodError("Shadow");
    }

    @Public
    private static NetworkManager func_150726_a(InetAddress address, int serverPort) {
        return createNetworkManagerAndConnect(address, serverPort, false);
    }

}
