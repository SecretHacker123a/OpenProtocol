package dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c;

import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import net.minecraft.network.PacketByteBuf;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.lang.reflect.Method;


@StringEncryption
@ControlFlowObfuscation
public class NeteaseCheckS2CPacket extends HeypixelCheckPacket {
    public NeteaseCheckS2CPacket(PacketByteBuf friendlyByteBuf) {
        try {
            Method declaredMethod = Class.forName("com.netease.mc.mod.fullscreenpopup.ToggleFullscreenTransformer").getDeclaredMethod("showGameStorePopup");
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
