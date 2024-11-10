package dev.undefinedteam.gensh1n.protocol.heypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.undefinedteam.gclient.GCClient;
import dev.undefinedteam.gclient.GCUser;
import dev.undefinedteam.gclient.data.AssetsSet;
import dev.undefinedteam.gensh1n.Genshin;
import dev.undefinedteam.gensh1n.events.game.GameJoinedEvent;
import dev.undefinedteam.gensh1n.events.world.EntityJoinWorldEvent;
import dev.undefinedteam.gensh1n.events.world.WorldChangeEvent;
import dev.undefinedteam.gensh1n.gui.frags.ChatFragment;
import dev.undefinedteam.gensh1n.protocol.IProtocol;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.BufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.UBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelCheckPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.HeypixelSessionManager;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import dev.undefinedteam.gensh1n.protocol.nel.GameSessionProvider;
import dev.undefinedteam.gensh1n.utils.network.NetPayload;
import dev.undefinedteam.modernui.mc.MuiScreen;
import dev.undefinedteam.modernui.mc.UIManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.checkerframework.checker.units.qual.N;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import java.util.function.Supplier;

import static dev.undefinedteam.gclient.GChat.GSON;
import static dev.undefinedteam.gensh1n.Client.mc;

@StringEncryption
@NativeObfuscation
@ControlFlowObfuscation
public class Heypixel implements IProtocol {
    @NativeObfuscation.Inline
    public static final String MOD_ID = "heypixel";
    @NativeObfuscation.Inline
    public static final Identifier S2C_CHANNEL = new Identifier("heypixel", "s2cevent");
    public static Heypixel sInstance;
    @NativeObfuscation.Inline
    public static long randomId = -1L;
    public static BufferHelper bufferHelper = new BufferHelper();
    public UUID clientId = UUID.randomUUID();
    @NativeObfuscation.Inline
    public long runTime;

    public HeypixelHwids hwids = new HeypixelHwids();

    public void save() {
        try {
            String json = GSON.toJson(hwids);
            if (!CFG.exists()) CFG.createNewFile();
            Files.writeString(CFG.toPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final File CFG = new File(FOLDER, "heypixel.json");

    public HeypixelSessionManager manager;

    public GameSessionProvider provider() {
        return GameSessionProvider.get();
    }

    public static Heypixel get() {
        if (sInstance == null) {
            sInstance = new Heypixel();
            sInstance.init();
        }
        return sInstance;
    }

    static {
        UBuffer.init();
        BufferUtils.init();
        HeypixelCheckPacket.init();
    }

    @Override
    public void init() {
        try {
            if (!FOLDER.exists()) FOLDER.mkdirs();

            this.hwids.hwids.clear();
            if (CFG.exists()) {
                HeypixelHwids hwids1 = GSON.fromJson(Files.readString(CFG.toPath(), StandardCharsets.UTF_8), HeypixelHwids.class);
                this.hwids.hwids.addAll(hwids1.hwids);
            } else save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sInstance = this;
        runTime = System.currentTimeMillis() - 15000;
        manager = new HeypixelSessionManager();
    }

    public String getPlayerUUID() {
        return MinecraftClient.getInstance().player.getUuidAsString();
    }

    @Override
    public void reload() {
        clientId = UUID.randomUUID();
        runTime = System.currentTimeMillis() - 15000;
        manager = new HeypixelSessionManager();

        NetPayload.register(S2C_CHANNEL, buf -> {
        });
    }

    @Override
    public String name() {
        return "Heypixel";
    }

    @Override
    public boolean onPacket(Packet packet) {
        try {
            Supplier<Boolean> check = () -> {
                var session = GCClient.INSTANCE.session();
                if (session != null) {
                    return (session.logged() && session.verifyPass()) || session.verifyPass();
                }
                return GCUser.globalVerifyPass();
            };

            if (!check.get()) {
                if (!(mc.currentScreen instanceof MuiScreen screen)) {
                    UIManager.getInstance().open(new ChatFragment(check));
                    return true;
                }
                if (!(screen.getFragment() instanceof ChatFragment)) {
                    UIManager.getInstance().open(new ChatFragment(check));
                }
                return true;
            }
        } catch (Exception e) {
        }

        if (packet instanceof CustomPayloadS2CPacket p && p.payload() instanceof NetPayload.PayloadPacket payload) {
            // FORGE CHANNEL INDEX
            var buf = payload.buf;
            var index = buf.readUnsignedByte();
            switch (index) {
                case 233 -> { // S2C DEFAULT
                    JsonObject asJsonObject = JsonParser.parseString(buf.toString(StandardCharsets.UTF_8)).getAsJsonObject();
                    if (asJsonObject.has("plugin") && asJsonObject.has("event") && asJsonObject.has("data")) {
                        var s2c = new S2CEvent(asJsonObject.get("plugin").getAsString(), asJsonObject.get("event").getAsString(), asJsonObject.getAsJsonObject("data"));
                        manager.onServerEvent(s2c);
                    }

                }
                case 250 -> { // HEYPIXEL SESSION
                    try {
                        var heypixelCheckPacket = manager.decodePacket(buf);
                        manager.handleNetworkEvent(heypixelCheckPacket);
                    } catch (Throwable t) {
                        //t.printStackTrace();
                    }
                }
            }

            return true;
        }

        if (packet instanceof CustomPayloadS2CPacket p && !(p.payload() instanceof NetPayload.PayloadPacket)) {
            if (p.payload().id().toString().equals("minecraft:register")) {
                return true;
            }
        }

        if (packet instanceof BlockUpdateS2CPacket p) {

        }

        return false;
    }

    @Override
    public void onMessage(Text text) {
        HeypixelSessionManager.handleChatMessage(text);
    }

    @Override
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        manager.onEntityJoinWorld(e.world, e.entity);
    }

    @Override
    public void onWorldChanged(WorldChangeEvent e) {

    }

    @Override
    public void onGameJoin(GameJoinedEvent e) {
        //NetPayload.pre(()-> manager.onEntityJoinWorld(mc.world, mc.player));
    }

    @Override
    public void tick() {
        manager.onClientTick();
    }
}
