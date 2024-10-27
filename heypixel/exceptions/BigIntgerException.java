package dev.undefinedteam.gensh1n.protocol.heypixel.exceptions;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.math.BigInteger;

public class BigIntgerException extends RuntimeException {
    public BigInteger bigInteger;


    public BigIntgerException(long data) {
        this(BigInteger.valueOf(data));
    }

    public BigIntgerException(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

    public BigIntgerException(String str, BigInteger bigInteger) {
        super(str);
        this.bigInteger = bigInteger;
    }

    public String getString() {
        return this.bigInteger.toString();
    }

    public BigInteger getBigInter() {
        return this.bigInteger;
    }
}
