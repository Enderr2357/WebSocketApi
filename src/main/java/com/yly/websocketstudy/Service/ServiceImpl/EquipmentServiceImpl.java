package com.yly.websocketstudy.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.websocketstudy.Service.EquipmentService;
import com.yly.websocketstudy.entity.Equipment;
import com.yly.websocketstudy.mapper.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/20 16:11
 **/
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Equipment getEquipment(String equipmentId) {
        LambdaQueryWrapper<Equipment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Equipment::getEquipmentId, equipmentId);
        Equipment equipment = equipmentMapper.selectOne(queryWrapper);

            return equipment;
    }

    @Override
    public int insertEquipment(Equipment equipment) {
        return equipmentMapper.insert(equipment);
    }

    @Override
    public Equipment getEquipmentByRoomId(Integer roomId) {
        LambdaQueryWrapper<Equipment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Equipment::getRoomId, roomId);
        Equipment equipment = equipmentMapper.selectOne(queryWrapper);
        return equipment;
    }


}
