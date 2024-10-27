package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.BufferConstructorType;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public class BufferConstructorMapping {

    public static int[] constructorTypeToIntMap = new int[BufferConstructorType.values().length];

    static {
        try {
            constructorTypeToIntMap[BufferConstructorType.TYPE_1.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            constructorTypeToIntMap[BufferConstructorType.TYPE_2.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            constructorTypeToIntMap[BufferConstructorType.TYPE_3.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            constructorTypeToIntMap[BufferConstructorType.TYPE_4.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            constructorTypeToIntMap[BufferConstructorType.TYPE_5.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
