package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;


import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public class BufferWrapper implements ILBuffer {
    public boolean isNull;
    public UBuffer buf;

    public BufferWrapper(UBuffer buffer) {
        this.buf = buffer;
        this.isNull = buffer == null;
    }

    public BufferWrapper(byte[] bArr, int i, int i2) {
        this(UBuffer.wrapByteArray((byte[]) MsgUtils.requireNonNull(bArr, "数组为空"), i, i2));
    }


    public BufferWrapper(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    @Override
    public void close() {
        this.buf = null;
        this.isNull = true;
    }

    public void setBufferFromByteArray(byte[] bArr) {
        replaceBuffer(UBuffer.createFromByteArray((byte[]) MsgUtils.requireNonNull(bArr, "数组为空")));
    }

    @Override
    public UBuffer getBuffer() {
        if (this.isNull) {
            return null;
        }
        this.isNull = true;
        return this.buf;
    }

    public UBuffer replaceBuffer(UBuffer buffer) {
        UBuffer buffer2 = this.buf;
        this.buf = buffer;
        this.isNull = buffer == null;
        return buffer2;
    }

    public void setBufferFromPartialByteArray(byte[] bArr, int i, int i2) {
        replaceBuffer(UBuffer.wrapByteArray((byte[]) MsgUtils.requireNonNull(bArr, "数组为空"), i, i2));
    }
}
