package com.yly.websocketstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/21 0:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMessage {
    private String type;
    private String data;
}
