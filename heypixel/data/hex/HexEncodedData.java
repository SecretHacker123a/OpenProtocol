package dev.undefinedteam.gensh1n.protocol.heypixel.data.hex;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.BytesDataProvider;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.AbstractDataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.BytesDataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.ObjectRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.CharacterCodingFailedException;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import org.checkerframework.checker.units.qual.N;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;



@StringEncryption
@NativeObfuscation
@ControlFlowObfuscation
public abstract class HexEncodedData extends AbstractDataRetriever implements BytesDataRetriever {
    @NativeObfuscation.Inline
    public static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public byte[] data;
    public String result;
    public CharacterCodingException exception;

    public HexEncodedData(byte[] bArr) {
        this.data = bArr;
    }


    public HexEncodedData(String str) {
        this.result = str;
        this.data = str.getBytes(DataBufferUtils.charset);
    }

    public static void appendUnicodeCharacter(StringBuilder sb, String str) {
        sb.append("\"");
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < ' ') {
                switch (charAt) {
                    case '\b':
                        sb.append("\\b");
                        break;
                    case '\t':
                        sb.append("\\t");
                        break;
                    case '\n':
                        sb.append("\\n");
                        break;
                    case 11:
                    default:
                        Method11459(sb, charAt);
                        break;
                    case '\f':
                        sb.append("\\f");
                        break;
                    case '\r':
                        sb.append("\\r");
                        break;
                }
            } else if (charAt <= 127) {
                switch (charAt) {
                    case '\"':
                        sb.append("\\\"");
                        break;
                    case '\\':
                        sb.append("\\\\");
                        break;
                    default:
                        sb.append(charAt);
                        break;
                }
            } else if (charAt < 55296 || charAt > 57343) {
                sb.append(charAt);
            } else {
                Method11459(sb, charAt);
            }
        }
        sb.append("\"");
    }

    public static void Method11459(StringBuilder sb, int i) {
        sb.append("\\u");
        int i2 = i >> 12;
        sb.append(HEX_ARRAY[(15 | ((-i2) - 1)) - ((-i2) - 1)]);
        int i3 = i >> 8;
        sb.append(HEX_ARRAY[(15 | ((-i3) - 1)) - ((-i3) - 1)]);
        int i4 = i >> 4;
        sb.append(HEX_ARRAY[(15 | ((-i4) - 1)) - ((-i4) - 1)]);
        sb.append(HEX_ARRAY[(15 | ((-i) - 1)) - ((-i) - 1)]);
    }

    @Override
    public BytesDataRetriever retrieveBytes() {
        return this;
    }

    @Override
    public boolean isBytesBufType() {
        return super.isBytesBufType();
    }

    @Override
    public IBytesData getDefaultType() {
        return super.getDefaultType();
    }

    @Override
    public IDataList retrieveListData() {
        return super.retrieveListData();
    }

    @Override
    public String toString() {
        if (this.result == null) {
            Method11465();
        }
        return this.result;
    }

    @Override
    public boolean isNullType() {
        return super.isNullType();
    }

    @Override
    public IDoubleData retrieveDoubleData() {
        return super.retrieveDoubleData();
    }

    @Override
    public boolean isDoubleType() {
        return super.isDoubleType();
    }

    @Override
    public boolean isListType() {
        return super.isListType();
    }

    @Override
    public IHexBytesData retrieveByteArrayData() {
        return super.retrieveByteArrayData();
    }

    @Override
    public ILongNumberData retrieveLongNumber() {
        return super.retrieveLongNumber();
    }

    @Override
    public IBufferData retrieveBytesData() {
        return super.retrieveBytesData();
    }

    public byte[] getBytes() {
        return Arrays.copyOf(this.data, this.data.length);
    }

    @Override
    public IMapData retrieveMapData() {
        return super.retrieveMapData();
    }

    @Override
    public IInstantBytes retrieveInstantData() {
        return super.retrieveInstantData();
    }

    @Override
    public BytesDataProvider getAsBytes() {
        return this.retrieveBytes();
    }

    public String getDataAsString() {
        StringBuilder sb = new StringBuilder();
        appendUnicodeCharacter(sb, toString());
        return sb.toString();
    }

    @Override
    public ObjectRetriever getRetriever() {
        return super.getRetriever();
    }

    @Override
    public boolean isNullable() {
        return super.isNullable();
    }

    @Override
    public boolean isInstantType() {
        return super.isInstantType();
    }

    @Override
    public boolean isMapType() {
        return super.isMapType();
    }

    public void Method11465() {
        synchronized (this.data) {
            if (this.result != null) {
                return;
            }
            try {
                this.result = DataBufferUtils.charset.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).decode(getByteBuffer()).toString();
            } catch (CharacterCodingException e) {
                try {
                    this.result = DataBufferUtils.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).decode(getByteBuffer()).toString();
                    this.exception = e;
                } catch (CharacterCodingException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }

    @Override
    public INullData retrieveNullData() {
        return super.retrieveNullData();
    }

    @Override
    public boolean isBooleanType() {
        return super.isBooleanType();
    }

    @Override
    public boolean isBytesType() {
        return super.isBytesType();
    }

    @Override
    public boolean isVariableLength() {
        return super.isVariableLength();
    }

    @Override
    public boolean isLongType() {
        return super.isLongType();
    }

    public ByteBuffer getByteBuffer() {
        return ByteBuffer.wrap(this.data).asReadOnlyBuffer();
    }

    @Override
    public boolean isInstant1Type() {
        return super.isInstant1Type();
    }

    @Override
    public IBoolData retrieveBoolean() {
        return super.retrieveBoolean();
    }

    public String getString() {
        if (this.result == null) {
            Method11465();
        }
        if (this.exception != null) {
            throw new CharacterCodingFailedException(this.exception);
        }
        return this.result;
    }
}
