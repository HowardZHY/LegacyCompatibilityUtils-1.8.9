package space.libs.mixins.nbt;

import net.minecraft.nbt.NBTTagByteArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.DataInput;
import java.io.IOException;

@SuppressWarnings("unused")
@Mixin(NBTTagByteArray.class)
public class MixinNBTTagByteArray extends MixinNBTBase {

    @Shadow
    private byte[] data;

    public void func_74735_a(DataInput paramDataInput, int paramInt) {
        try {
            int i = paramDataInput.readInt();
            this.data = new byte[i];
            paramDataInput.readFully(this.data);
        } catch (IOException e) {
            System.out.println("Exception while reading NBT data input : " + e);
        }

    }
}
