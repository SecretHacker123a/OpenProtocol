package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.ByteBuffer;


@StringEncryption
@ControlFlowObfuscation
public interface BytesDataProvider extends ObjectData {
    ByteBuffer getByteBuffer();

    String toString();

    String getString();

    byte[] getBytes();
}
