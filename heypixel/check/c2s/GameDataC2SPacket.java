package dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s;

import dev.undefinedteam.gensh1n.protocol.heypixel.Heypixel;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelSessionManager;
import dev.undefinedteam.gensh1n.protocol.heypixel.value.Value;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.HeypixelVarUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.HitResult;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@StringEncryption
@ControlFlowObfuscation
public class GameDataC2SPacket extends HeypixelCheckPacket {


    public Entity entity;


    public ClientPlayerEntity player;


    public HitResult hitResult;

    public GameDataC2SPacket(ClientPlayerEntity player, Entity entity) {
        this.player = player;
        this.entity = entity;
        this.hitResult = mc.crosshairTarget;
    }


    public GameDataC2SPacket(PacketByteBuf friendlyByteBuf) {
    }

    public static void check(HeypixelSessionManager manager, ClientPlayerEntity player, Entity entity) {
        var minecraft = MinecraftClient.getInstance();
        if (minecraft.getCameraEntity() == null || minecraft.world == null || mc.crosshairTarget == null) {
            return;
        }
        new GameDataC2SPacket(player, entity).m(manager).sendCheckPacketVanilla();
    }


    @Override
    public void writeSessionData(DataBuffer dataBuffer) {
        try {
            var uuid = this.entity.getUuidAsString().equals(Heypixel.get().getPlayerUUID()) ? Heypixel.get().getPlayerUUID() : entity.getUuidAsString();
            dataBuffer.writeObjectData(new Value().setDataFromString(uuid));
            dataBuffer.writeSignedVarInt(this.hitResult.getType().ordinal());
            dataBuffer.writeDoubleLE(this.hitResult.getPos().x);
            dataBuffer.writeDoubleLE(this.hitResult.getPos().y);
            dataBuffer.writeDoubleLE(this.hitResult.getPos().z);
            dataBuffer.writeObjectData(new Value().setDataFromLong(this.player.getPose().ordinal()));
            dataBuffer.writeObjectData(new Value().setListData(
                List.of(
                    new Value().setDataFromDouble(this.player.getPos().x),
                    new Value().setDataFromDouble(this.player.getPos().y),
                    new Value().setDataFromDouble(this.player.getPos().z)
                )));
            dataBuffer.writeFloatLE(this.player.getRotationClient().x);
            dataBuffer.writeFloatLE(this.player.getRotationClient().y);

            dataBuffer.writeSignedVarInt(this.entity.getPose().ordinal());
            dataBuffer.writeDoubleLE(this.entity.getPos().x);
            dataBuffer.writeDoubleLE(this.entity.getPos().y);
            dataBuffer.writeDoubleLE(this.entity.getPos().z);
            dataBuffer.writeObjectData(new Value().setDataFromFloat(this.entity.getRotationClient().x));
            dataBuffer.writeObjectData(new Value().setDataFromFloat(this.entity.getRotationClient().y));
        } catch (IOException e) {
        }
    }

    @Override
    public void processBuffer(PacketByteBuf buf, BufferHelper bufferHelper) {
        var uuid = this.entity.getUuidAsString().equals(Heypixel.get().getPlayerUUID()) ? Heypixel.get().getPlayerUUID() : entity.getUuidAsString();
        bufferHelper.writeUUID(buf, UUID.fromString(uuid));
        HeypixelVarUtils.writeVarInt(buf, this.hitResult.getType().ordinal());
        bufferHelper.writeVec3(buf, this.hitResult.getPos());

        bufferHelper.writeEnum(buf, this.player.getPose());
        bufferHelper.writeVec3(buf, this.player.getPos());
        bufferHelper.writeVec2(buf, this.player.getRotationClient());

        bufferHelper.writeEnum(buf, this.entity.getPose());
        bufferHelper.writeVec3(buf, this.entity.getPos());
        bufferHelper.writeVec2(buf, this.entity.getRotationClient());
    }
}
