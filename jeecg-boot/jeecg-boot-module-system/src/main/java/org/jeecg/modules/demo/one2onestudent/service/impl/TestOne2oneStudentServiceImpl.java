package org.jeecg.modules.demo.one2onestudent.service.impl;

import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneStudent;
import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneBookbage;
import org.jeecg.modules.demo.one2onestudent.mapper.TestOne2oneBookbageMapper;
import org.jeecg.modules.demo.one2onestudent.mapper.TestOne2oneStudentMapper;
import org.jeecg.modules.demo.one2onestudent.service.ITestOne2oneStudentService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Service
public class TestOne2oneStudentServiceImpl extends ServiceImpl<TestOne2oneStudentMapper, TestOne2oneStudent> implements ITestOne2oneStudentService {

	@Autowired
	private TestOne2oneStudentMapper testOne2oneStudentMapper;
	@Autowired
	private TestOne2oneBookbageMapper testOne2oneBookbageMapper;
	
	@Override
	@Transactional
	public void saveMain(TestOne2oneStudent testOne2oneStudent, List<TestOne2oneBookbage> testOne2oneBookbageList) {
		testOne2oneStudentMapper.insert(testOne2oneStudent);
		if(testOne2oneBookbageList!=null && testOne2oneBookbageList.size()>0) {
			for(TestOne2oneBookbage entity:testOne2oneBookbageList) {
				//外键设置
				entity.setStudentId(testOne2oneStudent.getId());
				testOne2oneBookbageMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(TestOne2oneStudent testOne2oneStudent,List<TestOne2oneBookbage> testOne2oneBookbageList) {
		testOne2oneStudentMapper.updateById(testOne2oneStudent);
		
		//1.先删除子表数据
		testOne2oneBookbageMapper.deleteByMainId(testOne2oneStudent.getId());
		
		//2.子表数据重新插入
		if(testOne2oneBookbageList!=null && testOne2oneBookbageList.size()>0) {
			for(TestOne2oneBookbage entity:testOne2oneBookbageList) {
				//外键设置
				entity.setStudentId(testOne2oneStudent.getId());
				testOne2oneBookbageMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		testOne2oneBookbageMapper.deleteByMainId(id);
		testOne2oneStudentMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testOne2oneBookbageMapper.deleteByMainId(id.toString());
			testOne2oneStudentMapper.deleteById(id);
		}
	}
	
}
