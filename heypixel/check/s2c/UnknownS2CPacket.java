package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.EncryptDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBufferProcessor;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.UUID;


@StringEncryption
@ControlFlowObfuscation
public class UnknownS2CPacket extends HeypixelCheckPacket {


    public UUID uuid;
    public Long field_9461;

    public UnknownS2CPacket(PacketByteBuf friendlyByteBuf) {
        try {
            DataBufferProcessor processor = DataBufferUtils.create(helper.readByteArray(friendlyByteBuf));
            DataRetriever obj = processor.readObject();
            DataRetriever obj1 = processor.readObject();
            this.uuid = UUID.fromString(obj.retrieveBytesData().toString());
            this.field_9461 = obj1.retrieveLongNumber().getLong();
            new EncryptDataC2SPacket(getPacketId()).m(manager).sendCheckPacket();
            processor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
