package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.Closeable;
import java.io.IOException;



public interface ILBuffer extends Closeable {
    @Override
    void close() throws IOException;

    UBuffer getBuffer() throws IOException;
}
