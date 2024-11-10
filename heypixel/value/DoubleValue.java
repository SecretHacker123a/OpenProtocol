package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IDoubleData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DoubleRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class DoubleValue extends NumberValue implements DoubleRetriever {
    public Value data;


    public DoubleValue(Value value) {
        super(value);
        this.data = value;
    }

    public IDoubleData get() {
        return DataFactory.ofDouble(this.data.doubleValue);
    }

    public DataTypes getValueType() {
        return DataTypes.DOUBLE;
    }

    @Override
    public DoubleRetriever getAsDouble() {
        return this;
    }

    public DataRetriever getValue() {
        return get();
    }


    public void write(MessageBuffer buf) throws IOException {
        buf.writeDoubleLE(this.data.doubleValue);
    }
}
