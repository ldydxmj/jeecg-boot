package org.jeecg.modules.demo.one2onestudent.vo;

import java.util.List;
import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneStudent;
import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneBookbage;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Data
@ApiModel(value="test_one2one_studentPage对象", description="学生表")
public class TestOne2oneStudentPage {
	
	/**id*/
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
	/**性别*/
	@Excel(name = "性别", width = 15)
	@ApiModelProperty(value = "性别")
	private java.lang.String sex;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
	@ApiModelProperty(value = "用户名")
	private java.lang.String name;
	/**班级*/
	@Excel(name = "班级", width = 15)
	@ApiModelProperty(value = "班级")
	private java.lang.String mclass;
	/**年级*/
	@Excel(name = "年级", width = 15)
	@ApiModelProperty(value = "年级")
	private java.lang.String gruade;
	/**班级中的学号*/
	@Excel(name = "班级中的学号", width = 15)
	@ApiModelProperty(value = "班级中的学号")
	private java.lang.Integer studentNo;
	
	@ExcelCollection(name="书包表")
	@ApiModelProperty(value = "书包表")
	private List<TestOne2oneBookbage> testOne2oneBookbageList;
	
}
