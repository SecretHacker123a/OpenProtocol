package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.Genshin;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.BytesDataProvider;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.ValueDataType;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Map;


@StringEncryption
@ControlFlowObfuscation
public class Value implements ObjectData {
    public static long MAX_INT = 2147483647L;
    public static long MIN_INT = -2147483648L;
    public static long MAX_SHORT = 32767;
    public static long MIN_SHORT = -32768;
    public static long MAX_BYTE = 127;
    public static long MIN_BYTE = -128;
    public static BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    public static BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    public double doubleValue;
    public AbstractValue dataContainer;
    public Object data;
    public ValueDataType valueType;
    public long longValue;

    public NullValue nullData = new NullValue(this);
    public BoolValue booleanData = new BoolValue(this);
    public LongValue longData = new LongValue(this);
    public DoubleValue doubleData = new DoubleValue(this);
    public BytesValue bytesData = new BytesValue(this);
    public BytesBufValue bytesAData = new BytesBufValue(this);
    public ListValue listData = new ListValue(this);
    public MapValue mapData = new MapValue(this);
    public InstantPayloadValue instantData1 = new InstantPayloadValue(this);
    public InstantValue instantData = new InstantValue(this);


    public Value() {
        setDefaultValues();
    }

    public Value initializeWithData(byte b, byte[] bArr) {
        this.valueType = ValueDataType.UINSTANT_TYPE;
        this.dataContainer = this.instantData1;
        this.data = DataFactory.ofPayload(b, bArr);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return getValue().equals(obj);
    }

    public MapRetriever getAsMap() {
        if (isMapType()) {
            return (MapRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public Value setDefaultValues() {
        this.valueType = ValueDataType.NULL_TYPE;
        this.dataContainer = this.nullData;
        return this;
    }

    public <T extends ObjectData> Value setMapData(Map<T, T> map) {
        this.valueType = ValueDataType.MAP_DATA;
        this.dataContainer = this.mapData;
        ObjectData[] objectDataArr = new ObjectData[map.size() * 2];
        int i = 0;
        for (var entry : map.entrySet()) {
            objectDataArr[i] = entry.getKey();
            int i2 = i + 1;
            objectDataArr[i2] = entry.getValue();
            i = i2 + 1;
        }
        this.data = objectDataArr;
        return this;
    }

    public LongNumberRetriever getAsLong() {
        if (isLongType()) {
            return (LongNumberRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public boolean isMapType() {
        return getValueType().isMap();
    }

    public Value setArrayData(ObjectData[] objectDataArr) {
        this.valueType = ValueDataType.MAP_DATA;
        this.dataContainer = this.mapData;
        this.data = objectDataArr;
        return this;
    }

    public void write(MessageBuffer buf) throws IOException {
        this.dataContainer.write(buf);
    }

    public DataListRetriever getAsList() {
        if (isListType()) {
            return (DataListRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public BytesRetriever getAsByteArr() {
        if (isBytesType()) {
            return (BytesRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public boolean isNullType() {
        return getValueType().isNull();
    }

    public NullRetriever getAsNull() {
        if (isNullType()) {
            return (NullRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public DoubleRetriever getAsDouble() {
        if (isDoubleType()) {
            return (DoubleRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public boolean isInstantType() {
        return this.valueType == ValueDataType.INSTANT_TYPE;
    }

    public DataRetriever getValue() {
        return this.dataContainer.getValue();
    }

    public <T extends ObjectData> Value setListData(List<T> list) {
        this.valueType = ValueDataType.LIST_TYPE;
        this.dataContainer = this.listData;
        this.data = list.toArray(new ObjectData[0]);
        return this;
    }

    public PayloadRetriever getAsPayload() {
        if (isInstant1Type()) {
            return (PayloadRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public BoolRetriever getAsBool() {
        if (isBooleanType()) {
            return (BoolRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public BytesDataProvider getAsBytes() {
        if (isNullable()) {
            return (BytesDataProvider) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public Value setArrayDataWithType(ObjectData[] objectDataArr) {
        this.valueType = ValueDataType.LIST_TYPE;
        this.dataContainer = this.listData;
        this.data = objectDataArr;
        return this;
    }

    public boolean isVariableLength() {
        return getValueType().isVariableLength();
    }

    public DataTypes getValueType() {
        return this.valueType.getValueType();
    }

    public boolean isDoubleType() {
        return getValueType().isDouble();
    }

    public boolean isNullable() {
        return getValueType().isNullable();
    }

    public boolean isLongType() {
        return getValueType().isLong();
    }

    public Value setDataFromBytes(byte[] bArr) {
        this.valueType = ValueDataType.BYTES_TYPE;
        this.dataContainer = this.bytesAData;
        this.data = bArr;
        return this;
    }

    public Value setDataFromInstant(Instant instant) {
        this.valueType = ValueDataType.INSTANT_TYPE;
        this.dataContainer = this.instantData;
        this.data = DataFactory.ofInstant(instant);
        return this;
    }

    public InstantRetriever getAsInstant() {
        if (isInstantType()) {
            return (InstantRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public boolean isBooleanType() {
        return getValueType().isBoolean();
    }

    public Value setDataFromDouble(double d) {
        this.valueType = ValueDataType.DOUBLE_TYPE;
        this.dataContainer = this.doubleData;
        this.doubleValue = d;
        this.longValue = (long) d;
        return this;
    }

    public Value setDataFromBigInteger(BigInteger bigInteger) {
        if (0 > bigInteger.compareTo(MIN_LONG) || bigInteger.compareTo(MAX_LONG) > 0) {
            this.valueType = ValueDataType.BIGINTEGER_LONG_TYPE;
            this.dataContainer = this.longData;
            this.data = bigInteger;
        } else {
            this.valueType = ValueDataType.LONG_TYPE;
            this.dataContainer = this.longData;
            this.longValue = bigInteger.longValue();
        }
        return this;
    }

    public Value setDataFromString(String str) {
        return setDataFromBytes(str.getBytes(DataBufferUtils.charset));
    }

    public boolean isBytesBufType() {
        return getValueType().isBytesBuf();
    }

    public Value setDataFromBoolean(boolean z) {
        this.valueType = ValueDataType.BOOLEAN_TYPE;
        this.dataContainer = this.booleanData;
        this.longValue = z ? 1L : 0L;
        return this;
    }

    public NumberValueRetriever getValueRetriever() {
        if (isVariableLength()) {
            return (NumberValueRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public Value setDataFromLong(long j) {
        this.valueType = ValueDataType.LONG_TYPE;
        this.dataContainer = this.longData;
        this.longValue = j;
        return this;
    }

    public ByteBufRetriever getAsBytesBuf() {
        if (isBytesBufType()) {
            return (ByteBufRetriever) this.dataContainer;
        }
        throw new RuntimeException();
    }

    public String toString() {
        return getValue().toString();
    }

    public boolean isInstant1Type() {
        return getValueType().isInstant();
    }

    public boolean isBytesType() {
        return getValueType().isBytes();
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    public Value setDataFromFloat(float f) {
        this.valueType = ValueDataType.DOUBLE_TYPE;
        this.dataContainer = this.doubleData;
        this.longValue = (long) f;
        return this;
    }

    public boolean isListType() {
        return getValueType().isList();
    }

    public String getDataAsString() {
        return getValue().getDataAsString();
    }

    public Value setByteArrayData(byte[] bArr) {
        this.valueType = ValueDataType.BTYEARRAY_TYPE;
        this.dataContainer = this.bytesData;
        this.data = bArr;
        return this;
    }
}
