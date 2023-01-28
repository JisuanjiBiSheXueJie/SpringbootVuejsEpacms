package com.kevin.epacms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kevin.epacms.constant.CommonConstant;
import io.lettuce.core.dynamic.annotation.Command;
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
 * @since 2022-11-10
 */
@Getter
@Setter
@TableName("t_news")
@ApiModel(value = "News对象", description = "")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("新闻标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("新闻URL")
    @TableField("url")
    private String url;

    @ApiModelProperty("摘录日期")
    @TableField("create_time")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime createTime;
}
