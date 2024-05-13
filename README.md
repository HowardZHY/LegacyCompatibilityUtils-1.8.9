# LegacyCompatibilityUtils

[Here for 1.7.10 Version with 1.7.2 Mod Compat](https://github.com/HowardZHY/LegacyCompatibilityUtils-1.7.10)

## The Most Cursed 1.8.9 Forge Mod

(CompatLib in short) Made many 1.8.0 mod working on 1.8.9 (except some coremods like with `@MCVersion("1.8")` ).

This is done by re-add removed classes and srg methods/fields.

Requires [MixinBooter](https://github.com/CleanroomMC/MixinBooter).

Use version 8.4 and remove the line with crash report in `mixin.mixinbooter.init.json` if you crash with OptiFine.

This mod contains following libs:

A Relocated https://github.com/FabricCompatibilityLayers/CursedMixinExtensions

1.10.2 MinecraftForge KeyBinding API

# Notice

If anyone is interested at making another runtime deobf remapper, feel free to open topic in Discussion.

Submit an issue if you met incompatibilities.

# Known Not Working

[BlockSteps](https://www.curseforge.com/minecraft/mc-mods/blocksteps)
Blocks won't show, Unknown reason

[Mo' Bends](https://www.curseforge.com/minecraft/mc-mods/mo-bends)
Players & Zombies Actions won't show, Unknown reason

[Gender & Age Mod](https://www.curseforge.com/minecraft/mc-mods/gender)
Notch Code ASM

NEI, maybe other mods using ChickenBones Core as well
Coremods with ASM

TMI (Official 1.8 Forge Version)
The Only 1.8 Forge Mod in Totally Notch Code
