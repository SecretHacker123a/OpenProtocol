package dev.undefinedteam.gensh1n.protocol.heypixel;


import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public class TypeRegistry {
    public static int[] VALUE_TYPES;
    public static int[] DATA_TYPES = new int[DataTypes.values().length];

    static {
        try {
            DATA_TYPES[DataTypes.NULL.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            DATA_TYPES[DataTypes.BOOLEAN.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            DATA_TYPES[DataTypes.LONG.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            DATA_TYPES[DataTypes.DOUBLE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            DATA_TYPES[DataTypes.BYTES_BUF.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            DATA_TYPES[DataTypes.BYTES.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            DATA_TYPES[DataTypes.LIST.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            DATA_TYPES[DataTypes.MAP.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
        try {
            DATA_TYPES[DataTypes.INSTANT.ordinal()] = 9;
        } catch (NoSuchFieldError e9) {
        }
        VALUE_TYPES = new int[ValueTypeEnum.values().length];
        try {
            VALUE_TYPES[ValueTypeEnum.IiIIiiIIIIiIIIiIiIIiIiiiiiIiIIiiIIIIiiii.ordinal()] = 1;
        } catch (NoSuchFieldError e10) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIIiiiIIiIiIIIiIIIiiiIiIIiIIiiIIIiIiIiiI.ordinal()] = 2;
        } catch (NoSuchFieldError e11) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIIIiIiiiIIIIIIIIiiIIIIIiiIIIiiiIiIIIiII.ordinal()] = 3;
        } catch (NoSuchFieldError e12) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IiiiIiIIiIiIiIiIiIIIiIIiIiIiIIiiiIIiIiIi.ordinal()] = 4;
        } catch (NoSuchFieldError e13) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIiIiiiiiIIiiiiIiiiIIiiiiiIIIIiIiIIiiIii.ordinal()] = 5;
        } catch (NoSuchFieldError e14) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iIiiIiIiiiIiIiiiiiiIIIIiIiiiiIIIiiiIIiIi.ordinal()] = 6;
        } catch (NoSuchFieldError e15) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIiiiiiIIIiIiiIiiiiIiIIiiiiIiIiIiIIIIIii.ordinal()] = 7;
        } catch (NoSuchFieldError e16) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.BYTE_BIGINTEGER.ordinal()] = 8;
        } catch (NoSuchFieldError e17) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIiIIiIIIIiIiiiIIIIiiIIiiIIiIIiiiiIiiIii.ordinal()] = 9;
        } catch (NoSuchFieldError e18) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.SHORT_BIGINTEGER.ordinal()] = 10;
        } catch (NoSuchFieldError e19) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiIIiiiIIIiiIIiiiiiiIiiiIiiIIiiiIiiiiiIi.ordinal()] = 11;
        } catch (NoSuchFieldError e20) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.INT_BIGINTEGER.ordinal()] = 12;
        } catch (NoSuchFieldError e21) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiiiiIIiIIIIiIiiIIIIIiiIiIiiIIIiiiIiIiii.ordinal()] = 13;
        } catch (NoSuchFieldError e22) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiiiiiiiiiiIiiIIIiiIiiIiiiIiiiiiIIiIIIii.ordinal()] = 14;
        } catch (NoSuchFieldError e23) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.LONG_BIGINTEGER.ordinal()] = 15;
        } catch (NoSuchFieldError e24) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.BIGINTEGER.ordinal()] = 16;
        } catch (NoSuchFieldError e25) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iIIIIIiIiiiIIIiiiiiiiIIIIIiIIiiiIiiIiiII.ordinal()] = 17;
        } catch (NoSuchFieldError e26) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iIiiIiiIIiiIiiiiiIIiiIiIiIiIIiIiIIiiIiiI.ordinal()] = 18;
        } catch (NoSuchFieldError e27) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiIiiiIIiIiiiiiIIiIIIiiiIIIiiIiIIIiIIIIi.ordinal()] = 19;
        } catch (NoSuchFieldError e28) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiIiiiIiiIiiiIiiiIIIIiIIiIIIiIIIIiIiiIiI.ordinal()] = 20;
        } catch (NoSuchFieldError e29) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIiiIIiIIIiiiiiIiIIiIIiiiIiIIiIiiiiiIIiI.ordinal()] = 21;
        } catch (NoSuchFieldError e30) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIIIiIIIiiIIiIiIiiIIIIIIiiIIIIIIIIiIIiIi.ordinal()] = 22;
        } catch (NoSuchFieldError e31) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiIiIIiiiIiIIiiIIiIiiiiIIIIiIIIiiIIIIiii.ordinal()] = 23;
        } catch (NoSuchFieldError e32) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiIiiIIiiiiiiIiIIIiiIIiiiIiIiiIIIiIiIiiI.ordinal()] = 24;
        } catch (NoSuchFieldError e33) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiiiiIiiiIIiIiiiIiiIIIIIiIiIIiIIIiiIIiii.ordinal()] = 25;
        } catch (NoSuchFieldError e34) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIIiiiIiiIiIiIiiIIIIiiiIiIIIIIIiIIiIiIIi.ordinal()] = 26;
        } catch (NoSuchFieldError e35) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iIiiiIIIiIIiIiIiiiIiIIiIIiIIIIiIIIIiIiIi.ordinal()] = 27;
        } catch (NoSuchFieldError e36) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIIIIIIiiIIIiiIIiiIIiIiiiIIiIIiIiiiiIIii.ordinal()] = 28;
        } catch (NoSuchFieldError e37) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IIiIiiIiIIiiiIiiiiIiiiIiiIiiiIiIiiiiiiiI.ordinal()] = 29;
        } catch (NoSuchFieldError e38) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiiiIiiIiIIIiiiIIiiiiIiiiIiiIiIIIiIIIiIi.ordinal()] = 30;
        } catch (NoSuchFieldError e39) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iiiiIIIIIiIIiiIIIIIIIiIiIIIIIiIiIiiiIiiI.ordinal()] = 31;
        } catch (NoSuchFieldError e40) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.iIiiiiIIiiiIIiiiiiIiiiiiIiIIiIiiiIIIIIii.ordinal()] = 32;
        } catch (NoSuchFieldError e41) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IiiIiIIIiiiiIiiIIIIIiiIiiIiIiIIiIIIIIiII.ordinal()] = 33;
        } catch (NoSuchFieldError e42) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IiiiIiIiIiiIiiIIiiIiiIIIiIIiIiiiiiIiIiiI.ordinal()] = 34;
        } catch (NoSuchFieldError e43) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.IiIiIiiiiiiiIiiIIiiIIIIiiiIIiiIiIiIIiIIi.ordinal()] = 35;
        } catch (NoSuchFieldError e44) {
        }
        try {
            VALUE_TYPES[ValueTypeEnum.NULL.ordinal()] = 36;
        } catch (NoSuchFieldError e45) {
        }
    }
}
