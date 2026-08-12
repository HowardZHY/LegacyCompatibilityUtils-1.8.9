package net.minecraft.util.registry;

import java.util.Set;

@SuppressWarnings("all")
public interface IRegistry<K, V> extends net.minecraft.util.IRegistry<K, V> {

    @Override
    V getObject(K paramK);

    @Override
    void putObject(K paramK, V paramV);

    Set<K> getKeys();
}
