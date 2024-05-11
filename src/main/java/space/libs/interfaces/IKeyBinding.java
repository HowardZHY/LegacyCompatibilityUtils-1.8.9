package space.libs.interfaces;

import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;

@SuppressWarnings("unused")
public interface IKeyBinding {

    boolean allowsKeyModifiers();

    IKeyConflictContext getKeyConflictContext();

    KeyModifier getKeyModifier();

    boolean isActiveAndMatches(int code);

}
