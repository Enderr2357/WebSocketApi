package com.yly.websocketstudy.entity;

import com.yly.websocketstudy.config.WebSocket;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapSession {

    private String sessionId;
    private WebSocket webSocket;

}
