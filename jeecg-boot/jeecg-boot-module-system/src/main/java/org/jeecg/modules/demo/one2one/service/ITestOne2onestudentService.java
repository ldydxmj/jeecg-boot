package org.jeecg.modules.demo.one2one.service;

import org.jeecg.modules.demo.one2one.entity.TestOne2oneHeadthy;
import org.jeecg.modules.demo.one2one.entity.TestOne2onestudent;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 一对一学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface ITestOne2onestudentService extends IService<TestOne2onestudent> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TestOne2onestudent testOne2onestudent,List<TestOne2oneHeadthy> testOne2oneHeadthyList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TestOne2onestudent testOne2onestudent,List<TestOne2oneHeadthy> testOne2oneHeadthyList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
