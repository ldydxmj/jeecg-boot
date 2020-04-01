package org.jeecg.modules.demo.one2manystudent.service;

import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMathScore;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 学生的数学成绩
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface ITestOne2manyMathScoreService extends IService<TestOne2manyMathScore> {

	public List<TestOne2manyMathScore> selectByMainId(String mainId);
}
