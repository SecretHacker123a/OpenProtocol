package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.ByteBuffer;


@StringEncryption
@ControlFlowObfuscation
public class BufferSlice extends UBuffer {

    public BufferSlice(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }


    public BufferSlice(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
    }


    public BufferSlice(Object obj, long j, int i) {
        super(obj, j, i);
    }

    public BufferSlice sliceBuffer0(int i, int i2) {
        if (i == 0 && i2 == remaining()) {
            return this;
        }
        MsgUtils.checkArgument((i | i2) + ((i2 | ((-i) - 1)) - ((-i) - 1)) <= remaining());
        return new BufferSlice(this.backingArray, this.offset + i, i2);
    }

    @Override
    public void putDouble(int i, double d) {
        unsafe.putDouble(this.backingArray, this.offset + i, d);
    }

    @Override
    public long getLong(int i) {
        return unsafe.getLong(this.backingArray, this.offset + i);
    }

    @Override
    public int getInt(int i) {
        return unsafe.getInt(this.backingArray, this.offset + i);
    }

    @Override
    public short getShort(int i) {
        return unsafe.getShort(this.backingArray, this.offset + i);
    }

    @Override
    public void putInt(int i, int i2) {
        unsafe.putInt(this.backingArray, this.offset + i, i2);
    }

    @Override
    public double toDouble(int i) {
        return unsafe.getDouble(this.backingArray, this.offset + i);
    }

    @Override
    public void putShort(int i, short s) {
        unsafe.putShort(this.backingArray, this.offset + i, s);
    }

    @Override
    public float toFloat(int i) {
        return unsafe.getFloat(this.backingArray, this.offset + i);
    }

    @Override
    public UBuffer sliceBuffer(int i, int i2) {
        return sliceBuffer0(i, i2);
    }

    @Override
    public void putLong(int i, long j) {
        unsafe.putLong(this.backingArray, this.offset + i, j);
    }
}
