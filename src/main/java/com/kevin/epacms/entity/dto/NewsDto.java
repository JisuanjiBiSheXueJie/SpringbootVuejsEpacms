package com.kevin.epacms.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * @author kevin
 * @since 2022/11/12 21:45
 */
@Data
public class NewsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("新闻标题")
    private String title;

    @ApiModelProperty("新闻URL")
    private String url;
}
