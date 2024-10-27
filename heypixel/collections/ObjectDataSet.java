package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.AbstractSet;
import java.util.Iterator;

public class ObjectDataSet<T> extends AbstractSet<T> {
    public T[] arr;

    public ObjectDataSet(T[] objectDataArr) {
        this.arr = objectDataArr;
    }

    public int size() {
        return this.arr.length / 2;
    }

    public Iterator<T> iterator() {
        return new ObjectDataIterator<>(this.arr, 0);
    }
}
