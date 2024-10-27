package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IBytesData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.PayloadRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class InstantPayloadValue extends AbstractValue implements PayloadRetriever {
    public Value data;

    public InstantPayloadValue(Value value) {
        super(value);
        this.data = value;
    }

    public byte getDataType() {
        return ((IBytesData) this.data.data).getDataType();
    }

    public void write(MessageBuffer buf) throws IOException {
        ((IBytesData) this.data.data).write(buf);
    }

    public DataTypes getValueType() {
        return DataTypes.INSTANT;
    }

    public DataRetriever getValue() {
        return getData();
    }

    @Override
    public PayloadRetriever getAsPayload() {
        return this;
    }

    public byte[] getDataBytes() {
        return ((IBytesData) this.data.data).getDataBytes();
    }

    public IBytesData getData() {
        return (IBytesData) this.data.data;
    }
}
