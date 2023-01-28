package com.kevin.epacms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kevin.epacms.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Data
@TableName("t_record")
@ApiModel(value = "Record对象", description = "")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("uid")
    private Integer uid;

    @ApiModelProperty("位置")
    @TableField("position")
    private String position;

    @ApiModelProperty("体温")
    @TableField("temperature")
    private Object temperature;

    @ApiModelProperty("是否咳嗽")
    @TableField("cough")
    private Integer cough;

    @ApiModelProperty("是否发烧")
    @TableField("fever")
    private Integer fever;

    @ApiModelProperty("打卡时间")
    @TableField("time")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime time;

    @ApiModelProperty("关联用户")
    @TableField(exist = false)
    private User user;

    @ApiModelProperty("每月的打卡数")
    @TableField(exist = false)
    private Long total;

    @ApiModelProperty("日期")
    @TableField(exist = false)
    private String date;
}
