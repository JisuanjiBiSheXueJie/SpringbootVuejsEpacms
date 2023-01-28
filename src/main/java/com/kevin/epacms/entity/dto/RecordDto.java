package com.kevin.epacms.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kevin.epacms.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kevin
 * @date 2022/11/25 22:44
 */
@Data
@ApiModel("NoticeDto")
public class RecordDto {
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer uid;

    @ApiModelProperty("位置")
    private String position;

    @ApiModelProperty("体温")
    private Object temperature;

    @ApiModelProperty("是否咳嗽")
    private Integer cough;

    @ApiModelProperty("是否发烧")
    private Integer fever;

    @ApiModelProperty("打卡时间")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime time;
}
