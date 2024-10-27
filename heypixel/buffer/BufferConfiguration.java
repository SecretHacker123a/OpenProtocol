package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;



@StringEncryption
@ControlFlowObfuscation
public class BufferConfiguration implements Cloneable {


    public int initialBufferSize;


    public boolean useDirectBuffers;


    public int bufferSize;


    public int maxBufferSize;

    public BufferConfiguration(BufferConfiguration bufferConfiguration) {
        this.initialBufferSize = 512;
        this.maxBufferSize = 8192;
        this.bufferSize = 8192;
        this.useDirectBuffers = true;
        this.initialBufferSize = bufferConfiguration.initialBufferSize;
        this.maxBufferSize = bufferConfiguration.maxBufferSize;
        this.bufferSize = bufferConfiguration.bufferSize;
        this.useDirectBuffers = bufferConfiguration.useDirectBuffers;
    }

    public BufferConfiguration() {
        this.initialBufferSize = 512;
        this.maxBufferSize = 8192;
        this.bufferSize = 8192;
        this.useDirectBuffers = true;
    }

    public MessageBuffer createMessageBuffer(BufferedOutput bufferedOutput) {
        return new MessageBuffer(bufferedOutput, this);
    }

    public int getInitialBufferSize() {
        return this.initialBufferSize;
    }

    public MessageBuffer createMessageBufferFromChannel(WritableByteChannel writableByteChannel) {
        return createMessageBuffer(new ChannelBuffer(writableByteChannel, this.bufferSize));
    }

    public BufferConfiguration withBufferSize(int i) {
        BufferConfiguration cloneConfiguration = cloneConfiguration();
        cloneConfiguration.bufferSize = i;
        return cloneConfiguration;
    }

    public BufferConfiguration withInitialBufferSize(int i) {
        BufferConfiguration cloneConfiguration = cloneConfiguration();
        cloneConfiguration.initialBufferSize = i;
        return cloneConfiguration;
    }

    public boolean isDirectBuffersEnabled() {
        return this.useDirectBuffers;
    }


    public BufferConfiguration withMaxBufferSize(int i) {
        BufferConfiguration cloneConfiguration = cloneConfiguration();
        cloneConfiguration.maxBufferSize = i;
        return cloneConfiguration;
    }

    public int hashCode() {
        int i = 31 * ((31 * this.initialBufferSize) + this.maxBufferSize);
        int i2 = this.bufferSize;
        int i3 = 31 * ((i | i2) + ((i2 | ((-i) - 1)) - ((-i) - 1)));
        int i4 = this.useDirectBuffers ? 1 : 0;
        int i5 = i4;
        return ((i3 | i4) * 2) - ((i3 | i5) & (((-i5) - 1) | ((-i3) - 1)));
    }


    public BufferConfiguration withDirectBuffersEnabled(boolean z) {
        BufferConfiguration cloneConfiguration = cloneConfiguration();
        cloneConfiguration.useDirectBuffers = z;
        return cloneConfiguration;
    }


    public MessageBuffer createMessageBufferFromStream(OutputStream outputStream) {
        return createMessageBuffer(new OutputStreamBuffer(outputStream, this.bufferSize));
    }


    public int getBufferSize() {
        return this.bufferSize;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BufferConfiguration bufferConfiguration)) {
            return false;
        }
        return this.initialBufferSize == bufferConfiguration.initialBufferSize && this.maxBufferSize == bufferConfiguration.maxBufferSize && this.bufferSize == bufferConfiguration.bufferSize && this.useDirectBuffers == bufferConfiguration.useDirectBuffers;
    }


    public BufferConfiguration cloneConfiguration() {
        return new BufferConfiguration(this);
    }


    public int getMaxBufferSize() {
        return this.maxBufferSize;
    }


    public DataBuffer createBuffer() {
        return new DataBuffer(this);
    }


    public Object clone() {
        return cloneConfiguration();
    }
}
