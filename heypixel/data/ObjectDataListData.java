package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.collections.ObjectDataIterator1;
import dev.undefinedteam.gensh1n.protocol.heypixel.collections.ObjectDataList;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



@StringEncryption
@ControlFlowObfuscation
public class ObjectDataListData extends AbstractDataRetriever implements IDataList {
    public static ObjectDataListData EMPTY = new ObjectDataListData(new ObjectData[0]);
    public ObjectData[] arr;

    public ObjectDataListData(ObjectData[] objectDataArr) {
        this.arr = objectDataArr;
    }

    public static void append(StringBuilder sb, ObjectData objectData) {
        if (objectData.isNullable()) {
            sb.append(objectData.getDataAsString());
        } else {
            sb.append(objectData);
        }
    }

    public static IDataList empty() {
        return EMPTY;
    }

    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }

    public DataTypes getValueType() {
        return DataTypes.LIST;
    }

    @Override
    public IDataList retrieveListData() {
        return this;
    }

    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    public String toString() {
        if (this.arr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        append(sb, this.arr[0]);
        for (int i = 1; i < this.arr.length; i++) {
            sb.append(",");
            append(sb, this.arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }

    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }

    @Override
    public boolean isLongType() {
        return super.isLongType();
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    @Override
    public DataListRetriever getAsList() {
        return retrieveListData();
    }

    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectData objectData)) {
            return false;
        }
        if (objectData instanceof ObjectDataListData) {
            return Arrays.equals(this.arr, ((ObjectDataListData) objectData).arr);
        }
        if (!objectData.isListType()) {
            return false;
        }
        DataListRetriever dataAsList = objectData.getAsList();
        if (size() != dataAsList.size()) {
            return false;
        }
        Iterator<ObjectData> it = dataAsList.iterator();
        for (ObjectData data : this.arr) {
            if (!it.hasNext() || !data.equals(it.next())) {
                return false;
            }
        }
        return true;
    }

    public DataRetriever getValue() {
        return Method17369();
    }

    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeShortWithSign(this.arr.length);
        for (ObjectData objectData : this.arr) {
            objectData.write(buf);
        }
    }

    public int size() {
        return this.arr.length;
    }

    public String getDataAsString() {
        if (this.arr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.arr[0].getDataAsString());
        for (int i = 1; i < this.arr.length; i++) {
            sb.append(",");
            sb.append(this.arr[i].getDataAsString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isNullType() {
        return super.isNullType();
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    @Override
    public ObjectRetriever getRetriever() {
        return super.getRetriever();
    }

    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    @Override
    public Iterator<ObjectData> iterator() {
        return new ObjectDataIterator1(this.arr);
    }

    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }

    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }

    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }

    @Override
    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }

    public IDataList Method17369() {
        return this;
    }


    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    public ObjectData get(int i) {
        return this.arr[i];
    }


    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }


    @Override
    public List<ObjectData> getObjectDataList() {
        return new ObjectDataList(this.arr);
    }


    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }


    @Override
    public boolean isNullable() {
        return super.isNullable();
    }


    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }


    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }


    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.arr.length; i2++) {
            i = (31 * i) + this.arr[i2].hashCode();
        }
        return i;
    }


    public ObjectData getNullable(int i) {
        return (i >= this.arr.length || i < 0) ? NullData.getNULL() : this.arr[i];
    }
}
