package space.libs.util.mods;

import net.gobbob.mobends.AnimatedEntity;
import net.gobbob.mobends.client.renderer.entity.RenderBendsPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import space.libs.interfaces.IRenderLiving;
import space.libs.util.ModDetector;

public abstract class MoBendsUtils {

    public static <T extends EntityLivingBase> boolean RenderLivingEvent(IRenderLiving<T> renderer, EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (Minecraft.getMinecraft().theWorld == null) {
            return false;
        }
        if (ModDetector.mobends() && entity instanceof EntityPlayer) {
            if (renderer instanceof RenderBendsPlayer) {
                return false;
            }
            AnimatedEntity ae = AnimatedEntity.getByEntity(entity);
            if (ae != null && ae.animate) {
                AbstractClientPlayer player = (AbstractClientPlayer)entity;
                AnimatedEntity.getPlayerRenderer(player).func_76986_a(player, x, y, z, entityYaw, partialTicks);
                return true;
            }
        }
        return false;
    }
}
