package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;


@StringEncryption
@ControlFlowObfuscation
public class ReadableChannelBuffer implements ILBuffer {
    public ReadableByteChannel channel;
    public UBuffer buffer;

    public ReadableChannelBuffer(ReadableByteChannel readableByteChannel, int i) {
        this.channel = (ReadableByteChannel) MsgUtils.requireNonNull(readableByteChannel, "传入数据通道为空");
        MsgUtils.checkArgumentWithMessage(i > 0, "请传入有效数据: " + i);
        this.buffer = UBuffer.allocateBuffer(i);
    }

    public ReadableChannelBuffer(ReadableByteChannel readableByteChannel) {
        this(readableByteChannel, 8192);
    }

    @Override
    public void close() throws IOException {
        this.channel.close();
    }

    public ReadableByteChannel replaceChannel(ReadableByteChannel readableByteChannel) {
        ReadableByteChannel readableByteChannel2 = this.channel;
        this.channel = readableByteChannel;
        return readableByteChannel2;
    }

    public UBuffer getBuffer() throws IOException {
        ByteBuffer byteBuffer = this.buffer.getByteBuffer();
        if (this.channel.read(byteBuffer) == -1) {
            return null;
        }
        byteBuffer.flip();
        return this.buffer.sliceBuffer(0, byteBuffer.limit());
    }
}
