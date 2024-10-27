package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import dev.undefinedteam.gensh1n.protocol.heypixel.IDKAnnotation;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public class MsgUtils {

    @NativeObfuscation.Inline
    public static String formatMessage(String str, @IDKAnnotation Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        StringBuilder sb = new StringBuilder(valueOf.length() + (16 * objArr.length));
        int i = 0;
        int i2 = 0;
        while (i2 < objArr.length && (indexOf = valueOf.indexOf("%s", i)) != -1) {
            sb.append(valueOf, i, indexOf);
            int i3 = i2;
            i2++;
            sb.append(objArr[i3]);
            i = (indexOf | 2) + ((2 | ((-indexOf) - 1)) - ((-indexOf) - 1));
        }
        sb.append(valueOf.substring(i));
        if (i2 < objArr.length) {
            sb.append(" [");
            int i4 = i2;
            int i5 = i2 + 1;
            StringBuilder append = sb.append(objArr[i4]);
            while (i5 < objArr.length) {
                sb.append(", ");
                int i6 = i5;
                i5++;
                append = sb.append(objArr[i6]);
            }
            sb.append(']');
        }
        return sb.toString();
    }


    @NativeObfuscation.Inline
    public static Object requireNonNull(Object obj, @IDKAnnotation Object obj2) {
        if (obj == null) {
            throw new NullPointerException(String.valueOf(obj2));
        }
        return obj;
    }


    @NativeObfuscation.Inline
    public static String validateRange(int i, int i2, String str) {
        if (i < 0) {
            return formatMessage("%s (%s) 不能为负数", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("负数大小: " + i2);
        }
        return formatMessage("%s (%s) 必须大于 (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }


    public static int checkIndexBounds(int i, int i2, @IDKAnnotation String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(validateRange(i, i2, str));
        }
        return i;
    }


    public static int checkIndexInclusiveBounds(int i, int i2, @IDKAnnotation String str) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(validateLowerBound(i, i2, str));
        }
        return i;
    }


    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }


    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }


    @NativeObfuscation.Inline
    public static String validateLowerBound(int i, int i2, String str) {
        if (i < 0) {
            return formatMessage("%s (%s) 不能为负", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("负数大小: " + i2);
        }
        return formatMessage("%s (%s) 必须小于 (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }


    public static void checkArgumentWithMessage(boolean z, @IDKAnnotation Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }


    public static void checkArgumentWithFormattedMessage(boolean z, @IDKAnnotation String str, @IDKAnnotation Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(formatMessage(str, objArr));
        }
    }


    public static int checkIndex(int i, int i2) {
        return checkIndexBounds(i, i2, "index");
    }


    public static void checkStateWithMessage(boolean condition, @IDKAnnotation String format, @IDKAnnotation Object... args) {
        if (!condition) {
            throw new IllegalStateException(formatMessage(format, args));
        }
    }


    public static Object requireNonNullWithMessage(Object obj, @IDKAnnotation String str, @IDKAnnotation Object... objArr) {
        if (obj == null) {
            throw new NullPointerException(formatMessage(str, objArr));
        }
        return obj;
    }


    @NativeObfuscation.Inline
    public static String validateIndexRange(int i, int i2, int i3) {
        return (i < 0 || i > i3) ? validateRange(i, i3, "start index") : (i2 < 0 || i2 > i3) ? validateRange(i2, i3, "end index") : formatMessage("搞鸡毛？ 最终下标 (%s) 必须要小于起始下标 (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }


    public static int checkIndexInclusive(int i, int i2) {
        return checkIndexInclusiveBounds(i, i2, "index");
    }


    public static Object requireNonNullStrict(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }


    public static void checkRangeBounds(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException(validateIndexRange(i, i2, i3));
        }
    }


    public static void checkStateStrict(boolean condition, @IDKAnnotation Object errorMessage) {
        if (!condition) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }
}
