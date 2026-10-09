package space.libs.mixins.server;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.management.UserListOpsEntry;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(UserListOpsEntry.class)
public class MixinUserListOpsEntry {

    @ShadowConstructor
    public void UserListOpsEntry(GameProfile player, int permissionLevelIn, boolean bypassesPlayerLimitIn) {}

    @NewConstructor
    public void UserListOpsEntry(GameProfile player, int permissionLevelIn) {
        this.UserListOpsEntry(player, permissionLevelIn, permissionLevelIn == 4);
    }
}
