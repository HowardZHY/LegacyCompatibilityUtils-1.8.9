package space.libs.mixins.entity;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ServersideAttributeMap.class)
public abstract class MixinServersideAttributeMap {

    @Shadow
    public abstract ModifiableAttributeInstance getAttributeInstance(IAttribute attribute);

    @Shadow
    public abstract ModifiableAttributeInstance getAttributeInstanceByName(String attributeName);

    public ModifiableAttributeInstance func_180795_e(IAttribute attribute) {
        return this.getAttributeInstance(attribute);
    }

    public ModifiableAttributeInstance func_180796_b(String name) {
        return this.getAttributeInstanceByName(name);
    }
}
