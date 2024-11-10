package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.List;



@StringEncryption
@ControlFlowObfuscation
public class DataBuffer extends MessageBuffer {

    public DataBuffer(BufferConfiguration bufferConfiguration) {
        this(new BufferSet(bufferConfiguration.getBufferSize()), bufferConfiguration);
    }

    public DataBuffer(BufferSet bufferSet, BufferConfiguration bufferConfiguration) {
        super(bufferSet, bufferConfiguration);
    }

    public byte[] getBytes() throws IOException {
        flush();
        return getOutputBuffer().getBytesArray();
    }

    public int getSize() {
        return getOutputBuffer().calculateBufferSize();
    }


    public List getList() throws IOException {
        flush();
        return getOutputBuffer().getBufferList();
    }


    @Override
    public BufferedOutput replaceBuffer(BufferedOutput bufferedOutput) throws IOException {
        if (bufferedOutput instanceof BufferSet) {
            return super.replaceBuffer(bufferedOutput);
        }
        throw new IllegalArgumentException("错误的数据类型#1！");
    }


    @Override
    public void resetBuffer() {
        super.resetBuffer();
        getOutputBuffer().clearBuffers();
    }


    public BufferSet getOutputBuffer() {
        return (BufferSet) this.outputBuffer;
    }


    public UBuffer getBuffer() throws IOException {
        flush();
        return getOutputBuffer().mergeBuffers();
    }
}
