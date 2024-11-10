package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.ValueTypeEnum;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.BigIntgerException;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.math.BigInteger;


@StringEncryption
@ControlFlowObfuscation
public class LongData extends AbstractDataRetriever implements ILongNumberData {
    public long val;

    public LongData(long j) {
        this.val = j;
    }

    @Override
    public ObjectRetriever getRetriever() {
        return this;
    }

    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }

    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    @Override
    public boolean isNullType() {
        return super.isNullType();
    }

    public int toInt() {
        if (isInt()) {
            return (int) this.val;
        }
        throw new BigIntgerException(this.val);
    }

    public byte getByte() {
        return (byte) this.val;
    }

    public long toLong() {
        return this.val;
    }

    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override
    public NumberValueRetriever getValueRetriever() {
        return getRetriever();
    }

    public BigInteger toBigInteger() {
        return BigInteger.valueOf(this.val);
    }

    @Override
    public boolean isLongType() {
        return super.isLongType();
    }

    @Override
    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }

    public boolean isInt() {
        return -2147483648L <= this.val && this.val <= 2147483647L;
    }

    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    public long getLong() {
        return this.val;
    }

    public int getInteger() {
        return (int) this.val;
    }

    public boolean isShort() {
        return -32768 <= this.val && this.val <= 32767;
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return this;
    }


    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }


    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }


    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }


    public void write(MessageBuffer buf) throws IOException {
        buf.writeSignedValue(this.val);
    }


    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }


    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    public ILongNumberData Method7319() {
        return this;
    }


    @Override
    public boolean isListType() {
        return super.isListType();
    }

    public int hashCode() {
        return (-2147483648L > this.val || this.val > 2147483647L) ? Long.hashCode(this.val) : (int) this.val;
    }


    public String getDataAsString() {
        return Long.toString(this.val);
    }


    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }


    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }


    public BigInteger getBigInteger() {
        return BigInteger.valueOf(this.val);
    }


    public ValueTypeEnum getValueTypeI() {
        return BigIntegerData.getBigIntegerType(this);
    }

    public DataTypes getValueType() {
        return DataTypes.LONG;
    }

    public float getFloat() {
        return (float) this.val;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectData objectData)) {
            return false;
        }
        if (!objectData.isLongType()) {
            return false;
        }
        LongNumberRetriever val = objectData.getAsLong();
        return val.isLong() && this.val == val.getLong();
    }


    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    public boolean isByte() {
        return -128 <= this.val && this.val <= 127;
    }


    public byte toByte() {
        if (isByte()) {
            return (byte) this.val;
        }
        throw new BigIntgerException(this.val);
    }


    public DataRetriever getValue() {
        return Method7319();
    }


    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }


    public short toShort() {
        if (isShort()) {
            return (short) this.val;
        }
        throw new BigIntgerException(this.val);
    }

    public double getDouble() {
        return this.val;
    }


    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }


    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }

    public short getShort() {
        return (short) this.val;
    }


    public String toString() {
        return getDataAsString();
    }


    @Override
    public LongNumberRetriever getAsLong() {
        return this.retrieveLongNumber();
    }

    public boolean isLong() {
        return true;
    }
}
