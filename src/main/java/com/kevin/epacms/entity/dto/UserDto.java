package com.kevin.epacms.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kevin.epacms.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserDto
 *
 * @author kevin
 * @since 2022-11-11
 */
@Data
@ApiModel(value = "UserDto", description = "")
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String sno;
    private String username;
    private String realName;
    private Integer age;
    private String sex;
    private String phone;
    private String email;
    private String address;
    private String dormitoryNum;
    private String nation;
    private String classNum;
    private String academy;
    private String avatarUrl;
    private Integer enable;
    private Integer rid;
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime registerTime;
}
