package com.yly.websocketstudy.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.websocketstudy.Service.EquipmentService;
import com.yly.websocketstudy.Service.InfoService;
import com.yly.websocketstudy.entity.Info;
import com.yly.websocketstudy.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Ender2357
 * @Description: TODO
 * @DateTime: 2023/12/20 16:11
 **/
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private InfoMapper infoMapper;

    @Override
    public Info getInfoByRoomId(Integer roomId) {
        LambdaQueryWrapper<Info> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Info::getRoomId, roomId);
        Info info = infoMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNull(info)) {
            throw new RuntimeException();
        } else {
            return info;
        }
    }
}
