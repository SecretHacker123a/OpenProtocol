package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.ValueDataType;
import dev.undefinedteam.gensh1n.protocol.heypixel.ValueTypeEnum;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.BigIntegerData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.ILongNumberData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.LongNumberRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.BigIntgerException;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.math.BigInteger;


@StringEncryption
@ControlFlowObfuscation
public class LongValue extends NumberValue implements LongNumberRetriever {
    public Value data;


    public LongValue(Value value) {
        super(value);
        this.data = value;
    }

    public ValueTypeEnum getValueTypeI() {
        return BigIntegerData.getBigIntegerType(this);
    }

    public boolean isShort() {
        return this.data.valueType != ValueDataType.BIGINTEGER_LONG_TYPE && -32768 <= this.data.longValue && this.data.longValue <= 32767;
    }

    public BigInteger toBigInteger() {
        return this.data.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? (BigInteger) this.data.data : BigInteger.valueOf(this.data.longValue);
    }

    public DataTypes getValueType() {
        return DataTypes.LONG;
    }

    public ILongNumberData getNumData() {
        return this.data.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? DataFactory.ofBigInteger((BigInteger) this.data.data) : DataFactory.ofLong(this.data.longValue);
    }

    public short toShort() {
        if (isByte()) {
            return (short) this.data.longValue;
        }
        throw new BigIntgerException(this.data.longValue);
    }

    public int toInt() {
        if (isInt()) {
            return (int) this.data.longValue;
        }
        throw new BigIntgerException(this.data.longValue);
    }

    public boolean isInt() {
        return this.data.valueType != ValueDataType.BIGINTEGER_LONG_TYPE && -2147483648L <= this.data.longValue && this.data.longValue <= 2147483647L;
    }

    public boolean isLong() {
        return this.data.valueType != ValueDataType.BIGINTEGER_LONG_TYPE;
    }

    @Override
    public LongNumberRetriever getAsLong() {
        return this;
    }


    public byte toByte() {
        if (isByte()) {
            return (byte) this.data.longValue;
        }
        throw new BigIntgerException(this.data.longValue);
    }


    public void write(MessageBuffer buf) throws IOException {
        if (this.data.valueType == ValueDataType.BIGINTEGER_LONG_TYPE) {
            buf.writeBigInteger((BigInteger) this.data.data);
        } else {
            buf.writeSignedValue(this.data.longValue);
        }
    }


    public long toLong() {
        if (isLong()) {
            return this.data.longValue;
        }
        throw new BigIntgerException(this.data.longValue);
    }


    public DataRetriever getValue() {
        return getNumData();
    }

    public boolean isByte() {
        return this.data.valueType != ValueDataType.BIGINTEGER_LONG_TYPE && -128 <= this.data.longValue && this.data.longValue <= 127;
    }
}
