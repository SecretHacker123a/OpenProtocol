package dev.undefinedteam.gensh1n.protocol.heypixel.data.retrievers;

import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.time.Instant;



public interface InstantRetriever extends PayloadRetriever {
    int getNano();

    long getEpochMilli();

    Instant getInstant();

    long getEpochSecond();
}
