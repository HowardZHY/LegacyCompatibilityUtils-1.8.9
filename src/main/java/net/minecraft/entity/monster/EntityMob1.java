package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

@SuppressWarnings("unused")
public class EntityMob1 implements Predicate<Object> {

    public final EntityMob field_179912_a;

    public EntityMob1(EntityMob mob) {
        this.field_179912_a = mob;
    }

    public boolean func_179911_a(Entity creeper) {
        return creeper instanceof EntityCreeper && ((EntityCreeper) creeper).getCreeperState() > 0;
    }

    @Override
    public boolean apply(Object input) {
        return this.func_179911_a((Entity) input);
    }
}

