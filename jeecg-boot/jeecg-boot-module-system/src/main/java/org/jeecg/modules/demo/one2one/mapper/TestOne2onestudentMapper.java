package org.jeecg.modules.demo.one2one.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.one2one.entity.TestOne2onestudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.one2one.model.TestOne2onestudentFullModel;
import org.jeecg.modules.system.model.AnnouncementSendModel;
import org.jeecg.modules.system.model.TreeSelectModel;

/**
 * @Description: 一对一学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface TestOne2onestudentMapper extends BaseMapper<TestOne2onestudent> {
    public List<TestOne2onestudentFullModel> listAll();



    public Page<TestOne2onestudentFullModel> listAllPage(Page<TestOne2onestudentFullModel> page, @Param("testOne2onestudentFullModel") QueryWrapper<TestOne2onestudentFullModel> queryWrapper);
}
