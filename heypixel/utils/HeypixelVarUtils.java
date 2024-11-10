package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import io.netty.buffer.ByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@NativeObfuscation
@ControlFlowObfuscation
public class HeypixelVarUtils {

    public HeypixelVarUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void writeVarInt(ByteBuf byteBuf, int i) {
        encodeVarLong(byteBuf, (((long) i << 1) ^ (i >> 31)) & 4294967295L);
    }

    public static void writeVarLong(ByteBuf byteBuf, long j) {
        encodeVarLong(byteBuf, j);
    }

    public static int readVarInt(ByteBuf byteBuf) {
        return (int) decodeVarLong(byteBuf, 32);
    }

    public static int readZigZagInt(ByteBuf byteBuf) {
        int decodeVarLong = (int) decodeVarLong(byteBuf, 32);
        int i = decodeVarLong >>> 1;
        int i2 = -((1 | ((-decodeVarLong) - 1)) - ((-decodeVarLong) - 1));
        return (i | i2) - ((i2 | ((-i) - 1)) - ((-i) - 1));
    }

    public static void encodeVarLong(ByteBuf byteBuf, long j) {
        if ((j & (-128)) == 0) {
            byteBuf.writeByte((byte) j);
        } else if ((j & (-16384)) == 0) {
            byteBuf.writeShort((int) ((((j & 127) | 128) << 8) | (j >>> 7)));
        } else {
            writeVarLongExtended(byteBuf, j);
        }
    }

    @NativeObfuscation.Inline
    public static void writeVarLongExtended(ByteBuf byteBuf, long j) {
        if ((j & (-128)) == 0) {
            byteBuf.writeByte((byte) j);
            return;
        }
        if ((j & (-16384)) == 0) {
            byteBuf.writeShort((int) ((((j & 127) | 128) << 8) | (j >>> 7)));
            return;
        }
        if ((j & (-2097152)) == 0) {
            byteBuf.writeMedium((int) ((((j & 127) | 128) << 16) | ((((j >>> 7) & 127) | 128) << 8) | (j >>> 14)));
            return;
        }
        if ((j & (-268435456)) == 0) {
            byteBuf.writeInt((int) ((((j & 127) | 128) << 24) | ((((j >>> 7) & 127) | 128) << 16) | ((((j >>> 14) & 127) | 128) << 8) | (j >>> 21)));
            return;
        }
        if ((j & (-34359738368L)) == 0) {
            byteBuf.writeInt((int) ((((j & 127) | 128) << 24) | ((((j >>> 7) & 127) | 128) << 16) | ((((j >>> 14) & 127) | 128) << 8) | ((j >>> 21) & 127) | 128));
            byteBuf.writeByte((int) (j >>> 28));
            return;
        }
        if ((j & (-4398046511104L)) == 0) {
            byteBuf.writeInt((int) ((((j & 127) | 128) << 24) | ((((j >>> 7) & 127) | 128) << 16) | ((((j >>> 14) & 127) | 128) << 8) | ((j >>> 21) & 127) | 128));
            byteBuf.writeShort((int) (((((j >>> 28) & 127) | 128) << 8) | (j >>> 35)));
            return;
        }
        if ((j & (-562949953421312L)) == 0) {
            byteBuf.writeInt((int) ((((j & 127) | 128) << 24) | ((((j >>> 7) & 127) | 128) << 16) | ((((j >>> 14) & 127) | 128) << 8) | ((j >>> 21) & 127) | 128));
            byteBuf.writeMedium((int) (((((j >>> 28) & 127) | 128) << 16) | ((((j >>> 35) & 127) | 128) << 8) | (j >>> 42)));
            return;
        }
        if ((j & (-72057594037927936L)) == 0) {
            byteBuf.writeLong((((j & 127) | 128) << 56) | ((((j >>> 7) & 127) | 128) << 48) | ((((j >>> 14) & 127) | 128) << 40) | ((((j >>> 21) & 127) | 128) << 32) | ((((j >>> 28) & 127) | 128) << 24) | ((((j >>> 35) & 127) | 128) << 16) | ((((j >>> 42) & 127) | 128) << 8) | (j >>> 49));
            return;
        }
        if ((j & Long.MIN_VALUE) == 0) {
            byteBuf.writeLong((((j & 127) | 128) << 56) | ((((j >>> 7) & 127) | 128) << 48) | ((((j >>> 14) & 127) | 128) << 40) | ((((j >>> 21) & 127) | 128) << 32) | ((((j >>> 28) & 127) | 128) << 24) | ((((j >>> 35) & 127) | 128) << 16) | ((((j >>> 42) & 127) | 128) << 8) | ((j >>> 49) & 127) | 128);
            byteBuf.writeByte((byte) (j >>> 56));
        } else {
            byteBuf.writeLong((((j & 127) | 128) << 56) | ((((j >>> 7) & 127) | 128) << 48) | ((((j >>> 14) & 127) | 128) << 40) | ((((j >>> 21) & 127) | 128) << 32) | ((((j >>> 28) & 127) | 128) << 24) | ((((j >>> 35) & 127) | 128) << 16) | ((((j >>> 42) & 127) | 128) << 8) | ((j >>> 49) & 127) | 128);
            byteBuf.writeShort((int) (((((j >>> 56) & 127) | 128) << 8) | (j >>> 63)));
        }
    }

    public static long decodeVarLong(ByteBuf byteBuf, int i) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2 += 7) {
            byte readByte = byteBuf.readByte();
            j |= (long) (readByte & 127) << i2;
            if ((128 | ((-readByte) - 1)) - ((-readByte) - 1) == 0) {
                return j;
            }
        }
        throw new ArithmeticException("Ints Error#1");
    }

    public static void writeUnsignedInt(ByteBuf byteBuf, int i) {
        encodeVarLong(byteBuf, i & 4294967295L);
    }

    public static long readZigZagLong(ByteBuf byteBuf) {
        long decodeVarLong = decodeVarLong(byteBuf, 64);
        return (decodeVarLong >>> 1) ^ (-(decodeVarLong & 1));
    }

    public static long readUnsignedLong(ByteBuf byteBuf) {
        return decodeVarLong(byteBuf, 64);
    }

    public static void writeZigZagLong(ByteBuf byteBuf, long j) {
        encodeVarLong(byteBuf, (j << 1) ^ (j >> 63));
    }
}
