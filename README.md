# FabricLoaderWrapper

Proof of concept of https://github.com/FabricMC/fabric-loader as a LaunchWrapper Forge mod.

## Usage & Limitations

Fabric Loader on LaunchWrapper couldn't load with intermediary mappings, so Legacy Fabric mods couldn't be loaded.

If you have some other way to remap mods feel free to contact me.

Requires Mixin fork with relocated ASM like UniMix. Just put in mods folder. Servers requires extra config to locate the vanilla server jar.

## Known Incompatibilities

1.8+: https://github.com/CleanroomMC/MixinBooter Shades invalid relocated ASM, must delete `org\spongepowered\asm\lib` from its jar.

1.7.10: https://github.com/TheWeatherPony/Partial-Modification-Loader Causes duplicated PML Mods.
