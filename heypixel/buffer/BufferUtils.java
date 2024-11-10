package dev.undefinedteam.gensh1n.protocol.heypixel.buffer;

import dev.undefinedteam.gensh1n.protocol.heypixel.BufferConstructorType;
import sun.misc.Unsafe;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;



@StringEncryption
@ControlFlowObfuscation
public class BufferUtils {


    public static Class directBufferClass;


    public static Constructor directBufferConstructor;


    public static Method cleanerMethod;


    public static Method invokeCleanerMethod;


    public static Unsafe unsafe;


    public static Method memoryBlockMethod;


    public static long addressOffset;


    public static Method cleanerMethodAlternate;


    public static BufferConstructorType bufferConstructorType;

    public static void init() {
        Constructor declaredConstructor;
        BufferConstructorType cType;
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1);
            directBufferClass = allocateDirect.getClass();
            Method method = null;
            try {
                declaredConstructor = directBufferClass.getDeclaredConstructor(Long.TYPE, Long.TYPE);
                cType = BufferConstructorType.TYPE_1;
            } catch (NoSuchMethodException e) {
                try {
                    declaredConstructor = directBufferClass.getDeclaredConstructor(Long.TYPE, Integer.TYPE, Object.class);
                    cType = BufferConstructorType.TYPE_2;
                } catch (NoSuchMethodException e2) {
                    try {
                        declaredConstructor = directBufferClass.getDeclaredConstructor(Long.TYPE, Integer.TYPE);
                        cType = BufferConstructorType.TYPE_3;
                    } catch (NoSuchMethodException e3) {
                        try {
                            declaredConstructor = directBufferClass.getDeclaredConstructor(Integer.TYPE, Integer.TYPE);
                            cType = BufferConstructorType.TYPE_4;
                        } catch (NoSuchMethodException e4) {
                            Class<?> cls = Class.forName("java.nio.MemoryBlock");
                            method = cls.getDeclaredMethod("wrapFromJni", Integer.TYPE, Long.TYPE);
                            method.setAccessible(true);
                            declaredConstructor = directBufferClass.getDeclaredConstructor(cls, Integer.TYPE, Integer.TYPE);
                            cType = BufferConstructorType.TYPE_5;
                        }
                    }
                }
            }
            directBufferConstructor = declaredConstructor;
            bufferConstructorType = cType;
            memoryBlockMethod = method;
            try {
                directBufferConstructor.setAccessible(true);
            } catch (RuntimeException e5) {
                if (!"java.lang.reflect.InaccessibleObjectException".equals(e5.getClass().getName())) {
                    throw e5;
                }
                directBufferConstructor = null;
            }
            initializeCleanerMethod(allocateDirect);
            try {
                Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                unsafe = (Unsafe) declaredField.get(null);
                addressOffset = unsafe.objectFieldOffset(java.nio.Buffer.class.getDeclaredField("address"));
            } catch (IllegalAccessException | NoSuchFieldException e6) {
                throw new RuntimeException("Unable to get Unsafe instance", e6);
            }
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public static boolean isDirectBufferInstance(Object obj) {
        return directBufferClass.isInstance(obj);
    }

    public static void initializeCleanerMethod(ByteBuffer byteBuffer) {
        Object doPrivileged = getCleanerMethod(byteBuffer);
        if (doPrivileged instanceof Throwable) {
            throw new RuntimeException((Throwable) doPrivileged);
        }
        invokeCleanerMethod = (Method) doPrivileged;
    }

    public static Object getCleanerMethod(ByteBuffer byteBuffer) {
        try {
            Method declaredMethod = UBuffer.unsafe.getClass().getDeclaredMethod("invokeCleaner", ByteBuffer.class);
            declaredMethod.invoke(UBuffer.unsafe, byteBuffer);
            return declaredMethod;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return e;
        }
    }

    public static long getBufferAddress(java.nio.Buffer buffer) {
        return unsafe.getLong(buffer, addressOffset);
    }

    public static ByteBuffer createBufferWrapper(long j, int i, int i2, ByteBuffer byteBuffer) {
        if (directBufferConstructor == null) {
            throw new IllegalStateException("记得添加 ALL-UNNAMED 参数");
        }
        try {
            switch (BufferConstructorMapping.constructorTypeToIntMap[bufferConstructorType.ordinal()]) {
                case 1:
                    Constructor constructor = directBufferConstructor;
                    Object[] objArr = new Object[2];
                    objArr[0] = j + i;
                    objArr[1] = (long) i2;
                    return (ByteBuffer) constructor.newInstance(objArr);
                case 2:
                    return (ByteBuffer) directBufferConstructor.newInstance(j + i, i2, byteBuffer);
                case 3:
                    return (ByteBuffer) directBufferConstructor.newInstance(j + i, i2);
                case 4:
                    return (ByteBuffer) directBufferConstructor.newInstance(((int) j) + i, i2);
                case 5:
                    Constructor constructor2 = directBufferConstructor;
                    Object[] objArr2 = new Object[3];
                    objArr2[0] = memoryBlockMethod.invoke(null, j + i, i2);
                    objArr2[1] = i2;
                    objArr2[2] = 0;
                    return (ByteBuffer) constructor2.newInstance(objArr2);
                default:
                    throw new IllegalStateException("错误参数");
            }
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }


    public static void cleanDirectBuffer(Object obj) {
        try {
            if (UBuffer.javaSpecVersion <= 8) {
                cleanerMethod.invoke(cleanerMethodAlternate.invoke(obj));
            } else {
                invokeCleanerMethod.invoke(UBuffer.unsafe, obj);
            }
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}
