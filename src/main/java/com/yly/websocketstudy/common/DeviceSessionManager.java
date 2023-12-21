package com.yly.websocketstudy.common;

import com.yly.websocketstudy.config.WebSocket;
import jakarta.websocket.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeviceSessionManager {
    public static final Map<String, WebSocket> deviceSessions= new ConcurrentHashMap<>();
    public static void addDeviceSession(String deviceId, WebSocket webSocket) {
        deviceSessions.put(deviceId, webSocket);
    }

    public static void removeDeviceSession(String deviceId) {
        deviceSessions.remove(deviceId);
    }

    public static WebSocket getSessionByDeviceId(String deviceId) {
        return deviceSessions.get(deviceId);
    }
}
