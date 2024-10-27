package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.ValueDataType;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.NumberValueRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.math.BigDecimal;
import java.math.BigInteger;


@StringEncryption
@ControlFlowObfuscation
public abstract class NumberValue extends AbstractValue implements NumberValueRetriever {
    public Value val;

    public NumberValue(Value value) {
        super(value);
        this.val = value;
    }

    @Override
    public NumberValueRetriever getValueRetriever() {
        return this;
    }

    public long getLong() {
        return this.val.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? ((BigInteger) this.val.data).longValue() : this.val.longValue;
    }

    public float getFloat() {
        return this.val.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? ((BigInteger) this.val.data).floatValue() : this.val.valueType == ValueDataType.DOUBLE_TYPE ? (float) this.val.doubleValue : (float) this.val.longValue;
    }

    public double getDouble() {
        return this.val.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? ((BigInteger) this.val.data).doubleValue() : this.val.valueType == ValueDataType.DOUBLE_TYPE ? this.val.doubleValue : this.val.longValue;
    }

    public int getInteger() {
        return this.val.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? ((BigInteger) this.val.data).intValue() : (int) this.val.longValue;
    }

    public short getShort() {
        return this.val.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? ((BigInteger) this.val.data).shortValue() : (short) this.val.longValue;
    }

    public BigInteger getBigInteger() {
        return this.val.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? (BigInteger) this.val.data : this.val.valueType == ValueDataType.DOUBLE_TYPE ? new BigDecimal(this.val.doubleValue).toBigInteger() : BigInteger.valueOf(this.val.longValue);
    }

    public byte getByte() {
        return this.val.valueType == ValueDataType.BIGINTEGER_LONG_TYPE ? ((BigInteger) this.val.data).byteValue() : (byte) this.val.longValue;
    }
}
