
package org.jeecg.modules.demo.one2one.model;

        import com.baomidou.mybatisplus.annotation.IdType;
        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableName;
        import com.fasterxml.jackson.annotation.JsonFormat;
        import io.swagger.annotations.ApiModel;
        import io.swagger.annotations.ApiModelProperty;
        import lombok.Data;
        import org.jeecgframework.poi.excel.annotation.Excel;
        import org.springframework.format.annotation.DateTimeFormat;

        import java.io.Serializable;
        import java.util.Date;

/**
 * @Description: 一对一学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@ApiModel(value="test_one2onestudent对象", description="一对一学生表")
@Data
@TableName("test_one2onestudent")
public class TestOne2onestudentFullModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private String id;
    /**创建人*/
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**创建日期*/
    @Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    /**更新人*/
    @Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**更新日期*/
    @Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
    /**学生名字*/
    @Excel(name = "学生名字", width = 15)
    @ApiModelProperty(value = "学生名字")
    private String name;
    /**学号*/
    @Excel(name = "学号", width = 15)
    @ApiModelProperty(value = "学号")
    private Integer xueNo;
    /**是否感冒*/
    @Excel(name = "是否感冒", width = 15)
    @ApiModelProperty(value = "是否感冒")
    private java.lang.Integer hasCool;
    /**年龄*/
    @Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private java.lang.Double age;
    /**上次打针时间*/
    @Excel(name = "上次打针时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上次打针时间")
    private java.util.Date lastDaZhen;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String mark;
}
