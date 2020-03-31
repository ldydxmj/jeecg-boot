package org.jeecg.modules.demo.one2onestudent.mapper;

import java.util.List;
import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneBookbage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 书包表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface TestOne2oneBookbageMapper extends BaseMapper<TestOne2oneBookbage> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TestOne2oneBookbage> selectByMainId(@Param("mainId") String mainId);
}
