package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.ByteBuffer;


@StringEncryption
@ControlFlowObfuscation
public class TPositionBuffer extends UBuffer {
    public static boolean assertion;

    static {
        assertion = !UBuffer.class.desiredAssertionStatus();
    }

    public ByteBuffer buf;


    public TPositionBuffer(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
        this.buf = ByteBuffer.wrap(bArr, i, i2).slice();
    }


    public TPositionBuffer(ByteBuffer byteBuffer) {
        super(byteBuffer);
        this.buf = byteBuffer.slice();
    }


    public TPositionBuffer(Object obj, long j, int i, ByteBuffer byteBuffer) {
        super(obj, j, i);
        this.buf = byteBuffer;
    }

    @Override
    public byte[] toByteArray() {
        byte[] bArr = new byte[remaining()];
        writeArrayDataToBuffer(0, bArr, 0, bArr.length);
        return bArr;
    }

    @Override
    public void copyMemory(int i, byte[] bArr, int i2, int i3) {
        this.buf.position(i);
        this.buf.put(bArr, i2, i3);
        flip();
    }

    public TPositionBuffer sliceBuffer0(int i, int i2) {
        if (i == 0 && i2 == remaining()) {
            return this;
        }
        MsgUtils.checkArgument((i | i2) + ((i2 | ((-i) - 1)) - ((-i) - 1)) <= remaining());
        this.buf.position(i);
        this.buf.limit((i - ((-i2) - 1)) - 1);
        TPositionBuffer tPositionBuffer = new TPositionBuffer(this.backingArray, this.offset + i, i2, this.buf.slice());
        flip();
        return tPositionBuffer;
    }

    @Override
    public void writeArrayDataToBuffer(int i, byte[] bArr, int i2, int i3) {
        this.buf.position(i);
        this.buf.get(bArr, i2, i3);
        flip();
    }

    @Override
    public void copyMemory(int i, UBuffer buffer, int i2, int i3) {
        putByteBufferData(i, buffer.sliceByteBuffer(i2, i3), i3);
    }

    @Override
    public void putByteBufferData(int i, ByteBuffer byteBuffer, int i2) {
        if (!assertion && i2 > byteBuffer.remaining()) {
            throw new AssertionError();
        }
        if (byteBuffer.hasArray()) {
            byte[] array = byteBuffer.array();
            int position = byteBuffer.position();
            int arrayOffset = byteBuffer.arrayOffset();
            copyMemory(i, array, (position ^ arrayOffset) + (((arrayOffset | ((-position) - 1)) - ((-position) - 1)) * 2), i2);
            int position2 = byteBuffer.position();
            byteBuffer.position((position2 | i2) + ((i2 | ((-position2) - 1)) - ((-position2) - 1)));
            return;
        }
        int limit = byteBuffer.limit();
        int position3 = byteBuffer.position();
        byteBuffer.limit((position3 ^ i2) + (((i2 | ((-position3) - 1)) - ((-position3) - 1)) * 2));
        this.buf.position(i);
        this.buf.put(byteBuffer);
        byteBuffer.limit(limit);
    }

    @Override
    public boolean hasArray() {
        return !this.buf.isDirect();
    }

    @Override
    public long getLong(int i) {
        return this.buf.getLong(i);
    }

    @Override
    public void putDouble(int i, double d) {
        this.buf.putDouble(i, d);
    }

    @Override
    public void putFloat(int i, float f) {
        this.buf.putFloat(i, f);
    }

    @Override
    public ByteBuffer sliceByteBuffer(int i, int i2) {
        this.buf.position(i);
        this.buf.limit((i | i2) + ((i2 | ((-i) - 1)) - ((-i) - 1)));
        ByteBuffer slice = this.buf.slice();
        flip();
        return slice;
    }

    @Override
    public void putInt(int i, int i2) {
        this.buf.putInt(i, i2);
    }

    @Override
    public ByteBuffer getByteBuffer() {
        return sliceByteBuffer(0, this.remaining);
    }

    @Override
    public short getShort(int i) {
        return this.buf.getShort(i);
    }

    @Override
    public boolean getBoolean(int i) {
        return this.buf.get(i) != 0;
    }

    public void flip() {
        this.buf.position(0);
        this.buf.limit(this.remaining);
    }

    @Override
    public byte getByte(int i) {
        return this.buf.get(i);
    }

    @Override
    public void putByteBuffer(int i, int i2, ByteBuffer byteBuffer) {
        this.buf.position(i);
        this.buf.limit((i | i2) + ((i2 | ((-i) - 1)) - ((-i) - 1)));
        byteBuffer.put(this.buf);
        flip();
    }

    @Override
    public void putLong(int i, long j) {
        this.buf.putLong(i, j);
    }

    @Override
    public byte[] getBackingArray() {
        if (hasArray()) {
            return this.buf.array();
        }
        return null;
    }


    @Override
    public void putBoolean(int i, boolean z) {
        this.buf.put(i, z ? (byte) 1 : (byte) 0);
    }


    @Override
    public UBuffer sliceBuffer(int i, int i2) {
        return sliceBuffer0(i, i2);
    }


    @Override
    public void copyMemoryTo(int i, UBuffer buffer, int i2, int i3) {
        this.buf.position(i);
        buffer.putByteBufferData(i2, this.buf, i3);
        flip();
    }


    @Override
    public void putShort(int i, short s) {
        this.buf.putShort(i, s);
    }


    @Override
    public float toFloat(int i) {
        return this.buf.getFloat(i);
    }


    @Override
    public double toDouble(int i) {
        return this.buf.getDouble(i);
    }


    @Override
    public void putByte(int i, byte b) {
        this.buf.put(i, b);
    }


    @Override
    public int getInt(int i) {
        return this.buf.getInt(i);
    }
}
