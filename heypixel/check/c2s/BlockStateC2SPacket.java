package dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelSessionManager;
import dev.undefinedteam.gensh1n.protocol.heypixel.value.Value;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallPlayerSkullBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;



@StringEncryption
@ControlFlowObfuscation
public class BlockStateC2SPacket extends HeypixelCheckPacket {


    public BlockState state;
    public String str;


    public BlockStateC2SPacket(BlockPos blockPos) {
        this.state = MinecraftClient.getInstance().world.getBlockState(blockPos);
//        if(state.getBlock() == Blocks.PLAYER_HEAD || state.getBlock() == Blocks.PLAYER_HEAD) {
//            this.str = ((SkullBlock)state.getBlock()).
//        }

        BlockEntity entity = mc.world.getBlockEntity(blockPos);
        if (entity != null && entity instanceof SkullBlockEntity sb) {
            int var2 = sb.getCachedState().get(SkullBlock.ROTATION);
            if (var2 == 15) {
                var2 = 0;
            } else {
                var2++;
            }
            var2 = (var2 / 4 + 2) % 4;
            Direction fac = switch (var2) {
                case 0 -> Direction.SOUTH;
                case 1 -> Direction.NORTH;
                case 2 -> Direction.WEST;
                case 3 -> Direction.EAST;
                default -> null;
            };

            str = "Block{" + sb.getOwner().getName() + "}[facing=" + (state.getBlock() instanceof WallPlayerSkullBlock ? state.get(WallPlayerSkullBlock.FACING).getDirection().toString() : fac.toString()) + ",type=single,waterlogged=false]";
        } else {
            str = state.toString();
        }
    }


    public BlockStateC2SPacket(PacketByteBuf friendlyByteBuf) {
        this.state = null;
    }

    public static void asyncCheck(HeypixelSessionManager manager, BlockPos blockPos) {
        check(manager, blockPos);
    }

    public static void check(HeypixelSessionManager manager, BlockPos blockPos) {
        new BlockStateC2SPacket(blockPos).m(manager).sendCheckPacket();
    }

    @Override
    public void processBuffer(PacketByteBuf friendlyByteBuf, BufferHelper bufferHelper) {
        bufferHelper.writeString(friendlyByteBuf, str);
//        System.out.println("state: " + str);
    }

    @Override
    public void writeSessionData(DataBuffer dataBuffer) throws IOException {
        dataBuffer.writeObjectData(new Value().setDataFromString(str));
//        System.out.println("state: " + str);
    }
}
