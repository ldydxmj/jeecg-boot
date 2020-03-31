package org.jeecg.modules.demo.one2onestudent.service.impl;

import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneBookbage;
import org.jeecg.modules.demo.one2onestudent.mapper.TestOne2oneBookbageMapper;
import org.jeecg.modules.demo.one2onestudent.service.ITestOne2oneBookbageService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 书包表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class TestOne2oneBookbageServiceImpl extends ServiceImpl<TestOne2oneBookbageMapper, TestOne2oneBookbage> implements ITestOne2oneBookbageService {
	
	@Autowired
	private TestOne2oneBookbageMapper testOne2oneBookbageMapper;
	
	@Override
	public List<TestOne2oneBookbage> selectByMainId(String mainId) {
		return testOne2oneBookbageMapper.selectByMainId(mainId);
	}
}
