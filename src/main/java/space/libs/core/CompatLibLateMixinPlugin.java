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
        if (CompatLibMixinPlugin.SIDE == MixinEnvironment.Side.CLIENT) {
            if (ModDetector.hasNEI) {
                CompatLibCore.LOGGER.info("Adding NEI Fix.");
                mixins.add("legacy.nei.MixinSpawnerRenderer");
            }
            if (Loader.isModLoaded("CameraStudio")) {
                CompatLibCore.LOGGER.info("Adding CameraStudio Fixes.");
                mixins.add("legacy.cs.MixinCameraStudioLoader");
                mixins.add("legacy.cs.MixinCameraStudioLogger");
                mixins.add("legacy.cs.MixinCameraStudioPrivateFields");
                mixins.add("legacy.cs.MixinCameraStudioRenderCamera");
                mixins.add("legacy.cs.MixinCameraStudioRenderCameraFOV");
            }
            if (Loader.isModLoaded("mobdictionary")) {
                CompatLibCore.LOGGER.info("Adding MobDictionary Fix.");
                mixins.add("legacy.mobdic.MixinMDClientProxy");
            }
            if (Loader.isModLoaded("mobends")) {
                ModDetector.hasMobends = true;
                CompatLibCore.LOGGER.info("Adding MoBends Fixes.");
                mixins.add("legacy.mobends.MixinBendsLogger");
                mixins.add("legacy.mobends.MixinClientProxy");
                mixins.add("legacy.mobends.MixinEventHandlerDataUpdate");
                mixins.add("legacy.mobends.MixinEventHandlerRenderPlayer");
                mixins.add("legacy.mobends.MixinModelRendererBends");
                mixins.add("legacy.mobends.MixinSmoothVector3f");
                mixins.add("legacy.mobends.MixinZombieAnimationWalk");
            }
            if (Loader.isModLoaded("gogskybox")) {
                ModDetector.hasSkybox = true;
                CompatLibCore.LOGGER.info("Adding GoGSkybox Fix.");
                mixins.add("fixes.skybox.MixinGoGSkybox");
            }
            if (ModDetector.hasCivCraft) {
                CompatLibCore.LOGGER.info("Adding CivCraft Fix.");
                mixins.add("legacy.civ.MixinGuiTechTree");
            }
        }
        if (ModDetector.hasTC5) {
            CompatLibCore.LOGGER.info("Adding TC5 Fix.");
            mixins.add("legacy.tc5.MixinItemResearchNotes");
        }
        if (Loader.isModLoaded("mochickens")) {
            CompatLibCore.LOGGER.info("Adding Mo Chickens Fix.");
            mixins.add("legacy.mochickens.MixinFileManager");
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
