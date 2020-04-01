package org.jeecg.modules.demo.one2manystudent.service.impl;

import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMathScore;
import org.jeecg.modules.demo.one2manystudent.mapper.TestOne2manyMathScoreMapper;
import org.jeecg.modules.demo.one2manystudent.service.ITestOne2manyMathScoreService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 学生的数学成绩
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Service
public class TestOne2manyMathScoreServiceImpl extends ServiceImpl<TestOne2manyMathScoreMapper, TestOne2manyMathScore> implements ITestOne2manyMathScoreService {
	
	@Autowired
	private TestOne2manyMathScoreMapper testOne2manyMathScoreMapper;
	
	@Override
	public List<TestOne2manyMathScore> selectByMainId(String mainId) {
		return testOne2manyMathScoreMapper.selectByMainId(mainId);
	}
}
