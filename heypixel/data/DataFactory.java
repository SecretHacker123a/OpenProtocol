package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.collections.ObjectDataMap;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.hex.HexBytesData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.MapRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.math.BigInteger;
import java.time.Instant;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@StringEncryption
@ControlFlowObfuscation
public class DataFactory {

    public static IInstantBytes ofInstant(long j, int i) {
        return ofInstant(Instant.ofEpochSecond(j, i));
    }


    public static ILongNumberData ofLong(byte b) {
        return new LongData(b);
    }


    public static IBytesData ofPayload(byte b, byte[] bArr) {
        return new PayloadData(b, bArr);
    }


    public static IDataList of(ObjectData... objectDataArr) {
        return objectDataArr.length == 0 ? ObjectDataListData.empty() : new ObjectDataListData(Arrays.copyOf(objectDataArr, objectDataArr.length));
    }


    public static IDoubleData of(float f) {
        return new DoubleData(f);
    }


    public static IInstantBytes ofInstant(Instant instant) {
        return new InstantByteData(instant);
    }


    public static INullData createNULL() {
        return NullData.getNULL();
    }


    public static IBufferData of(byte[] bArr, boolean direct) {
        return direct ? new BufferData(bArr) : new BufferData(Arrays.copyOf(bArr, bArr.length));
    }


    public static <T extends ObjectData> IMapData<T> ofMap(T[] objectDataArr, boolean direct) {
        return objectDataArr.length == 0 ? MapData.empty() : direct ? new MapData<>(objectDataArr) : new MapData<>(Arrays.copyOf(objectDataArr, objectDataArr.length));
    }


    public static ILongNumberData ofLong(long j) {
        return new LongData(j);
    }


    public static ObjectDataMap newObjectMap() {
        return new ObjectDataMap();
    }


    public static IHexBytesData ofHex(byte[] bArr) {
        return ofHex(bArr, false);
    }


    public static IBufferData ofBuffer(String str) {
        return new BufferData(str);
    }


    public static IBufferData ofBuffer(byte[] bArr) {
        return new BufferData(bArr);
    }

    public static ILongNumberData ofLong(int i) {
        return new LongData(i);
    }


    public static IInstantBytes ofInstant(long j) {
        return ofInstant(Instant.ofEpochMilli(j));
    }


    public static IMapData<ObjectData> ofMap(Map<ObjectData, ObjectData> map) {
        ObjectData[] arr = new ObjectData[map.size() * 2];
        int i = 0;
        for (var entry : map.entrySet()) {
            arr[i] = entry.getKey();
            int index = i + 1;
            arr[index] = entry.getValue();
            i = index + 1;
        }
        return new MapData<>(arr);
    }


    public static ILongNumberData ofLong(short s) {
        return new LongData(s);
    }

    public static IBoolData ofBool(boolean val) {
        return val ? BooleanData.TRUE : BooleanData.FALSE;
    }


    public static Map.Entry<ObjectData, ObjectData> newEntry(ObjectData objectData, ObjectData unMapped72) {
        return new AbstractMap.SimpleEntry<>(objectData, unMapped72);
    }


    public static IBufferData ofBytes(byte[] bArr, int i, int i2) {
        return ofBuffer(bArr, i, i2, false);
    }


    public static IHexBytesData ofHex(byte[] bArr, int i, int i2) {
        return ofHex(bArr, i, i2, false);
    }


    public static IHexBytesData ofHex(byte[] bArr, boolean z) {
        return z ? new HexBytesData(bArr) : new HexBytesData(Arrays.copyOf(bArr, bArr.length));
    }


    @SafeVarargs
    public static <T extends ObjectData> MapRetriever<T> ofMap(Map.Entry<T, T>... entryArr) {
        T[] objectDataArr = (T[]) new ObjectData[entryArr.length * 2];
        for (int i = 0; i < entryArr.length; i++) {
            objectDataArr[i * 2] = entryArr[i].getKey();
            int i2 = i * 2;
            objectDataArr[(((i2 | 1) & ((-2) | ((-i2) - 1))) - ((-(((1 | ((-i2) - 1)) - ((-i2) - 1)) * 2)) - 1)) - 1] = entryArr[i].getValue();
        }
        return ofMap(objectDataArr, true);
    }


    public static ILongNumberData ofBigInteger(BigInteger bigInteger) {
        return new BigIntegerData(bigInteger);
    }


    public static IDataList emptyList() {
        return ObjectDataListData.empty();
    }


    public static IDoubleData ofDouble(double d) {
        return new DoubleData(d);
    }


    @SafeVarargs
    public static <T extends ObjectData> IMapData<T> ofArray(T... objectDataArr) {
        return objectDataArr.length == 0 ? MapData.empty() : new MapData<T>(Arrays.copyOf(objectDataArr, objectDataArr.length));
    }


    public static <T extends ObjectData> IMapData<T> emptyMap() {
        return MapData.empty();
    }


    public static IBufferData ofBuffer(byte[] bArr, int i, int i2, boolean z) {
        return (z && i == 0 && i2 == bArr.length) ? new BufferData(bArr) : new BufferData(Arrays.copyOfRange(bArr, i, i2));
    }


    public static IDataList ofList(ObjectData[] objectDataArr, boolean direct) {
        return objectDataArr.length == 0 ? ObjectDataListData.empty() : direct ? new ObjectDataListData(objectDataArr) : new ObjectDataListData(Arrays.copyOf(objectDataArr, objectDataArr.length));
    }


    public static IDataList ofList(List<ObjectData> list) {
        return list.isEmpty() ? ObjectDataListData.empty() : new ObjectDataListData(list.toArray(new ObjectData[0]));
    }


    public static IHexBytesData ofHex(byte[] bArr, int i, int i2, boolean z) {
        return (z && i == 0 && i2 == bArr.length) ? new HexBytesData(bArr) : new HexBytesData(Arrays.copyOfRange(bArr, i, i2));
    }
}
