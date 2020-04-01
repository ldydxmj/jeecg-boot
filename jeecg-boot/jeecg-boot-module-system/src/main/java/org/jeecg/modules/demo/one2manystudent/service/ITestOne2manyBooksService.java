package org.jeecg.modules.demo.one2manystudent.service;

import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyBooks;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 学生的书本
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface ITestOne2manyBooksService extends IService<TestOne2manyBooks> {

	public List<TestOne2manyBooks> selectByMainId(String mainId);
}
