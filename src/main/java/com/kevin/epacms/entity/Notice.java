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
@TableName("t_notice")
@ApiModel("Notice对象")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("uid")
    private Integer uid;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("来源")
    @TableField("source")
    private String source;

    @ApiModelProperty("详细内容（md）")
    @TableField("detail_md")
    private String detailMd;

    @ApiModelProperty("点击数")
    @TableField("click_num")
    private Integer clickNum;

    @ApiModelProperty("详细内容（html）")
    @TableField("detail_html")
    private String detailHtml;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime updateTime;
}
