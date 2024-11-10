package dev.undefinedteam.gensh1n.protocol.heypixel.check;

import dev.undefinedteam.gensh1n.protocol.heypixel.Heypixel;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.HeypixelVarUtils;
import dev.undefinedteam.gensh1n.utils.network.NetPayload;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@StringEncryption
@ControlFlowObfuscation
public class HeypixelCheckPacket {
    @NativeObfuscation.Inline
    public static final String channel = "heypixel:check";
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    public static BufferHelper helper = Heypixel.bufferHelper;

    @NativeObfuscation.Inline
    public static Map<Integer, Function<PacketByteBuf, HeypixelCheckPacket>> s2cMap = new HashMap<>();
    @NativeObfuscation.Inline
    public static Map<Class<? extends HeypixelCheckPacket>, Integer> c2sMap = new HashMap<>();

    protected HeypixelSessionManager manager;

    @NativeObfuscation.Inline
    public static void init() {
        registerC2S(0, EmptyC2SPacket.class);
        registerC2S(1, GameDataC2SPacket.class);
        registerC2S(2, HitResultC2SPacket.class);
        registerC2S(3, ReflectDataC2SPacket.class);
        registerC2S(4, EncryptDataC2SPacket.class);
        registerC2S(5, BlockStateC2SPacket.class);
        registerC2S(6, ClassesC2SPacket.class);

        registerS2C(100, UnknownS2CPacket::new);
        registerS2C(101, HeypixelKeysS2CPacket::new);
        registerS2C(102, TokenDataS2CPacket::new);
        registerS2C(103, SyncKeysS2CPacket::new);
        registerS2C(104, ReflectCheckS2CPacket::new);
        registerS2C(105, PlayerListDataS2CPacket::new);
        registerS2C(106, BlockPosS2CPacket::new);
        registerS2C(107, NeteaseCheckS2CPacket::new);
    }

    private static void registerC2S(int id, Class<? extends HeypixelCheckPacket> cls) {
        c2sMap.put(cls, id);
    }

    private static void registerS2C(int id, Function<PacketByteBuf, HeypixelCheckPacket> function) {
        s2cMap.put(id, function);
    }

    public static BufferHelper getHelper() {
        return helper;
    }

    public static String getChannel() {
        return channel;
    }

    public static Function<PacketByteBuf, HeypixelCheckPacket> newS2CInstance(int i) {
        return s2cMap.get(i);
    }

    public void handleClass(Class<? extends HeypixelCheckPacket> cls) {
    }

    public void processBuffer(PacketByteBuf buf, BufferHelper helper) {
    }

    public void handleClientSide(ClientPlayerEntity player) {
        throw new UnsupportedOperationException("This packet ( " + getPacketId() + ") does not implement a client side handler.");
    }

    public PacketByteBuf createPacketBuffer() {
        var buf = new PacketByteBuf(Unpooled.buffer());
        HeypixelVarUtils.writeUnsignedInt(buf, getPacketId());
        return buf;
    }

    public void sendCheckPacket() {
        try {
            DataBuffer buffer = DataBufferUtils.createBuffer();
            buffer.writeStringWithLength(Heypixel.get().clientId.toString());
            buffer.writeStringWithLength(Heypixel.get().getPlayerUUID());
            writeSessionData(buffer);

            var buf = new PacketByteBuf(Unpooled.buffer());
            HeypixelVarUtils.writeUnsignedInt(buf, getPacketId());
            helper.writeByteArray(buf, buffer.getBytes());

            Identifier channel = new Identifier(getChannel());
            NetPayload.send(channel, buf);
            buffer.close();

//            System.out.println("heypixel: send check packet " + getClass().getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendCheckPacketVanilla() {
        Identifier channel;
        if (manager.getPlayerList() == null || manager.getPlayerList().isEmpty()) {
            channel = new Identifier(getChannel());
        } else {
            channel = new Identifier(Heypixel.MOD_ID + ":" + manager.convertBytesToString((byte[]) manager.getPlayerList().get(manager.getPlayerCount())));
        }

        var buf = createPacketBuffer();
        processBuffer(buf, getHelper());

        NetPayload.send(channel, buf);
//        System.out.println("heypixel: send check packet vanilla");
    }

    public int getPacketId() {
        return c2sMap.getOrDefault(getClass(), -1);
    }

    public void writeSessionData(DataBuffer dataBuffer) throws IOException {
    }

    public <T extends HeypixelCheckPacket> T m(HeypixelSessionManager manager) {
        this.manager = manager;
        return (T) this;
    }
}
