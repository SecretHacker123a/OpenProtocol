package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectDataIterator1 implements Iterator<ObjectData> {
    public ObjectData[] arr;
    public int index = 0;


    public ObjectDataIterator1(ObjectData[] objectDataArr) {
        this.arr = objectDataArr;
    }

    public ObjectData next0() {
        int i = this.index;
        if (i >= this.arr.length) {
            throw new NoSuchElementException();
        }
        this.index = ((i | 1) & ((-2) | ((-i) - 1))) + (((1 | ((-i) - 1)) - ((-i) - 1)) * 2);
        return this.arr[i];
    }

    @Override
    public ObjectData next() {
        return next0();
    }

    @Override
    public boolean hasNext() {
        return this.index != this.arr.length;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
