package dev.undefinedteam.gensh1n.protocol.heypixel.data.hex;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IHexBytesData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.BytesRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Arrays;



@StringEncryption
@ControlFlowObfuscation
public class HexBytesData extends HexEncodedData implements IHexBytesData {
    public HexBytesData(byte[] bArr) {
        super(bArr);
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return this;
    }

    public DataTypes getValueType() {
        return DataTypes.BYTES;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectData objectData)) {
            return false;
        }
        if (!objectData.isBytesType()) {
            return false;
        }
        if (!(objectData instanceof HexBytesData)) {
            return Arrays.equals(this.data, objectData.getAsByteArr().getBytes());
        }
        return Arrays.equals(this.data, ((HexBytesData) objectData).data);
    }

    public DataRetriever getValue() {
        return get();
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeVarInt(this.data.length);
        buf.writeBytes(this.data);
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public IHexBytesData get() {
        return this;
    }


    @Override
    public BytesRetriever getAsByteArr() {
        return this.retrieveByteArrayData();
    }
}
