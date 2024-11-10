package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;


import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public class ExTH {
    public byte type;
    public int len;

    public ExTH(byte type, int len) {
        MsgUtils.checkArgumentWithMessage(len >= 0, "length must be >= 0");
        this.type = type;
        this.len = len;
    }

    public static byte of(int i) {
        MsgUtils.checkArgumentWithMessage(-128 <= i && i <= 127, "Extension type code must be within the range of byte");
        return (byte) i;
    }

    public boolean reset() {
        return this.type == -1;
    }

    public String toString() {
        return String.format("ExTH -> 类型:%d, 长度:%,d", this.type, this.len);
    }

    public byte getType() {
        return this.type;
    }

    public int hashCode() {
        int i = (this.type + 31) * 31;
        int i2 = this.len;
        return (i ^ i2) + (((i2 | ((-i) - 1)) - ((-i) - 1)) * 2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExTH exTH)) {
            return false;
        }
        return this.type == exTH.type && this.len == exTH.len;
    }

    public int getLength() {
        return this.len;
    }
}
