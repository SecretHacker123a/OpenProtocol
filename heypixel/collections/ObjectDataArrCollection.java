package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.AbstractCollection;
import java.util.Iterator;

public class ObjectDataArrCollection<T> extends AbstractCollection<T> {
    public T[] dataArr;

    public ObjectDataArrCollection(T[] objectDataArr) {
        this.dataArr = objectDataArr;
    }

    @Override
    public int size() {
        return this.dataArr.length / 2;
    }

    @Override
    public Iterator<T> iterator() {
        return new ObjectDataIterator<>(this.dataArr, 1);
    }
}
