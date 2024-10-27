package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.EncryptDataC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.HeypixelVarUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.ArrayList;
import java.util.List;


@StringEncryption
@ControlFlowObfuscation
public class PlayerListDataS2CPacket extends HeypixelCheckPacket {


    public List<byte[]> playerList = new ArrayList<>();


    public int playerCount;

    public PlayerListDataS2CPacket(PacketByteBuf buf) {
        int readVarInt = HeypixelVarUtils.readVarInt(buf);
        for (int i = 0; i < readVarInt; i++) {
            this.playerList.add(helper.readByteArray(buf));
        }
        this.playerCount = HeypixelVarUtils.readVarInt(buf);
    }


    @Override
    public void handleClientSide(ClientPlayerEntity player) {
        manager.setSessionData(this);
        new EncryptDataC2SPacket(getPacketId()).m(manager).sendCheckPacketVanilla();
    }
}
