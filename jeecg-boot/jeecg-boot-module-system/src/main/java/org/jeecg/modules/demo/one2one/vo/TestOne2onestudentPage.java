package org.jeecg.modules.demo.one2one.vo;

import java.util.List;
import org.jeecg.modules.demo.one2one.entity.TestOne2onestudent;
import org.jeecg.modules.demo.one2one.entity.TestOne2oneHeadthy;
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
 * @Description: 一对一学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Data
@ApiModel(value="test_one2onestudentPage对象", description="一对一学生表")
public class TestOne2onestudentPage {
	
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
	/**学生名字*/
	@Excel(name = "学生名字", width = 15)
	@ApiModelProperty(value = "学生名字")
	private java.lang.String name;
	/**学号*/
	@Excel(name = "学号", width = 15)
	@ApiModelProperty(value = "学号")
	private java.lang.Integer xueNo;
	
	@ExcelCollection(name="学生健康表")
	@ApiModelProperty(value = "学生健康表")
	private List<TestOne2oneHeadthy> testOne2oneHeadthyList;
	
}
