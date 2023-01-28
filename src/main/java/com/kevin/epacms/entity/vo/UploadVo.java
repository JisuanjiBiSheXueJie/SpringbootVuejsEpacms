package com.kevin.epacms.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kevin.epacms.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kevin
 * @since 2022/11/13 19:16
 */
@Data
@ApiModel("UploadVo")
public class UploadVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件大小(KB)")
    private Long size;

    @ApiModelProperty("下载链接")
    private String url;

    @ApiModelProperty("是否禁用链接")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("上传时间")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime uploadTime;
}
