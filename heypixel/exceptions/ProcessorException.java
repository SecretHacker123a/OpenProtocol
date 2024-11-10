package dev.undefinedteam.gensh1n.protocol.heypixel.exceptions;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

public class ProcessorException extends RuntimeException {
    public long val;

    public ProcessorException(String str, long j) {
        super(str);
        this.val = j;
    }

    public ProcessorException(long j) {
        this.val = j;
    }

    public long Method17867() {
        return this.val;
    }
}
