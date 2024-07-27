package space.libs.mixins.mods.mobends;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;

@SuppressWarnings("unused")
@Pseudo
@Mixin(targets = "net.gobbob.mobends.client.model.entity.ModelBendsPlayer")
public class MixinModelBendsPlayer {

    @Dynamic
    @WrapOperation(
        method = "func_78087_a",
        constant = @Constant(classValue = ItemPickaxe.class, ordinal = 0),
        remap = false
    )
    private boolean setRotationAngles(Object o, Operation<Boolean> original) {
        return (original.call(o) || o instanceof ItemSword);
    }
}
