package com.yly.websocketstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/20 16:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    private Integer id;

    private String equipmentId;

    private Integer roomId;
}
