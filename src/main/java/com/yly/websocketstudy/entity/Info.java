package com.yly.websocketstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/20 16:09
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    private Integer id;

    private Integer roomId;

    private Integer patientId;

    private Integer status;
}
