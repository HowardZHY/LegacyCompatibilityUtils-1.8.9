package space.libs.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;

import static space.libs.util.ForgeUtils.*;

@SuppressWarnings("all")
public class CompatLibCoreContainer extends DummyModContainer {

    public CompatLibCoreContainer() {
        super(createModData("compatlibcore", "CompatLibCore", "1.8.9", "HowardZHY"));
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }
}
