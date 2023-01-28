package com.kevin.epacms.entity.dto;

import com.kevin.epacms.entity.vo.ConditionVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author kevin
 * @since 2022-11-14
 */
@Data
@ApiModel("SearchDto")
public class SearchDto {
    /** 封装查询条件 */
    private List<ConditionVo> conditions;
}
