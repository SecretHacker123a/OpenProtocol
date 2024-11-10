package dev.undefinedteam.gensh1n.protocol.heypixel.check;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.undefinedteam.gensh1n.protocol.heypixel.*;
import dev.undefinedteam.gensh1n.protocol.heypixel.buffer.DataBuffer;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.c2s.ClassesC2SPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c.PlayerListDataS2CPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.check.s2c.SyncKeysS2CPacket;
import dev.undefinedteam.gensh1n.protocol.heypixel.collections.StringSet;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.DataFactory;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.ObjectData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IBufferData;
import dev.undefinedteam.gensh1n.protocol.heypixel.data.interfaces.IMapData;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.BufferHelper;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.DataBufferUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.utils.HeypixelVarUtils;
import dev.undefinedteam.gensh1n.protocol.heypixel.value.Value;
import dev.undefinedteam.gensh1n.utils.RandomUtils;
import dev.undefinedteam.gensh1n.utils.chat.ChatUtils;
import dev.undefinedteam.gensh1n.utils.network.NetPayload;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.joml.Random;
import tech.skidonion.obfuscator.annotations.ControlFlowObfuscation;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.StringEncryption;

import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@StringEncryption
@NativeObfuscation
@ControlFlowObfuscation
public class HeypixelSessionManager {
    public byte[] key1;
    public byte[] key2;
    public byte[] key3;

    public Value hardwareInfo;

    public Value neteaseUserListHash;
    public Value diskSerials;
    public Value cpuInfo;
    public Value userIdInfo;
    public Value diskStoreInfo;
    public Value networkInterfaces;
    public Value baseboardInfo;
    public Value baseboardSerial;
    public Value networkHardwareInfo;
    public Value systemInfo;

    public byte[] clientToken;
    public int loadClassCount;
    public byte[] serverToken;

    public List playerList;
    public int playerCount;

    public static MinecraftClient mc;

    public int[] unknownArray;

    public byte[] unknownBytes;
    public int[] decodeKeys;
    public byte[] encryptionKey;

    public int modClassCount;
    public byte[] encryptMode;
    public HashMap<String, String> tokenMap;

    public byte[] serverId;
    public byte[] encryptMode1;

    public static String channel = "heypixel:check";
    public static int updateInterval = 5;
    public static String placeholderString1 = "EMPTY#1";
    public static String placeholderString2 = "EMPTY#2";
    public static String placeholderString3 = "EMPTY#3";
    public static String defaultBlankValue1 = "BLANK";
    public static String defaultBlankValue2 = "BLANK";
    public static String defaultBlankValue3 = "BLANK";
    public long lastCheckTimestamp = 0;
    public static Set<String> uniqueClassNames = new HashSet<>();
    public static HashMap<String, String> classLoaderMap = new HashMap<>();
    public boolean classesSent = false;
    public long nextSendClasses = -1;
    public static BufferHelper bufferHelper = new BufferHelper();
    public long lastActionTime = -1;
    public Identifier lastResourceLocation = null;

    public HeypixelSessionManager() {
        RANDOM_DISK_ = "SCRW1" + RandomUtils.randomNumber(7) + "F" + RandomUtils.randomNumber(4);
        BASEBOARD_SERIAL_NUM = RandomUtils.randomNumber(16);

        scanClassPath();
        randomNeteaseUsers();
        tokenMap = new HashMap<>();
        networkHardwareInfo = randomNetworkHardware();
        cpuInfo = randomCpuInfo();
        baseboardSerial = randomBaseboardSerial();
        diskSerials = randomDiskSerials();
        baseboardInfo = randomBaseboardInfo();
        diskStoreInfo = randomDiskInfo();
        networkInterfaces = randomNetworkInterfaces();
        systemInfo = randomSystemHwid();

//        System.out.println(networkHardwareInfo.getDataAsString());
//        System.out.println(cpuInfo.getDataAsString());
//        System.out.println(baseboardSerial.getDataAsString());
//        System.out.println(diskSerials.getDataAsString());
//        System.out.println(baseboardInfo.getDataAsString());
//        System.out.println(diskStoreInfo.getDataAsString());
//        System.out.println(networkInterfaces.getDataAsString());
//        System.out.println(systemInfo.getDataAsString());

        mc = MinecraftClient.getInstance();
    }

    public void loadUserInfo() {
        try {
            Heypixel.get().provider().refresh();

            if (mc.isInSingleplayer()) return;
            int port = 25565;
            if (mc.getNetworkHandler() != null && mc.getNetworkHandler().getServerInfo() != null) {
                var addr = mc.getNetworkHandler().getServerInfo().address;
                if (addr.contains(":")) {
                    port = Integer.parseInt(addr.split(":")[1]);
                }
            }

            long userId = Long.parseLong(Heypixel.get().provider().getUserId(port));

            var hwids = Heypixel.get().hwids;
            if (hwids.has(String.valueOf(userId))) {
                loadFromJson(hwids.get(String.valueOf(userId)));
            } else {
                hwids.add(saveHwid(String.valueOf(userId)));
                Heypixel.get().save();
            }

            HashMap<ObjectData, ObjectData> hashMap = new HashMap<>();
            hashMap.put(DataFactory.ofBuffer("TokenHash"), DataFactory.ofBuffer(hashStringWithException(Heypixel.get().provider().getToken(port))));

            hashMap.put(DataFactory.ofBuffer("UserId"), DataFactory.ofLong((userId & 2147483648L) == 0 ? userId | 2147483648L : userId));

            userIdInfo = new Value().setMapData(hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HeypixelHwid saveHwid(String userId) {
        var networkHardwareInfo = new ArrayList<String>();
        for (ObjectData objectData : this.networkHardwareInfo.getAsList()) {
            var name = new String(objectData.getAsBytesBuf().getBytes(), DataBufferUtils.charset);
            networkHardwareInfo.add(name);
        }

        var cpuInfo = new String((byte[]) this.cpuInfo.data, DataBufferUtils.charset);
        var baseboardSerial = new String((byte[]) this.baseboardSerial.data, DataBufferUtils.charset);
        var diskSerials = new ArrayList<String>();
        for (ObjectData objectData : this.diskSerials.getAsList()) {
            var name = new String(objectData.getAsBytesBuf().getBytes(), DataBufferUtils.charset);
            diskSerials.add(name);
        }

        var baseboardInfo = new HashMap<String, String>();
        for (Object o : this.baseboardInfo.getAsMap().getMap().entrySet()) {
            Map.Entry<ObjectData, ObjectData> entry = (Map.Entry<ObjectData, ObjectData>) o;

            var a = new String(entry.getKey().getAsBytesBuf().getBytes(), DataBufferUtils.charset);
            var b = new String(entry.getValue().getAsBytesBuf().getBytes(), DataBufferUtils.charset);
            baseboardInfo.put(a, b);
        }
        List<Map<String, String>> diskStoreInfo = new ArrayList<>();
        for (ObjectData objectData : this.diskStoreInfo.getAsList()) {
            var map = new HashMap<String, String>();
            for (Object o : objectData.getAsMap().getMap().entrySet()) {
                Map.Entry<ObjectData, ObjectData> entry = (Map.Entry<ObjectData, ObjectData>) o;
                var a = new String(entry.getKey().getAsBytesBuf().getBytes(), DataBufferUtils.charset);
                var b = new String(entry.getValue().getAsBytesBuf().getBytes(), DataBufferUtils.charset);
                map.put(a, b);
            }

            diskStoreInfo.add(map);
        }

        List<Map<String, String>> networkInterfaces = new ArrayList<>();
        for (ObjectData objectData : this.networkInterfaces.getAsList()) {
            var map = new HashMap<String, String>();
            for (Object o : objectData.getAsMap().getMap().entrySet()) {
                Map.Entry<ObjectData, ObjectData> entry = (Map.Entry<ObjectData, ObjectData>) o;
                var a = new String(entry.getKey().getAsBytesBuf().getBytes(), DataBufferUtils.charset);
                var b = new String(entry.getValue().getAsBytesBuf().getBytes(), DataBufferUtils.charset);
                map.put(a, b);
            }

            networkInterfaces.add(map);
        }
        var systemInfo = new String((byte[]) this.systemInfo.data, DataBufferUtils.charset);
        return new HeypixelHwid(userId, networkHardwareInfo, cpuInfo, baseboardSerial, diskSerials, baseboardInfo, diskStoreInfo, networkInterfaces, systemInfo);
    }

    private void loadFromJson(HeypixelHwid hwid) {
        {
            ArrayList<ObjectData> arrayList = new ArrayList<>();
            for (var s : hwid.networkHardware) {
                arrayList.add(DataFactory.ofBuffer(s));
            }
            networkHardwareInfo = new Value().setListData(arrayList);
        }

        cpuInfo = new Value().setDataFromString(hwid.cpuInfo);
        baseboardSerial = new Value().setDataFromString(hwid.baseboardSerial);
        {
            ArrayList<ObjectData> arrayList = new ArrayList<>();
            for (var s : hwid.diskSerials) {
                arrayList.add(DataFactory.ofBuffer(s));
            }
            diskSerials = new Value().setListData(arrayList);
        }

        {
            HashMap<ObjectData, ObjectData> map = new HashMap<>();
            for (var s : hwid.baseboardInfo.entrySet()) {
                map.put(DataFactory.ofBuffer(s.getKey()), DataFactory.ofBuffer(s.getValue()));
            }
            baseboardInfo = new Value().setMapData(map);
        }

        {
            ArrayList<IMapData<ObjectData>> arrayList = new ArrayList<>();
            for (var s : hwid.diskStoreInfo) {
                HashMap<ObjectData, ObjectData> map = new HashMap<>();

                for (var b : s.entrySet()) {
                    map.put(DataFactory.ofBuffer(b.getKey()), DataFactory.ofBuffer(b.getValue()));
                }

                arrayList.add(DataFactory.ofMap(map));
            }
            diskStoreInfo = new Value().setListData(arrayList);
        }

        {
            ArrayList<IMapData<ObjectData>> arrayList = new ArrayList<>();
            for (var s : hwid.networkInterfaces) {
                HashMap<ObjectData, ObjectData> map = new HashMap<>();

                for (var b : s.entrySet()) {
                    map.put(DataFactory.ofBuffer(b.getKey()), DataFactory.ofBuffer(b.getValue()));
                }

                arrayList.add(DataFactory.ofMap(map));
            }
            networkInterfaces = new Value().setListData(arrayList);
        }
        systemInfo = new Value().setDataFromString(hwid.systemHwid);
    }


    public String convertBytesToString(byte[] bArr) {
        return decodeString(new String(bArr, StandardCharsets.UTF_8));
    }

    public static int getNumericValue(int i) {
        return Character.getNumericValue(Integer.toString(i).charAt(0));
    }


    @NativeObfuscation.Inline
    public void updateServerInfo(JsonObject jsonObject) {
        Heypixel.randomId = System.currentTimeMillis() - jsonObject.get("ServerTime").getAsLong();
        JsonArray asJsonArray = jsonObject.get("YkdsemRBPT0").getAsJsonArray();
        List<String> arrayList = new ArrayList<>();
        for (JsonElement jsonElement : asJsonArray) {
            arrayList.add(jsonElement.getAsString());
        }
        placeholderString1 = hashString(jsonObject.get(arrayList.get(0)).getAsString());
        placeholderString2 = hashString(jsonObject.get(arrayList.get(1)).getAsString());
        placeholderString3 = hashString(jsonObject.get(arrayList.get(2)).getAsString());
        defaultBlankValue1 = hashString(jsonObject.get(arrayList.get(3)).getAsString());
        defaultBlankValue2 = String.valueOf(networkHardwareInfo);
        defaultBlankValue3 = String.valueOf(diskSerials);
    }


    @NativeObfuscation.Inline
    public static String getResourcePath(int i) {
        return switch (i) {
            case 1 -> "java.home";
            case 2 -> "jmap.exe";
            case 3 -> " -histo:live ";
            case 4 -> ".class";
            case 5 -> ".jar";
            case 6 -> "libraries/";
            case 7 -> "java.class.path";
            case 8 -> "mods";
            case 9 -> "bin";
            case 10 -> ":";
            case 11 -> "啊？ 啊？";
            case 12 -> "啊？";
            case 13 -> " GC.class_histogram";
            case 14 -> "jcmd.exe ";
            default -> "nothing";
        };
    }


    public void processSyncKeys(SyncKeysS2CPacket syncKeysPacket) {
        String str = tokenMap.get(convertBytesToString(serverToken));
        String str2 = tokenMap.get(convertBytesToString(clientToken));
        String str3 = tokenMap.get(convertBytesToString(serverId));
        String convertBytesToString = convertBytesToString(syncKeysPacket.keyA);
        String convertBytesToString2 = convertBytesToString(syncKeysPacket.keyB);
        String convertBytesToString3 = convertBytesToString(syncKeysPacket.keyC);
        if (convertBytesToString.equals(str)) {
            key1 = syncKeysPacket.keyListA;
        } else if (convertBytesToString.equals(str2)) {
            key2 = syncKeysPacket.keyListA;
        } else if (convertBytesToString.equals(str3)) {
            key3 = syncKeysPacket.keyListA;
        }
        if (convertBytesToString2.equals(str)) {
            key1 = syncKeysPacket.keyListB;
        } else if (convertBytesToString2.equals(str2)) {
            key2 = syncKeysPacket.keyListB;
        } else if (convertBytesToString2.equals(str3)) {
            key3 = syncKeysPacket.keyListB;
        }
        if (convertBytesToString3.equals(str)) {
            key1 = syncKeysPacket.keyListC;
        } else if (convertBytesToString3.equals(str2)) {
            key2 = syncKeysPacket.keyListC;
        } else if (convertBytesToString3.equals(str3)) {
            key3 = syncKeysPacket.keyListC;
        }
    }


    @NativeObfuscation.Inline
    public static String hashString(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString((b | (-256)) + 256);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    public void handleNetworkEvent(HeypixelCheckPacket heypixelPacket) {
        applyToPlayer(heypixelPacket);
    }

    public List getPlayerList() {
        return playerList;
    }

    public static String getClassName(String str, int i) {
        String[] split = str.split("\\.");
        if (split.length <= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder(split[0]);
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(".").append(split[i2]);
        }
        return sb.toString();
    }

    public void sendGameInfo() {
        var id = new Identifier("heypixel:game_info");

        var buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeByte(1);
        buf.writeLong(System.currentTimeMillis());
        buf.writeLong(Heypixel.randomId);

        NetPayload.send(id, buf);

        lastCheckTimestamp = System.currentTimeMillis();
    }

    public static boolean isModFile(File file) {
        return !file.getName().endsWith(getResourcePath(5));
    }


    public void scanClassPath() {
        loadClassCount = 74797;
        modClassCount = 39269;
    }


    public void setSessionData(PlayerListDataS2CPacket playerListCheckPacket) {
        playerList = playerListCheckPacket.playerList;
        playerCount = playerListCheckPacket.playerCount;
    }


    public void sendClasses0() {
        Map<String, String> hashMap = ClassesRandom.randomCheck1();

        var packet = new ClassesC2SPacket(
            1,
            hashMap.size(),
            hashMap,
            0,
            modClassCount,
            modClassCount
        );
        packet.sendCheckPacket();
    }

    public int getPlayerCount() {
        return playerCount;
    }


    public HeypixelCheckPacket decodePacket(PacketByteBuf buf) {
        int readInt = buf.readInt();
        var packetFunction = HeypixelCheckPacket.newS2CInstance(readInt);
        if (packetFunction != null) {
//            System.out.println("decodedd: " + readInt);
            return packetFunction.apply(buf);
        }
        throw new UnsupportedOperationException("This packet ( " + readInt + ") does not support in this version.");
    }


    public static char decodeChar(int i) {
        int i2 = i >> 2;
        return (char) ((i2 | 1) - ((1 | ((-i2) - 1)) - ((-i2) - 1)));
    }

    public void onEventTrigger() {
        dumpGCInfo();
    }


    public void sendClientData() {
        try {
            DataBuffer buffer = DataBufferUtils.createBuffer();
            buffer.writeSignedVarInt(0);
            buffer.writeStringWithLength(Heypixel.get().getPlayerUUID());
            buffer.writeObjectData(new Value().setDataFromLong(Heypixel.get().runTime));
            buffer.writeObjectData(new Value().setDataFromLong(System.currentTimeMillis()));
//            System.out.println("send: client data");

            var buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeBytes(buffer.getBytes());

            NetPayload.send(new Identifier(HeypixelCheckPacket.getChannel()), buf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String decodeMessage(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i += 4) {
            String sub = str.substring(i, i + 4);
            int parseInt = Integer.parseInt(sub);
            int parseInt1 = Integer.parseInt(sub.substring(0, 1));
            if (parseInt1 == 1) {
                parseInt = decodeInt(parseInt);
            }
            int result;
            if (parseInt1 == 1) {
                parseInt = decodeKeys[parseInt];
                result = decodeChar(parseInt);
                sb.append((char) result);
            } else {
                result = decodeIntValue(parseInt);
                sb.append(result);
            }
        }

        return sb.toString();
    }


    @NativeObfuscation.Inline
    public static void handleChatMessage(Text component) {
        String string = component.getString();
        if (string.contains("§a[") && string.contains("] §a[") && string.contains("] §r§r")) {
            try {
//                var packet = new ClassesC2SPacket(
//                    -3,
//                    -3,
//                    new StringSet(string),
//                    -3,
//                    -3,
//                    -3);
//
//                packet.sendCheckPacket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void handlePacket(HeypixelCheckPacket heypixelPacket, PacketByteBuf friendlyByteBuf) {
    }


    public void applyToPlayer(HeypixelCheckPacket heypixelPacket) {
        try {
//            System.out.println("apply: " + heypixelPacket.getClass().getName());
            heypixelPacket.manager = this;
            heypixelPacket.handleClientSide(MinecraftClient.getInstance().player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isModPath(Path path) {
        return path.toString().endsWith(getResourcePath(5));
    }


    public void checkStackTrace() {
        if (classesSent) {
            return;
        }
        classesSent = true;
        sendClasses0();
    }


    public String decodeString(String str) {
        return decodeMessage(str);
    }


    public int decodeInt(int i) {
        return Integer.parseInt(Integer.toString(i).substring(1));
    }


    public void dumpGCInfo() {
        uniqueClassNames.add("com.sun.jmx.remote.util");
        uniqueClassNames.add("com.sun.org.apache.xerces");
        uniqueClassNames.add("sun.text.resources.cldr.ext");
        uniqueClassNames.add("com.sun.xml.internal.stream");
        uniqueClassNames.add("org.openjdk.nashorn.internal.scripts");
        uniqueClassNames.add("jdk.internal.net.http.SocketTube$InternalWriteSubscriber$WriteSubscription");
        uniqueClassNames.add("net.minecraftforge.common.loot.CanToolPerformAction$Serializer");
        uniqueClassNames.add("sun.util.resources.cldr.provider");

        var packet = new ClassesC2SPacket(
            -1,
            uniqueClassNames.size(),
            uniqueClassNames,
            13064 + RandomUtils.nextInt(-100, 200),
            loadClassCount,
            loadClassCount
        );

        packet.sendCheckPacket();
        uniqueClassNames.clear();
    }


    public int decodeIntValue(int i) {
        int i2 = i >> 2;
        return (i2 | 1) - ((1 | ((-i2) - 1)) - ((-i2) - 1));
    }


    public String getEncryptKey() {
        return convertBytesToString(encryptionKey);
    }


    @NativeObfuscation.Inline
    public void sendClientDataFull() {
        try {
            loadUserInfo();

            if (userIdInfo == null) {
                NetPayload.pre(() -> {
                    ChatUtils.error("无法获取到网易用户信息。");
                });
                return;
            }

            DataBuffer buffer = DataBufferUtils.createBuffer();
            if (buffer == null) {
                return;
            }
            buffer.writeStringWithLength(Heypixel.get().clientId.toString());
            buffer.writeStringWithLength(Heypixel.get().getPlayerUUID());
            buffer.writeObjectData(new Value().setDataFromLong(Heypixel.get().runTime));
            buffer.writeBoolean(false);

            var mods = new ArrayList<String>();
            mods.add("minecaft");
            mods.add("entityculling");
            mods.add("armourers_workshop");
            mods.add("netease_official");
            mods.add("immediatelyfast");
            mods.add("culllessleaves");
            mods.add("heypixel");
            mods.add("nochatlagforge");
            mods.add("memoryleakfix");
            mods.add("reeses_sodium_options");
            mods.add("forge");
            mods.add("rubidium");
            mods.add("embeddiumplus");
            mods.add("iceberg");
            mods.add("geckolib3");

            buffer.writeObjectData(new Value().setDataFromString(mods.toString()));

            var local = RandomUtils.nextBoolean() ? "D" : "C";
            buffer.writeObjectData(new Value().setDataFromString(local + ":\\MCLDownload\\Game\\.minecraft"));
            buffer.writeObjectData(new Value().setDataFromString(local + ":\\MCLDownload\\ext\\jre-v64-220420\\jdk17"));

            buffer.writeObjectData(cpuInfo);
            buffer.writeObjectData(baseboardSerial);
            buffer.writeObjectData(diskSerials);
            buffer.writeObjectData(networkHardwareInfo);
            buffer.writeObjectData(userIdInfo);
            buffer.writeObjectData(baseboardInfo);
            buffer.writeObjectData(diskStoreInfo);
            buffer.writeObjectData(networkInterfaces);
            buffer.writeObjectData(systemInfo);
            buffer.writeObjectData(neteaseUserListHash);

//            System.out.println("send: client full");

            var id = new Identifier(HeypixelCheckPacket.getChannel());
            var buf = new PacketByteBuf(Unpooled.buffer());

            HeypixelVarUtils.writeUnsignedInt(buf, 1);
            bufferHelper.writeByteArray(buf, buffer.getBytes());

            NetPayload.send(id, buf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void asyncSendClientData() {
        sendClientDataFull();
    }


    public void periodicClientData() {
        if (System.currentTimeMillis() - lastCheckTimestamp > updateInterval * 1000L) {
            sendClientData();
            lastCheckTimestamp = System.currentTimeMillis();
        }
    }


    public void onClientTick() {
        checkStackTrace();
        periodicClientData();
    }


    @NativeObfuscation.Inline
    public String hashStringWithException(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString((b | (-256)) + 256);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @NativeObfuscation.Inline
    public void onServerEvent(S2CEvent s2CEvent) {
        String event = s2CEvent.getEvent();
        JsonObject data = s2CEvent.getData();
        if (event.equals("ServerRPC")) {
            placeholderString1 = data.get("s1").getAsString();
            placeholderString2 = data.get("s2").getAsString();
            placeholderString3 = data.get("s3").getAsString();
        }
        if ("SyncServerInfo".equals(s2CEvent.getEvent())) {
            updateServerInfo(data);
        }
    }

    public void onEntityJoinWorld(World world, Entity entity) {
        try {
            if (!entity.getUuid().equals(mc.player.getUuid())) return;

            Identifier location = world.getDimensionKey().getRegistry();
            sendClientDataFull();
            if (System.currentTimeMillis() > nextSendClasses) {
                nextSendClasses = System.currentTimeMillis() + 1000;
                classesSent = false;
            }
            if (System.currentTimeMillis() > this.lastActionTime + 180000) {
                onEventTrigger();
                this.lastActionTime = System.currentTimeMillis();
            } else {
                if (this.lastResourceLocation == null || location.getPath().equals(this.lastResourceLocation.getPath())) {
                    return;
                }
                this.lastResourceLocation = location;
                if (System.currentTimeMillis() > this.lastActionTime + 60000) {
                    onEventTrigger();
                    this.lastActionTime = System.currentTimeMillis();
                }
            }
        } catch (Exception ex) {

        }
    }

    private final String RANDOM_DISK_;
    private final String BASEBOARD_SERIAL_NUM;


    public void randomNeteaseUsers() {
        ArrayList<IBufferData> arrayList = new ArrayList<>();
        var users = new ArrayList<String>();

        for (int i = 0; i < RandomUtils.nextInt(0, 4); i++) {
            var email = RandomUtils.randomString(10) + "@163.com";
            users.add(email);
        }

        users.forEach((file) -> arrayList.add(DataFactory.ofBuffer(hashStringWithException(file))));
        neteaseUserListHash = new Value().setListData(arrayList);
    }


    public Value randomCpuInfo() {
        return new Value().setDataFromString(
            RandomUtils.random(6, "BEF0") + RandomUtils.randomStringHex(7) + "|" + HardwareList.randomCpu());
    }


    @NativeObfuscation.Inline
    public Value randomNetworkHardware() {
        ArrayList<ObjectData> arrayList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces2 = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces2.hasMoreElements()) {
                byte[] hardwareAddress = networkInterfaces2.nextElement().getHardwareAddress();
                if (hardwareAddress != null) {
                    var temp = "%A%A-%B%B-%C%C-%D%D-%E%E-%F%F";
                    while (temp.contains("%A")) {
                        temp = temp.replaceFirst("%A", RandomUtils.randomStringHex(1));
                    }
                    while (temp.contains("%B")) {
                        temp = temp.replaceFirst("%B", RandomUtils.randomStringHex(1));
                    }
                    while (temp.contains("%C")) {
                        temp = temp.replaceFirst("%C", RandomUtils.randomStringHex(1));
                    }
                    while (temp.contains("%D")) {
                        temp = temp.replaceFirst("%D", RandomUtils.randomStringHex(1));
                    }
                    while (temp.contains("%E")) {
                        temp = temp.replaceFirst("%E", RandomUtils.randomStringHex(1));
                    }
                    while (temp.contains("%F")) {
                        temp = temp.replaceFirst("%F", RandomUtils.randomStringHex(1));
                    }

                    arrayList.add(DataFactory.ofBuffer(temp));
                }
            }
        } catch (SocketException e) {
        }
        return new Value().setListData(arrayList);
    }


    public Value randomBaseboardSerial() {
        return new Value().setDataFromString(BASEBOARD_SERIAL_NUM);
    }


    public Value randomDiskSerials() {
        ArrayList<IBufferData> arrayList = new ArrayList<>();
        arrayList.add(DataFactory.ofBuffer(""));
        arrayList.add(DataFactory.ofBuffer(RANDOM_DISK_));
        arrayList.add(DataFactory.ofBuffer(""));
        arrayList.add(DataFactory.ofBuffer(""));
        arrayList.add(DataFactory.ofBuffer(""));
        return new Value().setListData(arrayList);
    }


    @NativeObfuscation.Inline
    public Value randomNetworkInterfaces() {
        ArrayList<IMapData> networks = new ArrayList<>();
        HashMap<ObjectData, ObjectData> hashMap;

        List<String> networkHs = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(2, 7); i++) {
            var network = HardwareList.randomNetwork();
            while (networkHs.contains(network)) {
                network = HardwareList.randomNetwork();
            }

            networkHs.add(network);
        }

        for (String networkH : networkHs) {
            hashMap = new HashMap<>();
            hashMap.put(DataFactory.ofBuffer("a"), DataFactory.ofBuffer(networkH));
            var mac = RandomUtils.randomStringHexLower(2) + // 1
                ":" + RandomUtils.randomStringHexLower(2) + // 2
                ":" + RandomUtils.randomStringHexLower(2) + // 3
                ":" + RandomUtils.randomStringHexLower(2) + // 4
                ":" + RandomUtils.randomStringHexLower(2) + // 5
                ":" + RandomUtils.randomStringHexLower(2); // 6
            hashMap.put(DataFactory.ofBuffer("b"), DataFactory.ofBuffer(mac));
            networks.add(DataFactory.ofMap(hashMap));
        }

        return new Value().setListData(networks);
    }


    public Value randomDiskInfo() {
        ArrayList<IMapData> disks = new ArrayList<>();
        HashMap<ObjectData, ObjectData> hashMap = new HashMap<>();
        hashMap.put(DataFactory.ofBuffer("a"), DataFactory.ofBuffer(RANDOM_DISK_));
        hashMap.put(DataFactory.ofBuffer("b"), DataFactory.ofBuffer("\\\\\\\\.\\\\PHYSICALDRIVE0"));
        hashMap.put(DataFactory.ofBuffer("c"), DataFactory.ofBuffer(HardwareList.randomDisk() + " (标准磁盘驱动器)"));
        disks.add(DataFactory.ofMap(hashMap));
        return new Value().setListData(disks);
    }


    public Value randomBaseboardInfo() {
        String manufacturer = RandomUtils.randomString(7);

        StringBuilder str = new StringBuilder();
        for (char c : manufacturer.toCharArray()) {
            str.append(RandomUtils.randomStringHex(2));
            str.append(c);
        }

        String model = "unknown";

        String version = "1.0";
        HashMap<ObjectData, ObjectData> hashMap = new HashMap<>();

        hashMap.put(DataFactory.ofBuffer("a"), DataFactory.ofBuffer(str.toString()));
        hashMap.put(DataFactory.ofBuffer("b"), DataFactory.ofBuffer(model));
        hashMap.put(DataFactory.ofBuffer("c"), DataFactory.ofBuffer(BASEBOARD_SERIAL_NUM));
        hashMap.put(DataFactory.ofBuffer("d"), DataFactory.ofBuffer(version));

        return new Value().setMapData(hashMap);
    }


    @NativeObfuscation.Inline
    public Value randomSystemHwid() {
        var uuid = "%A%A%A%A%A%A%A%A-%B%B%B%B-%C%C%C%C-%D%D%D%D-%E%E%E%E%E%E%E%E%E%E%E%E";

        while (uuid.contains("%A")) {
            uuid = uuid.replaceFirst("%A", RandomUtils.randomStringHex(1));
        }

        while (uuid.contains("%B")) {
            uuid = uuid.replaceFirst("%B", RandomUtils.randomStringHex(1));
        }

        while (uuid.contains("%C")) {
            uuid = uuid.replaceFirst("%C", RandomUtils.randomStringHex(1));
        }

        while (uuid.contains("%D")) {
            uuid = uuid.replaceFirst("%D", RandomUtils.randomStringHex(1));
        }

        while (uuid.contains("%E")) {
            uuid = uuid.replaceFirst("%E", RandomUtils.randomStringHex(1));
        }

        return new Value().setDataFromString(uuid);
    }
}
