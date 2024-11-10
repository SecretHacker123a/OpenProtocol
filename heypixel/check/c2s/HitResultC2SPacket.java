package dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.value.Value;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.GameMode;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.List;


@StringEncryption
@ControlFlowObfuscation
public class HitResultC2SPacket extends HeypixelCheckPacket {


    public static long delay;
    public Hand hand;
    public BlockHitResult hitResult;


    public ClientPlayerEntity player;

    public HitResultC2SPacket(PacketByteBuf friendlyByteBuf) {
    }


    public HitResultC2SPacket(ClientPlayerEntity localPlayer, Hand interactionHand, BlockHitResult blockHitResult) {
        this.player = localPlayer;
        this.hand = interactionHand;
        this.hitResult = blockHitResult;
    }

    public static void check(ClientPlayerEntity localPlayer, Hand clientLevel, Hand interactionHand, BlockHitResult blockHitResult, GameMode gameType) {
        var minecraft = MinecraftClient.getInstance();
        var cameraEntity = minecraft.getCameraEntity();
        if (System.currentTimeMillis() < delay || cameraEntity == null || minecraft.world == null || gameType.equals(GameMode.SPECTATOR) || blockHitResult == null) {
            return;
        }
        delay = System.currentTimeMillis() + 10;
        new HitResultC2SPacket(localPlayer, interactionHand, blockHitResult).sendCheckPacketVanilla();
    }

    @Override
    public void processBuffer(PacketByteBuf buf, BufferHelper bufferHelper) {
        bufferHelper.writeVec3(buf, this.player.getPos());
        buf.writeEnumConstant(this.hand);
        bufferHelper.writeBlockHitResult(buf, this.hitResult);
    }

    @Override
    public void writeSessionData(DataBuffer dataBuffer) {
        try {
            dataBuffer.writeObjectData(new Value().setListData(
                List.of(
                    new Value().setDataFromDouble(this.player.getPos().x),
                    new Value().setDataFromDouble(this.player.getPos().y),
                    new Value().setDataFromDouble(this.player.getPos().z)
                )));
            dataBuffer.writeSignedVarInt(this.hitResult.getSide().ordinal());
            dataBuffer.writeSignedVarInt(this.hitResult.getType().ordinal());
            dataBuffer.writeFloatLE((float) (this.hitResult.getPos().x - this.hitResult.getBlockPos().getX()));
            dataBuffer.writeFloatLE((float) (this.hitResult.getPos().y - this.hitResult.getBlockPos().getY()));
            dataBuffer.writeFloatLE((float) (this.hitResult.getPos().z - this.hitResult.getBlockPos().getZ()));
            dataBuffer.writeObjectData(new Value().setDataFromFloat(MinecraftClient.getInstance().player.getPitch()));
            dataBuffer.writeObjectData(new Value().setDataFromFloat(MinecraftClient.getInstance().player.getYaw()));
        } catch (IOException e) {
        }
    }
}
