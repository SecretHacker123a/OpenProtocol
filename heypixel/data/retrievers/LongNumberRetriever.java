package dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers;

import dev.undefinedteam.gensh1n.protocol.heypixel.ValueTypeEnum;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.math.BigInteger;



public interface LongNumberRetriever extends NumberValueRetriever {
    long toLong();

    boolean isLong();

    boolean isByte();

    boolean isInt();

    ValueTypeEnum getValueTypeI();

    boolean isShort();

    int toInt();

    BigInteger toBigInteger();

    short toShort();

    byte toByte();
}
