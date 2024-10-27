package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IHexBytesData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.BytesRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class BytesValue extends ByteBufValue implements BytesRetriever {
    public Value data;


    public BytesValue(Value value) {
        super(value);
        this.data = value;
    }

    public void write(MessageBuffer buf) throws IOException {
        byte[] bArr = (byte[]) this.data.data;
        buf.writeVarInt(bArr.length);
        buf.writeBytes(bArr);
    }

    public DataRetriever getValue() {
        return Method11135();
    }

    public DataTypes getValueType() {
        return DataTypes.BYTES;
    }


    public IHexBytesData Method11135() {
        return DataFactory.ofHex(getBytes());
    }

    @Override
    public BytesRetriever getAsByteArr() {
        return this;
    }
}
