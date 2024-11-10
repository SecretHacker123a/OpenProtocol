package dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.Collection;
import java.util.Map;
import java.util.Set;



public interface MapRetriever<T extends ObjectData> extends ObjectData {
    int size();

    Collection<T> getCollection();

    Set<Map.Entry<T, T>> entrySet();

    Map<T, T> getMap();

    Set<T> keySet();

    T[] getArray();
}
