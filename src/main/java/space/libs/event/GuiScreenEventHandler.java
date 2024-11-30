package space.libs.event;

import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiScreenEventHandler {

    public static boolean renderingGuiScreen = false;

    public GuiScreenEventHandler() {}

    @SubscribeEvent
    public void onGuiScreenRender(GuiScreenEvent.DrawScreenEvent.Pre event) {
        renderingGuiScreen = true;
    }

    @SubscribeEvent
    public void afterGuiScreenRender(GuiScreenEvent.DrawScreenEvent.Post event) {
        renderingGuiScreen = false;
    }

}
