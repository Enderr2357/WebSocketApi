package com.yly.websocketstudy.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yly.websocketstudy.Service.InfoService;
import com.yly.websocketstudy.common.WebSocketHandler;
import com.yly.websocketstudy.config.WebSocket;
import com.yly.websocketstudy.entity.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/21 0:36
 **/
@Component
@Slf4j
public class editStatusHandler implements WebSocketHandler {
    private static String message_Type = "edit";

    @Autowired
    private InfoService infoService;

    @Override
    public String getMessageType() {
        return message_Type;
    }

    @Override
    public void handle(String data,WebSocket webSocket) {
        ObjectMapper objectMapper =new ObjectMapper();
        Info info = new Info();
        try
        {
            info = objectMapper.readValue(data, Info.class);
            infoService.updateById(info);
            WebSocket.sendMessage("更改成功,数据为:"+info.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("Info对象为:{}",info);
    }
}
