package dev.undefinedteam.gensh1n.protocol.heypixel;

import com.google.gson.annotations.SerializedName;
import dev.undefinedteam.gensh1n.utils.json.GsonIgnore;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@StringEncryption
@ControlFlowObfuscation
public class HeypixelHwids {
    @SerializedName("hwids")
    public List<HeypixelHwid> hwids = new ArrayList<>();

    public boolean has(String s) {
        return hwids.stream().anyMatch(h -> h.userId.equals(s));
    }

    public HeypixelHwid get(String s) {
        return hwids.stream().filter(h -> h.userId.equals(s)).findFirst().orElse(null);
    }

    public void add(HeypixelHwid hwid) {
        if (!has(hwid.userId)) {
            hwids.add(hwid);
        }
    }
}
