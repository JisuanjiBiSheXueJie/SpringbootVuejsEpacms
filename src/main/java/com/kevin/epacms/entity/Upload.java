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
@TableName("t_upload")
@ApiModel(value = "Upload对象", description = "")
public class Upload implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文件名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("文件类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("文件大小(KB)")
    @TableField("size")
    private Long size;

    @ApiModelProperty("下载链接")
    @TableField("url")
    private String url;

    @ApiModelProperty("是否删除")
    @TableField("is_delete")
    private Boolean isDelete;

    @ApiModelProperty("是否禁用链接")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("文件md5")
    @TableField("md5")
    private String md5;

    @ApiModelProperty("文件UUID")
    @TableField("file_uuid")
    private String fileUuid;

    @ApiModelProperty("上传时间")
    @TableField("upload_time")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime uploadTime;
}
