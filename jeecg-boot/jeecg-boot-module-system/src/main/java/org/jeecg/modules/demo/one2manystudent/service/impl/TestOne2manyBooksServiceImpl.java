package org.jeecg.modules.demo.one2manystudent.service.impl;

import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyBooks;
import org.jeecg.modules.demo.one2manystudent.mapper.TestOne2manyBooksMapper;
import org.jeecg.modules.demo.one2manystudent.service.ITestOne2manyBooksService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 学生的书本
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Service
public class TestOne2manyBooksServiceImpl extends ServiceImpl<TestOne2manyBooksMapper, TestOne2manyBooks> implements ITestOne2manyBooksService {
	
	@Autowired
	private TestOne2manyBooksMapper testOne2manyBooksMapper;
	
	@Override
	public List<TestOne2manyBooks> selectByMainId(String mainId) {
		return testOne2manyBooksMapper.selectByMainId(mainId);
	}
}
