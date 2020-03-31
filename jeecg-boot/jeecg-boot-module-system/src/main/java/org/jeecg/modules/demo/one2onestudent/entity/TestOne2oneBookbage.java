package org.jeecg.modules.demo.one2onestudent.entity;

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
 * @Description: 书包表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@ApiModel(value="test_one2one_student对象", description="学生表")
@Data
@TableName("test_one2one_bookbage")
public class TestOne2oneBookbage implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**书包颜色*/
	@Excel(name = "书包颜色", width = 15)
	@ApiModelProperty(value = "书包颜色")
	private java.lang.String color;
	/**书包大小*/
	@Excel(name = "书包大小", width = 15)
	@ApiModelProperty(value = "书包大小")
	private java.lang.String size;
	/**班级*/
	@ApiModelProperty(value = "班级")
	private java.lang.String studentId;
	/**是否好看*/
	@Excel(name = "是否好看", width = 15)
	@ApiModelProperty(value = "是否好看")
	private java.lang.Integer isBeautiful;
	/**使用时长（年）*/
	@Excel(name = "使用时长（年）", width = 15)
	@ApiModelProperty(value = "使用时长（年）")
	private java.lang.Integer usedTime;
}
