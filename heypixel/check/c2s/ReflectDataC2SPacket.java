package dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import dev.undefinedteam.gensh1n.protocol.heypixel.Heypixel;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelSessionManager;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.value.Value;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@StringEncryption
@ControlFlowObfuscation
public class ReflectDataC2SPacket extends HeypixelCheckPacket {
    public static String[] allowedPackages = {
        "com.heypixel",
        "com.mojang",
        "net.minecraft",
        "net.minecraftforge",
        "cpw.mods",
        "org.spongepowered",
        "software.bernie.geckolib3",
        "me.jellysquid.mods.sodium",
        "net.fabricmc.loader",
        "repack.joml"
    };
    public static Gson GSON = new Gson();
    public String jsonData;


    public ReflectDataC2SPacket(String str) {
        try {
            this.jsonData = str;
//            System.out.println("action: " + str);
        } catch (Exception e) {
            e.printStackTrace();
            this.jsonData = "";
        }
    }


    public ReflectDataC2SPacket(PacketByteBuf friendlyByteBuf) {
    }

    public static void sendCheckPacket(HeypixelSessionManager manager, String str) {
        new ReflectDataC2SPacket(
            !str.startsWith("[{")
                ? JsonParser.parseString(str).getAsJsonObject().get(manager.getEncryptKey()).getAsString()
                : processJsonAction(str)
        ).m(manager).sendCheckPacket();
    }

    public static boolean isAllowedPackage(Map<String, String> map) {
        boolean z = false;
        String[] strArr = allowedPackages;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (map.get("className").startsWith(strArr[i])) {
                z = true;
                break;
            }
            i++;
        }
        return z;
    }

    public static String processJsonAction(String data) {
//        System.out.println("check: " + data);
        List<Map<String, String>> var1 = GSON.fromJson(data, List.class);
        Object result = null;

        try {
            Iterator<Map<String, String>> iterator = var1.iterator();

            while (iterator.hasNext()) {
                Map<String, String> map = iterator.next();
                if (!isAllowedPackage(map)) {
                    return String.valueOf(result);
                }

                String action = map.get("action");
                byte actionByte = switch (action) {
                    case "getEnumOrdinal" -> 1;
                    default -> 0;
                };

                Class clazz;
                switch (actionByte) {
                    case 0:
                        return String.valueOf(mc.player.getPlayerListEntry().getProfile().getId());
                    case 1:
                        clazz = Class.forName(map.get("className"));
                        if (!clazz.isEnum()) {
                            result = "Error Json!";
                        } else {
                            Object[] enumConstants = clazz.getEnumConstants();
                            Object[] constants = enumConstants;
                            int length = enumConstants.length;

                            for (int i = 0; i < length; ++i) {
                                Object var13 = constants[i];
                                if (var13.toString().equals(map.get("enumName"))) {
                                    result = ((Enum) var13).ordinal();
                                    break;
                                }
                            }
                        }
                }
            }

            return String.valueOf(result);
        } catch (Exception e) {
            result = "CheckError";
            return String.valueOf(result);
        }
    }

    @Override
    public void writeSessionData(DataBuffer dataBuffer) throws IOException {
        dataBuffer.writeObjectData(new Value().setDataFromString(this.jsonData));
        dataBuffer.writeObjectData(new Value().setDataFromString(Heypixel.get().getPlayerUUID()));
    }

    @Override
    public void processBuffer(PacketByteBuf buf, BufferHelper bufferHelper) {
        bufferHelper.writeString(buf, this.jsonData);
    }
}
