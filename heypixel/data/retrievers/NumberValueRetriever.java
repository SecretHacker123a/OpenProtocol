package dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.math.BigInteger;



public interface NumberValueRetriever extends ObjectData {
    byte getByte();

    BigInteger getBigInteger();

    int getInteger();

    float getFloat();

    double getDouble();

    long getLong();

    short getShort();
}
