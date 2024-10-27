package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.EncryptDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.List;


@StringEncryption
@ControlFlowObfuscation
public class HeypixelKeysS2CPacket extends HeypixelCheckPacket {
    public byte[] unknownBytes;
    public byte[] encryptModeA;
    public List<Integer> keys;
    public byte[] encryptKey;
    public byte[] serverToken;
    public List<Integer> unknownArr;
    public byte[] encryptModeB;
    public byte[] clientToken;
    public byte[] serverId;


    public HeypixelKeysS2CPacket(PacketByteBuf buf) {
        this.unknownArr = helper.readUnsignedByteList(buf);
        this.keys = helper.readUnsignedByteList(buf);
        this.encryptModeA = helper.readByteArray(buf);
        this.encryptModeB = helper.readByteArray(buf);
        this.unknownBytes = helper.readByteArray(buf);
        this.clientToken = helper.readByteArray(buf);
        this.serverId = helper.readByteArray(buf);
        this.serverToken = helper.readByteArray(buf);
        this.encryptKey = helper.readByteArray(buf);

    }

    @Override
    public void handleClientSide(ClientPlayerEntity player) {
        manager.unknownArray = this.unknownArr.stream().mapToInt(Integer::intValue).toArray();
        manager.decodeKeys = this.keys.stream().mapToInt(Integer::intValue).toArray();
        manager.encryptMode1 = this.encryptModeA;
        manager.encryptMode = this.encryptModeB;
        manager.unknownBytes = this.unknownBytes;
        manager.clientToken = this.clientToken;
        manager.serverId = this.serverId;
        manager.serverToken = this.serverToken;
        manager.encryptionKey = this.encryptKey;
        new EncryptDataC2SPacket(getPacketId()).m(manager).sendCheckPacketVanilla();
    }
}
