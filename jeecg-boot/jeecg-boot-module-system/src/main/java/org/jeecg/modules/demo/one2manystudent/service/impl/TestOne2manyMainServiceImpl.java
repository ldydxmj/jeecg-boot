package org.jeecg.modules.demo.one2manystudent.service.impl;

import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMain;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyBooks;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMathScore;
import org.jeecg.modules.demo.one2manystudent.mapper.TestOne2manyBooksMapper;
import org.jeecg.modules.demo.one2manystudent.mapper.TestOne2manyMathScoreMapper;
import org.jeecg.modules.demo.one2manystudent.mapper.TestOne2manyMainMapper;
import org.jeecg.modules.demo.one2manystudent.service.ITestOne2manyMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 测试一对多学生
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Service
public class TestOne2manyMainServiceImpl extends ServiceImpl<TestOne2manyMainMapper, TestOne2manyMain> implements ITestOne2manyMainService {

	@Autowired
	private TestOne2manyMainMapper testOne2manyMainMapper;
	@Autowired
	private TestOne2manyBooksMapper testOne2manyBooksMapper;
	@Autowired
	private TestOne2manyMathScoreMapper testOne2manyMathScoreMapper;
	
	@Override
	@Transactional
	public void saveMain(TestOne2manyMain testOne2manyMain, List<TestOne2manyBooks> testOne2manyBooksList,List<TestOne2manyMathScore> testOne2manyMathScoreList) {
		testOne2manyMainMapper.insert(testOne2manyMain);
		if(testOne2manyBooksList!=null && testOne2manyBooksList.size()>0) {
			for(TestOne2manyBooks entity:testOne2manyBooksList) {
				//外键设置
				entity.setBookMasterId(testOne2manyMain.getId());
				testOne2manyBooksMapper.insert(entity);
			}
		}
		if(testOne2manyMathScoreList!=null && testOne2manyMathScoreList.size()>0) {
			for(TestOne2manyMathScore entity:testOne2manyMathScoreList) {
				//外键设置
				entity.setMasterId(testOne2manyMain.getId());
				testOne2manyMathScoreMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(TestOne2manyMain testOne2manyMain,List<TestOne2manyBooks> testOne2manyBooksList,List<TestOne2manyMathScore> testOne2manyMathScoreList) {
		testOne2manyMainMapper.updateById(testOne2manyMain);
		
		//1.先删除子表数据
		testOne2manyBooksMapper.deleteByMainId(testOne2manyMain.getId());
		testOne2manyMathScoreMapper.deleteByMainId(testOne2manyMain.getId());
		
		//2.子表数据重新插入
		if(testOne2manyBooksList!=null && testOne2manyBooksList.size()>0) {
			for(TestOne2manyBooks entity:testOne2manyBooksList) {
				//外键设置
				entity.setBookMasterId(testOne2manyMain.getId());
				testOne2manyBooksMapper.insert(entity);
			}
		}
		if(testOne2manyMathScoreList!=null && testOne2manyMathScoreList.size()>0) {
			for(TestOne2manyMathScore entity:testOne2manyMathScoreList) {
				//外键设置
				entity.setMasterId(testOne2manyMain.getId());
				testOne2manyMathScoreMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		testOne2manyBooksMapper.deleteByMainId(id);
		testOne2manyMathScoreMapper.deleteByMainId(id);
		testOne2manyMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testOne2manyBooksMapper.deleteByMainId(id.toString());
			testOne2manyMathScoreMapper.deleteByMainId(id.toString());
			testOne2manyMainMapper.deleteById(id);
		}
	}
	
}
