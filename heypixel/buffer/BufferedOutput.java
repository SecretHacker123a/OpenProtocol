package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;


@StringEncryption
@ControlFlowObfuscation
public interface BufferedOutput extends Closeable, Flushable {
    UBuffer allocateBuffer(int i);

    void writeInt(int i) throws IOException;

    void writeBytes(byte[] bArr, int i, int i2) throws IOException;

    void writeBytesWithOffset(byte[] bArr, int i, int i2) throws IOException;
}
