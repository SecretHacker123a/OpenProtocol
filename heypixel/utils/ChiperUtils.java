package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelSessionManager;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;



@StringEncryption
@ControlFlowObfuscation
public class ChiperUtils {

    @NativeObfuscation.Inline
    public static Cipher getServerCipher2(HeypixelSessionManager manager) throws NoSuchPaddingException, NoSuchAlgorithmException {
        return HeypixelCipher.get(manager.convertBytesToString(manager.encryptMode1));
    }


    @NativeObfuscation.Inline
    public static ProtocolKeyGenerator Method5165(HeypixelSessionManager manager) throws NoSuchAlgorithmException {
        return ProtocolKeyGenerator.fromString(manager.convertBytesToString(manager.encryptMode1));
    }

    @NativeObfuscation.Inline
    public static Cipher getServerChipher(HeypixelSessionManager manager) throws NoSuchPaddingException, NoSuchAlgorithmException {
        return HeypixelCipher.get(manager.convertBytesToString(manager.encryptMode));
    }
}
