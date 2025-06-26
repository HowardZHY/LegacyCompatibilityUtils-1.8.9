package space.libs.mixins.forge;

import com.google.common.base.Strings;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.VersionParser;
import net.minecraftforge.fml.common.versioning.VersionRange;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = FMLModContainer.class, priority = 100, remap = false)
public abstract class MixinFMLModContainer implements ModContainer {

    @Shadow
    private Map<String, Object> descriptor;

    @Shadow
    private VersionRange minecraftAccepted;

    /**
     * @author HowardZHY
     * @reason 1.13+
     */
    @Overwrite
    @Override
    public String getModId() {
        String modid = (String)descriptor.get("modid");
        if (modid == null) {
            modid = (String)descriptor.get("value");
        }
        return modid;
    }

    @SuppressWarnings("all")
    @Inject(method = "bindMetadata", at = @At("TAIL"))
    public void bindMetadata(MetadataCollection collection, CallbackInfo ci) {
        String mcVersionString = (String)descriptor.get("acceptedMinecraftVersions");
        if ((mcVersionString == null) || (mcVersionString != "[1.8.9]")) {
            mcVersionString = "[1.8,)";
        }
        if (!Strings.isNullOrEmpty(mcVersionString)) {
            minecraftAccepted = VersionParser.parseRange(mcVersionString);
        } else {
            minecraftAccepted = Loader.instance().getMinecraftModContainer().getStaticVersionRange();
        }
    }
}
