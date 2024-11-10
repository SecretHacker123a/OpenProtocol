package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.MessageBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IDataList;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataListRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@StringEncryption
@ControlFlowObfuscation
public class ListValue extends AbstractValue implements DataListRetriever {
    public Value data;


    public ListValue(Value value) {
        super(value);
        this.data = value;
    }

    public void write(MessageBuffer buf) throws IOException {
        getList().write(buf);
    }

    public IDataList getList() {
        return DataFactory.of(getData());
    }

    @Override
    public DataListRetriever getAsList() {
        return this;
    }

    public DataTypes getValueType() {
        return DataTypes.LIST;
    }

    public ObjectData getNullable(int i) {
        ObjectData[] Method3443 = getData();
        return Method3443.length >= i ? Method3443[i] : DataFactory.createNULL();
    }

    public int size() {
        return getData().length;
    }

    public ObjectData[] getData() {
        return (ObjectData[]) this.data.data;
    }

    @Override
    public Iterator iterator() {
        return getObjectDataList().iterator();
    }


    public DataRetriever getValue() {
        return getList();
    }


    public ObjectData get(int i) {
        return getData()[i];
    }


    public List getObjectDataList() {
        return Arrays.asList(getData());
    }
}
