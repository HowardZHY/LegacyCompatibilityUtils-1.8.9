package net.minecraft.client.particle;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.World;

public class ParticleManager extends EffectRenderer {
    public ParticleManager(World worldIn, TextureManager rendererIn) {
        super(worldIn, rendererIn);
    }
}
