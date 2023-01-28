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
@TableName("t_operation_log")
@ApiModel(value = "OperationLog对象", description = "")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("操作类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("操作描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("功能模块")
    @TableField("module")
    private String module;

    @ApiModelProperty("操作时间")
    @TableField("operation_time")
    private LocalDateTime operationTime;

    @ApiModelProperty("请求参数")
    @TableField("req_param")
    private String reqParam;

    @ApiModelProperty("返回参数")
    @TableField("resp_param")
    private String respParam;

    @ApiModelProperty("请求方法")
    @TableField("req_method")
    private String reqMethod;

    @ApiModelProperty("请求URI")
    @TableField("req_uri")
    private String reqUri;

    @ApiModelProperty("ip地址")
    @TableField("req_ip")
    private String reqIp;
}
