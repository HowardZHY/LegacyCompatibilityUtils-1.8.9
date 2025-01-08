package space.libs.mixins.mods.civ;

import alexiil.mods.civ.api.tech.TechTree.*;
import alexiil.mods.civ.gui.GuiTechTree;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

import java.util.*;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = GuiTechTree.class, remap = false)
public abstract class MixinGuiTechTree {

    /**
     * @author HowardZHY
     * @reason Fix CME Crash?
     */
    @Overwrite
    private void populateTechMap(List<List<Tech>> techList, Map<Tech, Integer> tempMap) {
        Minecraft.getMinecraft().mouseHelper.ungrabMouseCursor();
        boolean isDirty = true;
        int ttl = 1000;
        while (isDirty && ttl > 0) {
            isDirty = false;
            for (int lstIdx = 0; lstIdx < techList.size(); lstIdx++) {
                List<Tech> techs = techList.get(lstIdx);
                for (int i = 0; i < techs.size(); i++) {
                    Tech t = techs.get(i);
                    for (Tech child : t.getChildTechs()) {
                        if (child.isLeafTech())
                            continue;
                        int idx = tempMap.get(child);
                        int newIndex = tempMap.get(t);
                        if (idx <= newIndex) {
                            tempMap.put(child, newIndex + 1);
                            while (techList.size() <= newIndex + 1) {
                                techList.add(new ArrayList<Tech>());
                            }
                            List<Tech> nextList = techList.get(newIndex + 1);
                            nextList.add(child);
                            List<Tech> prevList = techList.get(idx);
                            prevList.remove(child);
                            isDirty = true;
                        }
                    }
                }
            }
            ttl--;
        }
    }

}
