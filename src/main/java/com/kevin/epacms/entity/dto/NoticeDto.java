package com.kevin.epacms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kevin.epacms.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kevin
 * @since 2022-11-10
 */
@Data
@ApiModel("NoticeDto")
public class NoticeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime updateTime;
}
