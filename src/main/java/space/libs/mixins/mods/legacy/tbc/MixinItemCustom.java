package space.libs.mixins.mods.legacy.tbc;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@Pseudo
@Mixin(
    targets = {
        "com.superdextor.thinkbigcore.items.ItemCustomAxe",
        "com.superdextor.thinkbigcore.items.ItemCustomPickaxe",
        "com.superdextor.thinkbigcore.items.ItemCustomShovel",
        "com.superdextor.thinkbigcore.items.ItemCustomSword"
    }, remap = false
)
public abstract class MixinItemCustom extends Item {

    @ShadowConstructor
    public void ItemCustom(Item.ToolMaterial material, PotionEffect potionEffectIn) {}

    @NewConstructor
    public void ItemCustom(Item.ToolMaterial material, Potion potion, int duration) {
        ItemCustom(material, new PotionEffect(potion.id, duration * 20, 0));
    }

}
