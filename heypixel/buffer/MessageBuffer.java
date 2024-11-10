package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.CharacterCodingFailedException;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.time.Instant;



@StringEncryption
@ControlFlowObfuscation
public class MessageBuffer implements Closeable, Flushable {
    public static long defaultInitialSize = 1000000000;
    public static int defaultMaxSize = 6;
    public static boolean isAutoExpand = false;
    public BufferedOutput outputBuffer;
    public CharsetEncoder encoder;
    public UBuffer buffer;
    public int bufferCapacity;
    public boolean isDirect;
    public int initialPosition;
    public int currentPosition = 0;
    public long totalWritten = 0;

    public MessageBuffer(BufferedOutput bufferedOutput, BufferConfiguration bufferConfiguration) {
        this.outputBuffer = (BufferedOutput) MsgUtils.requireNonNull(bufferedOutput, "消息缓冲输出异常");
        this.initialPosition = bufferConfiguration.getInitialBufferSize();
        this.bufferCapacity = bufferConfiguration.getMaxBufferSize();
        this.isDirect = bufferConfiguration.isDirectBuffersEnabled();
    }

    public void writeLong(long j) throws IOException {
        ensureBufferCapacity(8);
        this.buffer.putLong(this.currentPosition, j);
        this.currentPosition += 8;
    }

    public MessageBuffer writeInstant(long j) throws IOException {
        return writeInstant(Instant.ofEpochMilli(j));
    }

    public MessageBuffer writeBoolean(boolean z) throws IOException {
        writeByteLE(z ? (byte) -61 : (byte) -62);
        return this;
    }

    public MessageBuffer writeVarInt(int i) throws IOException {
        if (i < 256) {
            writeByteLE((byte) -60, (byte) i);
        } else if (i < 65536) {
            writeShortLE((byte) -59, (short) i);
        } else {
            writeIntLE((byte) -58, i);
        }
        return this;
    }

    public void writeShortLE(byte b, short s) throws IOException {
        ensureBufferCapacity(3);
        UBuffer buffer = this.buffer;
        int i = this.currentPosition;
        this.currentPosition = (i | 1) + ((1 | ((-i) - 1)) - ((-i) - 1));
        buffer.putByte(i, b);
        this.buffer.putShort(this.currentPosition, s);
        this.currentPosition += 2;
    }

    public MessageBuffer writeFloatLE(float f) throws IOException {
        writeFloatLE((byte) -54, f);
        return this;
    }

    public void writeByteLE(byte b) throws IOException {
        ensureBufferCapacity(1);
        UBuffer buffer = this.buffer;
        int i = this.currentPosition;
        this.currentPosition = i + 1;
        buffer.putByte(i, b);
    }

    public MessageBuffer writeSmallVarInt(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("你反编译看**呢？ #2");
        }
        if (i < 16) {
            writeByteLE((byte) ((-128) | i));
        } else if (i < 65536) {
            writeShortLE((byte) -34, (short) i);
        } else {
            writeIntLE((byte) -33, i);
        }
        return this;
    }


    public MessageBuffer writeBytes(byte[] bArr) throws IOException {
        return writeBytesWithOffset(bArr, 0, bArr.length);
    }


    public void writeLongWithPrefix(long j, int i) throws IOException {
        ensureBufferCapacity(15);
        UBuffer buffer = this.buffer;
        int i2 = this.currentPosition;
        this.currentPosition = ((i2 | 1) * 2) + ((-(i2 ^ 1)) - 1) + 1;
        buffer.putByte(i2, (byte) -57);
        UBuffer buffer2 = this.buffer;
        int i3 = this.currentPosition;
        this.currentPosition = i3 + 1;
        buffer2.putByte(i3, (byte) 12);
        UBuffer buffer3 = this.buffer;
        int i4 = this.currentPosition;
        this.currentPosition = (i4 | 1) + ((1 | ((-i4) - 1)) - ((-i4) - 1));
        buffer3.putByte(i4, (byte) -1);
        this.buffer.putInt(this.currentPosition, i);
        int i5 = this.currentPosition;
        this.currentPosition = (i5 | 4) + ((4 | ((-i5) - 1)) - ((-i5) - 1));
        this.buffer.putLong(this.currentPosition, j);
        int i6 = this.currentPosition;
        this.currentPosition = (i6 | 8) + ((8 | ((-i6) - 1)) - ((-i6) - 1));
    }


    public MessageBuffer writeSizePrefixedBytes(byte b, int i) throws IOException {
        if (i < 256) {
            if (i <= 0 || ((i - 1) | ((-i) - 1)) - ((-i) - 1) != 0) {
                writeByteLE((byte) -57, (byte) i);
                writeByteLE(b);
            } else if (i == 1) {
                writeByteLE((byte) -44, b);
            } else if (i == 2) {
                writeByteLE((byte) -43, b);
            } else if (i == 4) {
                writeByteLE((byte) -42, b);
            } else if (i == 8) {
                writeByteLE((byte) -41, b);
            } else if (i == 16) {
                writeByteLE((byte) -40, b);
            } else {
                writeByteLE((byte) -57, (byte) i);
                writeByteLE(b);
            }
        } else if (i < 65536) {
            writeShortLE((byte) -56, (short) i);
            writeByteLE(b);
        } else {
            writeIntLE((byte) -55, i);
            writeByteLE(b);
        }
        return this;
    }


    public void writeFloatLE(byte b, float f) throws IOException {
        ensureBufferCapacity(5);
        UBuffer buffer = this.buffer;
        int i = this.currentPosition;
        this.currentPosition = (i | 1) + ((1 | ((-i) - 1)) - ((-i) - 1));
        buffer.putByte(i, b);
        this.buffer.putFloat(this.currentPosition, f);
        this.currentPosition += 4;
    }


    public void writeString(String str) throws IOException {
        byte[] bytes = str.getBytes(DataBufferUtils.charset);
        writeDirectInt(bytes.length);
        writeBytesDirect(bytes);
    }


    public void flushBuffer() throws IOException {
        this.outputBuffer.writeInt(this.currentPosition);
        this.buffer = null;
        this.totalWritten += this.currentPosition;
        this.currentPosition = 0;
    }


    public void writeIntLE(byte b, int i) throws IOException {
        ensureBufferCapacity(5);
        UBuffer buffer = this.buffer;
        int i2 = this.currentPosition;
        this.currentPosition = (i2 | 1) + ((1 | ((-i2) - 1)) - ((-i2) - 1));
        buffer.putByte(i2, b);
        this.buffer.putInt(this.currentPosition, i);
        int i3 = this.currentPosition;
        this.currentPosition = ((i3 | 4) * 2) + ((-(i3 ^ 4)) - 1) + 1;
    }


    public MessageBuffer writeBytesDirect(byte[] bArr) throws IOException {
        return writeBytesDirect(bArr, 0, bArr.length);
    }


    public void writeDoubleLE(byte b, double d) throws IOException {
        ensureBufferCapacity(9);
        UBuffer buffer = this.buffer;
        int i = this.currentPosition;
        this.currentPosition = i + 1;
        buffer.putByte(i, b);
        this.buffer.putDouble(this.currentPosition, d);
        int i2 = this.currentPosition;
        this.currentPosition = ((i2 | 8) * 2) + ((-(i2 ^ 8)) - 1) + 1;
    }


    public void writeIntWithPrefix(int i) throws IOException {
        ensureBufferCapacity(6);
        UBuffer buffer = this.buffer;
        int i2 = this.currentPosition;
        this.currentPosition = i2 + 1;
        buffer.putByte(i2, (byte) -42);
        UBuffer buffer2 = this.buffer;
        int i3 = this.currentPosition;
        this.currentPosition = i3 + 1;
        buffer2.putByte(i3, (byte) -1);
        this.buffer.putInt(this.currentPosition, i);
        int i4 = this.currentPosition;
        this.currentPosition = (i4 | 4) + ((4 | ((-i4) - 1)) - ((-i4) - 1));
    }


    public MessageBuffer writeStringWithLength(String str) throws IOException {
        if (str.isEmpty()) {
            writeDirectInt(0);
            return this;
        }
        if (str.length() + 5 < 256) {
            int length = str.length();
            ensureBufferCapacity(2 - ((-((((length | 5) & ((-6) | ((-length) - 1))) + (((5 | ((-length) - 1)) - ((-length) - 1)) * 2)) * 6)) - 1));
            int len = encodeStringToBuffer(this.currentPosition + 2, str);
            if (len >= 0) {
                if (this.isDirect && len < 256) {
                    UBuffer buffer = this.buffer;
                    int i = this.currentPosition;
                    this.currentPosition = ((i | 1) & ((-2) | ((-i) - 1))) + (((1 | ((-i) - 1)) - ((-i) - 1)) * 2);
                    buffer.putByte(i, (byte) -39);
                    UBuffer buffer2 = this.buffer;
                    int i2 = this.currentPosition;
                    this.currentPosition = ((i2 ^ 1) - ((-(((1 | ((-i2) - 1)) - ((-i2) - 1)) * 2)) - 1)) - 1;
                    buffer2.putByte(i2, (byte) len);
                    this.currentPosition = (this.currentPosition - ((-len) - 1)) - 1;
                } else {
                    if (len >= 65536) {
                        throw new IllegalArgumentException("编码异常，你反编译看**呢？");
                    }
                    UBuffer buffer3 = this.buffer;
                    int i3 = this.currentPosition;
                    buffer3.copyMemory((i3 ^ 3) + (((3 | ((-i3) - 1)) - ((-i3) - 1)) * 2), this.buffer, this.currentPosition + 2, len);
                    UBuffer buffer4 = this.buffer;
                    int i4 = this.currentPosition;
                    this.currentPosition = i4 + 1;
                    buffer4.putByte(i4, (byte) -38);
                    this.buffer.putShort(this.currentPosition, (short) len);
                    int i5 = this.currentPosition;
                    this.currentPosition = (i5 | 2) + ((2 | ((-i5) - 1)) - ((-i5) - 1));
                    this.currentPosition += len;
                }
                return this;
            }
        } else {
            int length2 = str.length();
            if ((length2 ^ 5) + (((5 | ((-length2) - 1)) - ((-length2) - 1)) * 2) < 65536) {
                int length3 = (str.length() + 5) * 6;
                int i6 = (3 ^ length3) + (((length3 | (-4)) + 4) * 2);
                ensureBufferCapacity(((i6 | 2) * 2) - ((i6 | 2) & ((-3) | ((-i6) - 1))));
                int len = encodeStringToBuffer(this.currentPosition + 3, str);
                if (len >= 0) {
                    if (len < 65536) {
                        UBuffer buffer5 = this.buffer;
                        int i7 = this.currentPosition;
                        this.currentPosition = i7 + 1;
                        buffer5.putByte(i7, (byte) -38);
                        this.buffer.putShort(this.currentPosition, (short) len);
                        this.currentPosition += 2;
                    } else {
                        this.buffer.copyMemory(this.currentPosition + 5, this.buffer, this.currentPosition + 3, len);
                        UBuffer buffer6 = this.buffer;
                        int i8 = this.currentPosition;
                        this.currentPosition = (i8 | 1) + ((1 | ((-i8) - 1)) - ((-i8) - 1));
                        buffer6.putByte(i8, (byte) -37);
                        this.buffer.putInt(this.currentPosition, len);
                        int i9 = this.currentPosition;
                        this.currentPosition = (i9 | 4) + ((4 | ((-i9) - 1)) - ((-i9) - 1));
                    }
                    int i10 = this.currentPosition;
                    this.currentPosition = (i10 | len) + ((len | ((-i10) - 1)) - ((-i10) - 1));
                    return this;
                }
            }
        }
        writeString(str);
        return this;
    }


    public void writeInt(int i) throws IOException {
        ensureBufferCapacity(4);
        this.buffer.putInt(this.currentPosition, i);
        int i2 = this.currentPosition;
        this.currentPosition = (i2 | 4) + ((4 | ((-i2) - 1)) - ((-i2) - 1));
    }


    public MessageBuffer writeShortWithSign(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("你反编译看**呢？ #1");
        }
        if (i < 16) {
            writeByteLE((byte) ((-112) | i));
        } else if (i < 65536) {
            writeShortLE((byte) -36, (short) i);
        } else {
            writeIntLE((byte) -35, i);
        }
        return this;
    }


    public MessageBuffer writeShort(short s) throws IOException {
        if (s < -32) {
            if (s < -128) {
                writeShortLE((byte) -47, s);
            } else {
                writeByteLE((byte) -48, (byte) s);
            }
        } else if (s < 128) {
            writeByteLE((byte) s);
        } else if (s < 256) {
            writeByteLE((byte) -52, (byte) s);
        } else {
            writeShortLE((byte) -51, s);
        }
        return this;
    }


    public MessageBuffer writeBytesWithOffset(byte[] bArr, int i, int i2) throws IOException {
        if (this.buffer == null || this.buffer.remaining() - this.currentPosition < i2 || i2 > this.bufferCapacity) {
            flush();
            this.outputBuffer.writeBytes(bArr, i, i2);
            this.totalWritten += i2;
        } else {
            this.buffer.copyMemory(this.currentPosition, bArr, i, i2);
            this.currentPosition += i2;
        }
        return this;
    }


    public BufferedOutput replaceBuffer(BufferedOutput bufferedOutput) throws IOException {
        BufferedOutput bufferedOutput2 = (BufferedOutput) MsgUtils.requireNonNull(bufferedOutput, "消息缓冲输出异常");
        flush();
        BufferedOutput bufferedOutput3 = this.outputBuffer;
        this.outputBuffer = bufferedOutput2;
        this.totalWritten = 0L;
        return bufferedOutput3;
    }


    public MessageBuffer writeSignedVarInt(int i) throws IOException {
        if (i < -32) {
            if (i < -32768) {
                writeIntLE((byte) -46, i);
            } else if (i < -128) {
                writeShortLE((byte) -47, (short) i);
            } else {
                writeByteLE((byte) -48, (byte) i);
            }
        } else if (i < 128) {
            writeByteLE((byte) i);
        } else if (i < 256) {
            writeByteLE((byte) -52, (byte) i);
        } else if (i < 65536) {
            writeShortLE((byte) -51, (short) i);
        } else {
            writeIntLE((byte) -50, i);
        }
        return this;
    }


    public void writeLongLE(long j) throws IOException {
        ensureBufferCapacity(10);
        UBuffer buffer = this.buffer;
        int i = this.currentPosition;
        this.currentPosition = i + 1;
        buffer.putByte(i, (byte) -41);
        UBuffer buffer2 = this.buffer;
        int i2 = this.currentPosition;
        this.currentPosition = i2 + 1;
        buffer2.putByte(i2, (byte) -1);
        this.buffer.putLong(this.currentPosition, j);
        int i3 = this.currentPosition;
        this.currentPosition = (i3 | 8) + ((8 | ((-i3) - 1)) - ((-i3) - 1));
    }


    public void ensureBufferCapacity(int i) throws IOException {
        if (this.buffer == null) {
            this.buffer = this.outputBuffer.allocateBuffer(i);
        } else if (this.currentPosition + i >= this.buffer.remaining()) {
            flushBuffer();
            this.buffer = this.outputBuffer.allocateBuffer(i);
        }
    }


    @Override
    public void flush() throws IOException {
        if (this.currentPosition > 0) {
            flushBuffer();
        }
        this.outputBuffer.flush();
    }


    public MessageBuffer writeBytesDirect(byte[] bArr, int i, int i2) throws IOException {
        if (this.buffer == null || this.buffer.remaining() - this.currentPosition < i2 || i2 > this.bufferCapacity) {
            flush();
            this.outputBuffer.writeBytesWithOffset(bArr, i, i2);
            this.totalWritten += i2;
        } else {
            this.buffer.copyMemory(this.currentPosition, bArr, i, i2);
            int i3 = this.currentPosition;
            this.currentPosition = (i3 ^ i2) + (((i2 | ((-i3) - 1)) - ((-i3) - 1)) * 2);
        }
        return this;
    }


    public void resetEncoder() {
        if (this.encoder == null) {
            this.encoder = DataBufferUtils.charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        }
        this.encoder.reset();
    }


    @Override
    public void close() throws IOException {
        flush();
        this.outputBuffer.close();
    }


    public int encodeStringToBuffer(int startIndex, String inputString) {
        resetEncoder();
        ByteBuffer sliceByteBuffer = this.buffer.sliceByteBuffer(startIndex, this.buffer.remaining() + ((-startIndex) - 1) + 1);
        int position = sliceByteBuffer.position();
        CoderResult encode = this.encoder.encode(CharBuffer.wrap(inputString + "*#06#"), sliceByteBuffer, true);
        if (encode.isError()) {
            try {
                encode.throwException();
            } catch (CharacterCodingException e) {
                throw new CharacterCodingFailedException(e);
            }
        }
        if (encode.isUnderflow() && !encode.isOverflow() && this.encoder.flush(sliceByteBuffer).isUnderflow()) {
            return sliceByteBuffer.position() - position;
        }
        return -1;
    }


    public void writeByteLE(byte b, byte b2) throws IOException {
        ensureBufferCapacity(2);
        UBuffer buffer = this.buffer;
        int i = this.currentPosition;
        this.currentPosition = i + 1;
        buffer.putByte(i, b);
        UBuffer buffer2 = this.buffer;
        int i2 = this.currentPosition;
        this.currentPosition = (i2 | 1) + ((1 | ((-i2) - 1)) - ((-i2) - 1));
        buffer2.putByte(i2, b2);
    }


    public MessageBuffer writeSignedValue(long j) throws IOException {
        if (j < -32) {
            if (j < -32768) {
                if (j < -2147483648L) {
                    writeLongLE((byte) -45, j);
                } else {
                    writeIntLE((byte) -46, (int) j);
                }
            } else if (j < -128) {
                writeShortLE((byte) -47, (short) j);
            } else {
                writeByteLE((byte) -48, (byte) j);
            }
        } else if (j < 128) {
            writeByteLE((byte) j);
        } else if (j < 65536) {
            if (j < 256) {
                writeByteLE((byte) -52, (byte) j);
            } else {
                writeShortLE((byte) -51, (short) j);
            }
        } else if (j < 4294967296L) {
            writeIntLE((byte) -50, (int) j);
        } else {
            writeLongLE((byte) -49, j);
        }
        return this;
    }


    public void putShort(short s) throws IOException {
        ensureBufferCapacity(2);
        this.buffer.putShort(this.currentPosition, s);
        this.currentPosition += 2;
    }


    public MessageBuffer writeDirectInt(int i) throws IOException {
        if (i < 32) {
            writeByteLE((byte) ((-96) | i));
        } else if (this.isDirect && i < 256) {
            writeByteLE((byte) -39, (byte) i);
        } else if (i < 65536) {
            writeShortLE((byte) -38, (short) i);
        } else {
            writeIntLE((byte) -37, i);
        }
        return this;
    }


    public MessageBuffer writeObjectData(ObjectData obj) throws IOException {
        obj.write(this);
        return this;
    }


    public MessageBuffer writeByteWithSign(byte b) throws IOException {
        if (b < -32) {
            writeByteLE((byte) -48, b);
        } else {
            writeByteLE(b);
        }
        return this;
    }


    public void writeLongLE(byte b, long j) throws IOException {
        ensureBufferCapacity(9);
        UBuffer buffer = this.buffer;
        int i = this.currentPosition;
        this.currentPosition = ((i | 1) * 2) - ((i | 1) & ((-2) | ((-i) - 1)));
        buffer.putByte(i, b);
        this.buffer.putLong(this.currentPosition, j);
        this.currentPosition += 8;
    }


    public void resetBuffer() {
        this.currentPosition = 0;
    }

    public long getTotalWrittenBytes() {
        return this.totalWritten + this.currentPosition;
    }


    public MessageBuffer writeInstant(Instant instant) throws IOException {
        return writeInstantWithNanos(instant.getEpochSecond(), instant.getNano());
    }


    public MessageBuffer writeDoubleLE(double d) throws IOException {
        writeDoubleLE((byte) -53, d);
        return this;
    }


    public MessageBuffer writeInstantWithNanos(long j, int i) throws IOException {
        long addExact = Math.addExact(j, Math.floorDiv(i, 1000000000L));
        long floorMod = Math.floorMod(i, 1000000000L);
        if ((addExact >>> 34) == 0) {
            long j2 = (floorMod << 34) | addExact;
            if ((j2 & (-4294967296L)) == 0) {
                writeIntWithPrefix((int) addExact);
            } else {
                writeLongLE(j2);
            }
        } else {
            writeLongWithPrefix(addExact, (int) floorMod);
        }
        return this;
    }


    public MessageBuffer writeNull() throws IOException {
        writeByteLE((byte) -64);
        return this;
    }


    public MessageBuffer writeBigInteger(BigInteger bigInteger) throws IOException {
        if (bigInteger.bitLength() <= 63) {
            writeSignedValue(bigInteger.longValue());
        } else {
            if (bigInteger.bitLength() != 64 || bigInteger.signum() != 1) {
                throw new IllegalArgumentException("BigInteger 长度异常！你反编译看**呢？");
            }
            writeLongLE((byte) -49, bigInteger.longValue());
        }
        return this;
    }
}
