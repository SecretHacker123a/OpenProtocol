package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectDataIterator<T> implements Iterator<T> {
    public T[] arr;
    public int index;


    public ObjectDataIterator(T[] objectDataArr, int i) {
        this.arr = objectDataArr;
        this.index = i;
    }

    public T next0() {
        int i = this.index;
        if (i >= this.arr.length) {
            throw new NoSuchElementException();
        }
        this.index = i + 2;
        return this.arr[i];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return this.index < this.arr.length;
    }

    @Override
    public T next() {
        return next0();
    }
}
