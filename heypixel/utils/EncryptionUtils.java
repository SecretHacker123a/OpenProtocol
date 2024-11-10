package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelSessionManager;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


@StringEncryption
@NativeObfuscation
@ControlFlowObfuscation
public class EncryptionUtils {


    @NativeObfuscation.Inline
    public static String decodeBase64(HeypixelSessionManager manager, String str) throws NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return decryptBytes(manager, Base64.getDecoder().decode(str));
    }


    @NativeObfuscation.Inline
    public static PublicKey generatePublicKey(HeypixelSessionManager manager, byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var mode = manager.convertBytesToString(manager.encryptMode);
        //System.out.println(mode);
        return KeyFactory.getInstance(mode).generatePublic(new X509EncodedKeySpec(bArr));
    }


    @NativeObfuscation.Inline
    public static String encryptString(HeypixelSessionManager manager, String str) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        PublicKey generatePublicKey = generatePublicKey(manager, manager.key2);
        Cipher serverChipher = ChiperUtils.getServerChipher(manager);
        serverChipher.init(1, generatePublicKey);
        return Base64.getEncoder().encodeToString(serverChipher.doFinal(str.getBytes()));
    }


    @NativeObfuscation.Inline
    public static PrivateKey generatePrivateKey(HeypixelSessionManager manager, byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(manager.convertBytesToString(manager.encryptMode)).generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }


    @NativeObfuscation.Inline
    public static String decryptBytes(HeypixelSessionManager manager, byte[] bArr) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException, InvalidKeySpecException {
        PrivateKey generatePrivateKey = generatePrivateKey(manager, manager.key3);
        Cipher serverChipher = ChiperUtils.getServerChipher(manager);
        serverChipher.init(2, generatePrivateKey);
        return new String(serverChipher.doFinal(bArr), StandardCharsets.UTF_8);
    }
}
