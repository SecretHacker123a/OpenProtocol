package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.Base64;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.EncryptDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.charset.StandardCharsets;


@StringEncryption
@ControlFlowObfuscation
public class TokenDataS2CPacket extends HeypixelCheckPacket {
    public byte[] keyA;
    public byte[] ketC;
    public byte[] keyB;

    public TokenDataS2CPacket(PacketByteBuf friendlyByteBuf) {
        this.keyA = helper.readByteArray(friendlyByteBuf);
        this.keyB = helper.readByteArray(friendlyByteBuf);
        this.ketC = helper.readByteArray(friendlyByteBuf);
    }


    @Override
    public void handleClientSide(ClientPlayerEntity player) {
        var r0 = new String(this.keyA, StandardCharsets.UTF_8);
        var r1 = new String(this.keyB, StandardCharsets.UTF_8);
        var r2 = new String(this.ketC, StandardCharsets.UTF_8);

        var s1 = new String(Base64.deocde(r0), StandardCharsets.UTF_8);
        var s2 = new String(Base64.deocde(r1), StandardCharsets.UTF_8);
        var s3 = new String(Base64.deocde(r2), StandardCharsets.UTF_8);

        var splitStr = " ";
        for (char c : s1.toCharArray()) {
            try {
                Integer.valueOf(c + "");
            } catch (NumberFormatException e) {
                splitStr = c + "";
                break;
            }
        }

        String[] split = s1.split(splitStr);
        String[] split2 = s2.split(splitStr);
        String[] split3 = s3.split(splitStr);

        manager.tokenMap.put(manager.decodeString(split[0]), manager.decodeString(split[1]));
        manager.tokenMap.put(manager.decodeString(split2[0]), manager.decodeString(split2[1]));
        manager.tokenMap.put(manager.decodeString(split3[0]), manager.decodeString(split3[1]));
        new EncryptDataC2SPacket(getPacketId()).m(manager).sendCheckPacketVanilla();
    }
}
