package dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.LongData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.HeypixelVarUtils;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.*;


@StringEncryption
@ControlFlowObfuscation
public class ClassesC2SPacket extends HeypixelCheckPacket {
    public int size3;
    public int size2;
    public int size1;
    public int size;
    public int id;
    public Object obj;

    public ClassesC2SPacket(PacketByteBuf buf) {
        this.id = -2;
        this.size = -1;
        this.size1 = -1;
        this.size2 = -1;
        this.size3 = -1;
        this.obj = new HashSet();
    }

    public ClassesC2SPacket(int i, int i2, Object obj, int i3, int i4, int i5) {
        this.id = i;
        this.size = i2;
        this.obj = obj;
        this.size1 = i3;
        this.size2 = i4;
        this.size3 = i5;
    }

    @Override
    public void processBuffer(PacketByteBuf buf, BufferHelper bufferHelper) {
        HeypixelVarUtils.writeVarInt(buf, this.id);
        HeypixelVarUtils.writeUnsignedInt(buf, this.size);
        HeypixelVarUtils.writeUnsignedInt(buf, this.size1);
        HeypixelVarUtils.writeUnsignedInt(buf, this.size2);
        HeypixelVarUtils.writeUnsignedInt(buf, this.size3);
        if (this.obj instanceof Set) {
            ArrayList<String> arrayList = new ArrayList<>((Set<String>) this.obj);
            Collections.shuffle(arrayList);
            bufferHelper.writeStringCollection(buf, arrayList.subList(0, Math.min(arrayList.size(), 30)));
        }
        Object obj = this.obj;
        if (obj instanceof HashMap hashMap) {
            bufferHelper.writeStringCollection(buf, new ArrayList<>(((HashMap<String,String>) hashMap).keySet()));
            bufferHelper.writeStringCollection(buf, new ArrayList<>(((HashMap<String,String>) hashMap).values()));
        }
    }

    @Override
    public void writeSessionData(DataBuffer buf) {
        try {
            buf.writeObjectData(new LongData(this.id));
            buf.writeObjectData(new LongData(this.size));
            buf.writeObjectData(new LongData(this.size1));
            buf.writeObjectData(new LongData(this.size2));
            buf.writeObjectData(new LongData(this.size3));
            buf.writeObjectData(new LongData(System.currentTimeMillis()));

            if (this.obj instanceof Set) {
                ArrayList<String> classes = new ArrayList<>((Set<String>) this.obj);
                Collections.shuffle(classes);
                List<String> subbedClasses = classes.subList(0, Math.min(classes.size(), 20));
                ArrayList<ObjectData> classesList = new ArrayList<>();
                for (var o : subbedClasses) {
                    classesList.add(DataFactory.ofBuffer(o));
                }

                buf.writeObjectData(DataFactory.ofList(classesList));
            } else {
                Object obj = this.obj;
                if (obj instanceof HashMap map) {
                    List<Map.Entry<String, String>> classes = new ArrayList<>(map.entrySet());
                    Collections.shuffle(classes);
                    List<Map.Entry<String, String>> subbedClasses = classes.subList(0, Math.min(20, classes.size()));
                    HashMap<ObjectData, ObjectData> classesMap = new HashMap<>();
                    for (Map.Entry<String, String> entry : subbedClasses) {
                        classesMap.put(
                            DataFactory.ofBuffer(entry.getKey()),
                            DataFactory.ofBuffer(
                                entry.getValue() == null ? "null" : entry.getValue()
                            ));
                    }
                    buf.writeObjectData(DataFactory.ofMap(classesMap));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
