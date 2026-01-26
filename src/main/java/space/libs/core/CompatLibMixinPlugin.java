package space.libs.core;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import space.libs.util.ModDetector;
import space.libs.util.cursedmixinextensions.CursedMixinExtensions;

import java.util.List;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
public class CompatLibMixinPlugin implements IMixinConfigPlugin {

    public static final MixinEnvironment.Side SIDE = MixinEnvironment.getCurrentEnvironment().getSide();

    @Override
    public void onLoad(String mixinPackage) {
        ModDetector.FixCoremods();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if ("space.libs.mixins.client.render.MixinWorldRendererVanilla".equals(mixinClassName)) {
            return !ModDetector.getCoreMod("meldexun.nothirium.mc.asm.NothiriumPlugin", false);
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, org.objectweb.asm.tree.ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, org.objectweb.asm.tree.ClassNode classNode, String s1, IMixinInfo iMixinInfo) {
        CursedMixinExtensions.postApply(classNode);
    }
}
