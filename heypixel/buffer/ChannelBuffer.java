package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;


@StringEncryption
@ControlFlowObfuscation
public class ChannelBuffer implements BufferedOutput {
    public UBuffer buf;
    public WritableByteChannel channel;


    public ChannelBuffer(WritableByteChannel writableByteChannel) {
        this(writableByteChannel, 8192);
    }

    public ChannelBuffer(WritableByteChannel writableByteChannel, int i) {
        this.channel = (WritableByteChannel) MsgUtils.requireNonNull(writableByteChannel, "传出数据通道为空");
        this.buf = UBuffer.allocateBuffer(i);
    }

    public void writeInt(int i) throws IOException {
        ByteBuffer sliceByteBuffer = this.buf.sliceByteBuffer(0, i);
        while (sliceByteBuffer.hasRemaining()) {
            this.channel.write(sliceByteBuffer);
        }
    }

    public void writeBytes(byte[] bArr, int i, int i2) throws IOException {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, i2);
        while (wrap.hasRemaining()) {
            this.channel.write(wrap);
        }
    }

    public void writeBytesWithOffset(byte[] bArr, int i, int i2) throws IOException {
        writeBytes(bArr, i, i2);
    }


    public UBuffer allocateBuffer(int i) {
        if (this.buf.remaining() < i) {
            this.buf = UBuffer.allocateBuffer(i);
        }
        return this.buf;
    }

    public WritableByteChannel replaceChannel(WritableByteChannel writableByteChannel) {
        WritableByteChannel writableByteChannel2 = this.channel;
        this.channel = writableByteChannel;
        return writableByteChannel2;
    }


    @Override
    public void close() throws IOException {
        this.channel.close();
    }

    @Override
    public void flush() {
    }
}
