package space.libs.mixins.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityMob1;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@Mixin(EntityMob.class)
public abstract class MixinEntityMob extends EntityCreature {

    public MixinEntityMob(World worldIn) {
        super(worldIn);
    }

    @MappedName("aiAvoidExplodingCreepers")
    public EntityAIBase field_175455_a; // = new EntityAIAvoidEntity(this, new EntityMob$1(this), 4.0F, 1.0, 2.0);

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(World worldIn, CallbackInfo ci) {
        EntityMob This = (EntityMob) (Object) this;
        if (!This.getClass().getName().startsWith("net.minecraft.")) {
            this.field_175455_a = new EntityAIAvoidEntity<>(This, EntityCreeper.class, new EntityMob1(This), 4.0F, 1.0, 2.0);
        }
    }
}
