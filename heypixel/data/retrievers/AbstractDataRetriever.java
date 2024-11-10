package dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers;


import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.BytesDataProvider;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public abstract class AbstractDataRetriever implements DataRetriever {

    @Override
    public ByteBufRetriever getAsBytesBuf() {
        return retrieveBytesData();
    }


    public boolean isBytesBufType() {
        return getValueType().isBytesBuf();
    }


    public boolean isListType() {
        return getValueType().isList();
    }


    @Override
    public BytesRetriever getAsByteArr() {
        return retrieveByteArrayData();
    }


    public NumberValueRetriever getValueRetriever() {
        return getRetriever();
    }


    public PayloadRetriever getAsPayload() {
        return getDefaultType();
    }


    public IHexBytesData retrieveByteArrayData() {
        throw new RuntimeException();
    }


    public ObjectRetriever getRetriever() {
        throw new RuntimeException();
    }


    public IBufferData retrieveBytesData() {
        throw new RuntimeException();
    }


    public IDataList retrieveListData() {
        throw new RuntimeException();
    }


    public IBoolData retrieveBoolean() {
        throw new RuntimeException();
    }


    public IMapData retrieveMapData() {
        throw new RuntimeException();
    }

    public boolean isInstantType() {
        return false;
    }


    public boolean isBooleanType() {
        return getValueType().isBoolean();
    }


    public ILongNumberData retrieveLongNumber() {
        throw new RuntimeException();
    }


    public boolean isLongType() {
        return getValueType().isLong();
    }


    public boolean isInstant1Type() {
        return getValueType().isInstant();
    }


    @Override
    public NullRetriever getAsNull() {
        return retrieveNullData();
    }


    @Override
    public LongNumberRetriever getAsLong() {
        return retrieveLongNumber();
    }


    @Override
    public DataListRetriever getAsList() {
        return retrieveListData();
    }


    public boolean isBytesType() {
        return getValueType().isBytes();
    }


    public boolean isVariableLength() {
        return getValueType().isVariableLength();
    }


    @Override
    public MapRetriever getAsMap() {
        return retrieveMapData();
    }


    public boolean isDoubleType() {
        return getValueType().isDouble();
    }


    @Override
    public DoubleRetriever getAsDouble() {
        return retrieveDoubleData();
    }


    public boolean isNullType() {
        return getValueType().isNull();
    }

    public boolean isMapType() {
        return getValueType().isMap();
    }


    public IBytesData getDefaultType() {
        throw new RuntimeException();
    }


    @Override
    public BoolRetriever getAsBool() {
        return retrieveBoolean();
    }


    @Override
    public BytesDataProvider getAsBytes() {
        return retrieveBytes();
    }


    public BytesDataRetriever retrieveBytes() {
        throw new RuntimeException();
    }


    public IInstantBytes retrieveInstantData() {
        throw new RuntimeException();
    }


    public boolean isNullable() {
        return getValueType().isNullable();
    }


    public IDoubleData retrieveDoubleData() {
        throw new RuntimeException();
    }


    @Override
    public InstantRetriever getAsInstant() {
        return retrieveInstantData();
    }


    public INullData retrieveNullData() {
        throw new RuntimeException();
    }
}
