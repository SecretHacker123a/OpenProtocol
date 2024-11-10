package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Arrays;


@StringEncryption
@ControlFlowObfuscation
public class PayloadData extends AbstractDataRetriever implements IBytesData {
    public byte[] dataBytes;
    public byte dataType;

    public PayloadData(byte b, byte[] bArr) {
        this.dataType = b;
        this.dataBytes = bArr;
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    public DataRetriever getValue() {
        return get();
    }

    public IBytesData get() {
        return this;
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    public byte[] getDataBytes() {
        return this.dataBytes;
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
        if (!objectData.isInstant1Type()) {
            return false;
        }
        PayloadRetriever payload = objectData.getAsPayload();
        return this.dataType == payload.getDataType() && Arrays.equals(this.dataBytes, payload.getDataBytes());
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    @Override
    public ObjectRetriever getRetriever() {
        return super.getRetriever();
    }

    @Override
    public boolean isLongType() {
        return super.isLongType();
    }

    public byte getDataType() {
        return this.dataType;
    }

    @Override
    public PayloadRetriever getAsPayload() {
        return getDefaultType();
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    @Override
    public IBytesData getDefaultType() {
        return this;
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeSizePrefixedBytes(this.dataType, this.dataBytes.length);
        buf.writeBytes(this.dataBytes);
    }

    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }

    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }

    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }

    public DataTypes getValueType() {
        return DataTypes.INSTANT;
    }

    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }

    public int hashCode() {
        byte b = this.dataType;
        int i = (31 | b) + (b | (-32)) + 32;
        for (byte b2 : this.dataBytes) {
            int i2 = 31 * i;
            i = (i2 | b2) + ((b2 | ((-i2) - 1)) - ((-i2) - 1));
        }
        return i;
    }

    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }

    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }

    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(this.dataType);
        sb.append(",0x");
        for (byte b : this.dataBytes) {
            sb.append(Integer.toString(b, 16));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean isNullType() {
        return super.isNullType();
    }


    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }


    public String getDataAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(this.dataType);
        sb.append(",\"");
        for (byte b : this.dataBytes) {
            sb.append(Integer.toString(b, 16));
        }
        sb.append("\"]");
        return sb.toString();
    }


    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }
}
