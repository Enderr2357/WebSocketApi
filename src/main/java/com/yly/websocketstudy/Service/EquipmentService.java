package com.yly.websocketstudy.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.websocketstudy.entity.Equipment;

public interface EquipmentService extends IService<Equipment> {

    public Equipment getEquipment(String equipmentId);

    public int insertEquipment(Equipment equipment);

    public Equipment getEquipmentByRoomId(Integer roomId);
}
