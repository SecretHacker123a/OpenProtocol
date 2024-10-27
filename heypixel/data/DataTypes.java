package dev.undefinedteam.gensh1n.protocol.heypixel.data;

import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public enum DataTypes {
    NULL(false, false),
    BOOLEAN(false, false),
    LONG(true, false),
    DOUBLE(true, false),
    BYTES_BUF(false, true),
    BYTES(false, true),
    LIST(false, false),
    MAP(false, false),
    INSTANT(false, false);

    public boolean isNullable;
    public boolean isVariableLength;

    DataTypes(boolean z, boolean z2) {
        this.isVariableLength = z;
        this.isNullable = z2;
    }

    public boolean isBytesBuf() {
        return this == BYTES_BUF;
    }

    public boolean isMap() {
        return this == MAP;
    }

    public boolean isInstant() {
        return this == INSTANT;
    }

    public boolean isBytes() {
        return this == BYTES;
    }

    public boolean isBoolean() {
        return this == BOOLEAN;
    }

    public boolean isList() {
        return this == LIST;
    }

    public boolean isNull() {
        return this == NULL;
    }

    public boolean isNullable() {
        return this.isNullable;
    }

    public boolean isLong() {
        return this == LONG;
    }

    public boolean isDouble() {
        return this == DOUBLE;
    }

    public boolean isVariableLength() {
        return this.isVariableLength;
    }
}
