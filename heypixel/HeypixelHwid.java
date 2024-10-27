package dev.undefinedteam.gensh1n.protocol.heypixel;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@StringEncryption
@ControlFlowObfuscation
public class HeypixelHwid {
    @SerializedName("userId")
    public String userId;
    @SerializedName("networkHardware")
    public List<String> networkHardware;
    @SerializedName("cpuInfo")
    public String cpuInfo;
    @SerializedName("baseboardSerial")
    public String baseboardSerial;
    @SerializedName("diskSerials")
    public List<String> diskSerials;
    @SerializedName("baseboardInfo")
    public Map<String,String> baseboardInfo;
    @SerializedName("diskStoreInfo")
    public List<Map<String,String>> diskStoreInfo;
    @SerializedName("networkInterfaces")
    public List<Map<String,String>> networkInterfaces;
    @SerializedName("systemHwid")
    public String systemHwid;
}
