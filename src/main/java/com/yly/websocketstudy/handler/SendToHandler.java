package com.yly.websocketstudy.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yly.websocketstudy.Service.EquipmentService;
import com.yly.websocketstudy.Service.InfoService;
import com.yly.websocketstudy.common.WebSocketHandler;
import com.yly.websocketstudy.config.WebSocket;
import com.yly.websocketstudy.entity.Equipment;
import com.yly.websocketstudy.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.yly.websocketstudy.common.DeviceSessionManager.deviceSessions;
@Component
public class SendToHandler implements WebSocketHandler {
    private static String message_Type = "send";

    @Autowired
    private InfoService infoService;

    @Autowired
    private EquipmentService equipmentService;
    @Override
    public String getMessageType() {
        return message_Type;
    }

    @Override
    public void handle(String data, WebSocket webSocket) {
        ObjectMapper objectMapper = new ObjectMapper();
        Info info =new Info();
        try {
            info=objectMapper.readValue(data, Info.class);
            infoService.updateById(info);
            Info sendInfo = infoService.getById(info.getId());
            Equipment equipment = equipmentService.getEquipmentByRoomId(sendInfo.getRoomId());
            WebSocket webSocket1 = deviceSessions.get(equipment.getId());
            webSocket1.sendMessage(objectMapper.writeValueAsString(sendInfo));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
