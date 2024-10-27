package dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.Iterator;
import java.util.List;



public interface DataListRetriever extends ObjectData, Iterable<ObjectData> {
    int size();

    @Override
    Iterator<ObjectData> iterator();

    List<ObjectData> getObjectDataList();

    ObjectData get(int i);

    ObjectData getNullable(int i);
}
