package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.CodingErrorAction;


@StringEncryption
@ControlFlowObfuscation
public class ProcessorBuilder implements Cloneable {
    public int initialBufferSize;
    public boolean useDirectBuffers;
    public int maxBufferSize;
    public boolean isClosed;
    public CodingErrorAction onMalformedInputAction;
    public CodingErrorAction onUnmappableCharacterAction;
    public int bufferChunkSize;


    public ProcessorBuilder() {
        this.isClosed = true;
        this.useDirectBuffers = true;
        this.onUnmappableCharacterAction = CodingErrorAction.REPLACE;
        this.onMalformedInputAction = CodingErrorAction.REPLACE;
        this.initialBufferSize = Integer.MAX_VALUE;
        this.bufferChunkSize = 8192;
        this.maxBufferSize = 8192;
    }

    public ProcessorBuilder(ProcessorBuilder base) {
        this.isClosed = true;
        this.useDirectBuffers = true;
        this.onUnmappableCharacterAction = CodingErrorAction.REPLACE;
        this.onMalformedInputAction = CodingErrorAction.REPLACE;
        this.initialBufferSize = Integer.MAX_VALUE;
        this.bufferChunkSize = 8192;
        this.maxBufferSize = 8192;
        this.isClosed = base.isClosed;
        this.useDirectBuffers = base.useDirectBuffers;
        this.onUnmappableCharacterAction = base.onUnmappableCharacterAction;
        this.onMalformedInputAction = base.onMalformedInputAction;
        this.initialBufferSize = base.initialBufferSize;
        this.bufferChunkSize = base.bufferChunkSize;
    }

    public ProcessorBuilder cloneConfiguration() {
        return new ProcessorBuilder(this);
    }

    public boolean useDirectBuffers() {
        return this.useDirectBuffers;
    }

    public CodingErrorAction getOnUnmappableCharacterAction() {
        return this.onUnmappableCharacterAction;
    }

    public ProcessorBuilder setOnUnmappableCharacterAction(CodingErrorAction codingErrorAction) {
        ProcessorBuilder builder = cloneConfiguration();
        builder.onUnmappableCharacterAction = codingErrorAction;
        return builder;
    }

    public DataBufferProcessor createDataBufferProcessorFromChannel(ReadableByteChannel readableByteChannel) {
        return createProcessor(new ReadableChannelBuffer(readableByteChannel, this.bufferChunkSize));
    }

    public int getBufferChunkSize() {
        return this.bufferChunkSize;
    }

    public ProcessorBuilder setBufferChunkSize(int i) {
        ProcessorBuilder builder = cloneConfiguration();
        builder.bufferChunkSize = i;
        return builder;
    }

    public Object clone() {
        return cloneConfiguration();
    }

    public int getMaxBufferSize() {
        return this.maxBufferSize;
    }

    public ProcessorBuilder setMaxBufferSize(int i) {
        ProcessorBuilder builder = cloneConfiguration();
        builder.maxBufferSize = i;
        return builder;
    }

    public int hashCode() {
        int i = 31 * (((31 * (this.isClosed ? 1 : 0)) - ((-(this.useDirectBuffers ? 1 : 0)) - 1)) - 1);
        int hashCode = this.onUnmappableCharacterAction != null ? this.onUnmappableCharacterAction.hashCode() : 0;
        int i2 = 31 * (((31 * ((i ^ hashCode) + (((hashCode | ((-i) - 1)) - ((-i) - 1)) * 2))) - ((-(this.onMalformedInputAction != null ? this.onMalformedInputAction.hashCode() : 0)) - 1)) - 1);
        int i3 = this.initialBufferSize;
        int i4 = 31 * ((i2 | i3) + ((i3 | ((-i2) - 1)) - ((-i2) - 1)));
        int i5 = this.bufferChunkSize;
        return (31 * ((i4 | i5) + ((i5 | ((-i4) - 1)) - ((-i4) - 1)))) + this.maxBufferSize;
    }


    public ProcessorBuilder setUseDirectBuffers(boolean z) {
        ProcessorBuilder builder = cloneConfiguration();
        builder.useDirectBuffers = z;
        return builder;
    }

    public CodingErrorAction getOnMalformedInputAction() {
        return this.onMalformedInputAction;
    }

    public ProcessorBuilder setOnMalformedInputAction(CodingErrorAction codingErrorAction) {
        ProcessorBuilder builder = cloneConfiguration();
        builder.onMalformedInputAction = codingErrorAction;
        return builder;
    }

    public DataBufferProcessor createFromByteBuffer(ByteBuffer byteBuffer) {
        return createProcessor(new CopiedBuffer(byteBuffer));
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public ProcessorBuilder setClosed(boolean z) {
        ProcessorBuilder builder = cloneConfiguration();
        builder.isClosed = z;
        return builder;
    }

    public int getInitialBufferSize() {
        return this.initialBufferSize;
    }

    public ProcessorBuilder setInitialBufferSize(int i) {
        ProcessorBuilder builder = cloneConfiguration();
        builder.initialBufferSize = i;
        return builder;
    }

    public DataBufferProcessor createProcessor(ILBuffer iLBuffer) {
        return new DataBufferProcessor(iLBuffer, this);
    }

    public DataBufferProcessor createFromBytes(byte[] bArr) {
        return createProcessor(new BufferWrapper(bArr));
    }

    public DataBufferProcessor createFromBytes(byte[] bArr, int i, int i2) {
        return createProcessor(new BufferWrapper(bArr, i, i2));
    }

    public DataBufferProcessor createFromStream(InputStream inputStream) {
        return createProcessor(new InputStreamBuffer(inputStream, this.bufferChunkSize));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProcessorBuilder unMapped5)) {
            return false;
        }
        return this.isClosed == unMapped5.isClosed && this.useDirectBuffers == unMapped5.useDirectBuffers && this.onUnmappableCharacterAction == unMapped5.onUnmappableCharacterAction && this.onMalformedInputAction == unMapped5.onMalformedInputAction && this.initialBufferSize == unMapped5.initialBufferSize && this.maxBufferSize == unMapped5.maxBufferSize && this.bufferChunkSize == unMapped5.bufferChunkSize;
    }
}
