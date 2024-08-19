package space.libs.interfaces;

import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public interface IKeyBinding {

    boolean allowsKeyModifiers();

    IKeyConflictContext getKeyConflictContext();

    KeyModifier getKeyModifier();

    boolean isActiveAndMatches(int code);

}
