package dev.undefinedteam.gensh1n.protocol.heypixel.value;

import dev.undefinedteam.gensh1n.protocol.heypixel.data.BytesDataProvider;
import dev.undefinedteam.gensh1n.protocol.heypixel.exceptions.CharacterCodingFailedException;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;


@StringEncryption
@ControlFlowObfuscation
public abstract class ByteBufValue extends AbstractValue implements BytesDataProvider {
    public Value value;


    public ByteBufValue(Value value) {
        super(value);
        this.value = value;
    }

    public String getString() {
        try {
            return DataBufferUtils.charset.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).decode(ByteBuffer.wrap((byte[]) this.value.data)).toString();
        } catch (CharacterCodingException e) {
            throw new CharacterCodingFailedException(e);
        }
    }

    @Override
    public BytesDataProvider getAsBytes() {
        return this;
    }

    public ByteBuffer getByteBuffer() {
        return ByteBuffer.wrap(getBytes());
    }

    @Override
    public String toString() {
        byte[] bArr = (byte[]) this.value.data;
        try {
            return DataBufferUtils.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).decode(ByteBuffer.wrap(bArr)).toString();
        } catch (CharacterCodingException e) {
            throw new CharacterCodingFailedException(e);
        }
    }

    public byte[] getBytes() {
        return (byte[]) this.value.data;
    }
}
