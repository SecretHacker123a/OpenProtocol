package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;


@StringEncryption
@ControlFlowObfuscation
public class InstantByteData extends AbstractDataRetriever implements IBytesData, IInstantBytes {
    public byte[] data;
    public Instant instant;
    public InstantByteData(Instant instant) {
        this.instant = instant;
    }

    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    @Override
    public ObjectRetriever getRetriever() {
        return super.getRetriever();
    }

    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }

    public byte[] getDataBytes() {
        byte[] bArr;
        if (this.data == null) {
            long epochSecond = getEpochSecond();
            int nano = getNano();
            if ((epochSecond >>> 34) == 0) {
                long j = ((long) nano << 34) | epochSecond;
                if ((j & (-4294967296L)) == 0) {
                    bArr = new byte[4];
                    UBuffer.createFromByteArray(bArr).putInt(0, (int) epochSecond);
                } else {
                    bArr = new byte[8];
                    UBuffer.createFromByteArray(bArr).putLong(0, j);
                }
            } else {
                bArr = new byte[12];
                UBuffer createFromByteArray = UBuffer.createFromByteArray(bArr);
                createFromByteArray.putInt(0, nano);
                createFromByteArray.putLong(4, epochSecond);
            }
            this.data = bArr;
        }
        return this.data;
    }

    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }

    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }

    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }

    public long getEpochMilli() {
        return this.instant.toEpochMilli();
    }

    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
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
        PayloadRetriever Method11267 = objectData.getAsPayload();
        if (Method11267 instanceof InstantRetriever) {
            return this.instant.equals(((InstantRetriever) Method11267).getInstant());
        }
        return -1 == Method11267.getDataType() && Arrays.equals(getDataBytes(), Method11267.getDataBytes());
    }

    public DataTypes getValueType() {
        return DataTypes.INSTANT;
    }

    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    @Override
    public boolean isInstantType() {
        return true;
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override
    public InstantRetriever getAsInstant() {
        return this.retrieveInstantData();
    }

    public IInstantBytes Method11039() {
        return this;
    }

    @Override
    public boolean isLongType() {
        return super.isLongType();
    }

    public int hashCode() {
        return this.instant.hashCode();
    }

    @Override
    public PayloadRetriever getAsPayload() {
        return getDefaultType();
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    @Override
    public IBytesData getDefaultType() {
        return this;
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    public String getDataAsString() {
        return "\"" + getInstant().toString() + "\"";
    }

    public byte getDataType() {
        return (byte) -1;
    }

    public Instant getInstant() {
        return this.instant;
    }

    public String toString() {
        return getInstant().toString();
    }

    @Override
    public IInstantBytes retrieveInstantData() {
        return this;
    }

    @Override
    public BytesDataRetriever retrieveBytes() {
        return super.retrieveBytes();
    }

    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }

    public long getEpochSecond() {
        return this.instant.getEpochSecond();
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeInstant(this.instant);
    }

    public int getNano() {
        return this.instant.getNano();
    }

    @Override
    public boolean isNullType() {
        return super.isNullType();
    }


    public DataRetriever getValue() {
        return Method11039();
    }


    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }
}
