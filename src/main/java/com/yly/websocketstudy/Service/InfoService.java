package com.yly.websocketstudy.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.websocketstudy.entity.Info;

public interface InfoService extends IService<Info> {
    public Info getInfoByRoomId(Integer roomId);
}
