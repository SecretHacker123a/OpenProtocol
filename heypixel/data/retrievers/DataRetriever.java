package dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers;


import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.BytesDataProvider;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;



public interface DataRetriever extends ObjectData {

    default LongNumberRetriever getAsLong() {
        return retrieveLongNumber();
    }

    ILongNumberData retrieveLongNumber();


    default BytesRetriever getAsByteArr() {
        return retrieveByteArrayData();
    }

    IHexBytesData retrieveByteArrayData();

    BytesDataRetriever retrieveBytes();


    default BoolRetriever getAsBool() {
        return retrieveBoolean();
    }


    default DataListRetriever getAsList() {
        return retrieveListData();
    }

    IBufferData retrieveBytesData();


    default MapRetriever getAsMap() {
        return retrieveMapData();
    }

    IInstantBytes retrieveInstantData();

    IBoolData retrieveBoolean();

    IDataList retrieveListData();

    INullData retrieveNullData();

    IMapData retrieveMapData();


    default BytesDataProvider getAsBytes() {
        return retrieveBytes();
    }


    default NullRetriever getAsNull() {
        return retrieveNullData();
    }


    default InstantRetriever getAsInstant() {
        return retrieveInstantData();
    }

    IDoubleData retrieveDoubleData();


    default ByteBufRetriever getAsBytesBuf() {
        return retrieveBytesData();
    }


    default DoubleRetriever getAsDouble() {
        return retrieveDoubleData();
    }
}
