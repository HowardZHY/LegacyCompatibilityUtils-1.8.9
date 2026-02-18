package com.superdextor.thinkbigcore;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;

import static space.libs.util.ForgeUtils.createModData;

@SuppressWarnings("all")
public class ThinkBigCoreDummyContainer extends DummyModContainer {

    public ThinkBigCoreDummyContainer() {
        super(createModData("ThinkBigCore", "ThinkBigCore-Dummy", "1.4", "superdextor"));
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }
}
