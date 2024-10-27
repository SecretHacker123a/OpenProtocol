package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IBufferData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.ByteBufRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class BytesBufValue extends ByteBufValue implements ByteBufRetriever {
    public Value data;


    public BytesBufValue(Value value) {
        super(value);
        this.data = value;
    }


    public void write(MessageBuffer buf) throws IOException {
        byte[] bArr = (byte[]) this.data.data;
        buf.writeDirectInt(bArr.length);
        buf.writeBytes(bArr);
    }

    public DataTypes getValueType() {
        return DataTypes.BYTES_BUF;
    }


    public DataRetriever getValue() {
        return wrapByteArray();
    }


    public IBufferData wrapByteArray() {
        return DataFactory.ofBuffer((byte[]) this.data.data);
    }

    @Override
    public ByteBufRetriever getAsBytesBuf() {
        return this;
    }
}
