package org.jeecg.modules.demo.one2one.mapper;

import java.util.List;
import org.jeecg.modules.demo.one2one.entity.TestOne2oneHeadthy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 学生健康表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface TestOne2oneHeadthyMapper extends BaseMapper<TestOne2oneHeadthy> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TestOne2oneHeadthy> selectByMainId(@Param("mainId") String mainId);
}
