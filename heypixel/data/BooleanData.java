package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class BooleanData extends AbstractDataRetriever implements IBoolData {
    public static IBoolData TRUE = new BooleanData(true);
    public static IBoolData FALSE = new BooleanData(false);
    public boolean value;


    public BooleanData(boolean z) {
        this.value = z;
    }

    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }

    public IBoolData Method6887() {
        return this;
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    public String getDataAsString() {
        return Boolean.toString(this.value);
    }

    public DataTypes getValueType() {
        return DataTypes.BOOLEAN;
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    @Override
    public ObjectRetriever getRetriever() {
        return super.getRetriever();
    }

    @Override
    public IBoolData retrieveBoolean() {
        return this;
    }


    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }


    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }


    public String toString() {
        return getDataAsString();
    }


    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }


    public DataRetriever getValue() {
        return Method6887();
    }


    @Override
    public boolean isMapType() {
        return super.isMapType();
    }


    public void write(MessageBuffer buf) throws IOException {
        buf.writeBoolean(this.value);
    }


    @Override
    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectData objectData)) {
            return false;
        }
        return objectData.isBooleanType() && this.value == objectData.getAsBool().getBoolean();
    }


    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }


    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }


    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }


    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }


    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }


    @Override
    public boolean isLongType() {
        return super.isLongType();
    }


    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }


    @Override
    public boolean isNullType() {
        return super.isNullType();
    }


    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }

    public boolean getBoolean() {
        return this.value;
    }


    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }


    @Override
    public BoolRetriever getAsBool() {
        return retrieveBoolean();
    }

    public int hashCode() {
        return this.value ? 1231 : 1237;
    }
}
