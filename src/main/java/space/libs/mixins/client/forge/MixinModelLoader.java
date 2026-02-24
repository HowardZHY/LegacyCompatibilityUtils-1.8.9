package space.libs.mixins.client.forge;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import space.libs.mixins.client.render.MixinModelBakery;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
@Mixin(value = ModelLoader.class, priority = 900)
public abstract class MixinModelLoader extends MixinModelBakery {

    @Shadow(remap = false)
    public abstract IModel getModel(ResourceLocation location) throws IOException;

    private final Map<String, Item> ExcludedNames = Maps.newHashMap();

    private final List<ResourceLocation> Excluded = new ArrayList<>();

    @Redirect(
        method = "onPostBakeEvent",
        at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"),
        remap = false
    )
    public void onPostBakeEvent(Logger instance, String msg, Throwable t) {
        instance.warn(msg + ": " + t.getMessage());
        StackTraceElement[] stack = t.getStackTrace();
        String trace1 = stack.length > 0 ? stack[0].toString() : "No Stack";
        String trace2 = stack.length > 1 ? stack[1].toString() : "";
        instance.warn("at " + trace1);
        instance.warn("at " + trace2 + " ...");
    }

    @Redirect(
        method = "loadItems",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/client/model/ModelLoader;getModel(Lnet/minecraft/util/ResourceLocation;)Lnet/minecraftforge/client/model/IModel;",
            ordinal = 0
        ),
        remap = false
    )
    private IModel loadItems(ModelLoader instance, ResourceLocation location) throws IOException {
        if (Excluded.contains(location)) {
            throw new FileNotFoundException("Ignored for legacy Fluid: " + location);
        }
        return getModel(location);
    }

    @Override
    public List<String> getVariantNames(Item item) {
        List<String> names = super.getVariantNames(item);
        if (Block.getBlockFromItem(item) instanceof BlockFluidBase) {
            if (!ExcludedNames.containsValue(item) && !names.isEmpty()) {
                ExcludedNames.put(names.get(0), item);
            }
        }
        return names;
    }

    @Override
    protected ResourceLocation getItemLocation(String name) {
        ResourceLocation location = super.getItemLocation(name);
        if (ExcludedNames.containsKey(name)) {
            if (!Excluded.contains(location)) {
                Excluded.add(location);
            }
        }
        return location;
    }
}
