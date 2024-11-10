package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.EncryptDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.Arrays;


@StringEncryption
@ControlFlowObfuscation
public class SyncKeysS2CPacket extends HeypixelCheckPacket {


    public byte[] keyListB;


    public byte[] keyListC;


    public byte[] keyB;


    public byte[] keyA;


    public byte[] keyC;


    public byte[] keyListA;


    public SyncKeysS2CPacket(PacketByteBuf buf) {
        this.keyA = helper.readByteArray(buf);
        this.keyListA = helper.readByteArray(buf);
        this.keyB = helper.readByteArray(buf);
        this.keyListB = helper.readByteArray(buf);
        this.keyC = helper.readByteArray(buf);
        this.keyListC = helper.readByteArray(buf);
    }

    @Override
    public void handleClientSide(ClientPlayerEntity player) {
        manager.processSyncKeys(this);
        new EncryptDataC2SPacket(getPacketId()).m(manager).sendCheckPacketVanilla();
    }

    public String toString() {
        return "SyncKeysPacket(keyA=" +
            Arrays.toString(this.keyA) +
            ", keyListA=" + Arrays.toString(this.keyListA) + ", " +
            "keyB=" + Arrays.toString(this.keyB) +
            ", keyListB=" + Arrays.toString(this.keyListB) +
            ", keyC=" + Arrays.toString(this.keyC) +
            ", keyListC=" + Arrays.toString(this.keyListC) + ")";
    }
}
