package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class ObjectDataEntryIterator<T> implements Iterator<Map.Entry<T, T>> {
    public T[] arr;
    public int index = 0;

    public ObjectDataEntryIterator(T[] objectDataArr) {
        this.arr = objectDataArr;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.arr.length;
    }

    public Map.Entry<T, T> next0() {
        if (this.index >= this.arr.length) {
            throw new NoSuchElementException();
        }
        T objectData = this.arr[this.index];
        T[] objectDataArr = this.arr;
        int i = this.index;
        Map.Entry<T, T> simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry<>(
            objectData,
            objectDataArr[(i | 1) + ((1 | ((-i) - 1)) - ((-i) - 1))]
        );
        int i2 = this.index;
        this.index = (i2 | 2) + ((2 | ((-i2) - 1)) - ((-i2) - 1));
        return simpleImmutableEntry;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map.Entry<T, T> next() {
        return next0();
    }
}
