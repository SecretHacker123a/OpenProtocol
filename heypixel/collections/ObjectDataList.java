package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.AbstractList;

public class ObjectDataList extends AbstractList<ObjectData> {
    public ObjectData[] arr;

    public ObjectDataList(ObjectData[] objectDataArr) {
        this.arr = objectDataArr;
    }

    public int size() {
        return this.arr.length;
    }


    @Override
    public ObjectData get(int i) {
        return find(i);
    }

    public ObjectData find(int i) {
        return this.arr[i];
    }
}
