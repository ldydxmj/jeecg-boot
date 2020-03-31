package org.jeecg.modules.demo.one2onestudent.service;

import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneBookbage;
import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 学生表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface ITestOne2oneStudentService extends IService<TestOne2oneStudent> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TestOne2oneStudent testOne2oneStudent,List<TestOne2oneBookbage> testOne2oneBookbageList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TestOne2oneStudent testOne2oneStudent,List<TestOne2oneBookbage> testOne2oneBookbageList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
