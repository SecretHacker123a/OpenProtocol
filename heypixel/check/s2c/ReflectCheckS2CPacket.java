package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.EncryptDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s.ReflectDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.UUID;


@StringEncryption
@ControlFlowObfuscation
public class ReflectCheckS2CPacket extends HeypixelCheckPacket {


    public UUID uuid;


    public String data;


    public ReflectCheckS2CPacket(PacketByteBuf friendlyByteBuf) {
        try {
            var invoke = DataBufferUtils.create(
                helper.readByteArray(friendlyByteBuf)
            );

            var invoke2 = invoke.readObject();
            var invoke3 = invoke.readObject();
            if (invoke2 == null) {
                throw new NullPointerException("Rid is null");
            }
            this.uuid = UUID.fromString(invoke2.toString());
            this.data = invoke3.toString();
            invoke.close();
        } catch (Throwable th) {
            if (this.uuid == null) {
                this.uuid = UUID.randomUUID();
            }
            if (this.data == null) {
                this.data = UUID.randomUUID().toString();
            }
            throw new RuntimeException(th);
        }
    }

    @Override
    public void handleClientSide(ClientPlayerEntity player) {
        if (this.data.equals("SCI")) { // Send Client Info
            manager.asyncSendClientData();
        } else {
            ReflectDataC2SPacket.sendCheckPacket(manager, this.data);
            new EncryptDataC2SPacket(getPacketId()).m(manager).sendCheckPacket();
        }
    }
}
