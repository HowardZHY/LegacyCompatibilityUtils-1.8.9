package space.libs.forge.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class EntityRendererInfo {

    public Class<? extends Entity> target;

    public Render<?> renderer;

    public EntityRendererInfo(Class<? extends Entity> target, Render<?> renderer) {
        this.target = target;
        this.renderer = renderer;
    }
}
