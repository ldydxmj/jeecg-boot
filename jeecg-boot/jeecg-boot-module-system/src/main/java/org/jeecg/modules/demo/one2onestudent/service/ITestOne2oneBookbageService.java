package org.jeecg.modules.demo.one2onestudent.service;

import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneBookbage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 书包表
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface ITestOne2oneBookbageService extends IService<TestOne2oneBookbage> {

	public List<TestOne2oneBookbage> selectByMainId(String mainId);
}
