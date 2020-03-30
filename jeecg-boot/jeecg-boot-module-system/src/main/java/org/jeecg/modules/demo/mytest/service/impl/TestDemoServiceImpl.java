package org.jeecg.modules.demo.mytest.service.impl;

import org.jeecg.modules.demo.mytest.entity.TestDemo;
import org.jeecg.modules.demo.mytest.mapper.TestDemoMapper;
import org.jeecg.modules.demo.mytest.service.ITestDemoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 测试用户表
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Service
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements ITestDemoService {

}
