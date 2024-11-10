package dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces;


import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.MapRetriever;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;



public interface IMapData<T extends ObjectData> extends DataRetriever, MapRetriever<T> {
}
