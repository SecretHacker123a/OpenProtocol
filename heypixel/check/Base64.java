package dev.undefinedteam.gensh1n.protocol.heypixel.check;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;



@StringEncryption
@NativeObfuscation
@ControlFlowObfuscation
public class Base64 {

    public static String encodeToString(byte[] bArr) {
        return java.util.Base64.getEncoder().encodeToString(bArr);
    }

    @NativeObfuscation.Inline
    public static byte[] deocde(String str) {
        return java.util.Base64.getDecoder().decode(str);
    }
}
