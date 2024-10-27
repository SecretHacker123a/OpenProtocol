package dev.undefinedteam.gensh1n.protocol.nel.zone;

import com.google.gson.annotations.SerializedName;
import dev.undefinedteam.gensh1n.protocol.nel.GameSessionProvider;
import dev.undefinedteam.gensh1n.utils.network.Http;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.util.ArrayList;
import java.util.List;

public class ZoneSessionsProvider implements GameSessionProvider {
    public final List<ZoneSessionInfo> sessions = new ArrayList<>();

    private static ZoneSessionsProvider sInstance;

    public static ZoneSessionsProvider get() {
        if (sInstance == null) {
            sInstance = new ZoneSessionsProvider();
        }
        return sInstance;
    }

    @Override
    public void refresh() {
        try {
            ZoneResponse response = Http.get("http://127.0.0.1:54188/")
                .sendJson(ZoneResponse.class);

            sessions.clear();
            sessions.addAll(response.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPlayerUUID(int port) {
        return sessions.stream().filter(s -> s.port == port).findFirst().orElse(new ZoneSessionInfo()).playerGameUuid;
    }

    @Override
    public String getUserId(int port) {
        return sessions.stream().filter(s -> s.port == port).findFirst().orElse(new ZoneSessionInfo()).entityId;
    }

    @Override
    public String getToken(int port) {
        return sessions.stream().filter(s -> s.port == port).findFirst().orElse(new ZoneSessionInfo()).token;
    }

    public static class ZoneResponse {
        @SerializedName("code")
        public int code;
        @SerializedName("msg")
        public String msg;
        @SerializedName("count")
        public int count;
        @SerializedName("total")
        public int total;
        @SerializedName("data")
        public List<ZoneSessionInfo> data;
    }
}
