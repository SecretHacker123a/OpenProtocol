package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class NullData extends AbstractDataRetriever implements INullData {
    public static INullData NULL = new NullData();

    public static INullData getNULL() {
        return NULL;
    }

    public DataRetriever getValue() {
        return get();
    }

    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeNull();
    }

    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    public INullData get() {
        return this;
    }

    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ObjectData) {
            return ((ObjectData) obj).isNullType();
        }
        return false;
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    public String toString() {
        return getDataAsString();
    }

    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }

    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }

    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }

    @Override
    public NullRetriever getAsNull() {
        return this.retrieveNullData();
    }

    @Override
    public INullData retrieveNullData() {
        return this;
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    @Override
    public ObjectRetriever getRetriever() {
        return super.getRetriever();
    }

    @Override
    public boolean isNullType() {
        return super.isNullType();
    }

    @Override
    public boolean isLongType() {
        return super.isLongType();
    }

    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }

    public String getDataAsString() {
        return "null";
    }

    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }

    public DataTypes getValueType() {
        return DataTypes.NULL;
    }


    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }


    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }


    @Override
    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }
}
