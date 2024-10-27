package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public interface ObjectData {
    DataRetriever getValue();

    PayloadRetriever getAsPayload();

    boolean isInstant1Type();

    InstantRetriever getAsInstant();

    BytesRetriever getAsByteArr();

    boolean isNullType();

    DataTypes getValueType();

    BytesDataProvider getAsBytes();

    boolean isMapType();

    MapRetriever getAsMap();

    boolean isBytesType();

    DataListRetriever getAsList();

    boolean isVariableLength();

    boolean isNullable();

    void write(MessageBuffer buf) throws IOException;

    LongNumberRetriever getAsLong();

    boolean equals(Object obj);

    NullRetriever getAsNull();

    boolean isListType();

    boolean isDoubleType();

    NumberValueRetriever getValueRetriever();

    DoubleRetriever getAsDouble();

    boolean isLongType();

    boolean isBooleanType();

    String getDataAsString();

    BoolRetriever getAsBool();

    boolean isInstantType();

    ByteBufRetriever getAsBytesBuf();

    boolean isBytesBufType();
}
