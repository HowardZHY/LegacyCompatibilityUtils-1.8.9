package space.libs.mixins.forge;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
@Mixin(value = VillagerRegistry.class, remap = false)
public class MixinVillagerRegistry {

    @Shadow(prefix = "original$")
    public static void original$addExtraVillageComponents(List<StructureVillagePieces.PieceWeight> list, Random random, int i) {}

    @Shadow(prefix = "original$")
    public static StructureVillagePieces.Village original$getVillageComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece,
                                                                     List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        throw new AbstractMethodError();
    }

    @Public
    private static void addExtraVillageComponents(ArrayList components, Random random, int i) {
        original$addExtraVillageComponents(components, random, i);
    }

    @Public
    private static Object getVillageComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece,
                                             List pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        return original$getVillageComponent(villagePiece, startPiece, pieces, random, p1, p2, p3, facing, p5);
    }

}
