package space.libs.mixins.mods.legacy.mobends;

import net.gobbob.mobends.AnimatedEntity;
import net.gobbob.mobends.client.ClientProxy;
import net.gobbob.mobends.event.EventHandler_DataUpdate;
import net.gobbob.mobends.event.EventHandler_Keyboard;
import net.gobbob.mobends.event.EventHandler_RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = ClientProxy.class, remap = false)
public class MixinClientProxy {

    /**
     * @author HowardZHY
     * @reason Try Fix
     */
    @Overwrite
    public void init(Configuration config) {
        AnimatedEntity.register(config);
        ClientRegistry.registerKeyBinding(EventHandler_Keyboard.key_Menu);
        FMLCommonHandler.instance().bus().register(new EventHandler_RenderPlayer());
        FMLCommonHandler.instance().bus().register(new EventHandler_DataUpdate());
        FMLCommonHandler.instance().bus().register(new EventHandler_Keyboard());
        MinecraftForge.EVENT_BUS.register(new EventHandler_RenderPlayer());
    }
}
