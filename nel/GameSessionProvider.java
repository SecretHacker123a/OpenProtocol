package dev.undefinedteam.gensh1n.protocol.nel;

import dev.undefinedteam.gensh1n.protocol.nel.myth.MythSessionsProvider;
import dev.undefinedteam.gensh1n.protocol.nel.zone.ZoneSessionsProvider;
import dev.undefinedteam.gensh1n.system.modules.misc.Protocol;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;



public interface GameSessionProvider {
    void refresh();

    String getPlayerUUID(int port);
    String getUserId(int port);
    String getToken(int port);

    static GameSessionProvider get() {
        return switch (Protocol.getNEL()) {
            case Zone -> ZoneSessionsProvider.get();
            case Myth -> MythSessionsProvider.get();
        };
    }
}
