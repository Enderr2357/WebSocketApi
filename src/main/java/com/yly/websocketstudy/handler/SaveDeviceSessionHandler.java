package com.yly.websocketstudy.handler;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yly.websocketstudy.Service.EquipmentService;
import com.yly.websocketstudy.common.WebSocketHandler;
import com.yly.websocketstudy.config.WebSocket;
import com.yly.websocketstudy.entity.Equipment;
import com.yly.websocketstudy.entity.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.yly.websocketstudy.common.DeviceSessionManager.deviceSessions;
@Slf4j
@Component
public class SaveDeviceSessionHandler implements WebSocketHandler {
    private static String message_Type = "save";

    @Autowired
    private EquipmentService equipmentService;

    @Override
    public String getMessageType() {
        return message_Type;
    }

    @Override
    public void handle(String data,WebSocket webSocket) {
        //保存当前会话的设备端id
        ObjectMapper objectMapper =new ObjectMapper();
        Equipment equipment =new Equipment();
        try {
            equipment= objectMapper.readValue(data, Equipment.class);
            Equipment equipment1 = equipmentService.getEquipment(equipment.getEquipmentId());
            if (ObjectUtils.isNull(equipment1)){
                equipmentService.insertEquipment(equipment);
                deviceSessions.put(equipment.getEquipmentId(),webSocket);
                log.info("设备已绑定当前会话:{},会话为:{}",equipment.getEquipmentId(),webSocket);
            }else {
                deviceSessions.put(equipment1.getEquipmentId(),webSocket);
                log.info("设备已绑定当前会话:{},会话为:{}",equipment1.getEquipmentId(),webSocket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
