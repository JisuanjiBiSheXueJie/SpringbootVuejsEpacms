package com.kevin.epacms.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kevin
 * @since 2022/11/12 15:04
 */
@Data
@ApiModel("LeaveDto")
public class LeaveDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long uid;
    private Integer rid;
    private String classNum;
    private String dateValue;
}
