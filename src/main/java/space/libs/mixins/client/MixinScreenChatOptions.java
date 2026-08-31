package space.libs.mixins.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenChatOptions.class)
public class MixinScreenChatOptions extends GuiScreen {

    public String field_146398_r;

    public int field_146397_s;

    public int OptionsCount;

    @Inject(method = "initGui", at = @At("HEAD"))
    public void initGui1(CallbackInfo ci) {
        this.field_146398_r = I18n.format("options.multiplayer.title");
    }

    @SuppressWarnings("all")
    @Inject(method = "initGui", at = @At(value = "JUMP", opcode = 167, ordinal = 1))
    public void initGui2(CallbackInfo ci) {
        this.OptionsCount++;
    }

    @Inject(method = "initGui", at = @At("RETURN"))
    public void initGui3(CallbackInfo ci) {
        this.field_146397_s = this.height / 6 + 24 * (this.OptionsCount >> 1);
    }
}
