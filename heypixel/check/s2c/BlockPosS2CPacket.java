package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.Heypixel;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.EncryptDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBufferProcessor;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s.BlockStateC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public class BlockPosS2CPacket extends HeypixelCheckPacket {


    public BlockPos blockPos;

    public BlockPosS2CPacket(PacketByteBuf buf) {
        try {
            DataBufferProcessor processor = DataBufferUtils.create(helper.readByteArray(buf));
            DataRetriever uuid = processor.readObject();
            DataRetriever posX = processor.readObject();
            DataRetriever posY = processor.readObject();
            DataRetriever posZ = processor.readObject();
            if (uuid.retrieveBytesData().toString().equals(Heypixel.get().getPlayerUUID())) {
                this.blockPos = new BlockPos(
                    posX.retrieveLongNumber().toInt(),
                    posY.retrieveLongNumber().toInt(),
                    posZ.retrieveLongNumber().toInt()
                );
            } else {
                this.blockPos = new BlockPos(
                    processor.readObject().retrieveLongNumber().toInt(),
                    processor.readObject().retrieveLongNumber().toInt(),
                    processor.readObject().retrieveLongNumber().toInt()
                );
            }
            processor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void handleClientSide(ClientPlayerEntity player) {
        BlockStateC2SPacket.asyncCheck(manager, this.blockPos);
        new EncryptDataC2SPacket(getPacketId()).m(manager).sendCheckPacket();
    }
}
