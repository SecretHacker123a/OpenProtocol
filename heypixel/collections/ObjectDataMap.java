package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.MapRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectDataMap {
    public Map<ObjectData, ObjectData> map = new LinkedHashMap<>();

    public MapRetriever<ObjectData> toMapData() {
        return DataFactory.ofMap(this.map);
    }

    public ObjectDataMap put(ObjectData objectData, ObjectData unMapped72) {
        this.map.put(objectData, unMapped72);
        return this;
    }


    public ObjectDataMap put(Map.Entry<ObjectData, ObjectData> entry) {
        put(entry.getKey(), entry.getValue());
        return this;
    }


    public ObjectDataMap putAll(Map<ObjectData, ObjectData> map) {
        for (Map.Entry<ObjectData, ObjectData> objectDataObjectDataEntry : map.entrySet()) {
            put(objectDataObjectDataEntry);
        }
        return this;
    }


    public ObjectDataMap putAll(Iterable<Map.Entry<ObjectData, ObjectData>> iterable) {
        for (var o : iterable) {
            put(o.getKey(), o.getValue());
        }
        return this;
    }
}
