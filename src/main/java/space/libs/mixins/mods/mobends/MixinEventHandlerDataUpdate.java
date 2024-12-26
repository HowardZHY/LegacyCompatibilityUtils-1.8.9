package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.data.*;
import net.gobbob.mobends.event.EventHandler_DataUpdate;
import net.gobbob.mobends.util.BendsLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.util.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = EventHandler_DataUpdate.class, remap = false)
public abstract class MixinEventHandlerDataUpdate {

    @Public
    private static float lastNotedRenderTick = 0.0f;

    @Public
    private static float lastNotedClientTick = 0.0f;

    private static Minecraft mc;

    /**
     * @author HowardZHY
     * @reason Try Fix
     */
    @Inject(method = "onClientTick", at = @At("HEAD"), cancellable = true)
    public void onClientTick(TickEvent.ClientTickEvent event, CallbackInfo ci) {
        if (event.phase == TickEvent.Phase.START) {
            this.ClientTick(event);
        }
        ci.cancel();
    }

    public void ClientTick(TickEvent.ClientTickEvent event) {
        mc = Minecraft.getMinecraft();
        if (mc.theWorld == null) {
            return;
        }
        for (int i = 0; i < Data_Player.dataList.size(); i++) {
            Data_Player data = Data_Player.dataList.get(i);
            Entity entity = mc.theWorld.getEntityByID(data.entityID);
            if (entity != null) {
                if (!data.entityType.equalsIgnoreCase(entity.getName())) {
                    Data_Player.dataList.remove(data);
                    Data_Player.add(new Data_Player(entity.getEntityId()));
                    BendsLogger.log("Reset EntityPlayer:" + data.entityID, BendsLogger.DEBUG);
                } else {
                    data.motion_prev.set(data.motion);
                    data.motion.x = (float) entity.posX - data.position.x;
                    data.motion.y = (float) entity.posY - data.position.y;
                    data.motion.z = (float) entity.posZ - data.position.z;
                    data.position = new Vector3f((float) entity.posX, (float) entity.posY, (float) entity.posZ);
                }
            } else {
                Data_Player.dataList.remove(data);
                BendsLogger.log("No EntityPlayer:" + data.entityID, BendsLogger.DEBUG);
            }
        }
        for (int j = 0; j < Data_Zombie.dataList.size(); j++) {
            Data_Zombie data = Data_Zombie.dataList.get(j);
            Entity entity = mc.theWorld.getEntityByID(data.entityID);
            if (entity != null) {
                if (!data.entityType.equalsIgnoreCase(entity.getName())) {
                    Data_Zombie.dataList.remove(data);
                    Data_Zombie.add(new Data_Zombie(entity.getEntityId()));
                    BendsLogger.log("Reset EntityZombie:" + data.entityID, BendsLogger.DEBUG);
                } else {
                    data.motion_prev.set(data.motion);
                    data.motion.x = (float) entity.posX - data.position.x;
                    data.motion.y = (float) entity.posY - data.position.y;
                    data.motion.z = (float) entity.posZ - data.position.z;
                    data.position = new Vector3f((float) entity.posX, (float) entity.posY, (float) entity.posZ);
                }
            } else {
                Data_Zombie.dataList.remove(data);
                BendsLogger.log("No EntityZombie:" + data.entityID, BendsLogger.DEBUG);
            }
        }
        for (int k = 0; k < Data_Spider.dataList.size(); k++) {
            Data_Spider data = Data_Spider.dataList.get(k);
            Entity entity = mc.theWorld.getEntityByID(data.entityID);
            if (entity != null) {
                if (!data.entityType.equalsIgnoreCase(entity.getName())) {
                    Data_Spider.dataList.remove(data);
                    Data_Spider.add(new Data_Spider(entity.getEntityId()));
                } else {
                    data.motion_prev.set(data.motion);
                    data.motion.x = (float) entity.posX - data.position.x;
                    data.motion.y = (float) entity.posY - data.position.y;
                    data.motion.z = (float) entity.posZ - data.position.z;
                    data.position = new Vector3f((float) entity.posX, (float) entity.posY, (float) entity.posZ);
                }
            } else {
                Data_Spider.dataList.remove(data);
            }
        }
    }
}
