package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IMapData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.MapRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


@StringEncryption
@ControlFlowObfuscation
public class MapValue extends AbstractValue implements MapRetriever<ObjectData> {
    public Value data;

    public MapValue(Value value) {
        super(value);
        this.data = value;
    }

    public IMapData<ObjectData> get() {
        return DataFactory.ofArray(getArray());
    }

    public Set<ObjectData> keySet() {
        return get().keySet();
    }

    public Map<ObjectData, ObjectData> getMap() {
        return get().getMap();
    }

    public DataRetriever getValue() {
        return get();
    }

    public DataTypes getValueType() {
        return DataTypes.MAP;
    }

    public void write(MessageBuffer buf) throws IOException {
        get().write(buf);
    }

    public int size() {
        return getArray().length / 2;
    }

    public Set<Map.Entry<ObjectData, ObjectData>> entrySet() {
        return get().entrySet();
    }

    public ObjectData[] getArray() {
        return (ObjectData[]) this.data.data;
    }

    public Collection<ObjectData> getCollection() {
        return get().getCollection();
    }

    @Override
    public MapRetriever<ObjectData> getAsMap() {
        return this;
    }
}
