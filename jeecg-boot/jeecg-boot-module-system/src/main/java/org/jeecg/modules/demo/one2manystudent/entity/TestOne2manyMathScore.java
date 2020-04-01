package org.jeecg.modules.demo.one2manystudent.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 学生的数学成绩
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@ApiModel(value="test_one2many_main对象", description="测试一对多学生")
@Data
@TableName("test_one2many_math_score")
public class TestOne2manyMathScore implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**数学成绩*/
	@Excel(name = "数学成绩", width = 15)
	@ApiModelProperty(value = "数学成绩")
	private java.lang.Integer score;
	/**主人的id*/
	@ApiModelProperty(value = "主人的id")
	private java.lang.String masterId;
	/**是否优等生*/
	@Excel(name = "是否优等生", width = 15)
	@ApiModelProperty(value = "是否优等生")
	private java.lang.Integer isGood;
	/**语文成绩是否好*/
	@Excel(name = "语文成绩是否好", width = 15)
	@ApiModelProperty(value = "语文成绩是否好")
	private java.lang.Integer chineseIsGood;
}
