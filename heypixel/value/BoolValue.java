package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IBoolData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.BoolRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class BoolValue extends AbstractValue implements BoolRetriever {
    public Value data;

    public BoolValue(Value value) {
        super(value);
        this.data = value;
    }

    public void write(MessageBuffer buf) throws IOException {
        buf.writeBoolean(this.data.longValue == 1);
    }

    public IBoolData Method14165() {
        return DataFactory.ofBool(getBoolean());
    }

    public boolean getBoolean() {
        return this.data.longValue == 1;
    }

    public DataTypes getValueType() {
        return DataTypes.BOOLEAN;
    }

    public DataRetriever getValue() {
        return Method14165();
    }

    @Override
    public BoolRetriever getAsBool() {
        return this;
    }
}
