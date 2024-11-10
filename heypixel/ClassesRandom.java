package dev.undefinedteam.gensh1n.protocol.heypixel;

import dev.undefinedteam.gensh1n.Genshin;
import dev.undefinedteam.gensh1n.utils.RandomUtils;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.HashMap;
import java.util.Map;

@StringEncryption
@ControlFlowObfuscation
public class ClassesRandom {
    /**
     * CLASS LOADER NAME
     * app , null , TRANSFORMER
     */

    public static final HashMap<String, String> check1 = new HashMap<>();

    static {
        check1.put("net.minecraftforge.client.ClientCommandHandler", "TRANSFORMER");
        check1.put("net.minecraftforge.client.event.EntityViewRenderEvent$FogColors", "TRANSFORMER");
        check1.put("net.minecraft.client.multiplayer.ClientLevel", "TRANSFORMER");
        check1.put("net.minecraft.client.gui.screens.Screen", "TRANSFORMER");
        check1.put("net.minecraft.client.multiplayer.ClientChunkCache", "TRANSFORMER");
        check1.put("net.minecraft.client.multiplayer.PlayerInfo", "TRANSFORMER");
        check1.put("net.minecraft.client.Camera", "TRANSFORMER");
        check1.put("net.minecraft.client.player.AbstractClientPlayer", "TRANSFORMER");
        check1.put("com.mojang.blaze3d.pipeline.RenderTarget", "TRANSFORMER");
        check1.put("net.minecraft.client.KeyMapping", "TRANSFORMER");
        check1.put("net.minecraftforge.client.settings.KeyConflictContext$2", "TRANSFORMER");
        check1.put("net.minecraft.client.resources.model.Material", "TRANSFORMER");
        check1.put("net.minecraftforge.client.event.EntityViewRenderEvent$FogEvent", "TRANSFORMER");
        check1.put("net.minecraftforge.client.ForgeRenderTypes$CustomizableTextureState", "TRANSFORMER");
        check1.put("net.minecraft.client.renderer.RenderStateShard$OverlayStateShard", "TRANSFORMER");
        check1.put("net.minecraft.client.renderer.RenderStateShard$LightmapStateShard", "TRANSFORMER");
        check1.put("net.minecraft.client.gui.chat.NarratorChatListener", "TRANSFORMER");
        check1.put("net.minecraft.client.renderer.RenderStateShard$TextureStateShard", "TRANSFORMER");
        check1.put("net.minecraftforge.client.model.ModelDataManager", "TRANSFORMER");
        check1.put("net.minecraft.client.gui.components.ComponentRenderUtils", "TRANSFORMER");
        check1.put("net.minecraftforge.client.model.ModelLoaderRegistry", "TRANSFORMER");
        check1.put("com.mojang.blaze3d.systems.RenderSystem", "TRANSFORMER");
        check1.put("net.minecraft.client.renderer.PanoramaRenderer", "TRANSFORMER");
        check1.put("net.minecraft.client.multiplayer.ClientLevel", "TRANSFORMER");
        check1.put("net.minecraft.client.renderer.texture.MissingTextureAtlasSprite", "TRANSFORMER");
        check1.put("net.minecraft.client.resources.SplashManager", "TRANSFORMER");
        check1.put("net.minecraft.client.renderer.texture.TextureManager", "TRANSFORMER");
        check1.put("net.minecraft.client.resources.SkinManager", "TRANSFORMER");
        check1.put("net.minecraft.client.multiplayer.PlayerInfo", "TRANSFORMER");
        check1.put("com.mojang.blaze3d.pipeline.RenderTarget", "TRANSFORMER");
        check1.put("net.minecraft.client.KeyMapping", "TRANSFORMER");
        check1.put("net.minecraft.client.resources.model.Material", "TRANSFORMER");
        check1.put("net.minecraftforge.client.event.EntityRenderersEvent$AddLayers", "TRANSFORMER");
        check1.put("net.minecraftforge.client.ForgeRenderTypes$CustomizableTextureState", "TRANSFORMER");
        check1.put("net.minecraftforge.client.event.EntityViewRenderEvent$FogEvent", "TRANSFORMER");
        check1.put("net.minecraftforge.client.event.FOVModifierEvent", "TRANSFORMER");
        check1.put("com.mojang.realmsclient.client.RealmsClient", "TRANSFORMER");
        check1.put("net.minecraft.client.renderer.RenderStateShard$LightmapStateShard", "TRANSFORMER");
        check1.put("net.minecraft.client.gui.chat.NarratorChatListener", "TRANSFORMER");
        check1.put("net.minecraftforge.client.model.ModelDataManager", "TRANSFORMER");
    }

    public static Map<String, String> randomCheck1() {
        Genshin.INSTANCE.onCheckVerify();

        int len = RandomUtils.nextInt(10, 12);

        var map = new HashMap<String, String>();
        for (int i = 0; i < len; i++) {
            var id = RandomUtils.nextInt(0, check1.size() - 1);
            var key = check1.keySet().toArray()[id].toString();
            while (map.containsKey(key)) {
                id = RandomUtils.nextInt(0, check1.size() - 1);
                key = check1.keySet().toArray()[id].toString();
            }

            map.put(key, check1.get(key));
        }

        return map;
    }
}
