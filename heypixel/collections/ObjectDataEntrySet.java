package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

public class ObjectDataEntrySet<T> extends AbstractSet<Map.Entry<T, T>> {
    public T[] arr;

    public ObjectDataEntrySet(T[] objectDataArr) {
        this.arr = objectDataArr;
    }


    public Iterator<Map.Entry<T, T>> iterator() {
        return new ObjectDataEntryIterator<>(this.arr);
    }

    public int size() {
        return this.arr.length / 2;
    }
}
