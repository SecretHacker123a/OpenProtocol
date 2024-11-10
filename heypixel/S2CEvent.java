package dev.undefinedteam.gensh1n.protocol.heypixel;

import com.google.gson.JsonObject;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;


@StringEncryption
@ControlFlowObfuscation
public class S2CEvent {
    public String CODEC;
    public String plugin;
    public JsonObject size;

    public S2CEvent(String str, String str2, JsonObject jsonObject) {
        this.plugin = str;
        this.CODEC = str2;
        this.size = jsonObject;
    }

    public String getPlugin() {
        return this.plugin;
    }

    public JsonObject getData() {
        return this.size;
    }

    public String getEvent() {
        return this.CODEC;
    }
}
