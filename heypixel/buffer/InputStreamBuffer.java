package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;


@StringEncryption
@ControlFlowObfuscation
public class InputStreamBuffer implements ILBuffer {
    public byte[] data;
    public InputStream stream;

    public InputStreamBuffer(InputStream inputStream, int i) {
        this.stream = (InputStream) MsgUtils.requireNonNull(inputStream, "空");
        this.data = new byte[i];
    }

    public InputStreamBuffer(InputStream inputStream) {
        this(inputStream, 8192);
    }

    public static ILBuffer replaceChannelN(InputStream inputStream) {
        FileChannel channel;
        MsgUtils.requireNonNull(inputStream, "输入流为空");
        return (!(inputStream instanceof FileInputStream) || (channel = ((FileInputStream) inputStream).getChannel()) == null) ? new InputStreamBuffer(inputStream) : new ReadableChannelBuffer(channel);
    }

    public UBuffer getBuffer() throws IOException {
        int read = this.stream.read(this.data);
        if (read == -1) {
            return null;
        }
        return UBuffer.wrapByteArray(this.data, 0, read);
    }

    public InputStream replaceChannel(InputStream inputStream) {
        InputStream inputStream2 = this.stream;
        this.stream = inputStream;
        return inputStream2;
    }

    @Override
    public void close() throws IOException {
        this.stream.close();
    }
}
