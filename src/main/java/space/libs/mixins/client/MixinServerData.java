package space.libs.mixins.client;

import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(ServerData.class)
public class MixinServerData {

    @ShadowConstructor
    public void ServerData(String name, String ip, boolean isLan) {}

    @NewConstructor
    public void ServerData(String name, String ip) {
        this.ServerData(name, ip, false);
    }

}
