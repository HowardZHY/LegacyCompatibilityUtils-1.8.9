package space.libs.core;

import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import space.libs.util.ModDetector;
import space.libs.util.cursedmixinextensions.CursedMixinExtensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CompatLibLateMixinPlugin implements IMixinConfigPlugin {

    public static final MixinEnvironment.Side SIDE = MixinEnvironment.getCurrentEnvironment().getSide();

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("ichun")) {
            return Loader.isModLoaded("iChunUtil");
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();
        if (SIDE == MixinEnvironment.Side.CLIENT) {
            if (ModDetector.hasNEI) {
                CompatLibCore.LOGGER.info("Adding NEI Fix.");
                mixins.add("nei.MixinSpawnerRenderer");
            }
            if (Loader.isModLoaded("CameraStudio")) {
                CompatLibCore.LOGGER.info("Adding CameraStudio Fixes.");
                mixins.add("cs.MixinCameraStudioLoader");
                mixins.add("cs.MixinCameraStudioLogger");
                mixins.add("cs.MixinCameraStudioPrivateFields");
                mixins.add("cs.MixinCameraStudioRenderCamera");
                mixins.add("cs.MixinCameraStudioRenderCameraFOV");
            }
            if (Loader.isModLoaded("mobends")) {
                ModDetector.hasMobends = true;
                CompatLibCore.LOGGER.info("Adding MoBends Fixes.");
                mixins.add("mobends.MixinBendsLogger");
                mixins.add("mobends.MixinClientProxy");
                mixins.add("mobends.MixinEventHandlerDataUpdate");
                mixins.add("mobends.MixinEventHandlerRenderPlayer");
                mixins.add("mobends.MixinModelRendererBends");
                mixins.add("mobends.MixinSmoothVector3f");
                mixins.add("mobends.MixinZombieAnimationWalk");
            }
            if (Loader.isModLoaded("mobdictionary")) {
                CompatLibCore.LOGGER.info("Adding MobDictionary Fix.");
                mixins.add("mobdic.MixinMDClientProxy");
            }
            if (Loader.isModLoaded("gogskybox")) {
                ModDetector.hasSkybox = true;
                CompatLibCore.LOGGER.info("Adding GoGSkybox Fix.");
                mixins.add("skybox.MixinGoGSkybox");
            }
            if (ModDetector.hasCivCraft) {
                CompatLibCore.LOGGER.info("Adding CivCraft Fix.");
                mixins.add("civ.MixinGuiTechTree");
            }
        }
        return mixins;
    }

    @Override
    public void preApply(String s, org.objectweb.asm.tree.ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, org.objectweb.asm.tree.ClassNode classNode, String s1, IMixinInfo iMixinInfo) {
        CursedMixinExtensions.postApply(classNode);
    }
}
