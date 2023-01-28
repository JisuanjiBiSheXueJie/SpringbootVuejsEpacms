package com.kevin.epacms.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author kevin
 * @since 2022/11/12 16:19
 */
@Getter
@ToString
public enum RoleEnum {
    /**
     * 管理员
     */
    ADMIN(1, "管理员"),
    /**
     * 辅导员
     */
    COUNSELOR(2, "辅导员"),
    /**
     * 学生
     */
    STUDENT(3, "学生"),
    /**
     * 老师
     */
    TEACHER(4, "教职工");

    private final Integer code;
    private final String desc;

    RoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
