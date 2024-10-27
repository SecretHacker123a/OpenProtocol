package dev.undefinedteam.gensh1n.protocol;

import dev.undefinedteam.gclient.GChat;
import dev.undefinedteam.gensh1n.Client;
import dev.undefinedteam.gensh1n.events.game.GameJoinedEvent;
import dev.undefinedteam.gensh1n.events.world.EntityJoinWorldEvent;
import dev.undefinedteam.gensh1n.events.world.WorldChangeEvent;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.File;

@StringEncryption
@ControlFlowObfuscation
public interface IProtocol {
    File FOLDER = new File(Client.FOLDER, "protocol");

    void init();

    void reload();

    String name();

    boolean onPacket(Packet packet);
    void onMessage(Text text);

    void onEntityJoinWorld(EntityJoinWorldEvent e);
    void onWorldChanged(WorldChangeEvent e);
    void onGameJoin(GameJoinedEvent e);
    void tick();
}
