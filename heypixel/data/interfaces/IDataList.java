package dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataListRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.Iterator;
import java.util.List;



public interface IDataList extends DataRetriever, DataListRetriever {
    List<ObjectData> getObjectDataList();

    @Override
    Iterator<ObjectData> iterator();
}
