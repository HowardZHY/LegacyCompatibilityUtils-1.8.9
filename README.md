# LegacyCompatibilityUtils

[Here for 1.7.10 Version with 1.7.2 Mod Compat](https://github.com/HowardZHY/LegacyCompatibilityUtils-1.7.10)

## The Most Cursed 1.8.9 Forge Mod

(CompatLib in short) Made many 1.8.0 Forge and LiteLoader Mods working on 1.8.9 (except some coremods like with `@MCVersion("1.8")` ).

This is done by re-add removed classes and srg methods/fields.

Requires [MixinBooter](https://github.com/CleanroomMC/MixinBooter) version 9.4.

This mod contains following libs:

A Relocated https://github.com/FabricCompatibilityLayers/CursedMixinExtensions

1.10.2 MinecraftForge KeyBinding API

https://github.com/jhalterman/typetools

# Notice

If anyone is interested at making another runtime deobf remapper, feel free to open topic in Discussion.

Submit an issue if you met incompatibilities.

### If you try to use [BlockSteps](https://www.curseforge.com/minecraft/mc-mods/blocksteps):

Disable use VBOs in Video Settings if you use this mod, otherwise blocks won't show.

# Known Not Working

### [Gender & Age Mod](https://www.curseforge.com/minecraft/mc-mods/gender)

Notch Code ASM

### NEI and other mods using ChickenBones' Core as well

Coremods using APIs with FML Core breaking changes

### TMI (Official 1.8 Forge Version)

The Only 1.8 Forge Mod in Totally Notch Code
