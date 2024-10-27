package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.ArrayList;
import java.util.List;


@StringEncryption
@ControlFlowObfuscation
public class BufferSet implements BufferedOutput {


    public List<UBuffer> bufferList;


    public int bufferSize;


    public UBuffer buffer;


    public BufferSet() {
        this(8192);
    }

    public BufferSet(int i) {
        this.bufferSize = i;
        this.bufferList = new ArrayList<>();
    }

    public void writeInt(int i) {
        this.bufferList.add(this.buffer.sliceBuffer(0, i));
        if (this.buffer.remaining() - i > this.bufferSize / 4) {
            this.buffer = this.buffer.sliceBuffer(i, this.buffer.remaining() - i);
        } else {
            this.buffer = null;
        }
    }

    public byte[] getBytesArray() {
        byte[] bArr = new byte[calculateBufferSize()];
        int i = 0;
        for (UBuffer buffer : this.bufferList) {
            buffer.writeArrayDataToBuffer(0, bArr, i, buffer.remaining());
            i += buffer.remaining();
        }
        return bArr;
    }

    public void writeBytes(byte[] bArr, int i, int i2) {
        UBuffer allocateBuffer = UBuffer.allocateBuffer(i2);
        allocateBuffer.copyMemory(0, bArr, i, i2);
        this.bufferList.add(allocateBuffer);
    }

    @Override
    public void flush() {
    }

    public UBuffer allocateBuffer(int i) {
        if (this.buffer != null && this.buffer.remaining() > i) {
            return this.buffer;
        }
        UBuffer allocateBuffer = UBuffer.allocateBuffer(Math.max(this.bufferSize, i));
        this.buffer = allocateBuffer;
        return allocateBuffer;
    }

    public UBuffer mergeBuffers() {
        return this.bufferList.size() == 1 ? this.bufferList.get(0) : this.bufferList.isEmpty() ? UBuffer.allocateBuffer(0) : UBuffer.createFromByteArray(getBytesArray());
    }

    @Override
    public void close() {
    }


    public void writeBytesWithOffset(byte[] bArr, int i, int i2) {
        this.bufferList.add(UBuffer.wrapByteArray(bArr, i, i2));
    }


    public void clearBuffers() {
        this.bufferList.clear();
    }


    public List<UBuffer> getBufferList() {
        return new ArrayList<>(this.bufferList);
    }


    public int calculateBufferSize() {
        int i = 0;
        for (UBuffer value : this.bufferList) {
            i = (i - ((-value.remaining()) - 1)) - 1;
        }
        return i;
    }
}
