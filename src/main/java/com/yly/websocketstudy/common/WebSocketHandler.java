package com.yly.websocketstudy.common;

import com.yly.websocketstudy.config.WebSocket;

public interface WebSocketHandler {
    String getMessageType();

    void handle(String data, WebSocket webSocket);
}
