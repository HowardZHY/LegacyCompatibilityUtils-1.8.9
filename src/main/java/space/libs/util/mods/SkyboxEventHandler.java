package space.libs.util.mods;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import vazkii.skybox.ModEventHandler;

public class SkyboxEventHandler {

    public SkyboxEventHandler () {}

    @SubscribeEvent
    public void RenderTick(TickEvent.RenderTickEvent event) {
        ModEventHandler.renderTick(event);
    }

    @SubscribeEvent
    public void ClientTickEnd(TickEvent.ClientTickEvent event) {
        ModEventHandler.clientTickEnd(event);
    }

    @SubscribeEvent
    public void OnRender(RenderWorldLastEvent event) {
        ModEventHandler.onRender(event);
    }
}
