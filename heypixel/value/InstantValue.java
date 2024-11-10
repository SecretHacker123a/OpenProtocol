package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IInstantBytes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.InstantRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.time.Instant;


@StringEncryption
@ControlFlowObfuscation
public class InstantValue extends AbstractValue implements InstantRetriever {
    public Value data;

    public InstantValue(Value value) {
        super(value);
        this.data = value;
    }

    public void write(MessageBuffer buf) throws IOException {
        ((IInstantBytes) this.data.data).write(buf);
    }


    public int getNano() {
        return ((IInstantBytes) this.data.data).getNano();
    }

    public IInstantBytes get() {
        return (IInstantBytes) this.data.data;
    }

    @Override
    public InstantRetriever getAsInstant() {
        return this;
    }


    public byte[] getDataBytes() {
        return ((IInstantBytes) this.data.data).getDataBytes();
    }


    public Instant getInstant() {
        return ((IInstantBytes) this.data.data).getInstant();
    }


    public DataRetriever getValue() {
        return get();
    }


    public long getEpochSecond() {
        return ((IInstantBytes) this.data.data).getEpochSecond();
    }


    public byte getDataType() {
        return ((IInstantBytes) this.data.data).getDataType();
    }


    public long getEpochMilli() {
        return ((IInstantBytes) this.data.data).getEpochMilli();
    }

    @Override
    public boolean isInstantType() {
        return true;
    }

    public DataTypes getValueType() {
        return DataTypes.INSTANT;
    }
}
