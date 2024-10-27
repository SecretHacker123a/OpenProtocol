package dev.undefinedteam.gensh1n.protocol.nel.myth;

import com.google.gson.annotations.SerializedName;
import dev.undefinedteam.gensh1n.protocol.nel.GameSessionProvider;
import dev.undefinedteam.gensh1n.protocol.nel.zone.ZoneSessionInfo;
import dev.undefinedteam.gensh1n.protocol.nel.zone.ZoneSessionsProvider;
import dev.undefinedteam.gensh1n.system.modules.misc.Protocol;
import dev.undefinedteam.gensh1n.utils.network.Http;
import lombok.Data;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.ArrayList;
import java.util.List;
public class MythSessionsProvider implements GameSessionProvider {
    public final List<MythSessionInfo> sessions = new ArrayList<>();

    private static MythSessionsProvider sInstance;

    public static MythSessionsProvider get() {
        if (sInstance == null) {
            sInstance = new MythSessionsProvider();
        }
        return sInstance;
    }

    @Override
    public void refresh() {
        try {
            MythResponse response = Http.get("http://127.0.0.1:14250/launcher/GetProxyServers")
                .sendJson(MythResponse.class);

            sessions.clear();
            sessions.addAll(response.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPlayerUUID(int port) {
        return sessions.stream().filter(s -> s.port == port).findFirst().orElse(new MythSessionInfo()).playerGameUuid;
    }

    @Override
    public String getUserId(int port) {
        return sessions.stream().filter(s -> s.port == port).findFirst().orElse(new MythSessionInfo()).entityId;
    }

    @Override
    public String getToken(int port) {
        return sessions.stream().filter(s -> s.port == port).findFirst().orElse(new MythSessionInfo()).token;
    }

    public static class MythResponse {
        @SerializedName("code")
        public int code;
        @SerializedName("msg")
        public String msg;
        @SerializedName("count")
        public int count;
        @SerializedName("total")
        public int total;
        @SerializedName("data")
        private List<MythSessionInfo> data;
    }
}
