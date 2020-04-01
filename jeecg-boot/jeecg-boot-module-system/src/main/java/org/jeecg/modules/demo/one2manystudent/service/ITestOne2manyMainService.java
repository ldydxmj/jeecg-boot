package org.jeecg.modules.demo.one2manystudent.service;

import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyBooks;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMathScore;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 测试一对多学生
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface ITestOne2manyMainService extends IService<TestOne2manyMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TestOne2manyMain testOne2manyMain,List<TestOne2manyBooks> testOne2manyBooksList,List<TestOne2manyMathScore> testOne2manyMathScoreList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TestOne2manyMain testOne2manyMain,List<TestOne2manyBooks> testOne2manyBooksList,List<TestOne2manyMathScore> testOne2manyMathScoreList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
