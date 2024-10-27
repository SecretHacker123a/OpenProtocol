package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.utils.MsgUtils;
import sun.misc.Unsafe;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;



@StringEncryption
@ControlFlowObfuscation
public class UBuffer {
    public static int initialArrayOffset;
    public static boolean isDirectBuffer;
    public static String class1259Name;
    public static Unsafe unsafe;
    public static String class2147Name;
    public static int javaSpecVersion;
    public static boolean isAssertionEnabled;
    public static Constructor byteArrayConstructor;
    public static String class2117Name;
    public static Constructor byteBufferConstructor;

    public static void init() {
        String str;
        String str2;
        isAssertionEnabled = !UBuffer.class.desiredAssertionStatus();
        javaSpecVersion = getJavaSpecVersion();
        class1259Name = TPositionBuffer.class.getName();
        class2147Name = BufferSlice.class.getName();
        class2117Name = UBuffer.class.getName();
        Unsafe unsafe2;
        int i;
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe2 = (Unsafe) declaredField.get(null);
            if (unsafe2 == null) {
                throw new RuntimeException("Unsafe 不可用");
            }
            i = unsafe2.arrayBaseOffset(byte[].class);
            int arrayIndexScale = unsafe2.arrayIndexScale(byte[].class);
            if (arrayIndexScale != 1) {
                throw new IllegalStateException("scale应为1，但为: " + arrayIndexScale);
            }
            unsafe = unsafe2;
            initialArrayOffset = i;
            isDirectBuffer = false;
            str2 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? class2117Name : class2147Name;
            if (class2117Name.equals(str2)) {
                byteBufferConstructor = null;
                byteArrayConstructor = null;
                return;
            }

            try {
                Class<?> cls = Class.forName(str2);
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor(byte[].class, Integer.TYPE, Integer.TYPE);
                declaredConstructor.setAccessible(true);
                byteBufferConstructor = declaredConstructor;
                Constructor<?> declaredConstructor2 = cls.getDeclaredConstructor(ByteBuffer.class);
                declaredConstructor2.setAccessible(true);
                byteArrayConstructor = declaredConstructor2;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            unsafe = null;
            initialArrayOffset = 16;
            isDirectBuffer = true;
            str = class1259Name;

            if (class2117Name.equals(str)) {
                byteBufferConstructor = null;
                byteArrayConstructor = null;
                return;
            }
            try {
                Class<?> cls2 = Class.forName(str);
                Constructor<?> declaredConstructor3 = cls2.getDeclaredConstructor(byte[].class, Integer.TYPE, Integer.TYPE);
                declaredConstructor3.setAccessible(true);
                byteBufferConstructor = declaredConstructor3;
                Constructor<?> declaredConstructor4 = cls2.getDeclaredConstructor(ByteBuffer.class);
                declaredConstructor4.setAccessible(true);
                byteArrayConstructor = declaredConstructor4;
            } catch (Exception e4) {
                e4.printStackTrace();
                throw new RuntimeException(e4);
            }
        }
    }

    public ByteBuffer buffer;
    public long offset;
    public int remaining;
    public Object backingArray;


    public UBuffer(Object obj, long offset, int remaining) {
        this.backingArray = obj;
        this.offset = offset;
        this.remaining = remaining;
        this.buffer = null;
    }

    public UBuffer(ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            if (!byteBuffer.hasArray()) {
                throw new IllegalArgumentException("不支持的操作");
            }
            this.backingArray = byteBuffer.array();
            int arrayOffset = initialArrayOffset + byteBuffer.arrayOffset();
            int position = byteBuffer.position();
            this.offset = ((arrayOffset | position) & (((-position) - 1) | ((-arrayOffset) - 1))) + (((position | ((-arrayOffset) - 1)) - ((-arrayOffset) - 1)) * 2L);
            this.remaining = byteBuffer.remaining();
            this.buffer = null;
            return;
        }
        if (isDirectBuffer) {
            this.backingArray = null;
            this.offset = 0L;
            this.remaining = byteBuffer.remaining();
            this.buffer = null;
            return;
        }
        this.backingArray = null;
        this.offset = BufferUtils.getBufferAddress(byteBuffer) + byteBuffer.position();
        this.remaining = byteBuffer.remaining();
        this.buffer = byteBuffer;
    }


    public UBuffer(byte[] bArr, int i, int i2) {
        this.backingArray = bArr;
        int i3 = initialArrayOffset;
        this.offset = ((i3 | i) & (((-i) - 1) | ((-i3) - 1))) + (((i | ((-i3) - 1)) - ((-i3) - 1)) * 2L);
        this.remaining = i2;
        this.buffer = null;
    }

    public static UBuffer createFromByteArray(byte[] bArr) {
        return newInstanceByteArray(bArr, 0, bArr.length);
    }

    public static UBuffer allocateBuffer(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("大小不得为负");
        }
        return createFromByteArray(new byte[i]);
    }

    public static void freeBuffer(UBuffer buffer) {
        if (isDirectBuffer || buffer.hasArray()) {
            return;
        }
        if (BufferUtils.isDirectBufferInstance(buffer.buffer)) {
            BufferUtils.cleanDirectBuffer(buffer.buffer);
        } else {
            unsafe.freeMemory(buffer.offset);
        }
    }

    public static int getJavaSpecVersion() {
        String property = System.getProperty("java.specification.version", "");
        int indexOf = property.indexOf(46);
        if (indexOf == -1) {
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 6;
            }
        }
        try {
            int parseInt = Integer.parseInt(property.substring(0, indexOf));
            return parseInt > 1 ? parseInt : Integer.parseInt(property.substring((indexOf ^ 1) + (((1 | ((-indexOf) - 1)) - ((-indexOf) - 1)) * 2)));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return 6;
        }
    }

    public static UBuffer wrapByteArray(byte[] bArr, int i, int i2) {
        return newInstanceByteArray(bArr, i, i2);
    }

    public static UBuffer newInstance(Constructor constructor, Object... objArr) {
        try {
            return (UBuffer) constructor.newInstance(objArr);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e3.getCause());
            }
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw new IllegalStateException(e3.getCause());
        }
    }

    public static UBuffer createFromByteBuffer(ByteBuffer byteBuffer) {
        return newInstanceByteBuffer(byteBuffer);
    }

    public static UBuffer newInstanceByteBuffer(ByteBuffer byteBuffer) {
        MsgUtils.requireNonNullStrict(byteBuffer);
        return byteArrayConstructor != null ? newInstance(byteArrayConstructor, byteBuffer) : new UBuffer(byteBuffer);
    }

    @NativeObfuscation.Inline
    public static UBuffer newInstanceByteArray(byte[] bArr, int i, int i2) {
        MsgUtils.requireNonNullStrict(bArr);
        return byteBufferConstructor != null ? newInstance(byteBufferConstructor, bArr, i, i2) : new UBuffer(bArr, i, i2);
    }

    @NativeObfuscation.Inline
    public void putShort(int i, short s) {
        unsafe.putShort(this.backingArray, this.offset + i, Short.reverseBytes(s));
    }

    public int calculatePosition() {
        return ((int) this.offset) - initialArrayOffset;
    }

    public void copyMemory(int i, UBuffer buffer, int i2, int i3) {
        unsafe.copyMemory(buffer.backingArray, buffer.offset + i2, this.backingArray, this.offset + i, i3);
    }

    public void putLong(int i, long j) {
        unsafe.putLong(this.backingArray, this.offset + i, Long.reverseBytes(j));
    }

    public void putByteBuffer(int i, int i2, ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < i2) {
            throw new BufferOverflowException();
        }
        byteBuffer.put(sliceByteBuffer(i, i2));
    }

    public void copyMemory(int i, byte[] bArr, int i2, int i3) {
        unsafe.copyMemory(bArr, initialArrayOffset + i2, this.backingArray, this.offset + i, i3);
    }

    public long getLong(int i) {
        return Long.reverseBytes(unsafe.getLong(this.backingArray, this.offset + i));
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[remaining()];
        unsafe.copyMemory(this.backingArray, this.offset, bArr, initialArrayOffset, remaining());
        return bArr;
    }

    public ByteBuffer sliceByteBuffer(int i, int i2) {
        if (hasArray()) {
            return ByteBuffer.wrap((byte[]) this.backingArray, (int) ((this.offset - initialArrayOffset) + i), i2);
        }
        if (isAssertionEnabled || !isDirectBuffer) {
            return BufferUtils.createBufferWrapper(this.offset, i, i2, this.buffer);
        }
        throw new AssertionError();
    }

    public int remaining() {
        return this.remaining;
    }

    public void putDouble(int i, double d) {
        putLong(i, Double.doubleToRawLongBits(d));
    }

    public byte getByte(int i) {
        return unsafe.getByte(this.backingArray, this.offset + i);
    }

    public double toDouble(int i) {
        return Double.longBitsToDouble(getLong(i));
    }

    public void putBoolean(int i, boolean z) {
        unsafe.putBoolean(this.backingArray, this.offset + i, z);
    }

    public int getInt(int i) {
        return Integer.reverseBytes(unsafe.getInt(this.backingArray, this.offset + i));
    }

    public boolean getBoolean(int i) {
        return unsafe.getBoolean(this.backingArray, this.offset + i);
    }

    public byte[] getBackingArray() {
        return (byte[]) this.backingArray;
    }

    public void putByteBufferData(int i, ByteBuffer byteBuffer, int i2) {
        if (!isAssertionEnabled && i2 > byteBuffer.remaining()) {
            throw new AssertionError();
        }
        if (!isAssertionEnabled && isDirectBuffer) {
            throw new AssertionError();
        }
        if (byteBuffer.isDirect()) {
            unsafe.copyMemory(null, BufferUtils.getBufferAddress(byteBuffer) + byteBuffer.position(), this.backingArray, this.offset + i, i2);
            int position = byteBuffer.position();
            byteBuffer.position(((position | i2) * 2) - ((position | i2) & (((-i2) - 1) | ((-position) - 1))));
        } else {
            if (byteBuffer.hasArray()) {
                unsafe.copyMemory(byteBuffer.array(), (initialArrayOffset - ((-byteBuffer.position()) - 1)) - 1, this.backingArray, this.offset + i, i2);
                int position2 = byteBuffer.position();
                byteBuffer.position(((position2 | i2) & (((-i2) - 1) | ((-position2) - 1))) + (((i2 | ((-position2) - 1)) - ((-position2) - 1)) * 2));
                return;
            }
            if (hasArray()) {
                byteBuffer.get((byte[]) this.backingArray, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                unsafe.putByte(this.backingArray, this.offset + i, byteBuffer.get());
            }
        }
    }

    public UBuffer sliceBuffer(int i, int i2) {
        if (i == 0 && i2 == remaining()) {
            return this;
        }
        MsgUtils.checkArgument(i + i2 <= remaining());
        return new UBuffer(this.backingArray, this.offset + i, i2);
    }

    public boolean hasArray() {
        return this.backingArray != null;
    }

    public void copyMemoryTo(int i, UBuffer buffer, int i2, int i3) {
        unsafe.copyMemory(this.backingArray, this.offset + i, buffer.backingArray, buffer.offset + i2, i3);
    }

    public void putInt(int i, int i2) {
        unsafe.putInt(this.backingArray, this.offset + i, Integer.reverseBytes(i2));
    }

    public void writeArrayDataToBuffer(int i, byte[] bArr, int i2, int i3) {
        unsafe.copyMemory(this.backingArray, this.offset + i, bArr, (initialArrayOffset - ((-i2) - 1)) - 1, i3);
    }

    public short getShort(int i) {
        return Short.reverseBytes(unsafe.getShort(this.backingArray, this.offset + i));
    }

    public String toHexString(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i2; i3++) {
            if (i3 != i) {
                sb.append(" ");
            }
            sb.append(String.format("%02x", getByte(i3)));
        }
        return sb.toString();
    }

    public void putByte(int i, byte b) {
        unsafe.putByte(this.backingArray, this.offset + i, b);
    }

    public ByteBuffer getByteBuffer() {
        return sliceByteBuffer(0, remaining());
    }


    public float toFloat(int i) {
        return Float.intBitsToFloat(getInt(i));
    }


    public void putFloat(int i, float f) {
        putInt(i, Float.floatToRawIntBits(f));
    }
}
