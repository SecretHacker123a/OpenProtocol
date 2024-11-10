package dev.undefinedteam.gensh1n.protocol.heypixel.exceptions;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.charset.CharacterCodingException;

public class CharacterCodingFailedException extends RuntimeException {
    public CharacterCodingFailedException(CharacterCodingException characterCodingException) {
        super(characterCodingException);
    }


    public CharacterCodingFailedException(String str, CharacterCodingException characterCodingException) {
        super(str, characterCodingException);
    }

    public CharacterCodingException cause() {
        return (CharacterCodingException) super.getCause();
    }

    public Throwable getThrowable() {
        return cause();
    }
}
