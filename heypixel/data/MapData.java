package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.collections.ObjectDataArrCollection;
import dev.undefinedteam.gensh1n.protocol.heypixel.collections.ObjectDataArrMap;
import dev.undefinedteam.gensh1n.protocol.heypixel.collections.ObjectDataEntrySet;
import dev.undefinedteam.gensh1n.protocol.heypixel.collections.ObjectDataSet;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


@StringEncryption
@ControlFlowObfuscation
public class MapData<T extends ObjectData> extends AbstractDataRetriever implements IMapData<T> {
    public static MapData<ObjectData> EMPTY = new MapData<ObjectData>(new ObjectData[0]);
    public T[] arr;

    public MapData(T[] objectDataArr) {
        this.arr = objectDataArr;
    }

    public static void append(StringBuilder sb, ObjectData objectData) {
        if (objectData.isNullable()) {
            sb.append(objectData.getDataAsString());
        } else {
            BufferData.appendUnicodeCharacter(sb, objectData.toString());
        }
    }

    public static void Method13793(StringBuilder sb, ObjectData objectData) {
        if (objectData.isNullable()) {
            sb.append(objectData.getDataAsString());
        } else {
            sb.append(objectData);
        }
    }

    public static <T extends ObjectData> IMapData<T> empty() {
        return (IMapData<T>) EMPTY;
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeSmallVarInt(this.arr.length / 2);
        for (T data : this.arr) {
            data.write(buf);
        }
    }

    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    public DataRetriever getValue() {
        return Method13805();
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    public int size() {
        return this.arr.length / 2;
    }

    @Override
    public IMapData<T> retrieveMapData() {
        return this;
    }

    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }

    public Set<Map.Entry<T, T>> entrySet() {
        return new ObjectDataEntrySet<>(this.arr);
    }

    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    public String getDataAsString() {
        if (this.arr.length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        append(sb, this.arr[0]);
        sb.append(":");
        sb.append(this.arr[1].getDataAsString());
        for (int i = 2; i < this.arr.length; i += 2) {
            sb.append(",");
            append(sb, this.arr[i]);
            sb.append(":");
            sb.append(this.arr[i + 1].getDataAsString());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }

    @Override
    public boolean isLongType() {
        return super.isLongType();
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
    public MapRetriever<T> getAsMap() {
        return retrieveMapData();
    }

    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectData objectData)) {
            return false;
        }
        if (!objectData.isMapType()) {
            return false;
        }
        return getMap().equals(objectData.getAsMap().getMap());
    }


    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }


    @Override
    public ObjectRetriever getRetriever() {
        return super.getRetriever();
    }


    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }


    public T[] getArray() {
        return Arrays.copyOf(this.arr, this.arr.length);
    }


    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }


    @Override
    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }


    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }


    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }


    public String toString() {
        if (this.arr.length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Method13793(sb, this.arr[0]);
        sb.append(":");
        Method13793(sb, this.arr[1]);
        for (int i = 2; i < this.arr.length; i += 2) {
            sb.append(",");
            Method13793(sb, this.arr[i]);
            sb.append(":");
            int i2 = i;
            Method13793(sb, this.arr[(i2 | 1) + ((1 | ((-i2) - 1)) - ((-i2) - 1))]);
        }
        sb.append("}");
        return sb.toString();
    }


    public Set<T> keySet() {
        return new ObjectDataSet<T>(this.arr);
    }


    public Map<T, T> getMap() {
        return new ObjectDataArrMap<>(this.arr);
    }


    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.arr.length; i2 += 2) {
            int i3 = i;
            int hashCode = this.arr[i2].hashCode();
            int hashCode2 = this.arr[i2 + 1].hashCode();
            int i4 = (hashCode | hashCode2) & (~((hashCode2 | ((-hashCode) - 1)) - ((-hashCode) - 1)));
            i = ((i3 | i4) * 2) - ((i3 | i4) & (((-i4) - 1) | ((-i3) - 1)));
        }
        return i;
    }

    public DataTypes getValueType() {
        return DataTypes.MAP;
    }


    public Collection<T> getCollection() {
        return new ObjectDataArrCollection<>(this.arr);
    }

    public IMapData Method13805() {
        return this;
    }


    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }


    @Override
    public boolean isNullType() {
        return super.isNullType();
    }


    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }
}
