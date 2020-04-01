package org.jeecg.modules.demo.one2manystudent.vo;

import java.util.List;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMain;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyBooks;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMathScore;
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
 * @Description: 测试一对多学生
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Data
@ApiModel(value="test_one2many_mainPage对象", description="测试一对多学生")
public class TestOne2manyMainPage {
	
	/**主键*/
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
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**名字*/
	@Excel(name = "名字", width = 15)
	@ApiModelProperty(value = "名字")
	private java.lang.String name;
	/**学号*/
	@Excel(name = "学号", width = 15)
	@ApiModelProperty(value = "学号")
	private java.lang.String xueNo;
	
	@ExcelCollection(name="学生的书本")
	@ApiModelProperty(value = "学生的书本")
	private List<TestOne2manyBooks> testOne2manyBooksList;
	@ExcelCollection(name="学生的数学成绩")
	@ApiModelProperty(value = "学生的数学成绩")
	private List<TestOne2manyMathScore> testOne2manyMathScoreList;
	
}
