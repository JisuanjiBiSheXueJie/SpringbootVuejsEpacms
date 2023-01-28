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
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Data
@TableName("t_leave")
@ApiModel(value = "Leave对象", description = "")
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("uid")
    private Integer uid;

    @ApiModelProperty("离校原因")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("行程轨迹")
    @TableField("trajectory")
    private String trajectory;

    @ApiModelProperty("审批状态")
    @TableField("status")
    private Short status;

    @ApiModelProperty("离校时间")
    @TableField("leave_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime leaveTime;

    @ApiModelProperty("返回时间")
    @TableField("return_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime returnTime;

    @ApiModelProperty("申请时间")
    @TableField("apply_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime applyTime;

    @ApiModelProperty("关联用户")
    @TableField(exist = false)
    private User user;

    @ApiModelProperty("每月的离校数")
    @TableField(exist = false)
    private Long total;

    @ApiModelProperty("日期")
    @TableField(exist = false)
    private String date;
}
