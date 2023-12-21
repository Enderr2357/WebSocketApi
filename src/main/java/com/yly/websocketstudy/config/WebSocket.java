package com.yly.websocketstudy.config;

import com.alibaba.fastjson.JSONObject;
import com.yly.websocketstudy.Service.EquipmentService;
import com.yly.websocketstudy.Service.InfoService;
import com.yly.websocketstudy.common.WebSocketHandler;
import com.yly.websocketstudy.entity.MapSession;
import com.yly.websocketstudy.entity.WebSocketMessage;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.yly.websocketstudy.common.DeviceSessionManager.deviceSessions;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/20 15:01
 **/
//注册成组件
@Component
//定义websocket服务器端，它的功能主要是将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问URL地址
@ServerEndpoint("/websocket")
//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;可以直接调用log.info
@Slf4j
@Controller
public class WebSocket {
    public static List<WebSocketHandler> handlerList;
    public static EquipmentService equipmentService;

    public static InfoService infoService;
    //实例一个session，这个session是websocket的session
    private Session session;


    //存放websocket的集合（本次demo不会用到，聊天室的demo会用到）
    public static final CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    //前端请求时一个websocket时
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【websocket消息】有新的连接:{}",session);
    }

    //前端关闭时一个websocket时
    @OnClose
    public void onClose() {
        webSockets.remove(this);
        log.info("【websocket消息】连接断开, 总数:{}", deviceSessions.size());
    }

    //前端向后端发送消息
    @OnMessage
    public void onMessage(String jsonMessage) {
        WebSocketMessage message = JSONObject.parseObject(jsonMessage, WebSocketMessage.class);
        String type = message.getType();
        String data = message.getData();
        for (WebSocketHandler handler : handlerList) {
            if (handler.getMessageType().equals(type)) {
                handler.handle(data,this);
            }
        }
        log.info("当前连接数量{}", deviceSessions.size());
    }

    //新增一个方法用于主动向客户端发送消息
    public static void sendMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            log.info("【websocket消息】广播消息, message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}