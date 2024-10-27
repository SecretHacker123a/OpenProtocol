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
public class BigIntegerData extends AbstractDataRetriever implements ILongNumberData {
    public static BigInteger BYTE_MIN = BigInteger.valueOf(-128);
    public static BigInteger BYTE_MAX = BigInteger.valueOf(127);
    public static BigInteger SHORT_MIN = BigInteger.valueOf(-32768);
    public static BigInteger SHORT_MAX = BigInteger.valueOf(32767);
    public static BigInteger INT_MIN = BigInteger.valueOf(-2147483648L);
    public static BigInteger INT_MAX = BigInteger.valueOf(2147483647L);
    public static BigInteger LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);
    public static BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);
    public BigInteger value;

    public BigIntegerData(BigInteger bigInteger) {
        this.value = bigInteger;
    }

    public static ValueTypeEnum getBigIntegerType(LongNumberRetriever data) {
        return data.isByte() ? ValueTypeEnum.BYTE_BIGINTEGER : data.isShort() ? ValueTypeEnum.SHORT_BIGINTEGER : data.isInt() ? ValueTypeEnum.INT_BIGINTEGER : data.isLong() ? ValueTypeEnum.LONG_BIGINTEGER : ValueTypeEnum.BIGINTEGER;
    }

    public ILongNumberData get() {
        return this;
    }

    public byte getByte() {
        return this.value.byteValue();
    }

    @Override

    public LongNumberRetriever getAsLong() {
        return retrieveLongNumber();
    }

    public boolean isInt() {
        return 0 <= this.value.compareTo(INT_MIN) && this.value.compareTo(INT_MAX) <= 0;
    }

    public short getShort() {
        return this.value.shortValue();
    }

    @Override

    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    @Override

    public boolean isLongType() {
        return super.isLongType();
    }

    @Override

    public ILongNumberData retrieveLongNumber() {
        return this;
    }

    @Override

    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    @Override

    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override

    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    @Override

    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }

    @Override

    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    public byte toByte() {
        if (isByte()) {
            return this.value.byteValue();
        }
        throw new BigIntgerException(this.value);
    }

    public BigInteger getBigInteger() {
        return this.value;
    }

    public short toShort() {
        if (isShort()) {
            return this.value.shortValue();
        }
        throw new BigIntgerException(this.value);
    }

    public boolean isByte() {
        return 0 <= this.value.compareTo(BYTE_MIN) && this.value.compareTo(BYTE_MAX) <= 0;
    }

    public int getInteger() {
        return this.value.intValue();
    }

    public String getDataAsString() {
        return this.value.toString();
    }

    public int hashCode() {
        if (INT_MIN.compareTo(this.value) <= 0 && this.value.compareTo(INT_MAX) <= 0) {
            return (int) this.value.longValue();
        }
        if (LONG_MIN.compareTo(this.value) > 0 || this.value.compareTo(LONG_MAX) > 0) {
            return this.value.hashCode();
        }
        long longValue = this.value.longValue();
        return (int) (longValue ^ (longValue >>> 32));
    }

    @Override

    public boolean isVariableLength() {
        return super.isVariableLength();
    }

    public int toInt() {
        if (isInt()) {
            return this.value.intValue();
        }
        throw new BigIntgerException(this.value);
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
        return this.value.equals(objectData.getAsLong().getBigInteger());
    }

    @Override

    public boolean isNullType() {
        return super.isNullType();
    }

    public double getDouble() {
        return this.value.doubleValue();
    }

    @Override

    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }

    @Override

    public boolean isListType() {
        return super.isListType();
    }

    public long getLong() {
        return this.value.longValue();
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeBigInteger(this.value);
    }

    public DataRetriever getValue() {
        return get();
    }

    public boolean isShort() {
        return 0 <= this.value.compareTo(SHORT_MIN) && this.value.compareTo(SHORT_MAX) <= 0;
    }

    @Override

    public boolean isNullable() {
        return super.isNullable();
    }

    @Override

    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }

    @Override

    public boolean isBooleanType() {
        return super.isBooleanType();
    }

    @Override

    public boolean isInstantType() {
        return super.isInstantType();
    }

    @Override

    public ObjectRetriever getRetriever() {
        return this;
    }

    public BigInteger toBigInteger() {
        return this.value;
    }


    public ValueTypeEnum getValueTypeI() {
        return getBigIntegerType(this);
    }


    public String toString() {
        return getDataAsString();
    }


    @Override

    public boolean isBytesType() {
        return super.isBytesType();
    }


    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }


    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }


    public boolean isLong() {
        return 0 <= this.value.compareTo(LONG_MIN) && this.value.compareTo(LONG_MAX) <= 0;
    }


    public long toLong() {
        if (isLong()) {
            return this.value.longValue();
        }
        throw new BigIntgerException(this.value);
    }


    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }


    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }


    public float getFloat() {
        return this.value.floatValue();
    }


    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    public DataTypes getValueType() {
        return DataTypes.LONG;
    }


    @Override
    public NumberValueRetriever getValueRetriever() {
        return getRetriever();
    }


    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }
}
