/*
 * The MIT License (MIT)
 * Copyright (c) 2014 ChickenBones
 */
package space.libs.mixins.mods.nei;

import codechicken.nei.SpawnerRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = SpawnerRenderer.class, remap = false)
public class MixinSpawnerRenderer {

    private boolean bossHasColorModifier;

    /**
     * @author HowardZHY
     * @reason Remove problematic rendering
     */
    @Overwrite
    public void renderItem(ItemStack stack) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;
        IBakedModel baseModel = mc.getRenderItem().getItemModelMesher().getModelManager().getModel(new ModelResourceLocation("mob_spawner"));
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5, 0.5, 0.5);
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        mc.getRenderItem().renderItem(stack, baseModel);
        GlStateManager.popMatrix();
    }
}
