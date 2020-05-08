package org.jeecg.modules.demo.user.service.impl;

import org.jeecg.modules.demo.user.entity.UserReadInfo;
import org.jeecg.modules.demo.user.mapper.UserReadInfoMapper;
import org.jeecg.modules.demo.user.service.IUserReadInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户阅读时长
 * @Author: jeecg-boot
 * @Date:   2020-05-08
 * @Version: V1.0
 */
@Service
public class UserReadInfoServiceImpl extends ServiceImpl<UserReadInfoMapper, UserReadInfo> implements IUserReadInfoService {

}
