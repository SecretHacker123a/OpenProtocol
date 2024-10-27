package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class ObjectDataArrMap<T> extends AbstractMap<T, T> {
    public T[] arr;

    public ObjectDataArrMap(T[] objectDataArr) {
        this.arr = objectDataArr;
    }

    @Override
    public Set<Map.Entry<T, T>> entrySet() {
        return new ObjectDataEntrySet<>(this.arr);
    }
}
