package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;


@StringEncryption
@ControlFlowObfuscation
public class HeypixelCipher extends Cipher {
    public HeypixelCipher(CipherSpi cipherSpi, Provider provider, String str) {
        super(cipherSpi, provider, str);
    }


    @NativeObfuscation.Inline
    public static Cipher get(String str) throws NoSuchPaddingException, NoSuchAlgorithmException {
        return getInstance(str);
    }
}
