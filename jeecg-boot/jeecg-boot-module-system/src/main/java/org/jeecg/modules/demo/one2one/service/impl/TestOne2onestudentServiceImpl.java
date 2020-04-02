package org.jeecg.modules.demo.one2one.service.impl;

import org.jeecg.modules.demo.one2one.entity.TestOne2onestudent;
import org.jeecg.modules.demo.one2one.entity.TestOne2oneHeadthy;
import org.jeecg.modules.demo.one2one.mapper.TestOne2oneHeadthyMapper;
import org.jeecg.modules.demo.one2one.mapper.TestOne2onestudentMapper;
import org.jeecg.modules.demo.one2one.service.ITestOne2onestudentService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 一对一学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Service
public class TestOne2onestudentServiceImpl extends ServiceImpl<TestOne2onestudentMapper, TestOne2onestudent> implements ITestOne2onestudentService {

	@Autowired
	private TestOne2onestudentMapper testOne2onestudentMapper;
	@Autowired
	private TestOne2oneHeadthyMapper testOne2oneHeadthyMapper;
	
	@Override
	@Transactional
	public void saveMain(TestOne2onestudent testOne2onestudent, List<TestOne2oneHeadthy> testOne2oneHeadthyList) {
		testOne2onestudentMapper.insert(testOne2onestudent);
		if(testOne2oneHeadthyList!=null && testOne2oneHeadthyList.size()>0) {
			for(TestOne2oneHeadthy entity:testOne2oneHeadthyList) {
				//外键设置
				entity.setStudentId(testOne2onestudent.getId());
				testOne2oneHeadthyMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(TestOne2onestudent testOne2onestudent,List<TestOne2oneHeadthy> testOne2oneHeadthyList) {
		testOne2onestudentMapper.updateById(testOne2onestudent);
		
		//1.先删除子表数据
		testOne2oneHeadthyMapper.deleteByMainId(testOne2onestudent.getId());
		
		//2.子表数据重新插入
		if(testOne2oneHeadthyList!=null && testOne2oneHeadthyList.size()>0) {
			for(TestOne2oneHeadthy entity:testOne2oneHeadthyList) {
				//外键设置
				entity.setStudentId(testOne2onestudent.getId());
				testOne2oneHeadthyMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		testOne2oneHeadthyMapper.deleteByMainId(id);
		testOne2onestudentMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testOne2oneHeadthyMapper.deleteByMainId(id.toString());
			testOne2onestudentMapper.deleteById(id);
		}
	}
	
}
