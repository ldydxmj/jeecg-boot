package org.jeecg.modules.demo.one2manystudent.mapper;

import java.util.List;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMathScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 学生的数学成绩
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface TestOne2manyMathScoreMapper extends BaseMapper<TestOne2manyMathScore> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TestOne2manyMathScore> selectByMainId(@Param("mainId") String mainId);
}
