package dev.undefinedteam.gensh1n.protocol.heypixel.utils;

import dev.undefinedteam.gensh1n.protocol.heypixel.ValueTypeEnum;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.*;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


@StringEncryption
@ControlFlowObfuscation
public class DataBufferUtils {
    public static byte field_10355 = -1;
    public static byte field_10367 = -32;
    public static byte field_10199 = -33;
    public static byte field_10313 = -34;
    public static byte field_10181 = -35;
    public static byte field_10193 = -36;
    public static byte field_10379 = -37;
    public static byte field_10283 = -38;
    public static byte field_10253 = -39;
    public static byte field_10175 = -40;
    public static byte field_10277 = -41;
    public static byte field_10247 = -42;
    public static byte field_10217 = -43;
    public static byte field_10265 = -44;
    public static byte field_10331 = -45;
    public static byte field_10385 = -46;
    public static byte field_10259 = -47;
    public static byte field_10301 = -48;
    public static byte field_10295 = -49;
    public static byte field_10319 = -50;
    public static byte field_10307 = -51;
    public static byte field_10211 = -52;
    public static byte field_10229 = -53;
    public static byte field_10289 = -54;
    public static byte field_10397 = -55;
    public static byte field_10169 = -56;
    public static byte field_10391 = -57;
    public static byte field_10373 = -58;
    public static byte field_10241 = -59;
    public static byte field_10205 = -60;
    public static byte field_10187 = -61;
    public static byte field_10271 = -62;
    public static byte field_10163 = -63;
    public static byte field_10325 = -64;
    public static byte field_10223 = -96;
    public static byte field_10235 = -112;
    public static byte MIN_BYTE = Byte.MIN_VALUE;
    public static byte field_10403 = Byte.MIN_VALUE;


    public static Charset charset = StandardCharsets.UTF_8;
    public static BufferConfiguration configuration = new BufferConfiguration();
    public static ProcessorBuilder builder = new ProcessorBuilder();
    public static ValueTypeEnum[] valueFormats = new ValueTypeEnum[256];

    public static boolean Method10409(byte b) {
        return ((-16) | (-b - 1)) - (-b - 1) == -128;
    }

    public static boolean Method10415(byte b) {
        return (((-16) | ((-b) - 1)) + b) + 1 == -112;
    }

    public static boolean Method10433(byte b) {
        int i = (255 | ((-b) - 1)) - ((-b) - 1);
        return i <= 127 || i >= 224;
    }

    public static boolean Method10469(byte b) {
        return ((-128) | ((-b) - 1)) - ((-b) - 1) == 0;
    }

    public static boolean Method10481(byte b) {
        return (((-32) | ((-b) - 1)) + b) + 1 == -96;
    }

    public static boolean Method10487(byte b) {
        return ((-32) | ((-b) - 1)) - ((-b) - 1) == -96;
    }

    public static boolean Method10505(byte b) {
        return ((-32) | ((-b) - 1)) - ((-b) - 1) == -32;
    }


    public static DataBufferProcessor create(ReadableByteChannel readableByteChannel) {
        return builder.createDataBufferProcessorFromChannel(readableByteChannel);
    }


    public static MessageBuffer createMessageBuffer(BufferedOutput bufferedOutput) {
        return configuration.createMessageBuffer(bufferedOutput);
    }


    public static DataBuffer createBuffer() {
        return configuration.createBuffer();
    }


    public static DataBufferProcessor create(byte[] bArr) {
        return builder.createFromBytes(bArr);
    }


    public static DataBufferProcessor create(InputStream inputStream) {
        return builder.createFromStream(inputStream);
    }


    public static DataBufferProcessor createProcessor(ILBuffer iLBuffer) {
        return builder.createProcessor(iLBuffer);
    }


    public static MessageBuffer create(WritableByteChannel writableByteChannel) {
        return configuration.createMessageBufferFromChannel(writableByteChannel);
    }


    public static DataBufferProcessor create(byte[] bArr, int i, int i2) {
        return builder.createFromBytes(bArr, i, i2);
    }


    public static DataBufferProcessor create(ByteBuffer byteBuffer) {
        return builder.createFromByteBuffer(byteBuffer);
    }


    public static MessageBuffer create(OutputStream outputStream) {
        return configuration.createMessageBufferFromStream(outputStream);
    }
}
