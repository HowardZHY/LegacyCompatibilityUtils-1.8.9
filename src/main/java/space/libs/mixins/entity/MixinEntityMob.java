package space.libs.mixins.entity;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(EntityMob.class)
public class MixinEntityMob {

    /** aiAvoidExplodingCreepers */
    public EntityAIBase field_175455_a; // = new EntityAIAvoidEntity(this, new EntityMob$1(this), 4.0F, 1.0, 2.0); This will cause bugs if implemented

}
