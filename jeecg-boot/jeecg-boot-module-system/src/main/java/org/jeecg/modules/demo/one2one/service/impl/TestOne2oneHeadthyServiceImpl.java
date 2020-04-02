package org.jeecg.modules.demo.one2one.service.impl;

import org.jeecg.modules.demo.one2one.entity.TestOne2oneHeadthy;
import org.jeecg.modules.demo.one2one.mapper.TestOne2oneHeadthyMapper;
import org.jeecg.modules.demo.one2one.service.ITestOne2oneHeadthyService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 学生健康表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Service
public class TestOne2oneHeadthyServiceImpl extends ServiceImpl<TestOne2oneHeadthyMapper, TestOne2oneHeadthy> implements ITestOne2oneHeadthyService {
	
	@Autowired
	private TestOne2oneHeadthyMapper testOne2oneHeadthyMapper;
	
	@Override
	public List<TestOne2oneHeadthy> selectByMainId(String mainId) {
		return testOne2oneHeadthyMapper.selectByMainId(mainId);
	}
}
