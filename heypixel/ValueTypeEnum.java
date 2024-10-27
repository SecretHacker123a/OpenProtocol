package dev.undefinedteam.gensh1n.protocol.heypixel;


import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public enum ValueTypeEnum {
    IiIIiiIIIIiIIIiIiIIiIiiiiiIiIIiiIIIIiiii(DataTypes.LONG),
    IIiIiiiiiIIiiiiIiiiIIiiiiiIIIIiIiIIiiIii(DataTypes.MAP),
    iIiiIiIiiiIiIiiiiiiIIIIiIiiiiIIIiiiIIiIi(DataTypes.LIST),
    IIiiiiiIIIiIiiIiiiiIiIIiiiiIiIiIiIIIIIii(DataTypes.BYTES_BUF),
    IiiiIiIIiIiIiIiIiIIIiIIiIiIiIIiiiIIiIiIi(DataTypes.NULL),
    NULL(null),
    IIIIiIiiiIIIIIIIIiiIIIIIiiIIIiiiIiIIIiII(DataTypes.BOOLEAN),
    iIiiIiiIIiiIiiiiiIIiiIiIiIiIIiIiIIiiIiiI(DataTypes.BYTES),
    iiIiiiIiiIiiiIiiiIIIIiIIiIIIiIIIIiIiiIiI(DataTypes.BYTES),
    IIIIiIIIiiIIiIiIiiIIIIIIiiIIIIIIIIiIIiIi(DataTypes.BYTES),
    IIiIiiIiIIiiiIiiiiIiiiIiiIiiiIiIiiiiiiiI(DataTypes.INSTANT),
    iiiiIiiIiIIIiiiIIiiiiIiiiIiiIiIIIiIIIiIi(DataTypes.INSTANT),
    iiiiIIIIIiIIiiIIIIIIIiIiIIIIIiIiIiiiIiiI(DataTypes.INSTANT),
    iiiiiiiiiiiIiiIIIiiIiiIiiiIiiiiiIIiIIIii(DataTypes.DOUBLE),
    iIIIIIiIiiiIIIiiiiiiiIIIIIiIIiiiIiiIiiII(DataTypes.DOUBLE),
    IIiIIiIIIIiIiiiIIIIiiIIiiIIiIIiiiiIiiIii(DataTypes.LONG),
    iiIIiiiIIIiiIIiiiiiiIiiiIiiIIiiiIiiiiiIi(DataTypes.LONG),
    iiiiiIIiIIIIiIiiIIIIIiiIiIiiIIIiiiIiIiii(DataTypes.LONG),
    BIGINTEGER(DataTypes.LONG),
    BYTE_BIGINTEGER(DataTypes.LONG),
    SHORT_BIGINTEGER(DataTypes.LONG),
    INT_BIGINTEGER(DataTypes.LONG),
    LONG_BIGINTEGER(DataTypes.LONG),
    iiIiiIIiiiiiiIiIIIiiIIiiiIiIiiIIIiIiIiiI(DataTypes.INSTANT),
    iiiiiIiiiIIiIiiiIiiIIIIIiIiIIiIIIiiIIiii(DataTypes.INSTANT),
    IIIiiiIiiIiIiIiiIIIIiiiIiIIIIIIiIIiIiIIi(DataTypes.INSTANT),
    iIiiiIIIiIIiIiIiiiIiIIiIIiIIIIiIIIIiIiIi(DataTypes.INSTANT),
    IIIIIIIiiIIIiiIIiiIIiIiiiIIiIIiIiiiiIIii(DataTypes.INSTANT),
    iiIiiiIIiIiiiiiIIiIIIiiiIIIiiIiIIIiIIIIi(DataTypes.BYTES_BUF),
    IIiiIIiIIIiiiiiIiIIiIIiiiIiIIiIiiiiiIIiI(DataTypes.BYTES_BUF),
    iiIiIIiiiIiIIiiIIiIiiiiIIIIiIIIiiIIIIiii(DataTypes.BYTES_BUF),
    iIiiiiIIiiiIIiiiiiIiiiiiIiIIiIiiiIIIIIii(DataTypes.LIST),
    IiiIiIIIiiiiIiiIIIIIiiIiiIiIiIIiIIIIIiII(DataTypes.LIST),
    IiiiIiIiIiiIiiIIiiIiiIIIiIIiIiiiiiIiIiiI(DataTypes.MAP),
    IiIiIiiiiiiiIiiIIiiIIIIiiiIIiiIiIiIIiIIi(DataTypes.MAP),
    IIIiiiIIiIiIIIiIIIiiiIiIIiIIiiIIIiIiIiiI(DataTypes.LONG);

    static {
        for (int i = 0; i <= 255; i++) {
            DataBufferUtils.valueFormats[i] = toMessageFormat((byte) i);
        }
    }

    public DataTypes valueType;


    ValueTypeEnum(DataTypes dataTypes) {
        this.valueType = dataTypes;
    }

    public static ValueTypeEnum toMessageFormat(byte b) {
        if (DataBufferUtils.Method10469(b)) {
            return IiIIiiIIIIiIIIiIiIIiIiiiiiIiIIiiIIIIiiii;
        }
        if (DataBufferUtils.Method10505(b)) {
            return IIIiiiIIiIiIIIiIIIiiiIiIIiIIiiIIIiIiIiiI;
        }
        if (DataBufferUtils.Method10487(b)) {
            return IIiiiiiIIIiIiiIiiiiIiIIiiiiIiIiIiIIIIIii;
        }
        if (DataBufferUtils.Method10415(b)) {
            return iIiiIiIiiiIiIiiiiiiIIIIiIiiiiIIIiiiIIiIi;
        }
        if (DataBufferUtils.Method10409(b)) {
            return IIiIiiiiiIIiiiiIiiiIIiiiiiIIIIiIiIIiiIii;
        }
        return switch (b) {
            case -64 -> IiiiIiIIiIiIiIiIiIIIiIIiIiIiIIiiiIIiIiIi;
            case -62, -61 -> IIIIiIiiiIIIIIIIIiiIIIIIiiIIIiiiIiIIIiII;
            case -60 -> iIiiIiiIIiiIiiiiiIIiiIiIiIiIIiIiIIiiIiiI;
            case -59 -> iiIiiiIiiIiiiIiiiIIIIiIIiIIIiIIIIiIiiIiI;
            case -58 -> IIIIiIIIiiIIiIiIiiIIIIIIiiIIIIIIIIiIIiIi;
            case -57 -> IIiIiiIiIIiiiIiiiiIiiiIiiIiiiIiIiiiiiiiI;
            case -56 -> iiiiIiiIiIIIiiiIIiiiiIiiiIiiIiIIIiIIIiIi;
            case -55 -> iiiiIIIIIiIIiiIIIIIIIiIiIIIIIiIiIiiiIiiI;
            case -54 -> iiiiiiiiiiiIiiIIIiiIiiIiiiIiiiiiIIiIIIii;
            case -53 -> iIIIIIiIiiiIIIiiiiiiiIIIIIiIIiiiIiiIiiII;
            case -52 -> IIiIIiIIIIiIiiiIIIIiiIIiiIIiIIiiiiIiiIii;
            case -51 -> iiIIiiiIIIiiIIiiiiiiIiiiIiiIIiiiIiiiiiIi;
            case -50 -> iiiiiIIiIIIIiIiiIIIIIiiIiIiiIIIiiiIiIiii;
            case -49 -> BIGINTEGER;
            case -48 -> BYTE_BIGINTEGER;
            case -47 -> SHORT_BIGINTEGER;
            case -46 -> INT_BIGINTEGER;
            case -45 -> LONG_BIGINTEGER;
            case -44 -> iiIiiIIiiiiiiIiIIIiiIIiiiIiIiiIIIiIiIiiI;
            case -43 -> iiiiiIiiiIIiIiiiIiiIIIIIiIiIIiIIIiiIIiii;
            case -42 -> IIIiiiIiiIiIiIiiIIIIiiiIiIIIIIIiIIiIiIIi;
            case -41 -> iIiiiIIIiIIiIiIiiiIiIIiIIiIIIIiIIIIiIiIi;
            case -40 -> IIIIIIIiiIIIiiIIiiIIiIiiiIIiIIiIiiiiIIii;
            case -39 -> iiIiiiIIiIiiiiiIIiIIIiiiIIIiiIiIIIiIIIIi;
            case -38 -> IIiiIIiIIIiiiiiIiIIiIIiiiIiIIiIiiiiiIIiI;
            case -37 -> iiIiIIiiiIiIIiiIIiIiiiiIIIIiIIIiiIIIIiii;
            case -36 -> iIiiiiIIiiiIIiiiiiIiiiiiIiIIiIiiiIIIIIii;
            case -35 -> IiiIiIIIiiiiIiiIIIIIiiIiiIiIiIIiIIIIIiII;
            case -34 -> IiiiIiIiIiiIiiIIiiIiiIIIiIIiIiiiiiIiIiiI;
            case -33 -> IiIiIiiiiiiiIiiIIiiIIIIiiiIIiiIiIiIIiIIi;
            default -> NULL;
        };
    }

    public static ValueTypeEnum valueOf(byte b) {
        return DataBufferUtils.valueFormats[(255 | ((-b) - 1)) + b + 1];
    }

    public static void main(String[] args) {
        var b = (byte) 127;
        System.out.println((255 | ((-b) - 1)) + b + 1);
    }

    public DataTypes getValueType() {
        if (this == NULL) {
            throw new RuntimeException("无法转换NS数据！你反编译看**呢？");
        }
        return this.valueType;
    }
}
