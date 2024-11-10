package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import javax.crypto.KeyGenerator;
import javax.crypto.KeyGeneratorSpi;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;


@StringEncryption
@ControlFlowObfuscation
public class ProtocolKeyGenerator extends KeyGenerator {

    public ProtocolKeyGenerator(KeyGeneratorSpi keyGeneratorSpi, Provider provider, String str) {
        super(keyGeneratorSpi, provider, str);
    }


    @NativeObfuscation.Inline
    public static ProtocolKeyGenerator fromString(String str) throws NoSuchAlgorithmException {
        return (ProtocolKeyGenerator) KeyGenerator.getInstance(str);
    }
}
