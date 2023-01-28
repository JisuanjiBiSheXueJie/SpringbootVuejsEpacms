package com.kevin.epacms.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author kevin
 * @since 2022/11/18 9:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("注册参数")
public class RegisterParam {
    @Length(min = 3, max = 16, message = "用户名长度是3-16位")
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @Length(max = 24, message = "密码不能超过24位")
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("确认密码")
    private String repassword;

    @Email(message = "邮箱格式错误")
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty("邮箱")
    private String email;

    @NotBlank(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "")
    @ApiModelProperty("验证码")
    private String code;
}
