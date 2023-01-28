package com.kevin.epacms.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kevin
 * @since 2022/11/17 22:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("登录参数")
public class LoginParam {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;
    
    @ApiModelProperty("验证码")
    private String code;
}
