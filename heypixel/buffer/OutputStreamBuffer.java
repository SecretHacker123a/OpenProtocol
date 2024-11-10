package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.io.OutputStream;


@StringEncryption
@ControlFlowObfuscation
public class OutputStreamBuffer implements BufferedOutput {


    public UBuffer buf;


    public OutputStream output;


    public OutputStreamBuffer(OutputStream outputStream) {
        this(outputStream, 8192);
    }

    public OutputStreamBuffer(OutputStream outputStream, int i) {
        this.output = (OutputStream) MsgUtils.requireNonNull(outputStream, "输出位空");
        this.buf = UBuffer.allocateBuffer(i);
    }

    public void writeBytesWithOffset(byte[] bArr, int i, int i2) throws IOException {
        writeBytes(bArr, i, i2);
    }

    @Override
    public void flush() throws IOException {
        this.output.flush();
    }


    public OutputStream setOutput(OutputStream outputStream) {
        OutputStream outputStream2 = this.output;
        this.output = outputStream;
        return outputStream2;
    }


    public void writeBytes(byte[] bArr, int i, int i2) throws IOException {
        this.output.write(bArr, i, i2);
    }


    @Override
    public void close() throws IOException {
        this.output.close();
    }


    public UBuffer allocateBuffer(int i) {
        if (this.buf.remaining() < i) {
            this.buf = UBuffer.allocateBuffer(i);
        }
        return this.buf;
    }


    public void writeInt(int i) throws IOException {
        writeBytes(this.buf.getBackingArray(), this.buf.calculatePosition(), i);
    }
}
