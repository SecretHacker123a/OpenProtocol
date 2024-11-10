package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.hex.HexEncodedData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IBufferData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.ByteBufRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Arrays;


@StringEncryption
@ControlFlowObfuscation
public class BufferData extends HexEncodedData implements IBufferData {

    public BufferData(byte[] bArr) {
        super(bArr);
    }

    public BufferData(String str) {
        super(str);
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeDirectInt(this.data.length);
        buf.writeBytes(this.data);
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public IBufferData getData() {
        return this;
    }

    @Override
    public IBufferData retrieveBytesData() {
        return this;
    }


    public DataRetriever getValue() {
        return getData();
    }


    @Override
    public ByteBufRetriever getAsBytesBuf() {
        return this.retrieveBytesData();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectData objectData)) {
            return false;
        }
        if (!objectData.isBytesBufType()) {
            return false;
        }
        if (!(objectData instanceof BufferData)) {
            return Arrays.equals(this.data, objectData.getAsBytesBuf().getBytes());
        }
        return Arrays.equals(this.data, ((BufferData) objectData).data);
    }

    public DataTypes getValueType() {
        return DataTypes.BYTES_BUF;
    }
}
