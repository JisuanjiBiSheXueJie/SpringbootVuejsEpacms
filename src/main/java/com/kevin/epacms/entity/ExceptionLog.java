package com.kevin.epacms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2022-11-15
 */
@Getter
@Setter
@TableName("t_exception_log")
@ApiModel(value = "ExceptionLog对象", description = "")
public class ExceptionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("异常名称")
    @TableField("exp_name")
    private String expName;

    @ApiModelProperty("异常信息")
    @TableField("exp_msg")
    private String expMsg;

    @ApiModelProperty("请求参数")
    @TableField("req_param")
    private String reqParam;

    @ApiModelProperty("请求方法")
    @TableField("req_method")
    private String reqMethod;

    @ApiModelProperty("请求URI")
    @TableField("req_uri")
    private String reqUri;

    @ApiModelProperty("请求IP")
    @TableField("req_ip")
    private String reqIp;

    @ApiModelProperty("异常发生时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
