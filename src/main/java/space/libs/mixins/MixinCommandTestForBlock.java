package space.libs.mixins;

import net.minecraft.command.server.CommandTestForBlock;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTUtil;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(CommandTestForBlock.class)
public class MixinCommandTestForBlock {

    @Public
    private static boolean func_175775_a(NBTBase p_175775_0_, NBTBase p_175775_1_, boolean p_175775_2_) {
        return NBTUtil.func_181123_a(p_175775_0_, p_175775_1_, p_175775_2_);
    }
}
