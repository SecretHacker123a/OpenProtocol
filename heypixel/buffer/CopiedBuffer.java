package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.ByteBuffer;


@StringEncryption
@ControlFlowObfuscation
public class CopiedBuffer implements ILBuffer {


    public ByteBuffer buffer;


    public boolean slice = false;
    public CopiedBuffer(ByteBuffer byteBuffer) {
        this.buffer = ((ByteBuffer) MsgUtils.requireNonNull(byteBuffer, "字节缓存为空")).slice();
    }

    @Override
    public void close() {
    }

    @Override
    public UBuffer getBuffer() {
        if (this.slice) {
            return null;
        }
        UBuffer createFromByteBuffer = UBuffer.createFromByteBuffer(this.buffer);
        this.slice = true;
        return createFromByteBuffer;
    }


    public ByteBuffer sliceBuffer(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = this.buffer;
        this.buffer = ((ByteBuffer) MsgUtils.requireNonNull(byteBuffer, "字节缓存为空")).slice();
        this.slice = false;
        return byteBuffer2;
    }
}
