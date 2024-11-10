package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.util.TriConsumer;
import org.joml.Vector2f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector3i;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.*;


@StringEncryption
@ControlFlowObfuscation
public class BufferHelper {

    @NativeObfuscation.Inline
    public static int readVarIntAsInteger(ByteBuf byteBuf, BufferHelper bufferHelper) {
        return HeypixelVarUtils.readVarInt(byteBuf);
    }

    @NativeObfuscation.Inline
    public static String readString(ByteBuf byteBuf, BufferHelper bufferHelper) {
        return bufferHelper.readString(byteBuf);
    }

    @NativeObfuscation.Inline
    public void writeScaledFloat(ByteBuf byteBuf, float f) {
        byteBuf.writeByte((byte) (f / 1.40625f));
    }

    @NativeObfuscation.Inline
    public void writeArray(ByteBuf byteBuf, Object[] objArr, BiConsumer<ByteBuf, Object> biConsumer) {
        HeypixelVarUtils.writeUnsignedInt(byteBuf, objArr.length);
        for (Object obj : objArr) {
            biConsumer.accept(byteBuf, obj);
        }
    }

    @NativeObfuscation.Inline
    public <T> void writeCollection(ByteBuf byteBuf, Collection<T> collection, ToLongFunction<ByteBuf> toLongFunction, BiFunction<ByteBuf, BufferHelper, T> biFunction) {
        long applyAsLong = toLongFunction.applyAsLong(byteBuf);
        for (int i = 0; i < applyAsLong; i++) {
            collection.add(biFunction.apply(byteBuf, this));
        }
    }

    @NativeObfuscation.Inline
    public <T> void readCollection(ByteBuf byteBuf, Collection<T> collection, Function<ByteBuf, T> function) {
        int readVarInt = HeypixelVarUtils.readVarInt(byteBuf);
        for (int i = 0; i < readVarInt; i++) {
            collection.add(function.apply(byteBuf));
        }
    }

    public void writeVector3i(ByteBuf byteBuf, Vector3i vector3i) {
        HeypixelVarUtils.writeVarInt(byteBuf, vector3i.x);
        HeypixelVarUtils.writeUnsignedInt(byteBuf, vector3i.y);
        HeypixelVarUtils.writeVarInt(byteBuf, vector3i.z);
    }

    @NativeObfuscation.Inline
    public void writeVector3iAlt(ByteBuf byteBuf, Vector3i vector3i) {
        HeypixelVarUtils.writeVarInt(byteBuf, vector3i.x);
        HeypixelVarUtils.writeVarInt(byteBuf, vector3i.y);
        HeypixelVarUtils.writeVarInt(byteBuf, vector3i.z);
    }

    public ByteBuf readRetainedSlice(ByteBuf byteBuf) {
        return byteBuf.readRetainedSlice(HeypixelVarUtils.readVarInt(byteBuf));
    }

    @NativeObfuscation.Inline
    public <T> void readCustomCollection(ByteBuf byteBuf, Collection<T> collection, BiFunction<ByteBuf, BufferHelper, T> biFunction) {
        writeCollection(byteBuf, collection, HeypixelVarUtils::readVarInt, biFunction);
    }

    public void writeVector2(ByteBuf byteBuf, Vector2f vector2f) {
        byteBuf.writeFloatLE(vector2f.x());
        byteBuf.writeFloatLE(vector2f.y());
    }

    public Object[] toArray(ByteBuf byteBuf, Object[] objArr, BiFunction<ByteBuf, BufferHelper, Object> biFunction) {
        ObjectArrayList<Object> objectArrayList = new ObjectArrayList<>();
        readCustomCollection(byteBuf, objectArrayList, biFunction);
        return objectArrayList.toArray(objArr);
    }

    public void writeByteArray(ByteBuf byteBuf, byte[] bArr) {
        HeypixelVarUtils.writeUnsignedInt(byteBuf, bArr.length + 1);
        byteBuf.writeBytes(bArr);
    }

    @NativeObfuscation.Inline
    public void writeUUID(ByteBuf byteBuf, UUID uuid) {
        byteBuf.writeLongLE(uuid.getMostSignificantBits());
        byteBuf.writeLongLE(uuid.getLeastSignificantBits());
    }

    public Object[] readObjectArray(ByteBuf byteBuf, Object[] objArr, Function<ByteBuf, Object> function) {
        ObjectArrayList<Object> objectArrayList = new ObjectArrayList<>();
        readCollection(byteBuf, objectArrayList, function);
        return objectArrayList.toArray(objArr);
    }

    @NativeObfuscation.Inline
    public void writeVec3(ByteBuf byteBuf, Vec3d vec3) {
        byteBuf.writeDoubleLE(vec3.x);
        byteBuf.writeDoubleLE(vec3.y);
        byteBuf.writeDoubleLE(vec3.z);
    }

    public Vector3f readVec3f(ByteBuf byteBuf) {
        return new Vector3f(byteBuf.readFloatLE(), byteBuf.readFloatLE(), byteBuf.readFloatLE());
    }

    @NativeObfuscation.Inline
    public void writeBlockHitResult(PacketByteBuf buf, BlockHitResult result) {
        var blockPos = result.getBlockPos();
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        var location = result.getPos();
        var player = MinecraftClient.getInstance().player;
        var eyePosition = player.getEyePos();
        writeVector3iAlt(buf, new Vector3i(x, y, z));
        HeypixelVarUtils.writeVarInt(buf, result.getSide().ordinal());
        HeypixelVarUtils.writeVarInt(buf, result.getType().ordinal());
        buf.writeFloat((float) (location.x - blockPos.getX()));
        buf.writeFloat((float) (location.y - blockPos.getY()));
        buf.writeFloat((float) (location.z - blockPos.getZ()));
        buf.writeBoolean(result.isInsideBlock());
        buf.writeDouble(eyePosition.x);
        buf.writeDouble(eyePosition.y);
        buf.writeDouble(eyePosition.z);
        buf.writeFloat(player.getYaw());
        buf.writeFloat(player.getPitch());
    }

    public byte[] readByteArray(ByteBuf byteBuf) {
        int readVarInt = HeypixelVarUtils.readVarInt(byteBuf);
        byte[] bArr = new byte[readVarInt];
        byteBuf.readBytes(bArr);
        return bArr;
    }

    public void writeBuffer(ByteBuf byteBuf, ByteBuf byteBuf2) {
        HeypixelVarUtils.writeUnsignedInt(byteBuf, byteBuf2.readableBytes());
        byteBuf.writeBytes(byteBuf2, byteBuf2.readerIndex(), byteBuf2.writerIndex());
    }

    public void writeString(ByteBuf byteBuf, String str) {
        HeypixelVarUtils.writeUnsignedInt(byteBuf, ByteBufUtil.utf8Bytes(str));
        byteBuf.writeCharSequence(str, StandardCharsets.UTF_8);
    }

    public void writeVec2(ByteBuf byteBuf, Vec2f vec2) {
        writeVector2(byteBuf, new Vector2f(vec2.x, vec2.y));
    }

    public <T> void writeCollection(ByteBuf byteBuf, Collection<T> collection, ObjIntConsumer<ByteBuf> objIntConsumer, TriConsumer<ByteBuf, BufferHelper, T> triConsumer) {
        objIntConsumer.accept(byteBuf, collection.size());
        for (T o : collection) {
            triConsumer.accept(byteBuf, this, o);
        }
    }

    public List<Integer> readUnsignedByteList(ByteBuf byteBuf) {
        ObjectArrayList<Integer> objectArrayList = new ObjectArrayList<>();
        writeCollection(byteBuf, objectArrayList, ByteBuf::readUnsignedByte, BufferHelper::readVarIntAsInteger);
        return objectArrayList;
    }

    public <T> void writeObjectArray(ByteBuf byteBuf, T[] objArr, TriConsumer<ByteBuf, BufferHelper, T> triConsumer) {
        HeypixelVarUtils.writeUnsignedInt(byteBuf, objArr.length);
        for (T obj : objArr) {
            triConsumer.accept(byteBuf, this, obj);
        }
    }

    public Vector3i readZigZagVector3i(ByteBuf byteBuf) {
        return new Vector3i(HeypixelVarUtils.readZigZagInt(byteBuf), HeypixelVarUtils.readVarInt(byteBuf), HeypixelVarUtils.readZigZagInt(byteBuf));
    }

    public void handleHitResult(PacketByteBuf buf, HitResult result) {
    }

    public String readString(ByteBuf byteBuf) {
        return (String) byteBuf.readCharSequence(HeypixelVarUtils.readVarInt(byteBuf), StandardCharsets.UTF_8);
    }

    public void writeVector3f(ByteBuf byteBuf, Vector3f vector3f) {
        byteBuf.writeFloatLE(vector3f.x());
        byteBuf.writeFloatLE(vector3f.y());
        byteBuf.writeFloatLE(vector3f.z());
    }

    public void writeEnum(ByteBuf byteBuf, Enum<?> r5) {
        HeypixelVarUtils.writeVarInt(byteBuf, r5.ordinal());
    }

    public UUID readUUID(ByteBuf byteBuf) {
        return new UUID(byteBuf.readLongLE(), byteBuf.readLongLE());
    }

    public void writeVec3(ByteBuf byteBuf, Vector3d vec3) {
        byteBuf.writeDoubleLE(vec3.x);
        byteBuf.writeDoubleLE(vec3.y);
        byteBuf.writeDoubleLE(vec3.z);
    }

    public List<String> readCustomStringList(ByteBuf byteBuf) {
        ObjectArrayList<String> objectArrayList = new ObjectArrayList<>();
        writeCollection(byteBuf, objectArrayList, ByteBuf::readUnsignedByte, BufferHelper::readString);
        return objectArrayList;
    }

    public void writeStringCollection(ByteBuf byteBuf, List<String> list) {
        writeCollectionWithBiConsumer(byteBuf, list, this::writeString);
    }

    public void writeIntegerCollection(ByteBuf byteBuf, List<Integer> list) {
        writeCollectionWithBiConsumer(byteBuf, list, HeypixelVarUtils::writeUnsignedInt);
    }

    public Vector3i readFullZigZagVector3i(ByteBuf byteBuf) {
        return new Vector3i(HeypixelVarUtils.readZigZagInt(byteBuf), HeypixelVarUtils.readZigZagInt(byteBuf), HeypixelVarUtils.readZigZagInt(byteBuf));
    }

    public Vector2f readVector2(ByteBuf byteBuf) {
        return new Vector2f(byteBuf.readFloatLE(), byteBuf.readFloatLE());
    }

    public <T> void writeCollectionWithBiConsumer(ByteBuf byteBuf, Collection<T> collection, BiConsumer<ByteBuf, T> biConsumer) {
        HeypixelVarUtils.writeUnsignedInt(byteBuf, collection.size());
        for (T o : collection) {
            biConsumer.accept(byteBuf, o);
        }
    }

    public float readScaledFloat(ByteBuf byteBuf) {
        return byteBuf.readByte() * 1.40625f;
    }
}
