package com.kevin.epacms.entity.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @author kevin
 * @since 2022/11/12 8:56
 */
@Data
public class ConditionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 数据库字段 */
    private String column;

    /** 字段值 */
    private String value;

    /** 连接类型，如（like, equals, gt, ge, lt, le） */
    private String type;

}
