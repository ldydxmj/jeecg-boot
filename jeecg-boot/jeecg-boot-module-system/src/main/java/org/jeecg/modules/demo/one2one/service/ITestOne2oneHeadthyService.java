package org.jeecg.modules.demo.one2one.service;

import org.jeecg.modules.demo.one2one.entity.TestOne2oneHeadthy;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 学生健康表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface ITestOne2oneHeadthyService extends IService<TestOne2oneHeadthy> {

	public List<TestOne2oneHeadthy> selectByMainId(String mainId);
}
