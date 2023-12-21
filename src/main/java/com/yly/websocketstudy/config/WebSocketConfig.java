package com.yly.websocketstudy.config;

import com.yly.websocketstudy.Service.EquipmentService;
import com.yly.websocketstudy.Service.InfoService;
import com.yly.websocketstudy.common.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/20 14:45
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setEquipmentService(EquipmentService equipmentService) {
        WebSocket.equipmentService = equipmentService;
    }

    @Autowired
    public void setInfoService(InfoService infoService) {
        WebSocket.infoService = infoService;
    }

    @Autowired
    public void setHandlerList(List<WebSocketHandler> handlerList) {
        WebSocket.handlerList = handlerList;
    }
}
