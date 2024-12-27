package space.libs.util.mods;

import net.gobbob.mobends.AnimatedEntity;
import net.gobbob.mobends.client.renderer.entity.RenderBendsPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import space.libs.util.ModDetector;

public abstract class MoBendsUtils {

    public static boolean RenderLivingEvent(RendererLivingEntity<?> renderer, EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (Minecraft.getMinecraft().theWorld == null) {
            return false;
        }
        if (ModDetector.hasMobends == 2 && entity instanceof EntityPlayer) {
            if (renderer instanceof RenderBendsPlayer) {
                return false;
            }
            AnimatedEntity ae = AnimatedEntity.getByEntity(entity);
            if (ae != null && ae.animate) {
                AbstractClientPlayer player = (AbstractClientPlayer)entity;
                AnimatedEntity.getPlayerRenderer(player).func_76986_a((EntityLivingBase) player, x, y, z, entityYaw, partialTicks);
                return true;
            }
        }
        return false;
    }
}
