package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;


@StringEncryption
@ControlFlowObfuscation
public class DoubleData extends AbstractDataRetriever implements IDoubleData {


    public double value;


    public DoubleData(double d) {
        this.value = d;
    }

    public String toString() {
        return Double.toString(this.value);
    }

    public long getLong() {
        return (long) this.value;
    }

    public DataTypes getValueType() {
        return DataTypes.DOUBLE;
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    @Override
    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }

    @Override
    public boolean isNullType() {
        return super.isNullType();
    }

    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }

    public DataRetriever getValue() {
        return self();
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    public String getDataAsString() {
        return (Double.isNaN(this.value) || Double.isInfinite(this.value)) ? "null" : Double.toString(this.value);
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override
    public IDoubleData retrieveDoubleData() {
        return this;
    }

    public int getInteger() {
        return (int) this.value;
    }

    public BigInteger getBigInteger() {
        return new BigDecimal(this.value).toBigInteger();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectData objectData)) {
            return false;
        }
        return objectData.isDoubleType() && this.value == objectData.getAsDouble().getDouble();
    }

    @Override
    public DoubleRetriever getAsDouble() {
        return retrieveDoubleData();
    }

    public double getDouble() {
        return this.value;
    }

    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    public DoubleData self() {
        return this;
    }

    public int hashCode() {
        return Double.hashCode(this.value);
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }


    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }


    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }


    @Override
    public NumberValueRetriever getValueRetriever() {
        return getRetriever();
    }


    @Override
    public boolean isLongType() {
        return super.isLongType();
    }

    public short getShort() {
        return (short) this.value;
    }


    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }


    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }


    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }


    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }

    @Override
    public ObjectRetriever getRetriever() {
        return this;
    }


    public void write(MessageBuffer buf) throws IOException {
        buf.writeDoubleLE(this.value);
    }


    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }

    public float getFloat() {
        return (float) this.value;
    }


    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }


    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }

    public byte getByte() {
        return (byte) this.value;
    }


    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }
}
