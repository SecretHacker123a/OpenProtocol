package dev.undefinedteam.gensh1n.protocol.heypixel;


import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataTypes;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public enum ValueDataType {
    NULL_TYPE(DataTypes.NULL),
    BOOLEAN_TYPE(DataTypes.BOOLEAN),
    LONG_TYPE(DataTypes.LONG),
    BIGINTEGER_LONG_TYPE(DataTypes.LONG),
    DOUBLE_TYPE(DataTypes.DOUBLE),
    BTYEARRAY_TYPE(DataTypes.BYTES),
    BYTES_TYPE(DataTypes.BYTES_BUF),
    LIST_TYPE(DataTypes.LIST),
    MAP_DATA(DataTypes.MAP),
    UINSTANT_TYPE(DataTypes.INSTANT),
    INSTANT_TYPE(DataTypes.INSTANT);

    public DataTypes valueType;

    ValueDataType(DataTypes dataTypes) {
        this.valueType = dataTypes;
    }

    public DataTypes getValueType() {
        return this.valueType;
    }
}
