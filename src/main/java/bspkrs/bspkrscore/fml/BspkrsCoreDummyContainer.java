package bspkrs.bspkrscore.fml;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;

import static space.libs.util.ForgeUtils.createModData;

@SuppressWarnings("all")
public class BspkrsCoreDummyContainer extends DummyModContainer {

    public BspkrsCoreDummyContainer() {
        super(createModData("bspkrsCore", "bspkrsCore-Dummy", "7.01", "bspkrs"));
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }
}
