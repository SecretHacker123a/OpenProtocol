package dev.undefinedteam.gensh1n.protocol.heypixel.value;


import dev.undefinedteam.gensh1n.protocol.heypixel.data.BytesDataProvider;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public abstract class AbstractValue implements ObjectData {
    public Value value;

    public AbstractValue(Value value) {
        this.value = value;
    }

    public boolean isDoubleType() {
        return getValueType().isDouble();
    }

    public NullRetriever getAsNull() {
        throw new RuntimeException();
    }

    public LongNumberRetriever getAsLong() {
        throw new RuntimeException();
    }

    public String toString() {
        return this.value.toString();
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean isListType() {
        return getValueType().isList();
    }

    public ByteBufRetriever getAsBytesBuf() {
        throw new RuntimeException();
    }

    public boolean isNullType() {
        return getValueType().isNull();
    }

    @Override
    public boolean equals(Object obj) {
        return this.value.equals(obj);
    }

    public boolean isMapType() {
        return getValueType().isMap();
    }

    public MapRetriever getAsMap() {
        throw new RuntimeException();
    }

    public BoolRetriever getAsBool() {
        throw new RuntimeException();
    }

    public DoubleRetriever getAsDouble() {
        throw new RuntimeException();
    }

    public PayloadRetriever getAsPayload() {
        throw new RuntimeException();
    }

    public boolean isBytesBufType() {
        return getValueType().isBytesBuf();
    }

    public InstantRetriever getAsInstant() {
        throw new RuntimeException();
    }

    public boolean isInstantType() {
        return false;
    }

    public NumberValueRetriever getValueRetriever() {
        throw new RuntimeException();
    }

    public BytesDataProvider getAsBytes() {
        throw new RuntimeException();
    }

    public BytesRetriever getAsByteArr() {
        throw new RuntimeException();
    }

    public boolean isNullable() {
        return getValueType().isNullable();
    }

    public boolean isBooleanType() {
        return getValueType().isBoolean();
    }


    public boolean isVariableLength() {
        return getValueType().isVariableLength();
    }


    public boolean isBytesType() {
        return getValueType().isBytes();
    }


    public boolean isLongType() {
        return getValueType().isLong();
    }


    public DataListRetriever getAsList() {
        throw new RuntimeException();
    }


    public boolean isInstant1Type() {
        return getValueType().isInstant();
    }


    public String getDataAsString() {
        return this.value.getDataAsString();
    }
}
