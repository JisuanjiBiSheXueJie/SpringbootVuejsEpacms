package com.kevin.epacms.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kevin
 * @since 2022/11/22 22:00
 */
@Data
@ApiModel("StatisticsVo")
public class StatisticsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userTotal;
    private Long recordTotal;
    private Long fileTotal;
    private Long newsTotal;
}
