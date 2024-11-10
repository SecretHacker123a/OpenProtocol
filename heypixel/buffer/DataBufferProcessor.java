package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.TypeRegistry;
import dev.undefinedteam.gensh1n.protocol.heypixel.ValueTypeEnum;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers.DataRetriever;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.BigIntgerException;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.CharacterCodingFailedException;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.ProcessorException;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.value.Value;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.time.Instant;



@StringEncryption
@ControlFlowObfuscation
public class DataBufferProcessor implements Closeable {


    public static String defaultCharsetName = "";
    public static UBuffer emptyBuffer;
    public static boolean isDebugMode;

    static {
        isDebugMode = !DataBufferUtils.class.desiredAssertionStatus();
        emptyBuffer = UBuffer.createFromByteArray(new byte[0]);
    }

    public int initialBufferSize;
    public long totalBytesRead;
    public boolean useDirectBuffers;
    public int bufferSize;
    public int maxBufferSize;
    public CharBuffer charBuffer;
    public StringBuilder sb;
    public ILBuffer messageBuffer;
    public boolean isClosed;
    public CodingErrorAction onMalformedInput;
    public CodingErrorAction onUnmappableCharacter;
    public int arrayOffset;
    public CharsetDecoder decoder;
    public UBuffer currentBuffer = emptyBuffer;
    public UBuffer tempBuffer = UBuffer.allocateBuffer(8);


    public DataBufferProcessor(ILBuffer iLBuffer, ProcessorBuilder builder) {
        this.messageBuffer = (ILBuffer) MsgUtils.requireNonNull(iLBuffer, "消息缓冲输入异常");
        this.useDirectBuffers = builder.useDirectBuffers();
        this.isClosed = builder.isClosed();
        this.onUnmappableCharacter = builder.getOnUnmappableCharacterAction();
        this.onMalformedInput = builder.getOnMalformedInputAction();
        this.maxBufferSize = builder.getInitialBufferSize();
        this.initialBufferSize = builder.getMaxBufferSize();
    }

    public static BigIntgerException createBigIntgerException(long j) {
        return new BigIntgerException(BigInteger.valueOf(j + Long.MAX_VALUE + 1).setBit(63));
    }

    public static BigIntgerException createTimestampFromShort(short s) {
        return new BigIntgerException(BigInteger.valueOf((65535 | ((-s) - 1)) - ((-s) - 1)));
    }

    public static BigIntgerException createTimestampFromByte(byte b) {
        return new BigIntgerException(BigInteger.valueOf((255 | ((-b) - 1)) - ((-b) - 1)));
    }

    public static RuntimeException createRuntimeException(String str, int i, int i2) {
        return new RuntimeException(String.format("%s : %d -> %d", str, i, i2));
    }

    public static BigIntgerException createBigIntegerEFromInt(int i) {
        return new BigIntgerException(BigInteger.valueOf((Integer.MAX_VALUE | ((-i) - 1)) + i + 1 + 2147483648L));
    }

    public static ProcessorException createIntegerException(int i) {
        return new ProcessorException((Integer.MAX_VALUE | ((-i) - 1)) + i + 1 + 2147483648L);
    }

    public static BigIntgerException createBigIntegerEFromShortLE(short s) {
        return new BigIntgerException(BigInteger.valueOf(s));
    }

    public static BigIntgerException createBigIntegerEFromLong(long j) {
        return new BigIntgerException(BigInteger.valueOf(j));
    }

    public static RuntimeException createReadException(String str, byte b) {
        ValueTypeEnum valueOf = ValueTypeEnum.valueOf(b);
        if (valueOf == ValueTypeEnum.NULL) {
            return new RuntimeException(String.format("%s -> 0xC1", str));
        }
        String name = valueOf.getValueType().name();
        return new RuntimeException(String.format("%s -> %s : %02x", str, name.charAt(0) + name.substring(1).toLowerCase(), b));
    }

    public static int calculatePadding(byte b) {
        int i = (255 | ((-b) - 1)) - ((-b) - 1);
        return Integer.numberOfLeadingZeros((-i - 1) << 24);
    }

    public static BigIntgerException createIntegerEFromInt(int i) {
        return new BigIntgerException(BigInteger.valueOf(i));
    }

    public boolean readBoolean() throws IOException {
        byte aByte = readNextByte();
        if (aByte == -62) {
            return false;
        }
        if (aByte == -61) {
            return true;
        }
        throw createReadException("boolean", aByte);
    }

    public void skipBytes(int i) throws IOException {
        while (i > 0) {
            byte valueTypeByte = readNextByte();
            switch (TypeRegistry.VALUE_TYPES[ValueTypeEnum.valueOf(valueTypeByte).ordinal()]) {
                case 5:
                    int i2 = (15 | ((-valueTypeByte) - 1)) - ((-valueTypeByte) - 1);
                    int i3 = i;
                    int i4 = i2 * 2;
                    i = (i3 | i4) + ((i4 | ((-i3) - 1)) - ((-i3) - 1));
                    break;
                case 6:
                    int i5 = (15 | ((-valueTypeByte) - 1)) - ((-valueTypeByte) - 1);
                    int i6 = i;
                    i = (i6 | i5) + ((i5 | ((-i6) - 1)) - ((-i6) - 1));
                    break;
                case 7:
                    skipBytesFully((31 | ((-valueTypeByte) - 1)) + valueTypeByte + 1);
                    break;
                case 8:
                case 9:
                    skipBytesFully(1);
                    break;
                case 10:
                case 11:
                    skipBytesFully(2);
                    break;
                case 12:
                case 13:
                case 14:
                    skipBytesFully(4);
                    break;
                case 15:
                case 16:
                case 17:
                    skipBytesFully(8);
                    break;
                case 18:
                case 19:
                    skipBytesFully(readUInt8());
                    break;
                case 20:
                case 21:
                    skipBytesFully(readVarInt32());
                    break;
                case 22:
                case 23:
                    skipBytesFully(readUInt32());
                    break;
                case 24:
                    skipBytesFully(2);
                    break;
                case 25:
                    skipBytesFully(3);
                    break;
                case 26:
                    skipBytesFully(5);
                    break;
                case 27:
                    skipBytesFully(9);
                    break;
                case 28:
                    skipBytesFully(17);
                    break;
                case 29:
                    skipBytesFully(readUInt8() + 1);
                    break;
                case 30:
                    int skipByteInt = readVarInt32();
                    skipBytesFully((skipByteInt ^ 1) + (((1 | ((-skipByteInt) - 1)) - ((-skipByteInt) - 1)) * 2));
                    break;
                case 31:
                    int skidByteUInt = readUInt32();
                    skipBytesFully(1);
                    skipBytesFully(skidByteUInt);
                    break;
                case 32:
                    int i7 = i;
                    int val = readVarInt32();
                    i = (i7 ^ val) + (((val | ((-i7) - 1)) - ((-i7) - 1)) * 2);
                    break;
                case 33:
                    i = (i - ((-readUInt32()) - 1)) - 1;
                    break;
                case 34:
                    int i8 = i;
                    int intData = readVarInt32() * 2;
                    i = (i8 ^ intData) + (((intData | ((-i8) - 1)) - ((-i8) - 1)) * 2);
                    break;
                case 35:
                    int i9 = i;
                    int uIntData = readUInt32() * 2;
                    i = (i9 ^ uIntData) + (((uIntData | ((-i9) - 1)) - ((-i9) - 1)) * 2);
                    break;
                case 36:
                    throw new RuntimeException("Encountered 0xC1 \"NEVER_USED\" byte");
            }
            i--;
        }
    }

    public int readVarInt() throws IOException {
        byte aByte = readNextByte();
        if (DataBufferUtils.Method10409(aByte)) {
            return (15 | ((-aByte) - 1)) - ((-aByte) - 1);
        }
        switch (aByte) {
            case -34:
                return readVarInt32();
            case -33:
                return readUInt32();
            default:
                throw createReadException("Map", aByte);
        }
    }

    public String readString0() throws IOException {
        var len = readNextByte();
        return new String(readBytes(len), DataBufferUtils.charset);
    }

    public String readString(int i) {
        if (this.onUnmappableCharacter == CodingErrorAction.REPLACE && this.onMalformedInput == CodingErrorAction.REPLACE && this.currentBuffer.hasArray()) {
            byte[] backingArray = this.currentBuffer.getBackingArray();
            int calculatePosition = this.currentBuffer.calculatePosition();
            int i2 = this.arrayOffset;
            String str = new String(backingArray, (calculatePosition | i2) + ((i2 | ((-calculatePosition) - 1)) - ((-calculatePosition) - 1)), i, DataBufferUtils.charset);
            int i3 = this.arrayOffset;
            this.arrayOffset = (i3 | i) + ((i | ((-i3) - 1)) - ((-i3) - 1));
            return str;
        }
        try {
            CharBuffer decode = this.decoder.decode(this.currentBuffer.sliceByteBuffer(this.arrayOffset, i));
            int i4 = this.arrayOffset;
            this.arrayOffset = ((i4 | i) & (((-i) - 1) | ((-i4) - 1))) + (((i | ((-i4) - 1)) - ((-i4) - 1)) * 2);
            return decode.toString();
        } catch (CharacterCodingException e) {
            throw new CharacterCodingFailedException(e);
        }
    }

    public short readShort() throws IOException {
        byte Method16901 = readNextByte();
        if (DataBufferUtils.Method10433(Method16901)) {
            return Method16901;
        }
        switch (Method16901) {
            case -52:
                byte Method169012 = readNextByte();
                return (short) ((255 | ((-Method169012) - 1)) - ((-Method169012) - 1));
            case -51:
                short Method16955 = readShortLE();
                if (Method16955 < 0) {
                    throw createTimestampFromShort(Method16955);
                }
                return Method16955;
            case -50:
                int Method17045 = readIntLE();
                if (Method17045 < 0 || Method17045 > 32767) {
                    throw createBigIntegerEFromInt(Method17045);
                }
                return (short) Method17045;
            case -49:
                long Method16775 = readLong();
                if (Method16775 < 0 || Method16775 > 32767) {
                    throw createBigIntgerException(Method16775);
                }
                return (short) Method16775;
            case -48:
                return readNextByte();
            case -47:
                return readShortLE();
            case -46:
                int Method170452 = readIntLE();
                if (Method170452 < -32768 || Method170452 > 32767) {
                    throw createIntegerEFromInt(Method170452);
                }
                return (short) Method170452;
            case -45:
                long Method167752 = readLong();
                if (Method167752 < -32768 || Method167752 > 32767) {
                    throw createBigIntegerEFromLong(Method167752);
                }
                return (short) Method167752;
            default:
                throw createReadException("Integer", Method16901);
        }
    }

    public int readInt() throws IOException {
        int result;
        byte type = readNextByte();
        if (DataBufferUtils.Method10481(type)) {
            return (31 | ((-type) - 1)) - ((-type) - 1);
        }
        int val = readVarIntType(type);
        if (val >= 0) {
            return val;
        }
        if (!this.useDirectBuffers || (result = readBinaryInt(type)) < 0) {
            throw createReadException("Binary", type);
        }
        return result;
    }

    public long readLong() throws IOException {
        return readBytesIntoBuffer(8).getLong(this.bufferSize);
    }

    public DataRetriever readObject() throws IOException {
        ValueTypeEnum valType = readValueType();
        switch (TypeRegistry.DATA_TYPES[valType.getValueType().ordinal()]) {
            case 1:
                readNextByte();
                return DataFactory.createNULL();
            case 2:
                return DataFactory.ofBool(readBoolean());
            case 3:
                return valType == ValueTypeEnum.BIGINTEGER ? DataFactory.ofBigInteger(readBigInteger()) : DataFactory.ofLong(readLongVar());
            case 4:
                return DataFactory.ofDouble(readSingleVar());
            case 5:
                int varIntLength = readVarIntLength();
                if (varIntLength > this.maxBufferSize) {
                    throw new ProcessorException(String.format("解包异常，数据长度错误 -> %,d: %,d", Integer.valueOf(this.maxBufferSize), Integer.valueOf(varIntLength)), varIntLength);
                }
                return DataFactory.of(readBytes(varIntLength), true);
            case 6:
                return DataFactory.ofHex(readBytes(readInt()), true);
            case 7:
                int Method17033 = readArrayLength();
                ObjectData[] objectDataArr = new ObjectData[Method17033];
                for (int i = 0; i < Method17033; i++) {
                    objectDataArr[i] = readObject();
                }
                return DataFactory.ofList(objectDataArr, true);
            case 8:
                int size = readVarInt();
                ObjectData[] arrayData = new ObjectData[size * 2];
                int i2 = 0;
                while (i2 < size * 2) {
                    arrayData[i2] = readObject();
                    int i3 = i2 + 1;
                    arrayData[i3] = readObject();
                    i2 = i3 + 1;
                }
                return DataFactory.ofMap(arrayData, true);
            case 9:
                ExTH exTH = readExtensibleType();
                switch (exTH.getType()) {
                    case -1:
                        return DataFactory.ofInstant(readInstantFromExTH(exTH));
                    default:
                        return DataFactory.ofPayload(exTH.getType(), readBytes(exTH.getLength()));
                }
            default:
                throw new RuntimeException("未知数据类型");
        }
    }

    public int readVarInt32() throws IOException {
        short Method16955 = readShortLE();
        return (65535 | ((-Method16955) - 1)) - ((-Method16955) - 1);
    }

    public boolean readNil() throws IOException {
        if (!ensureDataAvailable()) {
            throw new RuntimeException();
        }
        if (this.currentBuffer.getByte(this.arrayOffset) != -64) {
            return false;
        }
        readNextByte();
        return true;
    }

    public void transferToBuffer(ByteBuffer byteBuffer) throws IOException {
        while (true) {
            int remaining = byteBuffer.remaining();
            int remaining2 = this.currentBuffer.remaining() - this.arrayOffset;
            if (remaining2 >= remaining) {
                this.currentBuffer.putByteBuffer(this.arrayOffset, remaining, byteBuffer);
                int i = this.arrayOffset;
                this.arrayOffset = (i ^ remaining) + (((remaining | ((-i) - 1)) - ((-i) - 1)) * 2);
                return;
            } else {
                this.currentBuffer.putByteBuffer(this.arrayOffset, remaining2, byteBuffer);
                int i2 = this.arrayOffset;
                this.arrayOffset = (((i2 | remaining2) & (((-remaining2) - 1) | ((-i2) - 1))) - ((-(((remaining2 | ((-i2) - 1)) - ((-i2) - 1)) * 2)) - 1)) - 1;
                refreshCurrentBuffer();
            }
        }
    }

    public float readFloat() throws IOException {
        return readBytesIntoBuffer(4).toFloat(this.bufferSize);
    }

    public int readVarIntLength() throws IOException {
        int result;
        byte aByte = readNextByte();
        if (DataBufferUtils.Method10481(aByte)) {
            return (31 | ((-aByte) - 1)) - ((-aByte) - 1);
        }
        int val = readBinaryInt(aByte);
        if (val >= 0) {
            return val;
        }
        if (!this.isClosed || (result = readVarIntType(aByte)) < 0) {
            throw createReadException("String", aByte);
        }
        return result;
    }

    public byte readByte() throws IOException {
        byte Method16901 = readNextByte();
        if (DataBufferUtils.Method10433(Method16901)) {
            return Method16901;
        }
        switch (Method16901) {
            case -52:
                byte Method169012 = readNextByte();
                if (Method169012 < 0) {
                    throw createTimestampFromByte(Method169012);
                }
                return Method169012;
            case -51:
                short Method16955 = readShortLE();
                if (Method16955 < 0 || Method16955 > 127) {
                    throw createTimestampFromShort(Method16955);
                }
                return (byte) Method16955;
            case -50:
                int Method17045 = readIntLE();
                if (Method17045 < 0 || Method17045 > 127) {
                    throw createBigIntegerEFromInt(Method17045);
                }
                return (byte) Method17045;
            case -49:
                long Method16775 = readLong();
                if (Method16775 < 0 || Method16775 > 127) {
                    throw createBigIntgerException(Method16775);
                }
                return (byte) Method16775;
            case -48:
                return readNextByte();
            case -47:
                short Method169552 = readShortLE();
                if (Method169552 < -128 || Method169552 > 127) {
                    throw createBigIntegerEFromShortLE(Method169552);
                }
                return (byte) Method169552;
            case -46:
                int Method170452 = readIntLE();
                if (Method170452 < -128 || Method170452 > 127) {
                    throw createIntegerEFromInt(Method170452);
                }
                return (byte) Method170452;
            case -45:
                long Method167752 = readLong();
                if (Method167752 < -128 || Method167752 > 127) {
                    throw createBigIntegerEFromLong(Method167752);
                }
                return (byte) Method167752;
            default:
                throw createReadException("Integer", Method16901);
        }
    }

    public long readLongVar() throws IOException {
        byte Method16901 = readNextByte();
        if (DataBufferUtils.Method10433(Method16901)) {
            return Method16901;
        }
        switch (Method16901) {
            case -52:
                byte Method169012 = readNextByte();
                return (255 | ((-Method169012) - 1)) - ((-Method169012) - 1);
            case -51:
                short Method16955 = readShortLE();
                return (65535 | ((-Method16955) - 1)) + Method16955 + 1;
            case -50:
                int Method17045 = readIntLE();
                return Method17045 < 0 ? ((Integer.MAX_VALUE | ((-Method17045) - 1)) - ((-Method17045) - 1)) + 2147483648L : Method17045;
            case -49:
                long Method16775 = readLong();
                if (Method16775 < 0) {
                    throw createBigIntgerException(Method16775);
                }
                return Method16775;
            case -48:
                return readNextByte();
            case -47:
                return readShortLE();
            case -46:
                return readIntLE();
            case -45:
                return readLong();
            default:
                throw createReadException("Integer", Method16901);
        }
    }

    public void readBytesToArray(byte[] bArr, int i, int i2) throws IOException {
        while (true) {
            int remaining = this.currentBuffer.remaining() - this.arrayOffset;
            if (remaining >= i2) {
                this.currentBuffer.writeArrayDataToBuffer(this.arrayOffset, bArr, i, i2);
                int i3 = this.arrayOffset;
                int i4 = i2;
                this.arrayOffset = (i3 | i4) + ((i4 | ((-i3) - 1)) - ((-i3) - 1));
                return;
            }
            this.currentBuffer.writeArrayDataToBuffer(this.arrayOffset, bArr, i, remaining);
            i += remaining;
            i2 = i2 + ((-remaining) - 1) + 1;
            this.arrayOffset = (this.arrayOffset - ((-remaining) - 1)) - 1;
            refreshCurrentBuffer();
        }
    }

    public ILBuffer setBuffer(ILBuffer iLBuffer) {
        ILBuffer iLBuffer2 = (ILBuffer) MsgUtils.requireNonNull(iLBuffer, "消息缓冲输入异常");
        ILBuffer iLBuffer3 = this.messageBuffer;
        this.messageBuffer = iLBuffer2;
        this.currentBuffer = emptyBuffer;
        this.arrayOffset = 0;
        this.totalBytesRead = 0L;
        return iLBuffer3;
    }

    public long getTotalBytesRead() {
        return this.totalBytesRead + this.arrayOffset;
    }

    public byte[] readBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        readBytesIntoArray(bArr);
        return bArr;
    }

    public int readIntVar() throws IOException {
        byte aByte = readNextByte();
        if (DataBufferUtils.Method10433(aByte)) {
            return aByte;
        }
        switch (aByte) {
            case -52:
                byte byteVal = readNextByte();
                return (255 | ((-byteVal) - 1)) - ((-byteVal) - 1);
            case -51:
                short shortVal = readShortLE();
                return (65535 | ((-shortVal) - 1)) + shortVal + 1;
            case -50:
                int intVal = readIntLE();
                if (intVal < 0) {
                    throw createBigIntegerEFromInt(intVal);
                }
                return intVal;
            case -49:
                long longVal = readLong();
                if (longVal < 0 || longVal > 2147483647L) {
                    throw createBigIntgerException(longVal);
                }
                return (int) longVal;
            case -48:
                return readNextByte();
            case -47:
                return readShortLE();
            case -46:
                return readIntLE();
            case -45:
                long long1 = readLong();
                if (long1 < -2147483648L || long1 > 2147483647L) {
                    throw createBigIntegerEFromLong(long1);
                }
                return (int) long1;
            default:
                throw createReadException("Integer", aByte);
        }
    }

    public void ensureOpen() throws IOException {
        byte aByte = readNextByte();
        if (aByte != -64) {
            throw createReadException("Nil", aByte);
        }
    }

    public void copyToBuffer(UBuffer buffer, int index, int len) throws IOException {
        while (true) {
            int remaining = this.currentBuffer.remaining() - this.arrayOffset;
            if (remaining >= len) {
                buffer.copyMemory(index, this.currentBuffer, this.arrayOffset, len);
                int offset = this.arrayOffset;
                this.arrayOffset = ((offset | len) * 2) + ((-(offset ^ len)) - 1) + 1;
                return;
            }
            buffer.copyMemory(index, this.currentBuffer, this.arrayOffset, remaining);
            int i5 = index;
            index = (i5 ^ remaining) + (((remaining | ((-i5) - 1)) - ((-i5) - 1)) * 2);
            int i6 = len;
            len = ((i6 | remaining) & (((-remaining) - 1) | ((-i6) - 1))) - ((remaining & (~i6)) * 2);
            int i7 = this.arrayOffset;
            this.arrayOffset = (i7 ^ remaining) + (((remaining | ((-i7) - 1)) - ((-i7) - 1)) * 2);
            refreshCurrentBuffer();
        }
    }

    public int readUInt32() throws IOException {
        int intLE = readIntLE();
        if (intLE < 0) {
            throw createIntegerException(intLE);
        }
        return intLE;
    }

    public boolean ensureDataAvailable() throws IOException {
        while (this.currentBuffer.remaining() <= this.arrayOffset) {
            UBuffer buffer = this.messageBuffer.getBuffer();
            if (buffer == null) {
                return false;
            }
            this.totalBytesRead += this.currentBuffer.remaining();
            this.currentBuffer = buffer;
            this.arrayOffset = 0;
        }
        return true;
    }

    public byte readNextByte() throws IOException {
        if (this.currentBuffer.remaining() > this.arrayOffset) {
            byte b = this.currentBuffer.getByte(this.arrayOffset);
            this.arrayOffset++;
            return b;
        }
        refreshCurrentBuffer();
        if (this.currentBuffer.remaining() <= 0) {
            return readNextByte();
        }
        byte b2 = this.currentBuffer.getByte(0);
        this.arrayOffset = 1;
        return b2;
    }

    public UBuffer getNextBuffer() throws IOException {
        UBuffer buffer = this.messageBuffer.getBuffer();
        if (buffer == null) {
            throw new RuntimeException();
        }
        if (!isDebugMode && this.currentBuffer == null) {
            throw new AssertionError();
        }
        this.totalBytesRead += this.currentBuffer.remaining();
        return buffer;
    }

    public double readDouble() throws IOException {
        return readBytesIntoBuffer(8).toDouble(this.bufferSize);
    }

    public UBuffer readBytesIntoBuffer(int i) throws IOException {
        int remaining = this.currentBuffer.remaining() + ((-this.arrayOffset) - 1) + 1;
        if (remaining >= i) {
            this.bufferSize = this.arrayOffset;
            int i2 = this.arrayOffset;
            this.arrayOffset = ((i2 | i) * 2) - ((i2 | i) & (((-i) - 1) | ((-i2) - 1)));
            return this.currentBuffer;
        }
        int i3 = 0;
        if (remaining > 0) {
            this.tempBuffer.copyMemory(0, this.currentBuffer, this.arrayOffset, remaining);
            i -= remaining;
            i3 = (-((-remaining) - 1)) - 1;
        }
        while (true) {
            refreshCurrentBuffer();
            int remaining2 = this.currentBuffer.remaining();
            if (remaining2 >= i) {
                this.tempBuffer.copyMemory(i3, this.currentBuffer, 0, i);
                this.arrayOffset = i;
                this.bufferSize = 0;
                return this.tempBuffer;
            }
            this.tempBuffer.copyMemory(i3, this.currentBuffer, 0, remaining2);
            i -= remaining2;
            i3 = (i3 - ((-remaining2) - 1)) - 1;
        }
    }

    @Override
    public void close() throws IOException {
        this.totalBytesRead += this.arrayOffset;
        this.currentBuffer = emptyBuffer;
        this.arrayOffset = 0;
        this.messageBuffer.close();
    }

    public Value populateInfoData(Value value) throws IOException {
        ValueTypeEnum type = readValueType();
        switch (TypeRegistry.DATA_TYPES[type.getValueType().ordinal()]) {
            case 1:
                readNextByte();
                value.setDefaultValues();
                return value;
            case 2:
                value.setDataFromBoolean(readBoolean());
                return value;
            case 3:
                switch (TypeRegistry.VALUE_TYPES[type.ordinal()]) {
                    case 16:
                        value.setDataFromBigInteger(readBigInteger());
                        return value;
                    default:
                        value.setDataFromLong(readLongVar());
                        return value;
                }
            case 4:
                value.setDataFromDouble(readSingleVar());
                return value;
            case 5:
                int Method16817 = readVarIntLength();
                if (Method16817 > this.maxBufferSize) {
                    throw new ProcessorException(String.format("解包异常，数据长度错误 -> %,d: %,d", Integer.valueOf(this.maxBufferSize), Integer.valueOf(Method16817)), Method16817);
                }
                value.setDataFromBytes(readBytes(Method16817));
                return value;
            case 6:
                value.setByteArrayData(readBytes(readInt()));
                return value;
            case 7:
                int Method17033 = readArrayLength();
                ObjectData[] objectDataArr = new ObjectData[Method17033];
                for (int i = 0; i < Method17033; i++) {
                    objectDataArr[i] = readObject();
                }
                value.setArrayDataWithType(objectDataArr);
                return value;
            case 8:
                int readVarInt = readVarInt();
                ObjectData[] objectDataArr2 = new ObjectData[readVarInt * 2];
                int i2 = 0;
                while (i2 < readVarInt * 2) {
                    objectDataArr2[i2] = readObject();
                    int i3 = i2 + 1;
                    objectDataArr2[i3] = readObject();
                    i2 = i3 + 1;
                }
                value.setArrayData(objectDataArr2);
                return value;
            case 9:
                ExTH Method17069 = readExtensibleType();
                switch (Method17069.getType()) {
                    case -1:
                        value.setDataFromInstant(readInstantFromExTH(Method17069));
                        break;
                    default:
                        value.initializeWithData(Method17069.getType(), readBytes(Method17069.getLength()));
                        break;
                }
                return value;
            default:
                throw new RuntimeException("未知数据类型!");
        }
    }

    public double readSingleVar() throws IOException {
        byte type = readNextByte();
        return switch (type) {
            case -54 -> readFloat();
            case -53 -> readDouble();
            default -> throw createReadException("Float", type);
        };
    }

    public boolean ensureDataAvailable1() throws IOException {
        return ensureDataAvailable();
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0205, code lost:

        r0.throwException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0217, code lost:

        throw new class_sub.BJDException2("糟糕#1");
     */

    public short readShortLE() throws IOException {
        return readBytesIntoBuffer(2).getShort(this.bufferSize);
    }

    public Instant readInstant() throws IOException {
        return readInstantFromExTH(readExtensibleType());
    }

    public float readFloatLE() throws IOException {
        byte type = readNextByte();
        return switch (type) {
            case -54 -> readFloat();
            case -53 -> (float) readDouble();
            default -> throw createReadException("Float", type);
        };
    }

    public int readVarIntType(byte b) throws IOException {
        return switch (b) {
            case -60 -> readUInt8();
            case -59 -> readVarInt32();
            case -58 -> readUInt32();
            default -> -1;
        };
    }

    public void refreshCurrentBuffer() throws IOException {
        this.currentBuffer = getNextBuffer();
        this.arrayOffset = 0;
    }

    public void readBytesIntoArray(byte[] bArr) throws IOException {
        readBytesToArray(bArr, 0, bArr.length);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String readStringFully() throws IOException {
        int remaining;
        int bytesLen = readVarIntLength();
        if (bytesLen == 0) {
            return "";
        }
        if (bytesLen > this.maxBufferSize) {
            throw new ProcessorException(String.format("解包异常，数据长度错误 -> %,d: %,d", this.maxBufferSize, bytesLen), bytesLen);
        }
        initializeDecoder();
        if (this.currentBuffer.remaining() - this.arrayOffset >= bytesLen) {
            return readString(bytesLen);
        }
        int i = bytesLen;
        while (true) {
            if (i <= 0) {
                break;
            }
            try {
                int remaining2 = this.currentBuffer.remaining() - this.arrayOffset;
                if (remaining2 >= i) {
                    this.sb.append(readString(i));
                    break;
                }
                if (remaining2 == 0) {
                    refreshCurrentBuffer();
                } else {
                    ByteBuffer sliceByteBuffer = this.currentBuffer.sliceByteBuffer(this.arrayOffset, remaining2);
                    int position = sliceByteBuffer.position();
                    this.charBuffer.clear();
                    CoderResult decode = this.decoder.decode(sliceByteBuffer, this.charBuffer, false);
                    int position2 = sliceByteBuffer.position() - position;
                    int i2 = this.arrayOffset;
                    this.arrayOffset = (i2 ^ position2) + (((position2 | ((-i2) - 1)) - ((-i2) - 1)) * 2);
                    i -= position2;
                    this.sb.append(this.charBuffer.flip());
                    if (decode.isError()) {
                        handleDecodeException(decode);
                    }
                    if (decode.isUnderflow() && position2 < remaining2) {
                        ByteBuffer allocate = ByteBuffer.allocate(calculatePadding(this.currentBuffer.getByte(this.arrayOffset)));
                        this.currentBuffer.putByteBuffer(this.arrayOffset, this.currentBuffer.remaining() - this.arrayOffset, allocate);
                        while (true) {
                            refreshCurrentBuffer();
                            remaining = allocate.remaining();
                            if (this.currentBuffer.remaining() >= remaining) {
                                break;
                            }
                            this.currentBuffer.putByteBuffer(0, this.currentBuffer.remaining(), allocate);
                            this.arrayOffset = this.currentBuffer.remaining();
                        }
                        this.currentBuffer.putByteBuffer(0, remaining, allocate);
                        this.arrayOffset = remaining;
                        allocate.position(0);
                        this.charBuffer.clear();
                        CoderResult decode2 = this.decoder.decode(allocate, this.charBuffer, false);
                        if (decode2.isError()) {
                            handleDecodeException(decode2);
                        }
                        if (decode2.isOverflow() || (decode2.isUnderflow() && allocate.position() < allocate.limit())) {
                            try {
                                break;
                            } catch (Exception e) {
                                throw new RuntimeException("糟糕#2", e);
                            }
                        }
                        i -= allocate.limit();
                        this.sb.append(this.charBuffer.flip());
                    }
                }
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        return this.sb.toString();
    }

    public ValueTypeEnum readValueType() throws IOException {
        if (ensureDataAvailable()) {
            return ValueTypeEnum.valueOf(this.currentBuffer.getByte(this.arrayOffset));
        }
        throw new RuntimeException();
    }

    public BigInteger readBigInteger() throws IOException {
        byte type = readNextByte();
        if (DataBufferUtils.Method10433(type)) {
            return BigInteger.valueOf(type);
        }
        return switch (type) {
            case -52 -> {
                byte byteVal = readNextByte();
                yield BigInteger.valueOf((255 | ((-byteVal) - 1)) - ((-byteVal) - 1));
            }
            case -51 -> {
                short shortVal = readShortLE();
                yield BigInteger.valueOf((65535 | ((-shortVal) - 1)) - ((-shortVal) - 1));
            }
            case -50 -> {
                int intVal = readIntLE();
                yield intVal < 0 ? BigInteger.valueOf((Integer.MAX_VALUE | ((-intVal) - 1)) + intVal + 1 + 2147483648L) : BigInteger.valueOf(intVal);
            }
            case -49 -> {
                long longVal = readLong();
                yield longVal < 0 ? BigInteger.valueOf(longVal + Long.MAX_VALUE + 1).setBit(63) : BigInteger.valueOf(longVal);
            }
            case -48 -> BigInteger.valueOf(readNextByte());
            case -47 -> BigInteger.valueOf(readShortLE());
            case -46 -> BigInteger.valueOf(readIntLE());
            case -45 -> BigInteger.valueOf(readLong());
            default -> throw createReadException("Integer", type);
        };
    }

    public void skipBytesFully(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("大小必须 >= 0: " + i);
        }
        while (true) {
            int remaining = this.currentBuffer.remaining() + ((-this.arrayOffset) - 1) + 1;
            if (remaining >= i) {
                int i2 = this.arrayOffset;
                this.arrayOffset = (i2 | i) + ((i | ((-i2) - 1)) - ((-i2) - 1));
                return;
            } else {
                this.arrayOffset += remaining;
                int i4 = i;
                i = ((i4 & (~remaining)) * 2) - ((i4 | remaining) & (((-remaining) - 1) | ((-i4) - 1)));
                refreshCurrentBuffer();
            }
        }
    }

    public int readArrayLength() throws IOException {
        byte aByte = readNextByte();
        if (DataBufferUtils.Method10415(aByte)) {
            return (15 | ((-aByte) - 1)) - ((-aByte) - 1);
        }
        return switch (aByte) {
            case -36 -> readVarInt32();
            case -35 -> readUInt32();
            default -> throw createReadException("Array", aByte);
        };
    }

    public void initializeDecoder() {
        if (this.decoder == null) {
            this.charBuffer = CharBuffer.allocate(this.initialBufferSize);
            this.decoder = DataBufferUtils.charset.newDecoder().onMalformedInput(this.onUnmappableCharacter).onUnmappableCharacter(this.onMalformedInput);
        } else {
            this.decoder.reset();
        }
        if (this.sb == null) {
            this.sb = new StringBuilder();
        } else {
            this.sb.setLength(0);
        }
    }

    public int readIntLE() throws IOException {
        return readBytesIntoBuffer(4).getInt(this.bufferSize);
    }

    public Instant readInstantFromExTH(ExTH exTH) throws IOException {
        if (exTH.getType() != -1) {
            throw createRuntimeException("Timestamp", -1, exTH.getType());
        }
        return switch (exTH.getLength()) {
            case 4 -> Instant.ofEpochSecond(readIntLE() & 4294967295L);
            case 8 -> {
                var var2 = readLong();
                yield Instant.ofEpochSecond(var2 & 17179869183L, (int) (var2 >>> 34));
            }
            case 12 -> Instant.ofEpochSecond(readLong(), readIntLE() & 4294967295L);
            default ->
                throw new RuntimeException(String.format(" (%d) 需要 ->  4, 8, 12 bytes 但传入值为 %d bytes", (byte) -1, Integer.valueOf(exTH.getLength())));
        };
    }

    public int readBinaryInt(byte b) throws IOException {
        return switch (b) {
            case -39 -> readUInt8();
            case -38 -> readVarInt32();
            case -37 -> readUInt32();
            default -> -1;
        };
    }

    public ExTH readExtensibleType() throws IOException {
        byte aByte = readNextByte();
        switch (aByte) {
            case -57:
                UBuffer buffer = readBytesIntoBuffer(2);
                byte b = buffer.getByte(this.bufferSize);
                return new ExTH(buffer.getByte(this.bufferSize + 1), (255 | ((-b) - 1)) - ((-b) - 1));
            case -56:
                UBuffer buffer1 = readBytesIntoBuffer(3);
                short s = buffer1.getShort(this.bufferSize);
                return new ExTH(buffer1.getByte(this.bufferSize + 2), (65535 | ((-s) - 1)) - ((-s) - 1));
            case -55:
                UBuffer buffer2 = readBytesIntoBuffer(5);
                int i = buffer2.getInt(this.bufferSize);
                if (i < 0) {
                    throw createIntegerException(i);
                }
                return new ExTH(buffer2.getByte(this.bufferSize + 4), i);
            case -54:
            case -53:
            case -52:
            case -51:
            case -50:
            case -49:
            case -48:
            case -47:
            case -46:
            case -45:
            default:
                throw createReadException("Ext", aByte);
            case -44:
                return new ExTH(readNextByte(), 1);
            case -43:
                return new ExTH(readNextByte(), 2);
            case -42:
                return new ExTH(readNextByte(), 4);
            case -41:
                return new ExTH(readNextByte(), 8);
            case -40:
                return new ExTH(readNextByte(), 16);
        }
    }

    public void skip() throws IOException {
        skipBytes(1);
    }

    public int readUInt8() throws IOException {
        byte aByte = readNextByte();
        return (255 | ((-aByte) - 1)) + aByte + 1;
    }

    public void handleDecodeException(CoderResult coderResult) throws CharacterCodingException {
        if ((coderResult.isMalformed() && this.onUnmappableCharacter == CodingErrorAction.REPORT) || (coderResult.isUnmappable() && this.onMalformedInput == CodingErrorAction.REPORT)) {
            coderResult.throwException();
        }
    }

    public UBuffer readBuffer(int i) throws IOException {
        if (this.currentBuffer.remaining() - this.arrayOffset < i) {
            UBuffer allocateBuffer = UBuffer.allocateBuffer(i);
            copyToBuffer(allocateBuffer, 0, i);
            return allocateBuffer;
        }
        UBuffer Method13307 = this.currentBuffer.sliceBuffer(this.arrayOffset, i);
        int i2 = this.arrayOffset;
        this.arrayOffset = ((i2 | i) * 2) + ((-(i2 ^ i)) - 1) + 1;
        return Method13307;
    }
}
