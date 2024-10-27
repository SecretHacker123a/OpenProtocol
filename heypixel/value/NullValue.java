package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.INullData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.NullRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;

@StringEncryption
@ControlFlowObfuscation
public class NullValue extends AbstractValue implements NullRetriever {

    public Value data;

    public NullValue(Value value) {
        super(value);
        this.data = value;
    }

    public DataTypes getValueType() {
        return DataTypes.NULL;
    }

    public INullData get() {
        return DataFactory.createNULL();
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeNull();
    }

    public DataRetriever getValue() {
        return get();
    }

    @Override
    public NullRetriever getAsNull() {
        return this;
    }
}
